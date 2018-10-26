package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserStarPointIncrementStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserStarPointIncrementStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.utils.ListUtils;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointIncrementStatisticsMapper;
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
public class UserStarPointIncrementStatisticsController extends BaseController {

	@Autowired
	private UserStarPointIncrementStatisticsService userStarPointIncrementStatisticsService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/userStarPointIncrement/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/userStarPointIncrement");
	}

	@RequestMapping(value = "/userStarPointIncrement/chart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("type") int type) {
		List<UserStarPointIncrementStatisticsMapping> userStarPointIncrementStatisticsMappings = userStarPointIncrementStatisticsService.list(type);
		// labels
		List<String> labels = new ArrayList<>();
		List<Double> totalCountList = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		List<ChartLineVo> chartLineVos = new ArrayList<>();
		Double maxValue = 0.0d;
		for (UserStarPointIncrementStatisticsMapping userStarPointIncrementStatisticsMapping : userStarPointIncrementStatisticsMappings) {
			ApplicationDimensionMapper applicationDimensionMapper = userStarPointIncrementStatisticsMapping.getApplicationDimensionMapper();
			labels.add(applicationDimensionMapper.getName());
			List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = userStarPointIncrementStatisticsMapping.getUserStarPointIncrementStatisticsMappers();
			Double appStartTotalCount = 0.0d;
			List<Double> aosList = new ArrayList<>();
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : userStarPointIncrementStatisticsMappers) {

				String date = null;
				if (0 == type) {
					date = userStarPointIncrementStatisticsMapper.getStatisticsYear() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsMonth() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsDay() + " " + userStarPointIncrementStatisticsMapper.getStatisticsHour();
				} else if (1 == type) {
					// 获取天
					date = userStarPointIncrementStatisticsMapper.getStatisticsYear() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsMonth() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsDay();
				} else if (2 == type) {
					date = userStarPointIncrementStatisticsMapper.getStatisticsYear() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsMonth();
				}
				if (!dates.contains(date)) {
					dates.add(date);
				}

				appStartTotalCount += userStarPointIncrementStatisticsMapper.getIncrementCount();
				aosList.add(userStarPointIncrementStatisticsMapper.getIncrementCount());
				if (maxValue < userStarPointIncrementStatisticsMapper.getIncrementCount()) {
					maxValue = userStarPointIncrementStatisticsMapper.getIncrementCount();
				}
			}
			totalCountList.add(appStartTotalCount);
			chartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosList));
		}
		List list = new ArrayList();
		list.add(labels);
		list.add(totalCountList);
		list.add(chartLineVos);
		list.add(maxValue);
		return responseEntity(200, dates, "request.suc", 0, list);
	}

	@RequestMapping(value = "/userStarPointIncrement/lineChart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity lineChart(@RequestParam("type") int type) {
		List<UserStarPointIncrementStatisticsMapping> userStarPointIncrementStatisticsMappings = userStarPointIncrementStatisticsService.listByIncrementType(type);
		// labels
		List<String> labels = new ArrayList<>();
		List<Double> totalCountList = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		List<ChartLineVo> chartLineVos = new ArrayList<>();
		Double maxValue = 0.0d;
		for (UserStarPointIncrementStatisticsMapping userStarPointIncrementStatisticsMapping : userStarPointIncrementStatisticsMappings) {
			Integer incrementType = userStarPointIncrementStatisticsMapping.getIncrementType();
			String incrementTypeName = "";
			if (incrementType == 0) {
				incrementTypeName = "掉落";
			} else if (incrementType == 1) {
				incrementTypeName = "任务";
			} else if (incrementType == 2) {
				incrementTypeName = "广告";
			}
			labels.add(incrementTypeName);
			List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = userStarPointIncrementStatisticsMapping.getUserStarPointIncrementStatisticsMappers();
			Double appStartTotalCount = 0.0d;
			List<Double> aosList = new ArrayList<>();
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : userStarPointIncrementStatisticsMappers) {

				String date = null;
				if (0 == type) {
					date = userStarPointIncrementStatisticsMapper.getStatisticsYear() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsMonth() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsDay() + " " + userStarPointIncrementStatisticsMapper.getStatisticsHour();
				} else if (1 == type) {
					// 获取天
					date = userStarPointIncrementStatisticsMapper.getStatisticsYear() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsMonth() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsDay();
				} else if (2 == type) {
					date = userStarPointIncrementStatisticsMapper.getStatisticsYear() + "-" + userStarPointIncrementStatisticsMapper.getStatisticsMonth();
				}
				if (!dates.contains(date)) {
					dates.add(date);
				}

				appStartTotalCount += userStarPointIncrementStatisticsMapper.getIncrementCount();
				aosList.add(userStarPointIncrementStatisticsMapper.getIncrementCount());
				if (maxValue < userStarPointIncrementStatisticsMapper.getIncrementCount()) {
					maxValue = userStarPointIncrementStatisticsMapper.getIncrementCount();
				}
			}
			totalCountList.add(appStartTotalCount);
			chartLineVos.add(new ChartLineVo(incrementTypeName, true, aosList));
		}
		List list = new ArrayList();
		list.add(labels);
		list.add(totalCountList);
		list.add(chartLineVos);
		list.add(maxValue);
		return responseEntity(200, dates, "request.suc", 0, list);
	}

	@RequestMapping(value = "/userStarPointIncrement/detailChart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity detailChart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("type") int type) {
		List<String> labels = new ArrayList<>();
		List<Double> incrementCountDownList = new ArrayList<>();
		List<Double> incrementCountTaskList = new ArrayList<>();
		List<Double> incrementCountAdvList = new ArrayList<>();
		List<ApplicationDimensionMapper> appMappers = userStarPointIncrementStatisticsService.appList();
		int count = appMappers.size();
		for (ApplicationDimensionMapper applicationDimensionMapper : appMappers) {
			labels.add(applicationDimensionMapper.getName());
			List<UserStarPointIncrementStatisticsMapper> UserStarPointIncrementStatisticsMappers = userStarPointIncrementStatisticsService.listByType(type,applicationDimensionMapper.getName());
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : UserStarPointIncrementStatisticsMappers) {
				if (userStarPointIncrementStatisticsMapper.getIncrementType()==0) {
					incrementCountDownList.add(userStarPointIncrementStatisticsMapper.getIncrementCount());
				}
				if (userStarPointIncrementStatisticsMapper.getIncrementType()==1) {
					incrementCountTaskList.add(userStarPointIncrementStatisticsMapper.getIncrementCount());
				}
				if (userStarPointIncrementStatisticsMapper.getIncrementType()==2) {
					incrementCountAdvList.add(userStarPointIncrementStatisticsMapper.getIncrementCount());
				}
			}
		}
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo-1);
		List<List<Double>> pagelist1 = ListUtils.pagingDouble(incrementCountDownList, pageSize);
		List<Double> incrementCountDown = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Double>> pagelist2 = ListUtils.pagingDouble(incrementCountTaskList, pageSize);
		List<Double> incrementCountTask = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List<List<Double>> pagelist3 = ListUtils.pagingDouble(incrementCountAdvList, pageSize);
		List<Double> incrementCountAdv = pagelist3.size() == 0 ? null : pagelist3.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(incrementCountDown);
		list.add(incrementCountTask);
		list.add(incrementCountAdv);
		return responseEntity(200, "request.suc", count, list);
	}
}
