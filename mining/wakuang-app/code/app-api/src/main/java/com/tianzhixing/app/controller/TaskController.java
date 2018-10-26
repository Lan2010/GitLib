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

import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.TaskService;

/**
 * 用户任务相关接口
 */
@Controller
public class TaskController {
	private static Logger log = LoggerFactory.getLogger(TaskController.class);

	@Resource
	private TaskService taskService;

	/**
	 * 获取到我的未开始任务或者我的进行中的任务
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/task.getMyTask", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getMyTask(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws NumberFormatException, SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String pageindex = baseRequest.getParameter("pageindex");
		String pagesize = baseRequest.getParameter("pagesize");
		Page page = new Page(pageindex, pagesize);
		String type = baseRequest.getParameter("type");// '1 是完成的任务 2 是进行中的任务'
		if (StringUtils.isEmpty(type)) {
			type = "1";
		}
		ResultMessage result = taskService.getMyTask(userId, page, Integer.parseInt(type));
		return result.toString();
	}

	/**
	 * 获取需要提醒任务列表
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/task.getRemindAd", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getRemindAd(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String lat = baseRequest.getParameter("lat");// 当前的纬度
		String lng = baseRequest.getParameter("lng");// 当前的经度
		if (StringUtils.isEmpty(lat)) {
			lat = "0";
		}
		if (StringUtils.isEmpty(lng)) {
			lng = "0";
		}
		ResultMessage result = taskService.getRemindAd(userId, lat, lng);
		return result.toString();
	}

	/**
	 * 获取到当前的任务列表
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 */
	@RequestMapping(value = "/task.getTask", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getTask(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String pageindex = baseRequest.getParameter("pageindex");
		String pagesize = baseRequest.getParameter("pagesize");
		Page page = new Page(pageindex, pagesize);
		String lat = baseRequest.getParameter("lat");// 当前的纬度
		String lng = baseRequest.getParameter("lng");// 当前的经度
		String mi = baseRequest.getParameter("mi");// 距离单位米
		String cityCode = baseRequest.getParameter("cityCode");// 城市代码
		if (StringUtils.isEmpty(lat)) {
			lat = "0";
		}
		if (StringUtils.isEmpty(lng)) {
			lng = "0";
		}
		if (StringUtils.isEmpty(cityCode)) {
			cityCode = "340";
		}
		ResultMessage result = taskService.getTask(userId, page, lat, lng, cityCode);
		return result.toString();
	}

	/**
	 * 用户领取任务接口
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/task.userAcceptTask", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String userAcceptTask(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String orderNO = baseRequest.getParameter("orderNO"); // 任务的唯一标识
		String tl_id = baseRequest.getParameter("tl_id");// 地址的ID
		if (StringUtils.isEmpty(orderNO) || StringUtils.isEmpty(tl_id)) {
			return new ResultMessage(ReturnCode.FAILED, "参数缺失").toString();
		}
		Short mt = baseRequest.getMt();// 终端类型（1：PC 2：安卓 3：IOS 4：微信）
		ResultMessage result = taskService.userAcceptTask(request, userId, orderNO, tl_id, mt);
		return result.toString();
	}
}
