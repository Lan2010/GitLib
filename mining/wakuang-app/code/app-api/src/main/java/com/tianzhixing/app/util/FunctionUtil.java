package com.tianzhixing.app.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

public class FunctionUtil {
	private static Logger logger = LoggerFactory.getLogger(FunctionUtil.class);

	/**
	 * 隐藏电话号码
	 * 
	 * @param phone
	 * @return
	 */
	public static String hidPhone(String phone) {
		if (phone != null) {
			return phone.replaceAll("(\\d{3})\\d{4}(\\d{4})", "$1****$2");
		}
		return null;
	}

	/**
	 * 算出最大 最小的经纬度范围
	 * 
	 * @param lat经度
	 * @param lon纬度
	 * @param raidusMile 距离 米
	 * @return
	 */
	public static JSONObject getAround(float lat, float lon, float raidusMile) {
		float degree = (float) ((24901 * 1609) / 360.0);
		float dpmLat = 1 / degree;
		float radiusLat = dpmLat * raidusMile;
		float minLat = lat - radiusLat;
		float maxLat = lat + radiusLat;

		float mpdLng = (float) (degree * Math.cos(lat * (Math.PI / 180)));
		float dpmLng = 1 / mpdLng;
		float radiusLng = dpmLng * raidusMile;
		float minLng = lon - radiusLng;
		float maxLng = lon + radiusLng;

		JSONObject json = new JSONObject();
		json.put("minLat", minLat);
		json.put("minLong", minLng);
		json.put("maxLat", maxLat);
		json.put("maxLong", maxLng);

		return json;
	}
}
