package com.tianzhixing.app.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianzhixing.app.exception.BadRequestException;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.ChargeService;

/**
 * 充电宝绑定、解绑、列表展示有关的接口
 * @date 2018年7月31日
 */
@Controller
public class ChargeController {
	private static Logger log = LoggerFactory.getLogger(ChargeController.class);

	@Resource
	private ChargeService chargeService;

	/**
	 * 绑定设备
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws BadRequestException
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/charge.bind", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String bind(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws BadRequestException, SQLException, GrpcException {
		ResultMessage result = new ResultMessage();
		Integer userId = (Integer) request.getAttribute("user_id");
		String Mac = baseRequest.getParameter("Mac"); // Mac地址
		if (StringUtils.isEmpty(Mac)) {
			throw new BadRequestException(new ResultMessage(4000, "Mac字段为空").toString());
		}
		Short mt = baseRequest.getMt();// 终端类型（1：PC 2：安卓 3：IOS 4：微信）
		result = chargeService.bind(request, userId, Mac, mt);
		return result.toString();
	}

	/**
	 * 解绑操作
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws BadRequestException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/charge.unbindCharge", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String unbindCharge(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws BadRequestException, SQLException {
		ResultMessage result = new ResultMessage();
		Integer userId = (Integer) request.getAttribute("user_id");
		String Mac = baseRequest.getParameter("Mac"); // Mac地址
		if (StringUtils.isEmpty(Mac)) {
			throw new BadRequestException(new ResultMessage(4000, "Mac字段为空").toString());
		}
		result = chargeService.unbindCharge(userId, Mac);
		return result.toString();

	}

	/**
	 * 设备列表
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/charge.chargeList", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String chargeList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		ResultMessage result = new ResultMessage();
		Integer userId = (Integer) request.getAttribute("user_id");
		result = chargeService.chargeList(userId);
		return result.toString();
	}
}
