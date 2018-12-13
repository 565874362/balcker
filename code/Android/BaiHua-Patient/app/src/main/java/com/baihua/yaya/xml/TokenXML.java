package com.baihua.yaya.xml;

import com.blankj.utilcode.util.SPUtils;

/**
 * ProjectName：TuanLeApp
 * PackageName：com.joymates.tuanle.xml
 * ClassDescribe：
 * CreaterBy：SongGang
 * CreateDate：2017/12/29 14:36
 * Remark：
 */
public class TokenXML {

    static String tokenKey = "tokenKey";

    public static void saveToken(String token) {
        SPUtils.getInstance().put(tokenKey, token);
    }

    public static String getToken() {

        return SPUtils.getInstance().getString(tokenKey, "");
    }

    public static void clean() {
        SPUtils.getInstance().clear();
    }
}
