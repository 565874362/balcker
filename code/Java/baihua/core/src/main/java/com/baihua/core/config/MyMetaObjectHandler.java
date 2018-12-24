package com.baihua.core.config;

import java.util.Date;

import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title MyMetaObjectHandler.java
 * @Package com.baihua.baihuamedical.config
 * @date 2018年12月14日 14:32:47
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

	@Override
	public void insertFill(MetaObject metaObject) {
		Date now = new Date();
		this.setFieldValByName("gmtCreate", now, metaObject);
		this.setFieldValByName("gmtModified", now, metaObject);
		this.setFieldValByName("isDel", 0, metaObject);
	}

	@Override
	public void updateFill(MetaObject metaObject) {
		this.setFieldValByName("gmtModified", new Date(), metaObject);

	}
}

