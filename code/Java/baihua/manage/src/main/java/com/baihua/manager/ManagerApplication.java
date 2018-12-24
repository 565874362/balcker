package com.baihua.manager;

import java.util.Set;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title ManagerApplication.java
 * @Package com.baihua.baihuamedical
 * @date 2018年12月14日 13:43:25
 */
@SpringBootApplication
public class ManagerApplication implements ServletContainerInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ManagerApplication.class);
	}

	@Override
	public void onStartup(Set<Class<?>> set, ServletContext servletContext) throws ServletException {
		SpringApplication.run(ManagerApplication.class);
	}
}

