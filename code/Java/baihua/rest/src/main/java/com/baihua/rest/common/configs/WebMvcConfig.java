package com.baihua.rest.common.configs;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.baihua.rest.modules.basic.controller.UploadController;
import com.baihua.rest.modules.login.interceptor.AuthorizationInterceptor;
import com.baihua.rest.modules.login.resolver.LoginAccountHandlerMethodArgumentResolver;
import com.baihua.rest.modules.login.resolver.LoginDoctorHandlerMethodArgumentResolver;
import com.baihua.rest.modules.login.resolver.LoginPatientHandlerMethodArgumentResolver;

/**
 * MVC配置
 *
 * @author zhaodongdong
 * @email 2977260348@qq.com
 * @date 2017-04-20 22:30
 */
@Component
public class WebMvcConfig implements WebMvcConfigurer {

	@Autowired
	private ConfigProperties configProperties;

	@Autowired
	private AuthorizationInterceptor authorizationInterceptor;

	@Autowired
	private LoginAccountHandlerMethodArgumentResolver loginAccountHandlerMethodArgumentResolver;

	@Autowired
	private LoginDoctorHandlerMethodArgumentResolver loginDoctorHandlerMethodArgumentResolver;

	@Autowired
	private LoginPatientHandlerMethodArgumentResolver loginPatientHandlerMethodArgumentResolver;

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler(UploadController.VISIT_PREFIX + "/**").addResourceLocations("file:" + configProperties.getUploadDirectory() + "/");
	}


	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(authorizationInterceptor).addPathPatterns("/rest/**").excludePathPatterns("/rest/login/**");
	}

	@Override
	public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
		argumentResolvers.add(loginAccountHandlerMethodArgumentResolver);
		argumentResolvers.add(loginDoctorHandlerMethodArgumentResolver);
		argumentResolvers.add(loginPatientHandlerMethodArgumentResolver);
	}
}