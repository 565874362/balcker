package com.baihua.core.common.enums;

import org.springframework.http.HttpStatus;

/**
 * 返回状态
 *
 * @author zhaodongdong
 * @version V1.0
 * @Title ResponseStatus.java
 * @Package com.jhouse.jhouseservice.common.utils.enums
 * @date 2018年11月22日 23:05:11
 */
public enum ResponseStatus {

	SUCCESS(0,"操作成功"),

	FAIL(-1,"操作失败"),

	PARAMETER_EXCEPTION(HttpStatus.BAD_REQUEST.value(),"参数异常"),

	NOT_FOUND_EXCEPTION(HttpStatus.NOT_FOUND.value(),"资源不存在"),

	UNAUTHORIZED_EXCEPTION(HttpStatus.UNAUTHORIZED.value(),"未授权"),

	FORBIDDEN_EXCEPTION(HttpStatus.FORBIDDEN.value(),"访问拒绝"),

	EXCEPTION(HttpStatus.INTERNAL_SERVER_ERROR.value(),"未知异常，请联系管理员");

	private int code;

	private String msg;

	ResponseStatus(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public int getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
}

