package com.tianzhixing.app.pojo;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.exception.DESECBException;
import com.tianzhixing.app.util.EncryptUtil;
import com.tianzhixing.app.util.RSAUtil;

/**
 * - 全局响应类，封装格式，data字段进行加密 - 拥有各种结果返回状态 ，以及对返回结果 的格式化 -
 * 其中：200成功，400非法请求，500服务器错误
 */
public class BaseResponse {
	private static Logger log = LoggerFactory.getLogger(BaseResponse.class);
	private Integer ret = 200;// 返回状态码，其中：200成功，400非法请求，500服务器错误
	private String sign = "";// 签名
	private String data = "";// 待返回给客户端的数据,json结果经过base64编码再做3des加密
	private String error = "";// 错误返回信息
	private String jmpurl = "";// 需要跳转的目标地址
	private Map<String, String> headers = null;// 响应报文头部

	public BaseResponse() {
	}

	/**
	 * 
	 * @param ret
	 * @param sign
	 * @param data
	 * @param error
	 * @param jmpurl
	 * @param headers
	 */
	public BaseResponse(Integer ret, String data, String error, String jmpurl, Map<String, String> headers) {
		super();
		this.ret = ret;
		this.data = data;
		this.error = error;
		this.jmpurl = jmpurl;
		this.headers = headers;
	}

	public Integer getRet() {
		return ret;
	}

	public void setRet(Integer ret) {
		this.ret = ret;
	}

	public String getSign() {
		return sign;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getJmpurl() {
		return jmpurl;
	}

	public void setJmpurl(String jmpurl) {
		this.jmpurl = jmpurl;
	}

	public Map<String, String> getHeaders() {
		return headers;
	}

	/**
	 * 处理返回头
	 * 
	 * @param headers
	 * @param response
	 */
	public void setHeaders(Map<String, String> headers, HttpServletResponse response) {
		if (headers == null) {
			return;
		}
		for (Entry<String, String> entry : headers.entrySet()) {
			response.setHeader(entry.getKey(), entry.getValue());
		}
	}

	/**
	 * 结果输出
	 * 
	 * @throws IOException
	 * @throws DESECBException
	 */
	public void output(HttpServletResponse response) throws IOException{
		String result = getResult();
		response.setContentType("application/json");
		response.setCharacterEncoding("utf-8");
		response.getWriter().write(result);
	}

	/**
	 * 对api返回数据进行签名
	 * 
	 * @param source
	 */
	public String signature() {
		String signStr = toSignString();
		String rsaprikey = Constant.API_RSA_PRIKEY_PATH;
		String prikeyStr = "";
		if (rsaprikey != null && !"".equals(rsaprikey)) {
			prikeyStr = RSAUtil.getPrivateKey(rsaprikey);
			if (prikeyStr == null || prikeyStr.isEmpty()) {
				return "";
			}
			try {
				return RSAUtil.sign(signStr.getBytes(), prikeyStr);
			} catch (Exception e) {
				e.printStackTrace();
				return "";
			}
		}
		return "";
	}

	/**
	 * 返回body，json字符串格式
	 * 
	 * @return
	 * @throws DESECBException
	 */
	public String getResult() {
		JSONObject result = new JSONObject();
		result.put("ret", this.ret);
		result.put("error", this.error);
		if (Boolean.valueOf(Constant.API_ENABLED_ENCRYPT)) {// 判断配置文件是否开启（请求接口的返回的数据）加密开关
			try {
				data = EncryptUtil.encrypt3DESECB(data, Constant.API_3DES_KEY);
			} catch (Exception e) {
				log.error("Encrypt data exception.", e);
				result.put("ret", 5000);
				result.put("error","系统错误" );
				data = "";
			}
		}
		result.put("data", this.data);
		result.put("timestamp", System.currentTimeMillis());
		
		if (Boolean.valueOf(Constant.API_ENABLED_SIGN)) {// 判断是否进行签名
			this.sign = signature();
		}
		result.put("sign", this.sign);
		return result.toJSONString();
	}

	/**
	 * 获取签名字符串（传入的数组注意排除不参与签名的字段）。
	 * 
	 */
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
		Collections.sort(list);// 字段名排序
		String key;
		Object value;
		for (int i = 0; i < list.size(); i++) {
			if (!"sign".equals(list.get(i))) {
				key = list.get(i);
				value = map.get(key);
				if (value == null) {
					continue;
				}
				stringBuilder.append(value);
			}
		}
		String orderStr = EncryptUtil.encodeByMD5(stringBuilder.toString()).toUpperCase();
		return orderStr.toString();
	}

	public static void main(String[] args) throws Exception {
//		String data = "C/AGq/aHzLamPtcDek6Ux3ui+WwQskOxMQ5NeSnLwR0TS+WNtt6fjijyCpussT1fIpG32VrNC9XIqU9ccXWZLpB2L+6gZhZ23SsZk61iF7o=";
		String data = "C/AGq/aHzLaVPqGnOsICPt5lpLD2iQ4GeNCm6PnoIoU=";
		System.out.println(EncryptUtil.decrypt3DESECB(data, Constant.API_3DES_KEY));
	}
}
