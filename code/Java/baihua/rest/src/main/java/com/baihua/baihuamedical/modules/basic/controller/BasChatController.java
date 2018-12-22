package com.baihua.baihuamedical.modules.basic.controller;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.baihua.baihuamedical.common.enums.Constants;
import com.baihua.baihuamedical.common.utils.R;
import com.baihua.baihuamedical.modules.basic.service.ChatSupportService;
import com.baihua.baihuamedical.modules.login.annotation.LoginAccount;
import com.baihua.baihuamedical.modules.user.entity.UsAccountEntity;
import com.baihua.baihuamedical.modules.user.entity.UsDoctorEntity;
import com.baihua.baihuamedical.modules.user.entity.UsPatientEntity;
import com.baihua.baihuamedical.modules.user.service.UsAccountService;
import com.baihua.baihuamedical.modules.user.service.UsDoctorService;
import com.baihua.baihuamedical.modules.user.service.UsPatientService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 聊天
 *
 * @author zhaodongdong
 * @version V1.0
 * @Title BasChatController.java
 * @Package com.baihua.baihuamedical.modules.basic.controller
 * @date 2018年12月22日 09:44:48
 */
@Api(tags = "聊天")
@RestController
@RequestMapping("/rest/chat")
public class BasChatController {

	@Autowired
	private UsAccountService accountService;

	@Autowired
	private UsDoctorService doctorService;

	@Autowired
	private UsPatientService patientService;

	@Autowired
	private ChatSupportService chatSupportService;

	@GetMapping("/token")
	@ApiOperation("获取token")
	public R getToken(@ApiIgnore @LoginAccount UsAccountEntity accountEntity) {
		String token = chatSupportService.getToken(accountEntity.getId());
		return R.success().addResData("token", token);
	}

	@GetMapping("/info/{accountId}")
	@ApiOperation("获取账户信息")
	public R accountInfo(@PathVariable("accountId") Long accountId) {
		Map<String, Object> info = new LinkedHashMap<>();
		UsAccountEntity accountEntity = accountService.getById(accountId);
		if (accountEntity != null) {
			info.put("name", accountEntity.getSName());
			info.put("photo", "");
			if (accountEntity != null) {
				if (accountEntity.getType().intValue() == Constants.AccountType.doctor.getCode()) {
					UsDoctorEntity doctorEntity = doctorService.getOne(new QueryWrapper<UsDoctorEntity>().lambda()
							.select(UsDoctorEntity::getPhoto)
							.eq(UsDoctorEntity::getId, accountEntity.getSId()));
					if (doctorEntity != null) {
						info.put("photo", doctorEntity.getPhoto());
					}
				} else {
					UsPatientEntity patientEntity = patientService.getOne(new QueryWrapper<UsPatientEntity>().lambda()
							.select(UsPatientEntity::getPhoto)
							.eq(UsPatientEntity::getId, accountEntity.getSId()));
					if (patientEntity != null) {
						info.put("photo", patientEntity.getPhoto());
					}
				}
			}
		}
		return R.success().addResData("info", info);
	}
}

