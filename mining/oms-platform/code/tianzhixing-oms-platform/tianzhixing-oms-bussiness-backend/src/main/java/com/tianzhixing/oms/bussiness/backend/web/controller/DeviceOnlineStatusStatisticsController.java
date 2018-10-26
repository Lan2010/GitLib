package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.service.DeviceDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.DeviceOnlineStatusStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.utils.ListUtils;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOnlineStatusStatisticsMapper;
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
public class DeviceOnlineStatusStatisticsController extends BaseController {

	@Autowired
	private DeviceOnlineStatusStatisticsService deviceOnlineStatusStatisticsService;

	@Autowired
	private DeviceDimensionService deviceDimensionService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/deviceOnline/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/deviceOnline");
	}

	@RequestMapping(value = "/deviceOnline/chart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("type") int type) {
		long count = deviceOnlineStatusStatisticsService.count();
		// labels
		List<String> labels = new ArrayList<>();
		List<Integer> bindDeviceOnlineCountList = new ArrayList<>();
		List<Integer> bindDeviceDiffIDOnlineCountList = new ArrayList<>();
		List<Integer> unBindDeviceOnlineCountList = new ArrayList<>();
		List<Integer> unBindDeviceDiffIDOnlineCountList = new ArrayList<>();
		List<Integer> bindDeviceOfflineCountList = new ArrayList<>();
		List<Integer> bindDeviceDiffIDOfflineCountList = new ArrayList<>();
		List<Integer> unBindDeviceOfflineCountList = new ArrayList<>();
		List<Integer> unBindDeviceDiffIDOfflineCountList = new ArrayList<>();
		List<Integer> unBindDeviceCurrentOnlineCountList = new ArrayList<>();
		List<Integer> bindDeviceCurrentOnlineCountList = new ArrayList<>();
		List<DeviceDimensionMapper> deviceDimensionMappers = deviceDimensionService.list(true);
		for (DeviceDimensionMapper deviceDimensionMapper : deviceDimensionMappers) {
			labels.add(deviceDimensionMapper.getName());
			List<DeviceOnlineStatusStatisticsMapper> deviceOnlineStatusStatisticsMappers = deviceOnlineStatusStatisticsService.list(type,deviceDimensionMapper.getValue());
			for (DeviceOnlineStatusStatisticsMapper deviceOnlineStatusStatisticsMapper : deviceOnlineStatusStatisticsMappers) {
				bindDeviceOnlineCountList.add(deviceOnlineStatusStatisticsMapper.getBindDeviceOnlineCount());
				bindDeviceDiffIDOnlineCountList.add(deviceOnlineStatusStatisticsMapper.getBindDeviceDiffIDOnlineCount());
				unBindDeviceOnlineCountList.add(deviceOnlineStatusStatisticsMapper.getUnBindDeviceOnlineCount());
				unBindDeviceDiffIDOnlineCountList.add(deviceOnlineStatusStatisticsMapper.getUnBindDeviceDiffIDOnlineCount());
				bindDeviceOfflineCountList.add(deviceOnlineStatusStatisticsMapper.getBindDeviceOfflineCount());
				bindDeviceDiffIDOfflineCountList.add(deviceOnlineStatusStatisticsMapper.getBindDeviceDiffIDOfflineCount());
				unBindDeviceOfflineCountList.add(deviceOnlineStatusStatisticsMapper.getUnBindDeviceOfflineCount());
				unBindDeviceDiffIDOfflineCountList.add(deviceOnlineStatusStatisticsMapper.getUnBindDeviceDiffIDOfflineCount());
				unBindDeviceCurrentOnlineCountList.add(deviceOnlineStatusStatisticsMapper.getUnBindDeviceCurrentOnlineCount());
				bindDeviceCurrentOnlineCountList.add(deviceOnlineStatusStatisticsMapper.getBindDeviceCurrentOnlineCount());
			}
		}
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo - 1);
		List<List<Integer>> pagelist1 = ListUtils.pagingInt(bindDeviceOnlineCountList, pageSize);
		List<Integer> bindDeviceOnlineCount = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Integer>> pagelist2 = ListUtils.pagingInt(bindDeviceDiffIDOnlineCountList, pageSize);
		List<Integer> bindDeviceDiffIDOnlineCount = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List<List<Integer>> pagelist3 = ListUtils.pagingInt(unBindDeviceOnlineCountList, pageSize);
		List<Integer> unBindDeviceOnlineCount = pagelist3.size() == 0 ? null : pagelist3.get(pageNo - 1);
		List<List<Integer>> pagelist4 = ListUtils.pagingInt(unBindDeviceDiffIDOnlineCountList, pageSize);
		List<Integer> unBindDeviceDiffIDOnlineCount = pagelist4.size() == 0 ? null : pagelist4.get(pageNo - 1);
		List<List<Integer>> pagelist5 = ListUtils.pagingInt(bindDeviceOfflineCountList, pageSize);
		List<Integer> bindDeviceOfflineCount = pagelist5.size() == 0 ? null : pagelist5.get(pageNo - 1);
		List<List<Integer>> pagelist6 = ListUtils.pagingInt(bindDeviceDiffIDOfflineCountList, pageSize);
		List<Integer> bindDeviceDiffIDOfflineCount = pagelist6.size() == 0 ? null : pagelist6.get(pageNo - 1);
		List<List<Integer>> pagelist7 = ListUtils.pagingInt(unBindDeviceOfflineCountList, pageSize);
		List<Integer> unBindDeviceOfflineCount = pagelist7.size() == 0 ? null : pagelist7.get(pageNo - 1);
		List<List<Integer>> pagelist8 = ListUtils.pagingInt(unBindDeviceDiffIDOfflineCountList, pageSize);
		List<Integer> unBindDeviceDiffIDOfflineCount = pagelist8.size() == 0 ? null : pagelist8.get(pageNo - 1);
		List<List<Integer>> pagelist9 = ListUtils.pagingInt(unBindDeviceCurrentOnlineCountList, pageSize);
		List<Integer> unBindDeviceCurrentOnlineCount = pagelist9.size() == 0 ? null : pagelist9.get(pageNo - 1);
		List<List<Integer>> pagelist10 = ListUtils.pagingInt(bindDeviceCurrentOnlineCountList, pageSize);
		List<Integer> bindDeviceCurrentOnlineCount = pagelist10.size() == 0 ? null : pagelist10.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(bindDeviceOnlineCount);
		list.add(bindDeviceDiffIDOnlineCount);
		list.add(unBindDeviceOnlineCount);
		list.add(unBindDeviceDiffIDOnlineCount);
		list.add(bindDeviceOfflineCount);
		list.add(bindDeviceDiffIDOfflineCount);
		list.add(unBindDeviceOfflineCount);
		list.add(unBindDeviceDiffIDOfflineCount);
		list.add(unBindDeviceCurrentOnlineCount);
		list.add(bindDeviceCurrentOnlineCount);
		return responseEntity(200, "request.suc", count, list);
	}
}
