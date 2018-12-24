package com.baihua.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title RestApplication.java
 * @Package com.baihua.baihuamedical
 * @date 2018年12月14日 13:43:25
 */
@SpringBootApplication(scanBasePackages = "com.baihua")
public class RestApplication extends SpringBootServletInitializer {

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(RestApplication.class);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}


}

