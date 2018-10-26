package wechat.token;

import java.util.Date;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSONObject;

import core.util.HttpUtils;

public class Token {
	private static Logger logger = LoggerFactory.getLogger(Token.class);
	private static final String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";//获取Token的请求url
	private static final String  TOKEN_NAME = "access_token";//token的参数名称
	private static final String EXPIRES_IN_NAME = "expires_in";//expireIn的参数名称
	private static final int REDUNDANCE = 10 * 1000; // 冗余时间，提前10秒就去请求新的token
	
	private String token; // token
	private long expires; // token有效时间
	private long tokenTime; // token产生时间
	private String appid;
	private String appsecret;
	private String url;
	
	
	public Token(){
		
	}
	
	public String getAppid() {
		return appid;
	}


	public void setAppid(String appid) {
		this.appid = appid;
	}


	public String getAppsecret() {
		return appsecret;
	}


	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	/**
	 * 得到access token
	 */
	public String getToken() {
		return this.token;
	}

	/**
	 * 得到有效时间
	 */
	public long getExpires() {
		return this.expires;
	}

	private void setUrl() {
//		String appid = this.properties.getProperty("wechat.appid");
//		String appsecret = this.properties.getProperty("wechat.appsecret");
		this.url = ACCESS_TOKEN_URL + "&appid=" + this.appid + "&secret=" + this.appsecret;
		logger.info("创建获取access_token url："+url);
	}
	
	public boolean request() {
		setUrl();
		String result = HttpUtils.get(this.url);
		if (StringUtils.isBlank(result))
			return false;
		if (!parseData(result)) {
			return false;
		}
		logger.info("token（微信授权用）获取成功");
		return true;
	}

	/**
	 * 解析token数据
	 * 
	 * @param data
	 * @return
	 */
	private boolean parseData(String data) {
		JSONObject jsonObject = JSONObject.parseObject(data);
		try {
			String token = jsonObject.get(TOKEN_NAME).toString();
			if (token==null||"".equals(token)) {
				logger.error("token获取失败,获取结果" + data);
				return false;
			}
			this.token = token;
			this.tokenTime = (new Date()).getTime();
			String expiresIn = jsonObject.get(EXPIRES_IN_NAME).toString();
			if (StringUtils.isBlank(expiresIn)) {
				logger.error("token获取失败,获取结果" + expiresIn);
				return false;
			} else {
				this.expires = Long.valueOf(expiresIn);
			}
		} catch (Exception e) {
			logger.error("token 结果解析失败，token参数名称: " + TOKEN_NAME + ",有效期参数名称:"
					+ EXPIRES_IN_NAME + ",token请求结果:" + data);
			e.printStackTrace();
			return false;
		}
		return true;
	}


	/**
	 * accessToken 是否有效
	 * 
	 * @return true:有效，false: 无效
	 */
	public boolean isValid() {
		// 黑名单判定法
		if (StringUtils.isBlank(this.token))
			return false;
		if (this.expires <= 0)
			return false;
		// 过期
		if (isExpire())
			return false;
		return true;
	}

	/**
	 * 是否过期
	 * 
	 * @return true 过期 false：有效
	 */
	private boolean isExpire() {
		Date currentDate = new Date();
		long currentTime = currentDate.getTime();
		long expiresTime = expires * 1000 - REDUNDANCE;
		// 判断是否过期
		if ((tokenTime + expiresTime) > currentTime)
			return false;
		return true;
	}
	
}
