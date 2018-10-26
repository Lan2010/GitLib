package com.tianzhixing.app.util;

import java.util.UUID;

public class TokenUtil {
	/**
	 * 通过两个sha1(uuid随机字符串)拼接后取前64位，然后转换成大写
	 * @return 64位随机数
	 */
	public static String getToken() {
		return (EncryptUtil.encodeBySHA1(UUID.randomUUID().toString())
				+ EncryptUtil.encodeBySHA1(UUID.randomUUID().toString())).substring(0, 64).toUpperCase();
	}
}
