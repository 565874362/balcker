package com.baihua.baihuamedical;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.baihua.core.modules.basic.service.ISmsService;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title AliSmsTest.java
 * @Package com.baihua.baihuamedical
 * @date 2018年12月22日 14:54:17
 */
public class AliSmsTest extends BaseTest {

	@Autowired
	private ISmsService smsService;

	@Test
	public void getProperties(){
		try {
			//smsService.sendSms("18502940483");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

