package com.baihua.core.common.exception;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title TestController.java
 * @Package com.jhouse.jhouseservice.common.exception
 * @date 2018年11月14日 20:36:25
 */
public class BaihuaException extends RuntimeException {

	private static final long serialVersionUID = 1L;

    public BaihuaException(String msg) {
		super(msg);
	}
	
	public BaihuaException(String msg, Throwable e) {
		super(msg, e);
	}
}
