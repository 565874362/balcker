package com.baihua.rest.common.configs;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.baihua.core.modules.basic.service.impl.AliSmsServiceImpl;
import com.baihua.rest.modules.service.service.impl.RongRunChatServiceImpl;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title ThirdPartyConfig.java
 * @Package com.baihua.baihuamedical.common.configs
 * @date 2018年12月22日 15:12:55
 */
@Configuration
public class ThirdPartyConfig {

	@Autowired
	private ConfigProperties configProperties;

	@Bean
	public RongRunChatServiceImpl.RongYunProperties rongYunProperties(){
		RongRunChatServiceImpl.RongYunProperties rongYunProperties = new RongRunChatServiceImpl.RongYunProperties();
		BeanUtils.copyProperties(configProperties.getRongYun(),rongYunProperties);
		return rongYunProperties;
	}

	@Bean
	public AliSmsServiceImpl.AliSmsProperties aliSmsProperties(){
		AliSmsServiceImpl.AliSmsProperties aliSmsProperties = new AliSmsServiceImpl.AliSmsProperties();
		BeanUtils.copyProperties(configProperties.getAliSms(),aliSmsProperties);
		return aliSmsProperties;
	}
}



