package com.baihua.rest.modules.login.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title LoginIgnore.java
 * @Package com.baihua.baihuamedical.modules.login.annotation
 * @date 2018年12月17日 10:02:06
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface LoginIgnore {
}

