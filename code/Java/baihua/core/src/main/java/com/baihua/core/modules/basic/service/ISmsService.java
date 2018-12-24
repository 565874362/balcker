package com.baihua.core.modules.basic.service;

import java.util.Random;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title ISmsService.java
 * @Package com.baihua.baihuamedical.modules.basic.service
 * @date 2018年12月22日 13:28:43
 */
public interface ISmsService {

	/**
	 *
	 * @param phone
	 * @return
	 * @throws Exception
	 */
	boolean sendSms(String phone,String code) throws Exception;

	/**
	 *
	 * @param phone
	 * @param code
	 * @param retimes
	 */
	void asycSendSms(String phone,String code,int retimes);


	/**
	 * 生成验证码
	 * @return
	 */
	default String getCaptcha() {
		String str = "0,1,2,3,4,5,6,7,8,9";
		String str2[] = str.split(",");
		Random rand = new Random();
		int index = 0;
		String randStr = "";
		randStr = "";
		for (int i = 0; i < 6; ++i) {
			index = rand.nextInt(str2.length - 1);
			randStr += str2[index];
		}
		return randStr;
	}

}