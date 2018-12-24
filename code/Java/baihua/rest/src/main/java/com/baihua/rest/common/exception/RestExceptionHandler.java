package com.baihua.rest.common.exception;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baihua.core.common.enums.ResponseStatus;
import com.baihua.core.common.exception.UnauthorizedException;
import com.baihua.core.common.utils.R;

import lombok.extern.slf4j.Slf4j;


/**
 * 异常处理
 *
 * @author zhaodongdong
 * @version V1.0
 * @Title JHouseExceptionHandler.java
 * @Package com.jhouse.jhouseservice.common.exception
 * @date 2018年11月14日 20:36:25
 */
@Slf4j
@RestControllerAdvice
public class RestExceptionHandler {

	/**
	 * 未授权
	 */
	@ExceptionHandler(com.baihua.core.common.exception.UnauthorizedException.class)
	public R handleRRException(UnauthorizedException e){
		return R.fromResStatus(ResponseStatus.UNAUTHORIZED_EXCEPTION).exchange(e.getMessage());
	}

	/**
	 * 账户异常
	 */
	@ExceptionHandler(AccountException.class)
	public R handleRRException(AccountException e){
		return R.fromResStatus(ResponseStatus.FORBIDDEN_EXCEPTION).exchange(e.getMessage());
	}
}
