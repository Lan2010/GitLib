package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.service.UserGreeterCardStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserPostCardStatisticsService;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserGreeterCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserPostCardStatisticsMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/statistics")
public class UserCardStatisticsController extends BaseController {

	@Autowired
	private UserGreeterCardStatisticsService userGreeterCardStatisticsService;
	
	@Autowired
	private UserPostCardStatisticsService userPostCardStatisticsService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/userCard/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/userCard");
	}

	@RequestMapping(value = "/userGreeterCard/chart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("type") int type) {
		List<String> dates = new ArrayList<>();
		List<Integer> chartLineVos = new ArrayList<>();
		List<Integer> chartLineDiffIPVos = new ArrayList<>();
		Integer maxDiffIPValue = Integer.MIN_VALUE;
		Integer maxValue = Integer.MIN_VALUE;
		List<Integer> aosList = new ArrayList<>();
		List<Integer> aosDiffIPList = new ArrayList<>();
		List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers = userGreeterCardStatisticsService.list(type);
		for (UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper : userGreeterCardStatisticsMappers) {
			String date = null;
			if (0 == type) {
				date = userGreeterCardStatisticsMapper.getStatisticsYear() + "-" + userGreeterCardStatisticsMapper.getStatisticsMonth() + "-" + userGreeterCardStatisticsMapper.getStatisticsDay() + " " + userGreeterCardStatisticsMapper.getStatisticsHour();
			} else if (1 == type) {
				// 获取天
				date = userGreeterCardStatisticsMapper.getStatisticsYear() + "-" + userGreeterCardStatisticsMapper.getStatisticsMonth() + "-" + userGreeterCardStatisticsMapper.getStatisticsDay();
			} else if (2 == type) {
				date = userGreeterCardStatisticsMapper.getStatisticsYear() + "-" + userGreeterCardStatisticsMapper.getStatisticsMonth();
			}
			if (!dates.contains(date)) {
				dates.add(date);
			}

			aosList.add(userGreeterCardStatisticsMapper.getCreateCount());
			aosDiffIPList.add(userGreeterCardStatisticsMapper.getShareCount());
			if (maxDiffIPValue < userGreeterCardStatisticsMapper.getShareCount()) {
				maxDiffIPValue = userGreeterCardStatisticsMapper.getShareCount();
			}
			if (maxValue < userGreeterCardStatisticsMapper.getCreateCount()) {
				maxValue = userGreeterCardStatisticsMapper.getCreateCount();
			}
			chartLineVos.add(userGreeterCardStatisticsMapper.getCreateCount());
			chartLineDiffIPVos.add(userGreeterCardStatisticsMapper.getShareCount());
		}
		List list = new ArrayList();
		list.add(chartLineVos);
		list.add(chartLineDiffIPVos);
		list.add(maxValue);
		list.add(maxDiffIPValue);
		return responseEntity(200, dates, "request.suc", 0, list);
	}
	
	@RequestMapping(value = "/userPostCard/postCardChart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity postCardChart(@RequestParam("type") int type) {
		List<String> dates = new ArrayList<>();
		List<Integer> chartLineVos = new ArrayList<>();
		List<Integer> chartLineDiffIPVos = new ArrayList<>();
		Integer maxDiffIPValue = Integer.MIN_VALUE;
		Integer maxValue = Integer.MIN_VALUE;
		List<Integer> aosList = new ArrayList<>();
		List<Integer> aosDiffIPList = new ArrayList<>();
		List<UserPostCardStatisticsMapper> userPostCardStatisticsMappers = userPostCardStatisticsService.list(type);
		for (UserPostCardStatisticsMapper userPostCardStatisticsMapper : userPostCardStatisticsMappers) {
			String date = null;
			if (0 == type) {
				date = userPostCardStatisticsMapper.getStatisticsYear() + "-" + userPostCardStatisticsMapper.getStatisticsMonth() + "-" + userPostCardStatisticsMapper.getStatisticsDay() + " " + userPostCardStatisticsMapper.getStatisticsHour();
			} else if (1 == type) {
				// 获取天
				date = userPostCardStatisticsMapper.getStatisticsYear() + "-" + userPostCardStatisticsMapper.getStatisticsMonth() + "-" + userPostCardStatisticsMapper.getStatisticsDay();
			} else if (2 == type) {
				date = userPostCardStatisticsMapper.getStatisticsYear() + "-" + userPostCardStatisticsMapper.getStatisticsMonth();
			}
			if (!dates.contains(date)) {
				dates.add(date);
			}

			aosList.add(userPostCardStatisticsMapper.getCreateCount());
			aosDiffIPList.add(userPostCardStatisticsMapper.getShareCount());
			if (maxDiffIPValue < userPostCardStatisticsMapper.getShareCount()) {
				maxDiffIPValue = userPostCardStatisticsMapper.getShareCount();
			}
			if (maxValue < userPostCardStatisticsMapper.getCreateCount()) {
				maxValue = userPostCardStatisticsMapper.getCreateCount();
			}
			chartLineVos.add(userPostCardStatisticsMapper.getCreateCount());
			chartLineDiffIPVos.add(userPostCardStatisticsMapper.getShareCount());
		}
		List list = new ArrayList();
		list.add(chartLineVos);
		list.add(chartLineDiffIPVos);
		list.add(maxValue);
		list.add(maxDiffIPValue);
		return responseEntity(200, dates, "request.suc", 0, list);
	}
}
