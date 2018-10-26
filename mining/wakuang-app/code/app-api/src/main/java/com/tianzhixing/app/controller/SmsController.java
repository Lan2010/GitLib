package com.tianzhixing.app.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.pojo.User;
import com.tianzhixing.app.service.SmsService;
import com.tianzhixing.app.service.UserService;

/**
 * 短信操作相关接口
 * 
 * @author dev-teng
 * @date 2018年7月31日
 */
@Controller
public class SmsController {
	private static Logger log = LoggerFactory.getLogger(SmsController.class);
	@Resource
	private UserService userService;
	@Resource
	private SmsService smsService;

	/**
	 * 获取手机验证码。
	 * 用户点击“获取验证码”按钮，向短信服务层（grpc实现）发送请求，返回结果
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/sms/loginPhoneCode", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String loginPhoneCode(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		ResultMessage resultMessage;
		String phone = baseRequest.getParameter("phone");
		User user = userService.getByPhone(phone);
		if (user == null) {
			resultMessage = new ResultMessage(1, "该账户未注册过");
		} else {
			resultMessage = smsService.sendSms(phone, "login");
		}
		return resultMessage.toString();
	}

	/**
	 * 注册时，发送请求，获取手机短信验证码
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/sms/regCode", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String regCode(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		ResultMessage resultMessage = null;
		String phone = baseRequest.getParameter("phone");
		if (phone == null) {
			resultMessage = new ResultMessage(1, "手机号不正确");
		} else {
			User user = userService.getByPhone(phone);
			if (user != null) {
				resultMessage = new ResultMessage(1, "已经注册过了");
			} else {
				resultMessage = smsService.sendSms(phone, "reg");
			}
		}
		return resultMessage.toString();
	}

	/**
	 * 找回密码时，发送短信，获取验证码
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException 
	 * @throws GrpcException 
	 */
	@RequestMapping(value = "/sms/getBackPwd", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String getBackPwd(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) throws SQLException, GrpcException {
		ResultMessage resultMessage;
		String phone = baseRequest.getParameter("phone");
		User user = userService.getByPhone(phone);
		if (user == null) {
			resultMessage = new ResultMessage(1, "该账户未注册过");
		} else {
			resultMessage = smsService.sendSms(phone, "find");
		}
		return resultMessage.toString();
	}

}
