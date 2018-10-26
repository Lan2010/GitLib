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

import com.tianzhixing.app.exception.GrpcException;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.Page;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.StarService;
import com.tianzhixing.app.service.UserService;
/**
 * 收取星星、星星排行榜、收取星星的明细等接口
 */
@Controller
public class StarController {
	private static Logger log = LoggerFactory.getLogger(StarController.class);

	@Resource
	private StarService starService;
	@Resource
	private UserService userService;

	/**
	 * 星星排行榜
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/account/ranking", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String ranking(HttpServletResponse response, BaseRequest baseRequest) throws SQLException, GrpcException {
		ResultMessage result = starService.ranking();
		return result.toString();
	}

	/**
	 * 用户星星流水信息
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/account/records", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String records(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String pageindex = baseRequest.getParameter("pageindex");
		String pagesize = baseRequest.getParameter("pagesize");
		Page page = new Page(pageindex, pagesize);
		ResultMessage result = starService.records(userId, page);
		return result.toString();

	}

	/**
	 * 获得待收取星星信息
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/account/uncollection", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String unCollection(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		ResultMessage result = starService.unCollection(userId);
		return result.toString();
	}

	/**
	 * 收取任务星星
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws GrpcException
	 * @throws SQLException
	 */
	@RequestMapping(value = "/account/collection", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String collection(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws GrpcException, SQLException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String recordToken = baseRequest.getParameter("recordToken");// "记录Token"
		String advertId = baseRequest.getParameter("advertId");// "广告的ID"
		String taskId = baseRequest.getParameter("taskId");// "任务的ID，非任务星星不需要填写"
		ResultMessage result = starService.collection(userId, advertId, recordToken, taskId);
		return result.toString();
	}

	/**
	 * 收取广告星星
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @return
	 * @throws NumberFormatException
	 * @throws SQLException
	 * @throws GrpcException
	 */
	@RequestMapping(value = "/account/clickAd", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String clickAd(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest)
			throws NumberFormatException, SQLException, GrpcException {
		Integer userId = (Integer) request.getAttribute("user_id");
		String advertId = baseRequest.getParameter("advertId");// "广告的ID"
		String starPoint = baseRequest.getParameter("starPoint");// "星点数"
		String adStarPointType = baseRequest.getParameter("adStarPointType");// 收取的星星类型,浏览为0 1为点击
		if (adStarPointType.isEmpty()) {
			adStarPointType = "0";
		}
		ResultMessage result = starService.clickAd(request, userId, advertId, starPoint,
				Integer.parseInt(adStarPointType));
		return result.toString();
	}

	/**
	 * 用于获取到随机的广告星星
	 * 
	 * @param request
	 * @param response
	 * @param baseRequest
	 * @throws SQLException 
	 */
	@RequestMapping(value = "/ad/getLocationStar", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	@ResponseBody
	public String getLocationStar(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) throws SQLException {
			String code = baseRequest.getParameter("code");// "城市代码"
			String lat = baseRequest.getParameter("lat");// "纬度"
			String lng = baseRequest.getParameter("lng");// "广告的经度"
			String m = baseRequest.getParameter("m");// "距离米"
			if (StringUtils.isEmpty(code)) {
				code = "340";
			}
			if (StringUtils.isEmpty(lat)) {
				lat = "0";
			}
			if (StringUtils.isEmpty(lng)) {
				lng = "0";
			}
			if (StringUtils.isEmpty(m)) {
				m = "0";
			}
			ResultMessage result = starService.getLocationStar(code, lat, lng, m);
			return result.toString();

	}
}
