package com.tianzhixing.app.util;

import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESedeKeySpec;
import javax.crypto.spec.IvParameterSpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

/**
 * 加解密工具类
 * 
 * @author dev-teng
 * @date 2018年6月11日
 */
public class EncryptUtil {
	private static final char[] HEX_DIGITS = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd',
			'e', 'f' };

	/**
	 * 用于建立十六进制字符的输出的大写字符数组
	 */
	private static final char[] HEX_DIGITS_UPPER = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C',
			'D', 'E', 'F' };

	private static final String  DES_ECB_TRANSFORMATION ="DESede/ECB/PKCS5Padding";
	
	private static final String  DES_CBC_TRANSFORMATION ="DESede/CBC/PKCS5Padding";
	
	
	/**
	 * 字符串转换成十六进制字符串
	 *
	 * @param String
	 *            str 待转换的ASCII字符串
	 * @return String 每个Byte之间空格分隔，如: [61 6C 6B]
	 */
	public static String str2Hex(String str) {
		StringBuilder sb = new StringBuilder("");
		byte[] bs = str.getBytes();
		int bit;

		for (int i = 0; i < bs.length; i++) {
			bit = (bs[i] & 0x0f0) >> 4;
			sb.append(HEX_DIGITS_UPPER[bit]);
			bit = bs[i] & 0x0f;
			sb.append(HEX_DIGITS_UPPER[bit]);
		}
		return sb.toString().trim();
	}


	/**
	 * 十六进制转换字符串
	 *
	 * @param String
	 *            str Byte字符串(Byte之间无分隔符 如:[616C6B])
	 * @return String 对应的字符串
	 */
	public static String hex2Str(String hex) {
		String str = "0123456789ABCDEF";
		char[] hexs = hex.toCharArray();
		byte[] bytes = new byte[hex.length() / 2];
		int n;

		for (int i = 0; i < bytes.length; i++) {
			n = str.indexOf(hexs[2 * i]) * 16;
			n += str.indexOf(hexs[2 * i + 1]);
			bytes[i] = (byte) (n & 0xff);
		}
		return new String(bytes);
	}

	 /**
	 * 3DESECB加密,key必须是长度大于等于 3*8 = 24 位
	 *
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	 public static String encrypt3DESECB(final String source, final String key) throws Exception {
		 if(source==null || source.isEmpty()) {
			 return null;
		 }
		 final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
		 final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		 final SecretKey securekey = keyFactory.generateSecret(dks);
		
		 final Cipher cipher = Cipher.getInstance(DES_ECB_TRANSFORMATION);
		 cipher.init(Cipher.ENCRYPT_MODE, securekey);
		 final byte[] b = cipher.doFinal(source.getBytes());
		
		 final BASE64Encoder encoder = new BASE64Encoder();
		 return encoder.encode(b).replaceAll("\r", "").replaceAll("\n", "");
	
	 }
	
	 /**
	 * 3DESECB解密,key必须是长度大于等于 3*8 = 24 位
	 *
	 * @param src
	 * @param key
	 * @return
	 * @throws Exception
	 */
	 public static String decrypt3DESECB(final String data, final String key) throws Exception {
		 if(data==null || data.isEmpty()) {
			 return null;
		 }
		 // --通过base64,将字符串转成byte数组
		 final BASE64Decoder decoder = new BASE64Decoder();
		 final byte[] bytesrc = decoder.decodeBuffer(data);
		 // --解密的key
		 final DESedeKeySpec dks = new DESedeKeySpec(key.getBytes("UTF-8"));
		 final SecretKeyFactory keyFactory = SecretKeyFactory.getInstance("DESede");
		 final SecretKey securekey = keyFactory.generateSecret(dks);
		
		 // --Chipher对象解密
		 final Cipher cipher = Cipher.getInstance(DES_ECB_TRANSFORMATION);
		 cipher.init(Cipher.DECRYPT_MODE, securekey);

		 final byte[] retByte = cipher.doFinal(bytesrc);
		
		 return new String(retByte);
	 }

	/**
	 * ECB加密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] encrypt3DESECB(byte[] key, byte[] data) throws Exception {
		if(key==null || key.length==0) {
			 return null;
		 }
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance(DES_ECB_TRANSFORMATION);
		cipher.init(Cipher.ENCRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * ECB解密,不要IV
	 * 
	 * @param key
	 *            密钥
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] decrypt3DESECB(byte[] key, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance(DES_ECB_TRANSFORMATION);
		cipher.init(Cipher.DECRYPT_MODE, deskey);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC加密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            明文
	 * @return Base64编码的密文
	 * @throws Exception
	 */
	public static byte[] des3EncodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance(DES_CBC_TRANSFORMATION);
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.ENCRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * CBC解密
	 * 
	 * @param key
	 *            密钥
	 * @param keyiv
	 *            IV
	 * @param data
	 *            Base64编码的密文
	 * @return 明文
	 * @throws Exception
	 */
	public static byte[] des3DecodeCBC(byte[] key, byte[] keyiv, byte[] data) throws Exception {
		Key deskey = null;
		DESedeKeySpec spec = new DESedeKeySpec(key);
		SecretKeyFactory keyfactory = SecretKeyFactory.getInstance("desede");
		deskey = keyfactory.generateSecret(spec);
		Cipher cipher = Cipher.getInstance(DES_CBC_TRANSFORMATION);
		IvParameterSpec ips = new IvParameterSpec(keyiv);
		cipher.init(Cipher.DECRYPT_MODE, deskey, ips);
		byte[] bOut = cipher.doFinal(data);
		return bOut;
	}

	/**
	 * md5加密
	 * 
	 * @param source
	 * @return
	 */
	public static String encodeByMD5(String source) {
		if (source == null) {
			return null;
		}
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(source.getBytes()); // 通过使用 update 方法处理数据,使指定的 byte数组更新摘要
			byte[] encryptStr = md.digest(); // 获得密文完成哈希计算,产生128 位的长整数
			char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符
			int k = 0; // 表示转换结果中对应的字符位置
			for (int i = 0; i < 16; i++) { // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
				byte byte0 = encryptStr[i]; // 取第 i 个字节
				str[k++] = HEX_DIGITS[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
				str[k++] = HEX_DIGITS[byte0 & 0xf]; // 取字节中低 4 位的数字转换
			}
			return new String(str); // 换后的结果转换为字符串
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * SHA1加密
	 * 
	 * @param str
	 * @return
	 */
	public static String encodeBySHA1(String str) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA1");
			md.update(str.getBytes());
			StringBuffer buf = new StringBuffer();
			byte[] bits = md.digest();
			for (int i = 0; i < bits.length; i++) {
				int a = bits[i];
				if (a < 0)
					a += 256;
				if (a < 16)
					buf.append("0");
				buf.append(Integer.toHexString(a));
			}
			return buf.toString();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	/**
	 * BASE64解密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static byte[] decryptBASE64(String key) throws Exception {
		return (new BASE64Decoder()).decodeBuffer(key);
	}

	/**
	 * BASE64加密
	 * 
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String encryptBASE64(byte[] key) throws Exception {
		return (new BASE64Encoder()).encodeBuffer(key);
	}
}
