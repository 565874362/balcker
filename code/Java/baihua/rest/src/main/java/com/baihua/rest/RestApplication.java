package com.baihua.rest;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title RestApplication.java
 * @Package com.baihua.baihuamedical
 * @date 2018年12月14日 13:43:25
 */
@SpringBootApplication
public class RestApplication implements ServletContainerInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RestApplication.class);
	}

	@Bean
	public RestTemplate restTemplate(){
		return new RestTemplate();
	}

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
		SpringApplication.run(RestApplication.class);
	}
}

