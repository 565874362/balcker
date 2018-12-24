package com.baihua.manager.common.exception;


import org.apache.shiro.authz.AuthorizationException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.NoHandlerFoundException;

import com.baihua.core.common.enums.ResponseStatus;
import com.baihua.core.common.exception.BaihuaException;
import com.baihua.core.common.exception.ParameterException;
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
public class ManagerExceptionHandler {

	/**
	 * 处理自定义异常
	 */
	@ExceptionHandler(BaihuaException.class)
	public R handleRRException(BaihuaException e){
		return R.exp(e);
	}

	/**
	 * 参数异常
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public R handleRRException(HttpMessageNotReadableException e){
		return R.fromResStatus(ResponseStatus.PARAMETER_EXCEPTION);
	}

	/**
	 * 参数校验不通过
	 *
	 * @param e
	 * @return
	 */
	@ExceptionHandler(ParameterException.class)
	public R handleRRException(ParameterException e){
		return R.fromResStatus(ResponseStatus.PARAMETER_EXCEPTION).exchange(e.getMessage());
	}


	@ExceptionHandler(NoHandlerFoundException.class)
	public R handlerNoFoundException(Exception e) {
		log.error(e.getMessage(), e);
		return R.fromResStatus(ResponseStatus.NOT_FOUND_EXCEPTION).exchange("路径不存在，请检查路径是否正确");
	}

	@ExceptionHandler(DuplicateKeyException.class)
	public R handleDuplicateKeyException(DuplicateKeyException e){
		log.error(e.getMessage(), e);
		return R.fail("数据库中已存在该记录");
	}

	@ExceptionHandler(AuthorizationException.class)
	public R handleAuthorizationException(AuthorizationException e){
		log.error(e.getMessage(), e);
		return R.fail("没有权限，请联系管理员授权");
	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		log.error(e.getMessage(), e);
		return R.exp(e);
	}
}
