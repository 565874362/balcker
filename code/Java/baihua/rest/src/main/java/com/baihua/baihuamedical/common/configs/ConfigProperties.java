package com.baihua.baihuamedical.common.configs;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.stereotype.Component;

import com.baihua.baihuamedical.modules.basic.service.impl.AliSmsServiceImpl;
import com.baihua.baihuamedical.modules.service.service.impl.RongRunChatServiceImpl;

import lombok.Data;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title ConfigProperties.java
 * @Package com.baihua.baihuamedical.common.configs
 * @date 2018年12月14日 10:06:55
 */
@Component
@ConfigurationProperties(prefix = "baihua-rest")
@Data
public class ConfigProperties {

	private String tokenId;

	private String filePrex;

	private Integer tokenExpireTime;

	private Integer captchaExpireTime;

	private String uploadDirectory;

	@NestedConfigurationProperty
	private RongRunChatServiceImpl.RongYunProperties rongYun;

	@NestedConfigurationProperty
	private AliSmsServiceImpl.AliSmsProperties aliSms;

}

