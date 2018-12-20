package com.baihua.baihuamedical.common.validator;

import org.apache.commons.lang.StringUtils;

import com.baihua.baihuamedical.common.exception.BaihuaException;

/**
 * 数据校验
 * @author chenshun
 * @email sunlightcs@gmail.com
 * @date 2017-03-23 15:50
 */
public abstract class Assert {

    public static void isBlank(String str, String message) {
        if (StringUtils.isBlank(str)) {
            throw new BaihuaException(message);
        }
    }

    public static void isNull(Object object, String message) {
        if (object == null) {
            throw new BaihuaException(message);
        }
    }
}
