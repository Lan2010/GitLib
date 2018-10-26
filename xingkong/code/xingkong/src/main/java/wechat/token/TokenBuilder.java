package wechat.token;

import java.util.Properties;

import core.common.Constant;
import wechat.util.PropertyUtil;

public class TokenBuilder {
	private Token token = new Token();
	private int requestTimes = 3;// token请求失败后重新请求的次数
	private Properties properties;

	private TokenBuilder() {
	}

	private static class Inner {
		private static final TokenBuilder ACCESS_TOKEN_SERVER = new TokenBuilder();
	}

	public static TokenBuilder getInstance() {
		return Inner.ACCESS_TOKEN_SERVER;
	}

	public TokenBuilder defaultConfig() {
		properties = PropertyUtil.getProperties(Constant.APPLICATION_CONFIG_PATH);
		token.setAppid(properties.getProperty("mp.appid"));
		token.setAppsecret(properties.getProperty("mp.appsecret"));
		return this;
	}

	public TokenBuilder setAppid(String appid) {
		token.setAppid(appid);
		return this;
	}

	public TokenBuilder setAppsecret(String appsecret) {
		token.setAppsecret(appsecret);
		return this;
	}

	/**
	 * 通过中控服务器得到token
	 * 
	 * @return
	 */
	public String getToken() {
		// 没有可用的token，则去刷新
		if (!token.isValid()) {
			refresh();
		}
		return token.getToken();
	}

	public void refresh() {
		for (int i = 0; i < requestTimes; i++) {
			// 请求成功则退出
			if (token.request())
				break;
		}
	}

	public static void main(String[] args) {
		System.out.println(TokenBuilder.getInstance().defaultConfig().getToken());
	}
	
}
