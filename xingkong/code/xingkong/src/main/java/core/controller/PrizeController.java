package core.controller;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;

import core.common.StatusCode;
import core.pojo.Prize;
import core.pojo.ResultMessage;
import core.service.PrizeService;

@Controller
public class PrizeController {
	@Autowired
	private PrizeService prizeService;

	private static Logger log = LoggerFactory.getLogger(PrizeController.class);

	/**
	 * 获取中奖纪录，分页处理
	 * @param request
	 * @param response
	 * @param page
	 *            当前页
	 * @param pageSize
	 *            不同日期的个数作为分页尺寸（例如，想要获取2018年第一周，第二周，第三周的数据，则pageSize为3）
	 * @return
	 */
	@RequestMapping(value = "/api/prize/pagePrizes", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String pagePrizes(HttpServletRequest request, HttpServletResponse response, Integer page,
			Integer pageSize) {
		String userId = (String) request.getAttribute("userId");
		JSONArray jSONArray = new JSONArray();
		try {
			// 查找中奖用户总数
			int count = prizeService.selectPrizesCount();
			if (count <= 0) {
				return new ResultMessage(StatusCode.OK, "请求成功", 0, jSONArray).toString();
			}
			// 计算总页数
			// 查询的起始页数
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("pageSize", pageSize);// 当前显示的记录数
			map.put("page", page);// 当前显示的记录数
			jSONArray = prizeService.selectPrizesByPage(map);
			if (jSONArray != null) {
				return new ResultMessage(StatusCode.OK, "请求成功", count, jSONArray).toString();
			} else {
				return new ResultMessage(StatusCode.OK, "请求成功", 0, jSONArray).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	/**
	 * 获取用户个人中奖纪录
	 * @param request
	 * @param response
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/api/prize/individualPrizes", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String individualPrizes(HttpServletRequest request, HttpServletResponse response, Integer page,
			Integer pageSize) {
		String userId = (String) request.getAttribute("userId");
		//String userId = "6";
		try {
			JSONArray prizes = prizeService.individualPrizes(Integer.valueOf(userId));
			if (prizes == null || prizes.isEmpty()) {
				return new ResultMessage(StatusCode.OK, "请求成功", new ArrayList<Prize>()).toString();
			} else {
				return new ResultMessage(StatusCode.OK, "请求成功", prizes).toString();
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.NUMBER_FORMAT_EXCEPTION, "系统繁忙~").toString();
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}

	/**
	 * 获取所有用户中奖纪录
	 * @param request
	 * @param response
	 * @param page
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/api/prize/allPrizes", produces = "application/json; charset=utf-8", method = RequestMethod.GET)
	@ResponseBody
	public String allPrizes(HttpServletRequest request, HttpServletResponse response, Integer page, Integer pageSize) {
		try {
			JSONArray prizes = prizeService.allPrizes();
			if (prizes == null || prizes.isEmpty()) {
				return new ResultMessage(StatusCode.OK, "请求成功", new ArrayList<Prize>()).toString();
			} else {
				return new ResultMessage(StatusCode.OK, "请求成功", prizes).toString();
			}
		} catch (SQLException e) {
			e.printStackTrace();
			log.error(e.getMessage());
			return new ResultMessage(StatusCode.SQL_EXCEPTION, "系统繁忙~").toString();
		}
	}
}
