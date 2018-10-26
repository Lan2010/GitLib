package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.service.StatisticsDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.TaskDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserTaskStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.utils.ListUtils;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.TaskDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserTaskStatisticsMapper;
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
public class UserTaskStatisticsController extends BaseController {

	@Autowired
	private UserTaskStatisticsService userTaskStatisticsService;

	@Autowired
	private StatisticsDimensionService statisticsDimensionService;

	@Autowired
	private TaskDimensionService taskDimensionService;
	
	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/userTask/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/userTask");
	}

	@RequestMapping(value = "/userTask/chart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
		long count = userTaskStatisticsService.count();
		// labels
		List<String> labels = new ArrayList<>();
		List<Integer> acceptCountList = new ArrayList<>();
		List<Integer> cancelCountList = new ArrayList<>();
		List<TaskDimensionMapper> taskDimensionMappers = taskDimensionService.list(true);
		for (TaskDimensionMapper taskDimensionMapper : taskDimensionMappers) {
			labels.add(taskDimensionMapper.getName());
			List<UserTaskStatisticsMapper> userTaskStatisticsMappers = userTaskStatisticsService.list(taskDimensionMapper.getTaskId());
			for (UserTaskStatisticsMapper userTaskStatisticsMapper : userTaskStatisticsMappers) {
				acceptCountList.add(userTaskStatisticsMapper.getAcceptCount());
				cancelCountList.add(userTaskStatisticsMapper.getCancelCount());
			}
		}
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo - 1);
		List<List<Integer>> pagelist1 = ListUtils.pagingInt(acceptCountList, pageSize);
		List<Integer> acceptCount = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Integer>> pagelist2 = ListUtils.pagingInt(cancelCountList, pageSize);
		List<Integer> cancelCount = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(acceptCount);
		list.add(cancelCount);
		return responseEntity(200, "request.suc", count, list);
	}

	@RequestMapping(value = "/userTask/scatter", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity scatterChart(@RequestParam("type") int type) {
		List<String> dates = new ArrayList<>();
		List<ChartLineVo> chartLineVos = new ArrayList<>();
		List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
		Integer maxValue = 0;
		Integer maxDiffIPValue = 0;
		Map<Long, ApplicationDimensionMapper> applicationDimensionMapperMap = new HashMap<>();
		List<ApplicationDimensionMapper> applicationDimensionMappers = statisticsDimensionService.list(true);
		for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
			applicationDimensionMapperMap.put(applicationDimensionMapper.getId(), applicationDimensionMapper);
		}
		List<TaskDimensionMapper> taskDimensionMappers = taskDimensionService.list(true);
		for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
			// 如果为平台，且存在子类，则跳出
			boolean breakFor = false;
			if (StatisticsDimension.PLATFORM.equals(applicationDimensionMapper.getStatisticsDimension())) {
				for (ApplicationDimensionMapper adm : applicationDimensionMappers) {
					if (adm.getParentId() == applicationDimensionMapper.getId()) {
						breakFor = true;
						break;
					}
				}
			}
			if (breakFor)
				continue;
			String appName = applicationDimensionMapper.getName();
			for (TaskDimensionMapper taskDimensionMapper : taskDimensionMappers) {
				ApplicationDimensionMapper applicationDimensionMapperParent = applicationDimensionMapper;
				if (StatisticsDimension.CLIENT.equals(applicationDimensionMapperParent.getStatisticsDimension())) {
					applicationDimensionMapperParent = applicationDimensionMapperMap.get(applicationDimensionMapperParent.getParentId());
					if (applicationDimensionMapperParent == null) {
						applicationDimensionMapperParent = statisticsDimensionService.getById(applicationDimensionMapper.getParentId());
						applicationDimensionMapperMap.put(applicationDimensionMapperParent.getId(), applicationDimensionMapperParent);
					}
					applicationDimensionMapper.setName(applicationDimensionMapperParent.getName() + "-" + appName);
				}
				String channelName = applicationDimensionMapper.getName() + "(" + taskDimensionMapper.getName() + ")";
				String name = applicationDimensionMapper.getName() + "(" + taskDimensionMapper.getName() + ")";
				List<UserTaskStatisticsMapper> userTaskStatisticsMapperList = userTaskStatisticsService.listByTaskDimensionAndApplication(type, applicationDimensionMapperParent, StatisticsDimension.CLIENT.equals(applicationDimensionMapper.getStatisticsDimension()) ? applicationDimensionMapper : null, taskDimensionMapper);
				List<Integer> aosList = new ArrayList<>();
				List<Integer> aosDiffIPList = new ArrayList<>();
				for (UserTaskStatisticsMapper userTaskStatisticsMapper : userTaskStatisticsMapperList) {
					// 统计渠道
					String date = null;
					if (0 == type) {
						date = userTaskStatisticsMapper.getStatisticsYear() + "-" + userTaskStatisticsMapper.getStatisticsMonth() + "-" + userTaskStatisticsMapper.getStatisticsDay() + " " + userTaskStatisticsMapper.getStatisticsHour();
					} else if (1 == type) {
						// 获取天
						date = userTaskStatisticsMapper.getStatisticsYear() + "-" + userTaskStatisticsMapper.getStatisticsMonth() + "-" + userTaskStatisticsMapper.getStatisticsDay();
					} else if (2 == type) {
						date = userTaskStatisticsMapper.getStatisticsYear() + "-" + userTaskStatisticsMapper.getStatisticsMonth();
					}
					if (!dates.contains(date)) {
						dates.add(date);
					}
					aosList.add(userTaskStatisticsMapper.getAcceptCount());
					aosDiffIPList.add(userTaskStatisticsMapper.getCancelCount());
					if (maxValue < userTaskStatisticsMapper.getAcceptCount()) {
						maxValue = userTaskStatisticsMapper.getAcceptCount();
					}
					if (maxDiffIPValue < userTaskStatisticsMapper.getCancelCount()) {
						maxDiffIPValue = userTaskStatisticsMapper.getCancelCount();
					}
				}
				chartLineVos.add(new ChartLineVo(channelName, false, aosList));
				chartLineDiffIPVos.add(new ChartLineVo(name, false, aosDiffIPList));

			}
		}
		List list = new ArrayList();
		list.add(maxDiffIPValue);
		list.add(maxValue);
		list.add(chartLineVos);
		list.add(chartLineDiffIPVos);
		return responseEntity(200, dates, "request.suc", 0, list);
	}
}
