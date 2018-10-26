package core.util;

import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Properties;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CommonUtils {
	private static Logger logger = LoggerFactory.getLogger(CommonUtils.class);

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

	public static String randomID(Integer count) {
		String randomString = RandomStringUtils.randomAlphanumeric(count);
		long now = System.currentTimeMillis();
		return randomString + now;
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

}
