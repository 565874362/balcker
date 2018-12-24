package com.baihua.core.common.exception;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.baihua.core.common.enums.ResponseStatus;
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
public class GlobalExceptionHandler {

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


//	@ExceptionHandler(NoHandlerFoundException.class)
//	public R handlerNoFoundException(Exception e) {
//		logger.error(e.getMessage(), e);
//		return R.error(404, "路径不存在，请检查路径是否正确");
//	}
//
//	@ExceptionHandler(DuplicateKeyException.class)
//	public R handleDuplicateKeyException(DuplicateKeyException e){
//		logger.error(e.getMessage(), e);
//		return R.error("数据库中已存在该记录");
//	}
//
//	@ExceptionHandler(AuthorizationException.class)
//	public R handleAuthorizationException(AuthorizationException e){
//		logger.error(e.getMessage(), e);
//		return R.error("没有权限，请联系管理员授权");
//	}

	@ExceptionHandler(Exception.class)
	public R handleException(Exception e){
		log.error(e.getMessage(), e);
		return R.exp(e);
	}
}
