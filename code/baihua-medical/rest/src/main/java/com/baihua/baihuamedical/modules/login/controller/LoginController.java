package com.baihua.baihuamedical.modules.login.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.configs.ConfigProperties;
import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.exception.ParameterException;
import com.baihua.baihuamedical.common.utils.DateUtils;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.common.utils.VerificationGenerator;
import com.baihua.baihuamedical.common.validator.ValidatorUtils;
import com.baihua.baihuamedical.modules.service.entity.SerAdeptEntity;
import com.baihua.baihuamedical.modules.sys.entity.SysCaptchaEntity;
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baihua.baihuamedical.modules.user.service.UsAccountService;
import com.baihua.baihuamedical.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title LoginController.java
 * @Package com.baihua.baihuamedical.modules.login.controller
 * @date 2018年12月14日 14:02:59
 */
@Api(tags = "登陆")
@RestController
@RequestMapping("/rest/login")
public class LoginController {

	@Autowired
	private UsAccountService accountService;

	@Autowired
	private UsDoctorService doctorService;

	@Autowired
	private ConfigProperties configProperties;


	@ApiOperation("发送验证码")
	@GetMapping("/verification/{phone}")
	public R sendVerification(@PathVariable String phone) {
		String verificationCode = VerificationGenerator.get();
		//TODO
		SysCaptchaEntity sysCaptchaEntity = new SysCaptchaEntity();
		sysCaptchaEntity.setUuid(IdWorker.get32UUID());
		sysCaptchaEntity.setCode(verificationCode);
		sysCaptchaEntity.setExpireTime(DateUtils.addDateSeconds(new Date(), configProperties.getCaptchaExpireTime()));
		accountService.save(sysCaptchaEntity);
		return R.success().addResData("verificationCode", verificationCode).addResData("captchaId", sysCaptchaEntity.getUuid());
	}

	@ApiOperation("医生注册 [医生]")
	@PostMapping("/doctor/register")
	public R register(@RequestBody DoctorRegister doctorRegister) {
		ValidatorUtils.validateEntity(doctorRegister);

		SysCaptchaEntity sysCaptchaEntity = new SysCaptchaEntity();
		sysCaptchaEntity.setUuid(doctorRegister.getCaptchaId());
		sysCaptchaEntity.setCode(doctorRegister.getCaptchaCode());
		accountService.checkCaptcha(sysCaptchaEntity);

		UsAccountEntity accountEntity = accountService.getOne(new QueryWrapper<UsAccountEntity>().lambda()
				.eq(UsAccountEntity::getAccount, doctorRegister.getAccount()));
		if(accountEntity != null
				&& accountEntity.getType().intValue() == Constants.AccountType.doctor.getCode()
				&& accountEntity.getStatus().intValue() == Constants.AccountStatus.waitactive.getCode()){
			throw new ParameterException("账号正在审核中...");
		}

		UsDoctorEntity doctorEntity = new UsDoctorEntity();
		BeanUtils.copyProperties(doctorRegister,doctorEntity);

		List<Adept> adepts = doctorRegister.getAdepts();
		List<SerAdeptEntity> serAdeptEntities = new ArrayList<>();
		Iterator<Adept> iterator = adepts.iterator();
		while (iterator.hasNext()){
			Adept object = iterator.next();
			if(object == null || StringUtils.isEmpty(object.getDescribe()) || StringUtils.isEmpty(object.getName()) ){
				iterator.remove();
				continue;
			}
			SerAdeptEntity serAdeptEntity = new SerAdeptEntity();
			BeanUtils.copyProperties(object,serAdeptEntity);
			serAdeptEntities.add(serAdeptEntity);
		}

		doctorService.register(doctorRegister.getAccount(),doctorEntity,serAdeptEntities);
		return R.success();
	}

	@ApiOperation("登陆 [患者]")
	@PostMapping("/patient")
	public R patientLogin(@RequestBody LoginEntity loginEntity) {
		String token = login(loginEntity, Constants.AccountType.patient);
		return R.success().addResData("token", token);
	}

	@ApiOperation("登陆 [医生]")
	@PostMapping("/doctor")
	public R doctorRegister(@RequestBody LoginEntity loginEntity) {
		String token = login(loginEntity, Constants.AccountType.doctor);
		return R.success().addResData("token", token);
	}

	private String login(LoginEntity loginEntity, Constants.AccountType accountType) {
		ValidatorUtils.validateEntity(loginEntity);

		/**
		 * 验证码检测
		 */
		SysCaptchaEntity sysCaptchaEntity = new SysCaptchaEntity();
		sysCaptchaEntity.setUuid(loginEntity.captchaId);
		sysCaptchaEntity.setCode(loginEntity.captchaCode);
		accountService.checkCaptcha(sysCaptchaEntity);

		UsAccountEntity accountEntity = accountService.getOne(new QueryWrapper<UsAccountEntity>().lambda()
				.eq(UsAccountEntity::getAccount, loginEntity.getAccount()));
		if (accountEntity == null) {
			if (accountType == Constants.AccountType.patient) {
				accountEntity = accountService.generatorPatient(loginEntity.getAccount());
			} else {
				throw new ParameterException("账号不存在");
			}
		} else {
			switch (Constants.AccountStatus.valueOf(accountEntity.getStatus())){
				case waitactive:
					if(accountType == Constants.AccountType.doctor){
						throw new ParameterException("账号正在审核中...");
					}
				case freeze:
					throw new ParameterException("账号已被冻结");
			}
		}
		return accountService.generatorToken(accountEntity.getId());
	}

	@ApiModel("登陆参数")
	@Data
	private static class LoginEntity {
		@NotBlank(message = "账号不能为空")
		@ApiModelProperty("账号")
		private String account;
		@NotBlank(message = "验证码编号不能为空")
		@ApiModelProperty("验证码编号")
		private String captchaId;
		@NotBlank(message = "验证码不能为空")
		@ApiModelProperty("验证码")
		private String captchaCode;
	}

	@ApiModel("医生注册输入")
	@Data
	private static class DoctorRegister extends LoginEntity {
		/**
		 * 名称
		 */
		@ApiModelProperty("姓名")
		@NotEmpty(message = "姓名不能为空")
		private String name;
		/**
		 * 性别:1 男 0 女
		 */
		@ApiModelProperty("性别 1 男 0 女")
		@NotNull(message = "性别不能为空")
		private Integer gender;
		/**
		 * 科室编号
		 */
		@ApiModelProperty("科室编号")
		@NotNull(message = "科室编号不能为空")
		private Long offId;
		/**
		 * 科室名称
		 */
		@ApiModelProperty("科室名称")
		@NotEmpty(message = "科室名称不能为空")
		private String offName;

		/**
		 * 职位编号
		 */
		@ApiModelProperty("职位编号")
		@NotNull(message = "职位编号不能为空")
		private Long positionId;
		/**
		 * 职位名称
		 */
		@ApiModelProperty("职位名称")
		@NotEmpty(message = "职位名称不能为空")
		private String positionName;
		/**
		 * 医院编号
		 */
		@ApiModelProperty("医院编号")
		@NotNull(message = "医院编号不能为空")
		private Long hosId;
		/**
		 * 医院名称
		 */
		@ApiModelProperty("医院名称")
		@NotEmpty(message = "医院名称不能为空")
		private String hosName;
		/**
		 * 个人照片
		 */
		@ApiModelProperty("个人照片")
		@NotEmpty(message = "个人照片不能为空")
		private String photo;
		/**
		 * 医师资格证
		 */
		@ApiModelProperty("医师资格证")
		@NotEmpty(message = "医师资格证不能为空")
		private String physicianLicence;
		/**
		 * 身份证 正反以,分割
		 */
		@ApiModelProperty("身份证")
		@NotEmpty(message = "身份证不能为空")
		private String identityCard;
		/**
		 * 挂号费
		 */
		@ApiModelProperty("挂号费")
		@NotNull(message = "挂号费不能为空")
		private BigDecimal registrationFee;

		@ApiModelProperty("擅长")
		@NotNull(message = "擅长不能为空")
		private List<Adept> adepts = new ArrayList<>();
	}

	@ApiModel("擅长")
	@Data
	private static class Adept {
		@ApiModelProperty("名称")
		private String name;
		@ApiModelProperty("描述")
		private String describe;
	}
}

