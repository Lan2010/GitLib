package wechat.util;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import core.common.Constant;
import core.util.CommonUtils;
import core.util.HttpUtils;

/**
 * 微信公众号、小程序操作工具类
 * 
 * @author dev-teng
 * @date 2018年6月11日
 */
public class WeChatUtil {
	private static Logger log = LoggerFactory.getLogger(WeChatUtil.class);

	// 小程序登录接口
	private static final String JSCODE_TO_SESSION = "https://api.weixin.qq.com/sns/jscode2session?appid=%s&secret=%s&js_code=%s&grant_type=authorization_code";


	/**
	 * 获取用户信息
	 * 
	 * @param code
	 * @return
	 */
	public static String getUserInfo(String code) {
		String appid = CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "wechat.appid");
		String appsecret = CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "wechat.appsecret");
		if (appid == null || "".equals(appid)) {
			log.error("appid is null");
			return null;
		}
		if (appsecret == null || "".equals(appsecret)) {
			log.error("appsecret is null");
			return null;
		}
		String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid=" + appid + "&secret=" + appsecret
				+ "&code=" + code + "&grant_type=authorization_code";
		String result = HttpUtils.get(url);
		return result;
	}

	/**
	 * 小程序登录，获取mp_session信息
	 * 
	 * @param appid
	 * @param secret
	 * @param code
	 * @return
	 */
	public static Map<String, Object> getMpSession(String appid, String secret, String code) {
		String url = String.format(JSCODE_TO_SESSION, appid, secret, code);
		String result = HttpUtils.httpsGet(url);
		log.info("小程序登录，获取mpsession：{}", result);
		Map<String, Object> map = JSON.parseObject(result, new TypeReference<Map<String, Object>>() {});
		return map;
	}
}
