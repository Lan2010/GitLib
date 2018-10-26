package com.tianzhixing.app.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.fluent.Request;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

	private static final int PERMUTATION = 720;

	private static final int MAX_COMBINATION = 906192;

	private static final char[] STUFFS = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P',
			'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', '1', '2', '3', '4', '5', '6', '7', '8' };
	private static final int LEN = 6;

	/**
	 * 生成用户的邀请码
	 * 
	 * @param userId 必须小于652458240这个数目
	 * @return
	 */
	public static String generateInviteCode(Integer userId) {
		int com = userId / PERMUTATION;
		if (com >= MAX_COMBINATION) {//0到906191
			return "";
		}
		int per = userId % PERMUTATION;//0到719
		char[] chars = combination(com);
		chars = permutation(chars, per);
		return new String(chars);
	}

	private static char[] permutation(char[] chars, int per) {
		// $tmpchars = $chars;
		// $offset = array();
		// $charscount = $step = count($chars);
		// for ($i = $charscount - 1; $i >= 0; --$i) {
		// $offset[$i] = $per % $step;
		// $per /= $step;
		// $step--;
		// }
		//
		// for ($i = 0; $i < $charscount; ++$i) {
		// if ($offset[$i] == 0)
		// continue;
		// $tmp = $tmpchars[$i];
		// $tmpchars[$i] = $tmpchars[$i - $offset[$i]];
		// $tmpchars[$i - $offset[$i]] = $tmp;
		// }
		// return $tmpchars;
		char tmp;
		int step = chars.length;
		int charscount = chars.length;
		char[] tmpchars = chars;
		int[] offset = new int[step];
		for (int i = charscount-1; i > 0; --i) {
			offset[i] = per % step;
			per /= step;
			step--;
		}
		for (int i = 0; i < charscount; ++i) {
			if (offset[i] == 0) {
				continue;
			}
			tmp = tmpchars[i];
			tmpchars[i] = tmpchars[i - offset[i]];
			tmpchars[i - offset[i]] = tmp;
		}
		return tmpchars;
	}

	private static char[] combination(int com) {
		// $chars = array();
		// $start = 0;
		// $index = 0;
		// $length = count(self::STUFFS);
		// while ($index < self::LEN) {
		// for ($i = $start; $i < $length; ++$i) {
		// $c = $this->getCombination($length - $i - 1, self::LEN - $index - 1);
		// if ($com >= $c) {
		// $com -= $c;
		// continue;
		// }
		// $chars[$index++] = self::STUFFS[$i];
		// $start = $i + 1;
		// break;
		// }
		// }
		// return $chars;
		char[] chars = new char[LEN];
		int start = 0;
		int index = 0;
		int length = STUFFS.length;//32
		int c;
		while (index < LEN) {
			for (int i = start; i < length; ++i) {
				c = getCombination(length - i - 1, LEN - index - 1);
				if (com >= c) {
					com -= c;
					continue;
				}
				chars[index++] = STUFFS[i];
				start = i + 1;
				break;
			}
		}
		return chars;
	}
	
	
	private static int getCombination(int n, int m) {
		// $com = 1;
		// for ($i = $n - $m + 1; $i <= $n; ++$i) {
		// $com *= $i;
		// }
		// for ($i = 2; $i <= $m; ++$i) {
		// $com /= $i;
		// }
		// return $com;
		int com = 1;
		for (int i = n - m + 1; i <= n; ++i) {
			com *= i;
		}
		for (int i = 2; i <= m; ++i) {
			com /= i;
		}
		return com;
	}

	/**
	 * 获取配置文件内容
	 * 
	 * @param path
	 *            相对classpath路径
	 * @param key
	 *            需要取的key
	 * @return
	 */
	public static String getPropertiesValue(String path, String key) {
		if (getProperties(path) != null) {
			return getProperties(path).getProperty(key);
		}
		return null;
	}

	/**
	 * 设置Properties
	 * 
	 * @param path
	 *            相对classpath路径
	 */
	public static Properties getProperties(String path) {
		InputStream inputStream = null;
		Properties properties = new Properties();// 属性集合对象
		try {
			inputStream = CommonUtils.class.getResourceAsStream(path);
			properties.load(inputStream);
		} catch (IOException e) {
			logger.error(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (null != inputStream)
					inputStream.close();
			} catch (IOException e) {
				logger.error("InputStream关闭失败");
			}
		}
		return properties;
	}

	/**
	 * 获取用户的ip地址
	 * 
	 * @param request
	 * @return
	 * @throws UnknownHostException
	 */
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("X-Forwarded-For");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_X_CLUSTER_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_CLIENT_IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED_FOR");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_FORWARDED");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("HTTP_VIA");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("REMOTE_ADDR");
		}

		if (ip == null || ip.length() == 0 || "unkonown".equals(ip)) {
			ip = request.getHeader("X-Real-IP");
		}

		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}

		if ("127.0.0.1".equals(ip) || "0:0:0:0:0:0:0:1".equals(ip))
			try {
				ip = InetAddress.getLocalHost().getHostAddress();
			} catch (UnknownHostException unknownhostexception) {
				ip = "0.0.0.0";
			}
		if (ip.indexOf(",") > 0) {
			ip = ip.substring(0, ip.indexOf(","));
		}
		return ip;
	}

	/**
	 * 生成随机ID
	 * 
	 * @param count
	 *            随机字符串位数
	 * @return
	 */
	public static String randomID(Integer count) {
		String randomString = RandomStringUtils.randomAlphanumeric(count);
		long now = System.currentTimeMillis();
		return randomString + now;
	}

	

	/**
	 * 验证密码复杂度
	 * 
	 * @param password
	 */
	public static String validPass(String password) {
		String regex1 = "[A-Za-z]";// 英文字符
		String regex2 = "[0-9]";// 英文字符
		String regex3 = "[~!@#$%^&*()\\-_=+{};:<,.>?'\"\\/]";// 特殊符号
		int count = 0;
		boolean b1 = Pattern.compile(regex1).matcher(password).find();
		boolean b2 = Pattern.compile(regex2).matcher(password).find();
		boolean b3 = Pattern.compile(regex3).matcher(password).find();
		if (b1) {
			count++;
		}
		if (b2) {
			count++;
		}
		if (b3) {
			count++;
		}
		if (count < 2) {
			return "密码组合至少包含数字，字母或者特殊字符任意两种!";
		}
		if (password.length() < 6) {
			return "密码长度不能小于6个字符";
		}
		return null;
	}

	
	/**
	 * 发送get请求
	 * 
	 * @param url
	 * @return
	 */
	public static String httpGet(String url) {
		try {
			HttpEntity entity = Request.Get(url).execute().returnResponse().getEntity();
			return entity != null ? EntityUtils.toString(entity) : null;
		} catch (Exception e) {
			logger.error("get请求异常，" + e.getMessage() + "\n get url:" + url);
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 校验手机号
	 * @param phone
	 * @return
	 */
	public static boolean validatePhone(String phone) {
		if (phone == null || phone.isEmpty())
			return false;
		return Pattern.compile("^(1(([3|5|7][0-9])|(47)|[8][0123456789]))\\d{8}$").matcher(phone).find();
	}
	
}
