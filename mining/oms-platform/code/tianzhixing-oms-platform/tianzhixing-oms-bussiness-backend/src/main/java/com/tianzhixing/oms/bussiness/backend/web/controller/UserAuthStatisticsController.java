package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserAuthStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserAuthStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthStatisticsMapper;
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
public class UserAuthStatisticsController extends BaseController {

	@Autowired
	private UserAuthStatisticsService userAuthStatisticsService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/userAuth/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/userAuth");
	}

	@RequestMapping(value = "/userAuth/chart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("type") int type) {
		List<UserAuthStatisticsMapping> userAuthStatisticsMappings = userAuthStatisticsService.list(type);
		// labels
		List<String> labels = new ArrayList<>();
		List<Integer> totalCountList = new ArrayList<>();
		List<Integer> diffIPCountList = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		List<ChartLineVo> chartLineVos = new ArrayList<>();
		List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
		Integer maxDiffIPValue = Integer.MIN_VALUE;
		Integer maxValue = Integer.MIN_VALUE;
		for (UserAuthStatisticsMapping userAuthStatisticsMapping : userAuthStatisticsMappings) {
			ApplicationDimensionMapper applicationDimensionMapper = userAuthStatisticsMapping.getApplicationDimensionMapper();
			labels.add(applicationDimensionMapper.getName());
			List<UserAuthStatisticsMapper> userAuthStatisticsMappers = userAuthStatisticsMapping.getUserAuthStatisticsMappers();
			Integer appStartTotalCount = 0;
			Integer appStartDiffIPTotalCount = 0;
			List<Integer> aosList = new ArrayList<>();
			List<Integer> aosDiffIPList = new ArrayList<>();
			for (UserAuthStatisticsMapper userAuthStatisticsMapper : userAuthStatisticsMappers) {

				String date = null;
				if (0 == type) {
					date = userAuthStatisticsMapper.getStatisticsYear() + "-" + userAuthStatisticsMapper.getStatisticsMonth() + "-" + userAuthStatisticsMapper.getStatisticsDay() + " " + userAuthStatisticsMapper.getStatisticsHour();
				} else if (1 == type) {
					// 获取天
					date = userAuthStatisticsMapper.getStatisticsYear() + "-" + userAuthStatisticsMapper.getStatisticsMonth() + "-" + userAuthStatisticsMapper.getStatisticsDay();
				} else if (2 == type) {
					date = userAuthStatisticsMapper.getStatisticsYear() + "-" + userAuthStatisticsMapper.getStatisticsMonth();
				}
				if (!dates.contains(date)) {
					dates.add(date);
				}

				appStartTotalCount += userAuthStatisticsMapper.getAuthCount();
				appStartDiffIPTotalCount += userAuthStatisticsMapper.getSucAuthCount();
				aosList.add(userAuthStatisticsMapper.getAuthCount());
				aosDiffIPList.add(userAuthStatisticsMapper.getSucAuthCount());
				if (maxDiffIPValue < userAuthStatisticsMapper.getSucAuthCount()) {
					maxDiffIPValue = userAuthStatisticsMapper.getSucAuthCount();
				}
				if (maxValue < userAuthStatisticsMapper.getAuthCount()) {
					maxValue = userAuthStatisticsMapper.getAuthCount();
				}
			}
			totalCountList.add(appStartTotalCount);
			diffIPCountList.add(appStartDiffIPTotalCount);
			chartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosList));
			chartLineDiffIPVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosDiffIPList));
		}
		List list = new ArrayList();
		list.add(labels);
		list.add(totalCountList);
		list.add(diffIPCountList);
		list.add(chartLineVos);
		list.add(chartLineDiffIPVos);
		list.add(maxValue);
		list.add(maxDiffIPValue);
		return responseEntity(200, dates, "request.suc", 0, list);
	}
	
	@RequestMapping(value = "/userAuth/lineChart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity lineChart(@RequestParam("type") int type) {
		List<UserAuthStatisticsMapping> userAuthStatisticsMappings = userAuthStatisticsService.listByAuthType(type);
		// labels
		List<String> labels = new ArrayList<>();
		List<Integer> totalCountList = new ArrayList<>();
		List<Integer> diffIPCountList = new ArrayList<>();
		List<String> dates = new ArrayList<>();
		List<ChartLineVo> chartLineVos = new ArrayList<>();
		List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
		Integer maxDiffIPValue = Integer.MIN_VALUE;
		Integer maxValue = Integer.MIN_VALUE;
		for (UserAuthStatisticsMapping userAuthStatisticsMapping : userAuthStatisticsMappings) {
			UserAuthDimensionMapper userAuthDimensionMapper = userAuthStatisticsMapping.getUserAuthDimensionMapper();
			labels.add(userAuthDimensionMapper.getName());
			List<UserAuthStatisticsMapper> userAuthStatisticsMappers = userAuthStatisticsMapping.getUserAuthStatisticsMappers();
			Integer appStartTotalCount = 0;
			Integer appStartDiffIPTotalCount = 0;
			List<Integer> aosList = new ArrayList<>();
			List<Integer> aosDiffIPList = new ArrayList<>();
			for (UserAuthStatisticsMapper userAuthStatisticsMapper : userAuthStatisticsMappers) {

				String date = null;
				if (0 == type) {
					date = userAuthStatisticsMapper.getStatisticsYear() + "-" + userAuthStatisticsMapper.getStatisticsMonth() + "-" + userAuthStatisticsMapper.getStatisticsDay() + " " + userAuthStatisticsMapper.getStatisticsHour();
				} else if (1 == type) {
					// 获取天
					date = userAuthStatisticsMapper.getStatisticsYear() + "-" + userAuthStatisticsMapper.getStatisticsMonth() + "-" + userAuthStatisticsMapper.getStatisticsDay();
				} else if (2 == type) {
					date = userAuthStatisticsMapper.getStatisticsYear() + "-" + userAuthStatisticsMapper.getStatisticsMonth();
				}
				if (!dates.contains(date)) {
					dates.add(date);
				}

				appStartTotalCount += userAuthStatisticsMapper.getAuthCount();
				appStartDiffIPTotalCount += userAuthStatisticsMapper.getSucAuthCount();
				aosList.add(userAuthStatisticsMapper.getAuthCount());
				aosDiffIPList.add(userAuthStatisticsMapper.getSucAuthCount());
				if (maxDiffIPValue < userAuthStatisticsMapper.getSucAuthCount()) {
					maxDiffIPValue = userAuthStatisticsMapper.getSucAuthCount();
				}
				if (maxValue < userAuthStatisticsMapper.getAuthCount()) {
					maxValue = userAuthStatisticsMapper.getAuthCount();
				}
			}
			totalCountList.add(appStartTotalCount);
			diffIPCountList.add(appStartDiffIPTotalCount);
			chartLineVos.add(new ChartLineVo(userAuthDimensionMapper.getName(), true, aosList));
			chartLineDiffIPVos.add(new ChartLineVo(userAuthDimensionMapper.getName(), true, aosDiffIPList));
		}
		List list = new ArrayList();
		list.add(labels);
		list.add(totalCountList);
		list.add(diffIPCountList);
		list.add(chartLineVos);
		list.add(chartLineDiffIPVos);
		list.add(maxValue);
		list.add(maxDiffIPValue);
		return responseEntity(200, dates, "request.suc", 0, list);
	}
}
