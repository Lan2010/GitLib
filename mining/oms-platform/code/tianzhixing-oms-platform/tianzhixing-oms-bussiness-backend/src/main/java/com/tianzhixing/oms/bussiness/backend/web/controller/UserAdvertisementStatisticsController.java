package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.service.AdvertisementDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserAdvertisementStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.utils.ListUtils;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.AdvertisementDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAdvertisementStatisticsMapper;
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
public class UserAdvertisementStatisticsController extends BaseController {

	@Autowired
	private UserAdvertisementStatisticsService userAdvertisementStatisticsService;

	@Autowired
	private AdvertisementDimensionService advertisementDimensionService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/userAdvertisement/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/userAdvertisement");
	}

	@RequestMapping(value = "/userAdvertisement/chart", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
		long count = userAdvertisementStatisticsService.count();
		// labels
		List<String> labels = new ArrayList<>();
		List<Integer> accessCountList = new ArrayList<>();
		List<Integer> accessDiffIPCountList = new ArrayList<>();
		List<Integer> clickCountList = new ArrayList<>();
		List<Integer> clickDiffIPCountList = new ArrayList<>();
		List<AdvertisementDimensionMapper> advertisementDimensionMappers = advertisementDimensionService.list(true);
		for (AdvertisementDimensionMapper advertisementDimensionMapper : advertisementDimensionMappers) {
			labels.add(advertisementDimensionMapper.getName());
			List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers = userAdvertisementStatisticsService.list(advertisementDimensionMapper.getAdvertisementId());
			for (UserAdvertisementStatisticsMapper userAdvertisementStatisticsMapper : userAdvertisementStatisticsMappers) {
				accessCountList.add(userAdvertisementStatisticsMapper.getAccessCount());
				accessDiffIPCountList.add(userAdvertisementStatisticsMapper.getAccessDiffIPCount());
				clickCountList.add(userAdvertisementStatisticsMapper.getClickCount());
				clickDiffIPCountList.add(userAdvertisementStatisticsMapper.getClickDiffIPCount());
			}
		}
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo - 1);
		List<List<Integer>> pagelist1 = ListUtils.pagingInt(accessCountList, pageSize);
		List<Integer> accessCount = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Integer>> pagelist2 = ListUtils.pagingInt(accessDiffIPCountList, pageSize);
		List<Integer> accessDiffIPCount = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List<List<Integer>> pagelist3 = ListUtils.pagingInt(clickCountList, pageSize);
		List<Integer> clickCount = pagelist3.size() == 0 ? null : pagelist3.get(pageNo - 1);
		List<List<Integer>> pagelist4 = ListUtils.pagingInt(clickDiffIPCountList, pageSize);
		List<Integer> clickDiffIPCount = pagelist4.size() == 0 ? null : pagelist4.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(accessCount);
		list.add(accessDiffIPCount);
		list.add(clickCount);
		list.add(clickDiffIPCount);
		return responseEntity(200, "request.suc", count, list);
	}
}
