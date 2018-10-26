package core.service.impl;

import java.sql.SQLException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;

import core.common.Constant;
import core.mapper.UserMapper;
import core.pojo.User;
import core.service.LoginService;
import core.service.nats.NatsComponent;
import core.util.CommonUtils;
import wechat.util.WeChatUtil;

/**
 * 登录业务层
 * 
 * @author dev-teng
 * @date 2018年6月11日
 */
@Service
public class LoginServiceImpl implements LoginService {

	private static Logger logger = LoggerFactory.getLogger(LoginServiceImpl.class);
	@Autowired
	private NatsComponent natsComponent;
	@Autowired
	private UserMapper userMapper;

	public Map<String, Object> login(String code) {
		String appid = CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "mp.appid");
		String appsecret = CommonUtils.getPropertiesValue(Constant.APPLICATION_CONFIG_PATH, "mp.appsecret");
		if (appid == null || "".equals(appid)) {
			logger.error("appid is null");
			return null;
		}
		if (appsecret == null || "".equals(appsecret)) {
			logger.error("appsecret is null");
			return null;
		}
		Map<String, Object> sessionMap = WeChatUtil.getMpSession(appid, appsecret, code);
		if (sessionMap != null) {
			if (sessionMap.get("openid") != null) {
				return sessionMap;
			}
		}
		return null;
	}

	/**
	 * 发布登录/注册消息到nats
	 * 
	 * @param request
	 * @param operationType
	 */
	@Override
	public void publish4oms(HttpServletRequest request, Integer operationType, User user) {
		User temp = null;
		JSONObject json = new JSONObject();
		Long currentTime = System.currentTimeMillis();
		try {
			temp = userMapper.selectOne(user.getOpenid());
			json.put("id", CommonUtils.randomID(8));
			json.put("createTime", currentTime);
			json.put("platformFrom", Constant.PLATFORM_NAME);
			json.put("clientPlatformType", Constant.CLIENT_PLATFORM_TYPE);
			json.put("ip", CommonUtils.getIpAddr(request));
			if (temp == null) {//注册的新用户埋点
				json.put("regTime", currentTime);
				json.put("nickName", user.getNickName());
				json.put("avatar", user.getAvatarUrl());
				json.put("userOperType", "reg");
				json.put("wxID", user.getOpenid());
				natsComponent.publish4oms("oms.subject.user.basic.info", json.toJSONString());
			} else {//登录埋点
				json.put("operationTime", currentTime);
				json.put("operationType", operationType);
				natsComponent.publish4oms("oms.subject.user.login-logout", json.toJSONString());
			}
		}  catch (SQLException e) {
			e.printStackTrace();
			logger.error("SQLException.", e);
		} finally {
			
		}
	}

}
