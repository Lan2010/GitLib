package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOnlineStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceOnLineStatusStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class DeviceOnlineStatusStatisticsService {

	@Autowired
	private RPCDeviceOnLineStatusStatisticsService rpcDeviceOnlineStatusStatisticsService;

	/**
	 * 获取清单
	 *
	 * @param pagerMapping
	 * @return
	 */
	public List<DeviceOnlineStatusStatisticsMapper> list(int type,String value) {
		Date now = new Date();
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		return rpcDeviceOnlineStatusStatisticsService.list(type,hour,day,month,year,value);
	}

	public long count() {
		return rpcDeviceOnlineStatusStatisticsService.count();
	}
}
