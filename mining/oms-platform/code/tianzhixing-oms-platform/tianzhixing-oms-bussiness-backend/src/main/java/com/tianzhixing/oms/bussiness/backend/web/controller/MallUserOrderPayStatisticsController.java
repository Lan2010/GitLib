package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.service.MallProductDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.MallUserOrderPayStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.utils.ListUtils;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallProductDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallUserOrderPayStatisticsMapper;
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
public class MallUserOrderPayStatisticsController extends BaseController {

	@Autowired
	private MallUserOrderPayStatisticsService mallUserOrderPayStatisticsService;

	@Autowired
	private MallProductDimensionService mallProductDimensionService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/mallUserOrderPay/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("statistics/mallUserOrderPay");
	}

	@RequestMapping(value = "/mallUserOrderPay/barByApplication", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity detailChart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("type") int type) {
		List<String> labels = new ArrayList<>();
		List<Double> alreadyPayAmountList = new ArrayList<>();
		List<Double> awaitPayAmountList = new ArrayList<>();
		List<Double> failedPayAmountList = new ArrayList<>();
		List<Integer> alreadyPayCountList = new ArrayList<>();
		List<Integer> awaitPayCountList = new ArrayList<>();
		List<Integer> failedPayCountList = new ArrayList<>();
		List<ApplicationDimensionMapper> appMappers = mallUserOrderPayStatisticsService.appList();
		long count = appMappers.size();
		for (ApplicationDimensionMapper applicationDimensionMapper : appMappers) {
			labels.add(applicationDimensionMapper.getName());
			List<MallUserOrderPayStatisticsMapper> MallUserOrderPayStatisticsMappers = mallUserOrderPayStatisticsService.listByApp(type, applicationDimensionMapper.getName());
			for (MallUserOrderPayStatisticsMapper mallUserOrderPayStatisticsMapper : MallUserOrderPayStatisticsMappers) {
				alreadyPayCountList.add(mallUserOrderPayStatisticsMapper.getAlreadyPayCount());
				awaitPayCountList.add(mallUserOrderPayStatisticsMapper.getAwaitPayCount());
				failedPayCountList.add(mallUserOrderPayStatisticsMapper.getFailedPayCount());
				alreadyPayAmountList.add(mallUserOrderPayStatisticsMapper.getAlreadyPayAmount());
				awaitPayAmountList.add(mallUserOrderPayStatisticsMapper.getAwaitPayAmount());
				failedPayAmountList.add(mallUserOrderPayStatisticsMapper.getFailedPayAmount());
			}
		}
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo - 1);
		List<List<Integer>> pagelist1 = ListUtils.pagingInt(alreadyPayCountList, pageSize);
		List<Integer> alreadyPayCount = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Integer>> pagelist2 = ListUtils.pagingInt(awaitPayCountList, pageSize);
		List<Integer> awaitPayCount = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List<List<Integer>> pagelist3 = ListUtils.pagingInt(failedPayCountList, pageSize);
		List<Integer> failedPayCount = pagelist3.size() == 0 ? null : pagelist3.get(pageNo - 1);
		List<List<Double>> pagelist4 = ListUtils.pagingDouble(alreadyPayAmountList, pageSize);
		List<Double> alreadyPayAmount = pagelist4.size() == 0 ? null : pagelist4.get(pageNo - 1);
		List<List<Double>> pagelist5 = ListUtils.pagingDouble(awaitPayAmountList, pageSize);
		List<Double> awaitPayAmount = pagelist5.size() == 0 ? null : pagelist5.get(pageNo - 1);
		List<List<Double>> pagelist6 = ListUtils.pagingDouble(failedPayAmountList, pageSize);
		List<Double> failedPayAmount = pagelist6.size() == 0 ? null : pagelist6.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(alreadyPayCount);
		list.add(awaitPayCount);
		list.add(failedPayCount);
		list.add(alreadyPayAmount);
		list.add(awaitPayAmount);
		list.add(failedPayAmount);
		return responseEntity(200, "request.suc", count, list);
	}

	@RequestMapping(value = "/mallUserOrderPay/barByType", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity chart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("type") int type) {
		long count = mallUserOrderPayStatisticsService.count();
		// labels
		List<String> labels = new ArrayList<>();
		List<Double> alreadyPayAmountList = new ArrayList<>();
		List<Double> awaitPayAmountList = new ArrayList<>();
		List<Double> failedPayAmountList = new ArrayList<>();
		List<Integer> alreadyPayCountList = new ArrayList<>();
		List<Integer> awaitPayCountList = new ArrayList<>();
		List<Integer> failedPayCountList = new ArrayList<>();
		List<MallProductDimensionMapper> mallProductDimensionMappers = mallProductDimensionService.list(true);
		for (MallProductDimensionMapper mallProductDimensionMapper : mallProductDimensionMappers) {
			labels.add(mallProductDimensionMapper.getName());
			List<MallUserOrderPayStatisticsMapper> mallUserOrderPayStatisticsMappers = mallUserOrderPayStatisticsService.listByType(type, mallProductDimensionMapper.getValue());
			for (MallUserOrderPayStatisticsMapper mallUserOrderPayStatisticsMapper : mallUserOrderPayStatisticsMappers) {
				alreadyPayCountList.add(mallUserOrderPayStatisticsMapper.getAlreadyPayCount());
				awaitPayCountList.add(mallUserOrderPayStatisticsMapper.getAwaitPayCount());
				failedPayCountList.add(mallUserOrderPayStatisticsMapper.getFailedPayCount());
				alreadyPayAmountList.add(mallUserOrderPayStatisticsMapper.getAlreadyPayAmount());
				awaitPayAmountList.add(mallUserOrderPayStatisticsMapper.getAwaitPayAmount());
				failedPayAmountList.add(mallUserOrderPayStatisticsMapper.getFailedPayAmount());
			}
		}
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo - 1);
		List<List<Integer>> pagelist1 = ListUtils.pagingInt(alreadyPayCountList, pageSize);
		List<Integer> alreadyPayCount = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Integer>> pagelist2 = ListUtils.pagingInt(awaitPayCountList, pageSize);
		List<Integer> awaitPayCount = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List<List<Integer>> pagelist3 = ListUtils.pagingInt(failedPayCountList, pageSize);
		List<Integer> failedPayCount = pagelist3.size() == 0 ? null : pagelist3.get(pageNo - 1);
		List<List<Double>> pagelist4 = ListUtils.pagingDouble(alreadyPayAmountList, pageSize);
		List<Double> alreadyPayAmount = pagelist4.size() == 0 ? null : pagelist4.get(pageNo - 1);
		List<List<Double>> pagelist5 = ListUtils.pagingDouble(awaitPayAmountList, pageSize);
		List<Double> awaitPayAmount = pagelist5.size() == 0 ? null : pagelist5.get(pageNo - 1);
		List<List<Double>> pagelist6 = ListUtils.pagingDouble(failedPayAmountList, pageSize);
		List<Double> failedPayAmount = pagelist6.size() == 0 ? null : pagelist6.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(alreadyPayCount);
		list.add(awaitPayCount);
		list.add(failedPayCount);
		list.add(alreadyPayAmount);
		list.add(awaitPayAmount);
		list.add(failedPayAmount);
		return responseEntity(200, "request.suc", count, list);
	}

	@RequestMapping(value = "/mallUserOrderPay/barByAppAndType", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity allChart(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @RequestParam("type") int type) {
		// labels
		List<String> labels = new ArrayList<>();
		List<Double> alreadyPayAmountList = new ArrayList<>();
		List<Double> awaitPayAmountList = new ArrayList<>();
		List<Double> failedPayAmountList = new ArrayList<>();
		List<Integer> alreadyPayCountList = new ArrayList<>();
		List<Integer> awaitPayCountList = new ArrayList<>();
		List<Integer> failedPayCountList = new ArrayList<>();
		List<ApplicationDimensionMapper> appMappers = mallUserOrderPayStatisticsService.appList();
		List<MallProductDimensionMapper> mallProductDimensionMappers = mallProductDimensionService.list(true);
		for (ApplicationDimensionMapper applicationDimensionMapper : appMappers) {
			for (MallProductDimensionMapper mallProductDimensionMapper : mallProductDimensionMappers) {
				String channelName = applicationDimensionMapper.getName() + "(" + mallProductDimensionMapper.getName() + ")";
				labels.add(channelName);
				List<MallUserOrderPayStatisticsMapper> mallUserOrderPayStatisticsMappers = mallUserOrderPayStatisticsService.listByAppAndType(type, applicationDimensionMapper.getName(), mallProductDimensionMapper.getValue());
				for (MallUserOrderPayStatisticsMapper mallUserOrderPayStatisticsMapper : mallUserOrderPayStatisticsMappers) {
					alreadyPayCountList.add(mallUserOrderPayStatisticsMapper.getAlreadyPayCount());
					awaitPayCountList.add(mallUserOrderPayStatisticsMapper.getAwaitPayCount());
					failedPayCountList.add(mallUserOrderPayStatisticsMapper.getFailedPayCount());
					alreadyPayAmountList.add(mallUserOrderPayStatisticsMapper.getAlreadyPayAmount());
					awaitPayAmountList.add(mallUserOrderPayStatisticsMapper.getAwaitPayAmount());
					failedPayAmountList.add(mallUserOrderPayStatisticsMapper.getFailedPayAmount());
				}
			}
		}
		long count = labels.size();
		List<List<String>> pagelist = ListUtils.pagingString(labels, pageSize);
		List<String> label = pagelist.size() == 0 ? null : pagelist.get(pageNo - 1);
		List<List<Integer>> pagelist1 = ListUtils.pagingInt(alreadyPayCountList, pageSize);
		List<Integer> alreadyPayCount = pagelist1.size() == 0 ? null : pagelist1.get(pageNo - 1);
		List<List<Integer>> pagelist2 = ListUtils.pagingInt(awaitPayCountList, pageSize);
		List<Integer> awaitPayCount = pagelist2.size() == 0 ? null : pagelist2.get(pageNo - 1);
		List<List<Integer>> pagelist3 = ListUtils.pagingInt(failedPayCountList, pageSize);
		List<Integer> failedPayCount = pagelist3.size() == 0 ? null : pagelist3.get(pageNo - 1);
		List<List<Double>> pagelist4 = ListUtils.pagingDouble(alreadyPayAmountList, pageSize);
		List<Double> alreadyPayAmount = pagelist4.size() == 0 ? null : pagelist4.get(pageNo - 1);
		List<List<Double>> pagelist5 = ListUtils.pagingDouble(awaitPayAmountList, pageSize);
		List<Double> awaitPayAmount = pagelist5.size() == 0 ? null : pagelist5.get(pageNo - 1);
		List<List<Double>> pagelist6 = ListUtils.pagingDouble(failedPayAmountList, pageSize);
		List<Double> failedPayAmount = pagelist6.size() == 0 ? null : pagelist6.get(pageNo - 1);
		List list = new ArrayList();
		list.add(label);
		list.add(alreadyPayCount);
		list.add(awaitPayCount);
		list.add(failedPayCount);
		list.add(alreadyPayAmount);
		list.add(awaitPayAmount);
		list.add(failedPayAmount);
		return responseEntity(200, "request.suc", count, list);
	}
}
