package com.baihua.core.common.validator;

import org.apache.commons.lang.StringUtils;

import com.baihua.core.common.exception.BaihuaException;

/**
 * 数据校验
 * @author zhaodongdong
 * @email 2977260348@qq.com
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
