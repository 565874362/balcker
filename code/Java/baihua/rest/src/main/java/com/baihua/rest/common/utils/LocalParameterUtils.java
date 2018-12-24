package com.baihua.rest.common.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title LocalParameterUtils.java
 * @Package com.baihua.baihuamedical.common.utils
 * @date 2018年12月14日 11:18:52
 */
public class LocalParameterUtils {

	private static final ThreadLocal<Map<String,Object>> threadLocal = new ThreadLocal();

	public static <T>  void set(String key,T o){
		Map<String, Object> stringObjectMap = threadLocal.get();
		if(stringObjectMap == null){
			stringObjectMap = new HashMap<>();
		}
		stringObjectMap.put(key, o);
		threadLocal.set(stringObjectMap);
	}

	public static <T> T get(String key){
		return (T)threadLocal.get().get(key);
	}
}

