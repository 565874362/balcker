package com.baihua.baihuamedical.common.utils;

import java.security.MessageDigest;

import org.apache.commons.codec.binary.Hex;

/**
 * @author zhaodongdong
 * @version V1.0
 * @Title CodeUtil.java
 * @Package com.baihua.baihuamedical.common.utils
 * @date 2018年12月22日 10:25:17
 */
public class CodeUtil {

	public static String hexSHA1(String value) {
		try {
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			md.update(value.getBytes("utf-8"));
			byte[] digest = md.digest();
			return byteToHexString(digest);
		} catch (Exception ex) {
			throw new RuntimeException(ex);
		}
	}

	public static String byteToHexString(byte[] bytes) {
		return String.valueOf(Hex.encodeHex(bytes));
	}
}

