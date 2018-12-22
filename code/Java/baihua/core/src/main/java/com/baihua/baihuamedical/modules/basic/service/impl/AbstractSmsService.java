package com.baihua.baihuamedical.modules.basic.service.impl;

import org.springframework.scheduling.annotation.Async;

import com.baihua.baihuamedical.modules.basic.service.ISmsService;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title AbstractSmsService.java
 * @Package com.baihua.baihuamedical.modules.basic.service.impl
 * @date 2018年12月22日 15:26:02
 */
public abstract class AbstractSmsService implements ISmsService {

	/**
	 * @param phone
	 * @param code
	 * @param retimes
	 */
	@Async
	public void asycSendSms(String phone,String code,int retimes) {
		for (int i = 0; i < retimes; i++) {
			boolean result = false;
			try {
				result = sendSms(phone,code);
			} catch (Exception e) {
			}
			if(result){
				break;
			}
		}
	}
}

