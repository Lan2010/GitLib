package com.tianzhixing.app.pojo;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.exception.DESECBException;
import com.tianzhixing.app.util.EncryptUtil;

public class BaseRequest {
	private static Logger log = LoggerFactory.getLogger(BaseRequest.class);
	private String s = "sign";//签名
	private String d = "abcdefg";//设备ID
	private Short mt = 3;//终端类型（1：PC 2：安卓 3：IOS 4：微信）
	private Long t = System.currentTimeMillis()/1000;//当前时间的时间戳
	private String sv = "2.0.0";//接入的接口服务版本号
	private String v = "2.0.0";//终端版本号
	private String token;//自定义令牌
	private String data;//
	
	public String getS() {
		return s;
	}
	public void setS(String s) {
		this.s = s;
	}
	public String getD() {
		return d;
	}
	public void setD(String d) {
		this.d = d;
	}
	public Short getMt() {
		return mt;
	}
	public void setMt(Short mt) {
		this.mt = mt;
	}
	public Long getT() {
		return t;
	}
	public void setT(Long t) {
		this.t = t;
	}
	public String getSv() {
		return sv;
	}
	public void setSv(String sv) {
		this.sv = sv;
	}
	public String getV() {
		return v;
	}
	public void setV(String v) {
		this.v = v;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public BaseRequest decryptData() throws DESECBException {
		try {
			if (Boolean.valueOf(Constant.API_ENABLED_ENCRYPT)) {// 判断配置文件是否开启（请求接口的返回的数据）加密开关
				data = EncryptUtil.decrypt3DESECB(data, Constant.API_3DES_KEY);//解密
			}
		} catch (Exception e) {
			log.error("Decrypt data exception.",e);
			throw new DESECBException("解密data出错");
		}
		return this;
	}
	
	public String getParameter(String key) {
		if(key==null||key.isEmpty()) {
			return null;
		}
		if(data==null || data.isEmpty()) {
			return null;
		}
		JSONObject json = JSON.parseObject(data);
		return json.getString(key);
	}
	
	public String toSignString() {
		StringBuilder stringBuilder = new StringBuilder();
		HashMap<String, Object> map = new HashMap<String, Object>();
		@SuppressWarnings("rawtypes")
		Class cls = this.getClass();
		Field[] fields = cls.getDeclaredFields();
		for (int i = 0; i < fields.length; i++) {
			Field f = fields[i];
			f.setAccessible(true);
			try {
				map.put(f.getName(), f.get(this));
			} catch (IllegalArgumentException e1) {
				e1.printStackTrace();
			} catch (IllegalAccessException e1) {
				e1.printStackTrace();
			}
		}
		Collection<String> keyset = map.keySet();
		List<String> list = new ArrayList<String>(keyset);
		Collections.sort(list);//字段名排序
		String key;
		Object value;
		for (int i = 0; i < list.size(); i++) {
			if (!"s".equals(list.get(i))) {
				key = list.get(i);
				value = map.get(key);
				if (value == null) {
					continue;
				}
				stringBuilder.append(value);
			}
		}
		String orderStr = EncryptUtil.encodeByMD5(stringBuilder.toString()).toUpperCase();
		return orderStr;
	}
	public static void main(String[] args) throws Exception {
		System.out.println(EncryptUtil.encrypt3DESECB("{\"phone\":\"15768118003\"}", Constant.API_3DES_KEY));
	}
}
