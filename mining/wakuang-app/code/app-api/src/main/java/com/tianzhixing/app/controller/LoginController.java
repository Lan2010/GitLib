package com.tianzhixing.app.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.UserService;
import com.tianzhixing.app.util.CommonUtils;

import io.swagger.annotations.ApiOperation;

/**
 * 用户普通登录、短信登录、退出登录的操作
 * 
 * @author dev-teng
 * @date 2018年7月31日
 */
@Controller
public class LoginController {
	@Resource
	private UserService userService;

	/**
	 * 用户普通方式（手机号+密码）登录
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 */
	@ApiOperation(value="用户普通方式登录",notes = "用户输入手机号、密码登录app")
	@RequestMapping(value = "/user/login", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		ResultMessage rm;
		User user = new User();
		try {
			Short terminal = baseRequest.getMt();
			user.setPhone(baseRequest.getParameter("phone"));
			user.setPassword(baseRequest.getParameter("password"));
			user.setRegTerminal(terminal != null ? terminal : 3);
			user.setRegIp(CommonUtils.getIpAddr(request));
			rm = userService.login(user, baseRequest);
			return rm.toString();
		} finally {
			userService.pushLogin2Nats(user);
		}
	}

	/**
	 * 手机短信验证码方式登录
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@ApiOperation(value="用户短信验证码方式登录",notes = "用户输入手机号、短信验证码登录app")
	@RequestMapping(value = "/user/smsLogin", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
	@ResponseBody
	public String smsLogin(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		ResultMessage rm;
		User user = new User();
		try {
			Short terminal = baseRequest.getMt();
			user.setPhone(baseRequest.getParameter("phone"));
			user.setSmsCode(baseRequest.getParameter("smsCode"));
			user.setRegIp(CommonUtils.getIpAddr(request));
			user.setRegTerminal(terminal != null ? terminal : 3);
			rm = userService.smsLogin(user, baseRequest);
			return rm.toString();
		} finally {
			userService.pushLogin2Nats(user);
		}
	}

	/**
	 * 退出登录
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 */
	@ApiOperation(value="用户退出登录",notes = "用户退出登录，清除相关redis键值")
	@RequestMapping(value = "/user/logout", produces = "application/json; charset=utf-8",method = RequestMethod.POST)
	@ResponseBody
	public String logout(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) {
		Long result = userService.logout(request, baseRequest);
		ResultMessage rm;
		try {
			if (result > 0) {
				rm = new ResultMessage(ReturnCode.OK, "退出登录成功");
				return rm.toString();
			} else {
				rm = new ResultMessage(ReturnCode.FAILED, "退出登录失败");
			}
			return rm.toString();
		} finally {
			// nats
			userService.pushLogout2Nats(request);
		}

	}

}
