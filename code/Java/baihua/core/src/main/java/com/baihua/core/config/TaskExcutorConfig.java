package com.baihua.core.config;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import lombok.extern.slf4j.Slf4j;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title TaskExcutorConfig.java
 * @Package com.baihua.baihuamedical.config
 * @date 2018年12月22日 15:42:46
 */
@Configuration
@ComponentScan("com.baihua.baihuamedical")
@EnableAsync
@Slf4j
public class TaskExcutorConfig implements AsyncConfigurer {

	@Override
	public Executor getAsyncExecutor() {
		return Executors.newFixedThreadPool(5);
	}

	@Override
	public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
		return (throwable, method, args) -> log.error("异步任务出错: 方法:{},参数:{},异常{}");
	}
}

