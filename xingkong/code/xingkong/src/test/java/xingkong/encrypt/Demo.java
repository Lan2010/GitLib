package xingkong.encrypt;

import java.security.SecureRandom;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;

public class Demo {
	public static void main(String[] args) throws Exception {
		byte[] encrypData = Base64.decodeBase64("mgxuts7uEbmLQKcW329VaQTua4MIdRXKTm+YnFSaOGYK1zd+uKEhhGj5cfU8g5oP1RSk4PXbDrHuQY8MI0zwtfQL2+Zfq0NDm11w8efNCclVSxEbHdUYeJUkhhTsNkZgt3SzYv5ptqTNyJ7TOTaX78wkN1uWTEV2sa/imvdnBclp0Qim0TS3MGoaSyE09MqTvbeG9Z1PjxsDwjVH0FxG1Q==");
		byte[] ivData = Base64.decodeBase64("SG386etdytWA3sOXHxqfnw==");
		byte[] sessionKey = Base64.decodeBase64("Fn6r4gAIOiZI0JBXn4hQ0w==");

		System.out.println(encrypt(encrypData, sessionKey));
		String encryptStr = encrypt(encrypData, sessionKey);
		System.out.println(decrypt(sessionKey, ivData, encryptStr));
	}

	private static String decrypt(byte[] key, byte[] iv, String encrypData) throws Exception {
		byte[] decryptFrom = parseHexStr2Byte(encrypData);
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		// SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, genKey(key), ivSpec);
		// 解析解密后的字符串
		return new String(cipher.doFinal(decryptFrom));

	}
	
	private static String decrypt(byte[] key, byte[] iv, byte[] encrypData) throws Exception {
		IvParameterSpec ivSpec = new IvParameterSpec(iv);
		// SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
		Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
		cipher.init(Cipher.DECRYPT_MODE, genKey(key), ivSpec);
		// 解析解密后的字符串
		return new String(cipher.doFinal(encrypData));
		
	}

	private static String encrypt(String content, byte[] key) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			byte[] byteContent = content.getBytes("utf-8");
			cipher.init(Cipher.ENCRYPT_MODE, genKey(key));// 初始化
			byte[] result = cipher.doFinal(byteContent);
			return parseByte2HexStr(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	private static String encrypt(byte[] encrypData, byte[] key) throws Exception {
		try {
			Cipher cipher = Cipher.getInstance("AES");// 创建密码器
			cipher.init(Cipher.ENCRYPT_MODE, genKey(key));// 初始化
			byte[] result = cipher.doFinal(encrypData);
			return parseByte2HexStr(result); // 加密
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * 根据密钥获得 SecretKeySpec
	 * 
	 * @return
	 */
	private static SecretKeySpec genKey(byte[] key) {
		byte[] enCodeFormat = { 0 };
		;
		try {
			KeyGenerator kgen = KeyGenerator.getInstance("AES");
			SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
			secureRandom.setSeed(key);
			kgen.init(128, secureRandom);
			SecretKey secretKey = kgen.generateKey();
			enCodeFormat = secretKey.getEncoded();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new SecretKeySpec(enCodeFormat, "AES");
	}

	/**
	 * 将二进制转换成16进制
	 * 
	 * @param buf
	 * @return
	 */
	private static String parseByte2HexStr(byte buf[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < buf.length; i++) {
			String hex = Integer.toHexString(buf[i] & 0xFF);
			if (hex.length() == 1) {
				hex = '0' + hex;
			}
			sb.append(hex.toUpperCase());
		}
		return sb.toString();
	}

	/**
	 * 将16进制转换为二进制
	 * 
	 * @param hexStr
	 * @return
	 */
	private static byte[] parseHexStr2Byte(String hexStr) {
		if (hexStr.length() < 1)
			return null;
		byte[] result = new byte[hexStr.length() / 2];
		for (int i = 0; i < hexStr.length() / 2; i++) {
			int high = Integer.parseInt(hexStr.substring(i * 2, i * 2 + 1), 16);
			int low = Integer.parseInt(hexStr.substring(i * 2 + 1, i * 2 + 2), 16);
			result[i] = (byte) (high * 16 + low);
		}
		return result;
	}
}
