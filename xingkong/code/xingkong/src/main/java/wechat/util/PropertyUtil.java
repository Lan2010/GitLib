package wechat.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.controller.PostCardController;

public class PropertyUtil {
	private static Logger logger = LoggerFactory.getLogger(PostCardController.class);

	private static Map<String, Object> map = new HashMap<String, Object>();

	public static Properties getProperties(String filePath) {
		Properties p = new Properties();
		//String mapKey = "properties";
		if (!map.containsKey(filePath)) {
			InputStream inputStream = PropertyUtil.class.getResourceAsStream(filePath);
			if (inputStream == null) {
				logger.error("找不到" + filePath + "文件");
				return null;
			}
			try {
				p.load(inputStream);
				map.put(filePath, p);// 放入静态变量map（栈内存）中
			} catch (IOException e) {
				logger.error("加载配置文件时发生IO异常");
			} finally {
				try {
					if (null != inputStream)
						inputStream.close();
				} catch (IOException e) {
					logger.error("InputStream关闭失败");
				}
			}
		} else {
			p = (Properties) map.get(filePath);// 从内存当中取数据
		}
		return p;
	}
}
