package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallUserOrderPayStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCMallUserOrderPayStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class MallUserOrderPayStatisticsService {

	@Autowired
	private RPCMallUserOrderPayStatisticsService rpcMallUserOrderPayStatisticsService;

	@Autowired
	private RPCApplicationDimensionService rpcApplicationDimensionService;

	/**
	 * 获取所有客户端
	 *
	 * @return
	 */
	public List<ApplicationDimensionMapper> appList() {
		List<ApplicationDimensionMapper> appMappers = new ArrayList<>();
		List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
		for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
			List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
			if (subList != null && !subList.isEmpty()) {
				for (ApplicationDimensionMapper adm : subList) {
					adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
					appMappers.add(adm);
				}
			} else {
				// 未配置客户端
				appMappers.add(applicationDimensionMapper);
			}

		}
		return appMappers;
	}
	
	/**
	 * 获取清单
	 *
	 * @param pagerMapping
	 * @return
	 */
	public List<MallUserOrderPayStatisticsMapper> listByApp(int type,String platformName) {
		Date now = new Date();
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		return rpcMallUserOrderPayStatisticsService.list(type,hour,day,month,year,platformName);
	}
	
	/**
	 * 获取清单
	 *
	 * @param pagerMapping
	 * @return
	 */
	public List<MallUserOrderPayStatisticsMapper> listByType(int type,String value) {
		Date now = new Date();
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		return rpcMallUserOrderPayStatisticsService.listByType(type,hour,day,month,year,value);
	}

	public long count() {
		return rpcMallUserOrderPayStatisticsService.count();
	}
	
	/**
	 * 获取清单
	 *
	 * @param pagerMapping
	 * @return
	 */
	public List<MallUserOrderPayStatisticsMapper> listByAppAndType(int type,String platformName,String value) {
		Date now = new Date();
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		return rpcMallUserOrderPayStatisticsService.listByAppAndType(type,hour,day,month,year,platformName,value);
	}
}
