package com.baihua.rest.modules.service.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.baihua.core.common.enums.Constants;
import com.baihua.core.common.exception.ParameterException;
import com.baihua.core.common.utils.PageQuery;
import com.baihua.core.common.utils.PageUtils;
import com.baihua.core.common.utils.R;
import com.baihua.core.common.validator.ValidatorUtils;
import com.baihua.core.modules.basic.entity.BasHealthExaminationEntity;
import com.baihua.rest.modules.basic.service.BasHealthExaminationService;
import com.baihua.rest.modules.login.annotation.LoginDoctor;
import com.baihua.rest.modules.login.annotation.LoginPatient;
import com.baihua.core.modules.service.entity.SerInquiryEntity;
import com.baihua.rest.modules.service.service.SerInquiryService;
import com.baihua.core.modules.user.entity.UsAccountEntity;
import com.baihua.core.modules.user.entity.UsDoctorEntity;
import com.baihua.core.modules.user.entity.UsPatientEntity;
import com.baihua.rest.modules.user.service.UsAccountService;
import com.baihua.rest.modules.user.service.UsDoctorService;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import springfox.documentation.annotations.ApiIgnore;


/**
 * 问诊
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2018-12-06 10:17:17
 */
@Api(tags = "问诊")
@RestController
@RequestMapping("/rest/serinquiry")
public class SerInquiryController {

	@Autowired
	private SerInquiryService serInquiryService;

	@Autowired
	private UsAccountService accountService;

	@Autowired
	private BasHealthExaminationService healthExaminationService;

	@Autowired
	private UsDoctorService doctorService;


	@ApiOperation("用户问诊列表 [患者]")
	@PostMapping("/patientList")
	public R userList(@RequestBody PageQuery<SerInquiryEntity> pageQuery, @ApiIgnore @LoginPatient UsPatientEntity patientEntity) {
		ValidatorUtils.validateEntity(pageQuery);
		LambdaQueryWrapper<SerInquiryEntity> queryWrapper = new QueryWrapper<SerInquiryEntity>().lambda()
				.eq(SerInquiryEntity::getPatientId, patientEntity.getId())
				.select(SerInquiryEntity::getId, SerInquiryEntity::getName, SerInquiryEntity::getAge, SerInquiryEntity::getCharacterDescribe, SerInquiryEntity::getGender, SerInquiryEntity::getStatus, SerInquiryEntity::getGmtModified)
				.orderByDesc(SerInquiryEntity::getGmtModified);
		IPage content = serInquiryService.page(pageQuery.getPage(), queryWrapper);
		return R.success().addResData("page", content);
	}

	@ApiOperation("医生患者列表 [医生]")
	@PostMapping("/doctorList")
	public R doctorList(@RequestBody PageQuery<SerInquiryEntity> pageQuery, @ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity) {
		ValidatorUtils.validateEntity(pageQuery);
		LambdaQueryWrapper<SerInquiryEntity> queryWrapper = new QueryWrapper<SerInquiryEntity>().lambda();
		queryWrapper.eq(SerInquiryEntity::getDoctorId, doctorEntity.getId());
		queryWrapper.eq(SerInquiryEntity::getStatus, Constants.InquiryStatus.replied.getCode());
		queryWrapper.orderByDesc(SerInquiryEntity::getGmtModified);
		queryWrapper.select(SerInquiryEntity::getId, SerInquiryEntity::getName, SerInquiryEntity::getAge, SerInquiryEntity::getCharacterDescribe, SerInquiryEntity::getGender, SerInquiryEntity::getStatus, SerInquiryEntity::getGmtModified);
		IPage content = serInquiryService.page(pageQuery.getPage(), queryWrapper);
		return R.success().addResData("page", content);
	}

	@ApiOperation("医生首页患者 [医生]")
	@PostMapping("/doctorIndex")
	public R doctorIndex(@RequestBody PageQuery<SerInquiryEntity> pageQuery, @ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity) {
		ValidatorUtils.validateEntity(pageQuery);
		IPage<SerInquiryEntity> serInquiryEntityIPage = serInquiryService.selectDocterIndexPage(pageQuery.getPage(), doctorEntity.getId());
		return R.success().addResData("page", serInquiryEntityIPage);
	}

	@ApiOperation("医生接诊 [医生]")
	@GetMapping("/accept/{inquiryId}")
	public R accept(@PathVariable("inquiryId") Long inquiryId, @ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity) {
		boolean result = serInquiryService.aquire(inquiryId, doctorEntity.getId());
		return R.success().addResData("result", result);
	}

	@ApiOperation("医生回复 [医生]")
	@PostMapping("/response")
	public R accept(@RequestBody ResponseInput responseInput, @ApiIgnore @LoginDoctor UsDoctorEntity doctorEntity) {
		ValidatorUtils.validateEntity(responseInput);
		SerInquiryEntity serInquiryEntity = new SerInquiryEntity();
		BeanUtils.copyProperties(responseInput, serInquiryEntity);

		String exaIds = "";
		List<BasHealthExaminationEntity> healthExaminationEntities = new ArrayList<>();
		if (responseInput.getExaIds() != null && responseInput.getExaIds().length != 0) {
			healthExaminationEntities = healthExaminationService.list(new QueryWrapper<BasHealthExaminationEntity>().lambda()
					.in(BasHealthExaminationEntity::getId, responseInput.getExaIds()));
			exaIds = StringUtils.join(responseInput.getExaIds(), ",");
		}

		BigDecimal totalFee = healthExaminationEntities.stream()
				.map(e -> e.getPrice())
				.reduce(BigDecimal.ZERO, (e, t) -> t.add(e));

		serInquiryEntity.setExaIds(exaIds);
		serInquiryEntity.setExaContent(JSON.toJSONString(healthExaminationEntities));
		serInquiryEntity.setDoctorId(doctorEntity.getId());
		serInquiryEntity.setExaFee(totalFee);
		serInquiryEntity.setStatus(Constants.InquiryStatus.replied.getCode());
		boolean result = serInquiryService.updateById(serInquiryEntity);
		return R.success().addResData("result", result);
	}

	/**
	 * 列表
	 */
	@RequestMapping("/list")
	public R list(@RequestParam Map<String, Object> params) {
		PageUtils page = serInquiryService.queryPage(params);
		return R.success().addResData("page", page);
	}

	/**
	 * 信息
	 */
	@ApiOperation("用户问诊详细 [患者医生共用]")
	@GetMapping("/info/{id}")
	public R info(@PathVariable("id") Long id) {
		SerInquiryEntity serInquiry = serInquiryService.getById(id);
		if (serInquiry == null) {
			throw new ParameterException("问诊不存在");
		}
		Map<String, Object> data = new HashMap<>();
		data.put("info", serInquiry);
		if (serInquiry.getStatus().intValue() != Constants.InquiryStatus.waitcheck.getCode()) {
			UsDoctorEntity doctor = doctorService.getOne(new QueryWrapper<UsDoctorEntity>()
					.lambda()
					.eq(UsDoctorEntity::getId, serInquiry.getDoctorId()));
			UsAccountEntity account = accountService.getOne(new QueryWrapper<UsAccountEntity>().lambda()
					.select(UsAccountEntity::getId)
					.eq(UsAccountEntity::getSId, doctor.getId())
					.eq(UsAccountEntity::getType, Constants.AccountType.doctor.getCode()));
			if(account != null){
				doctor.setAccountId(account.getId());
			}
			data.put("doctor", doctor);
			List<BasHealthExaminationEntity> basHealthExaminationEntities = JSON.parseArray(serInquiry.getExaContent(), BasHealthExaminationEntity.class);
			data.put("healthExaminations", basHealthExaminationEntities);
		}
		return R.success().addResData(data);
	}

	/**
	 * 保存
	 */
	@ApiOperation("增加问诊 [患者]")
	@PostMapping("/save")
	public R save(@RequestBody SaveInput serInquiry, @ApiIgnore @LoginPatient UsPatientEntity patientEntity) {
		ValidatorUtils.validateEntity(serInquiry);
		SerInquiryEntity entity = new SerInquiryEntity();
		BeanUtils.copyProperties(serInquiry, entity);
		entity.setPatientId(patientEntity.getId());
		entity.setStatus(Constants.InquiryStatus.waitcheck.getCode());
		List<UsDoctorEntity> matchDoctors = serInquiryService.commit(entity);
		return R.success().addResData("matchDoctors",matchDoctors);
	}


	@ApiModel("添加实体")
	@Data
	private static class SaveInput {
		/**
		 * 名称
		 */
		@NotEmpty
		@ApiModelProperty("患者名称")
		private String name;
		/**
		 * 性别:1 男 0 女
		 */
		@ApiModelProperty("性别 1 男 0 女")
		@NotNull
		private Integer gender;
		/**
		 * 年龄
		 */
		@NotNull
		@ApiModelProperty("年龄")
		private Integer age;
		/**
		 * 电话
		 */
		@NotEmpty
		@ApiModelProperty("电话")
		private String phone;
		/**
		 * 饮食
		 */
		@NotNull
		@ApiModelProperty("饮食")
		private Integer diet;
		/**
		 * 睡眠
		 */
		@NotNull
		@ApiModelProperty("睡眠")
		private Integer sleep;
		/**
		 * 文字描述
		 */
		@ApiModelProperty("症状描述")
		@NotEmpty
		private String characterDescribe;
		/**
		 * 语音描述
		 */
		@ApiModelProperty("语音描述")
		private String voiceDescribe;
		/**
		 * 照片
		 */
		@ApiModelProperty("以前检查结果")
		private String image;
	}

	@ApiModel("医生响应")
	@Data
	private static class ResponseInput {

		@ApiModelProperty("诊断编号")
		@NotNull(message = "诊断编号不能为空")
		private Long id;

		/**
		 * 诊断结果
		 */
		@ApiModelProperty("诊断结果")
		@NotEmpty(message = "诊断结果不能为空")
		private String response;
		/**
		 * 医嘱
		 */
		@ApiModelProperty("医嘱")
		@NotEmpty(message = "医嘱不能为空")
		private String advice;
		/**
		 * 检查项编号
		 */
		@ApiModelProperty("检查项编号")
		private Long[] exaIds;
	}
}
