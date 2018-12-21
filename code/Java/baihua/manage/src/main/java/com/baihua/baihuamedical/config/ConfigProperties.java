package com.baihua.baihuamedical.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title ConfigProperties.java
 * @Package com.baihua.baihuamedical.common.configs
 * @date 2018年12月14日 10:06:55
 */
@Component
@ConfigurationProperties(prefix = "baihua")
@Data
public class ConfigProperties {

	private String filePrex;

	private String uploadDirectory;

}

