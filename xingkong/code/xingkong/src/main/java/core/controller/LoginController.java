package core.controller;

import java.net.UnknownHostException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;

import core.common.Constant;
import core.common.StatusCode;
import core.pojo.ResultMessage;
import core.pojo.User;
import core.service.LoginService;
import core.service.TokenService;
import core.service.UserService;
import core.service.nats.NatsComponent;
import core.util.CommonUtils;

/**
 * 小程序登录控制层
 * 
 * @author dev-teng
 * @date 2018年6月11日
 */
@Controller
public class LoginController {
	@Autowired
	private LoginService loginService;
	@Autowired
	private UserService userService;
	@Autowired
	private TokenService tokenService;

	private static Logger logger = LoggerFactory.getLogger(LoginController.class);

	@RequestMapping(value = "/api/login", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String login(@RequestParam("code") String code, HttpServletRequest request) {
		JSONObject data = new JSONObject();
		User user = null;
		try {
			if (code.isEmpty()) {
				logger.error("code码是空的...");
				return new ResultMessage(StatusCode.NO_CODE, "小程序登录失败").toString();
			}
			Map<String, Object> sessionMap = loginService.login(code);
			if (sessionMap == null) {
				logger.error("发生异常...");
				return new ResultMessage(StatusCode.ERROR, "小程序登录失败").toString();
			}
			String openid = (String) sessionMap.get("openid");
			String session_key = (String) sessionMap.get("session_key");
			if (openid == null || "".equals(openid)) {
				logger.error("openid为空...");
				return new ResultMessage(StatusCode.NO_OPENID, "小程序登录失败").toString();
			} else {
				user = new User();
				user.setOpenid(openid);
				// 保存登录用户信息
				Integer userId = userService.addUser(user);
				tokenService.setClaims(userId, session_key).createToken().addTokenToRedis().addSessionKeyToRedis();
				data.put(Constant.TOKEN, tokenService.getToken());
				// iOperateLogService.addUserRecord(request, Integer.valueOf(user_id));//
				// 添加用户操作日志
				// log.info(user_id + "小程序登录成功...");
				logger.info("小程序登录成功,code={}", code);

				return new ResultMessage(StatusCode.OK, "请求成功", data).toString();
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			return new ResultMessage(StatusCode.ERROR, "小程序登录失败", data).toString();
		} finally {
			loginService.publish4oms(request, 1, user);
		}
	}
}
