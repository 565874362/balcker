package com.baihua.core.common.utils;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.LinkedHashMap;
import java.util.Map;

import com.baihua.core.common.enums.ResponseStatus;

/**
 * 返回值封装
 *
 * @author zhaodongdong
 * @version V1.0
 * @Title R.java
 * @Package com.jhouse.jhouseservice.common.utils
 * @date 2018年11月22日 22:56:58
 */
public class R extends LinkedHashMap<String, Object>  {

	private static final long serialVersionUID = 1L;

	private static final String RETURN_STATUS = "code";
	private static final String RETURN_MASSAGE = "msg";
	private static final String RETURN_SERVER_TIME = "time";
	private static final String RETURN_SERVER_TIMESTAMP = "timestamp";
	private static final String RETURN_DATA = "data";

	private R(ResponseStatus responseStatus) {
		LocalDateTime now = LocalDateTime.now();
		put(RETURN_STATUS, responseStatus.getCode());
		put(RETURN_MASSAGE, responseStatus.getMsg());
		put(RETURN_SERVER_TIME, DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).format(now));
		put(RETURN_SERVER_TIMESTAMP,now.atZone(ZoneId.systemDefault()).getNano() /1000);
		put(RETURN_DATA,new LinkedHashMap<String,Object>());
	}

	public static R success() {
		return fromResStatus(ResponseStatus.SUCCESS);
	}

	public static R success(String key, Object val) {
		return fromResStatus(ResponseStatus.SUCCESS).addResData(key,val);
	}

	public static R success(Map<String,Object> data) {
		return fromResStatus(ResponseStatus.SUCCESS).addResData(data);
	}

	public static R fail(String msg) {
		return fromResStatus(ResponseStatus.FAIL).exchange(RETURN_MASSAGE,msg);
	}

	public static R fail(Throwable e) {
		return fromResStatus(ResponseStatus.FAIL).exchange(RETURN_MASSAGE,e.getMessage());
	}

	public static R exp() {
		return fromResStatus(ResponseStatus.EXCEPTION);
	}

	public static R exp(String msg) {
		return fromResStatus(ResponseStatus.EXCEPTION).exchange(RETURN_MASSAGE,msg);
	}

	public static R exp(Throwable e) {
		return fromResStatus(ResponseStatus.EXCEPTION).exchange(RETURN_MASSAGE,e.getMessage());
	}

	public static R fromResStatus(ResponseStatus responseStatus){
		return new R(responseStatus);
	}

	@SuppressWarnings("")
	public R addResData(String key, Object val) {
		Map<String, Object> transferDataMap = (Map<String, Object>)this.get(RETURN_DATA);
		transferDataMap.put(key,val);
		return this;
	}

	@SuppressWarnings("")
	public R addResData(Map<String,Object> data) {
		Map<String, Object> transferDataMap = (Map<String, Object>)this.get(RETURN_DATA);
		transferDataMap.putAll(data);
		return this;
	}

	public R exchange(String msg) {
		super.put(RETURN_MASSAGE, msg);
		return this;
	}

	private R exchange(String key, Object value) {
		super.put(key, value);
		return this;
	}
}

