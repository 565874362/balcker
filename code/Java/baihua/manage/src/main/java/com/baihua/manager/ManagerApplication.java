package com.baihua.manager;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title ManagerApplication.java
 * @Package com.baihua.baihuamedical
 * @date 2018年12月14日 13:43:25
 */
@SpringBootApplication(scanBasePackages = "com.baihua")
public class ManagerApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(ManagerApplication.class);
	}

}

