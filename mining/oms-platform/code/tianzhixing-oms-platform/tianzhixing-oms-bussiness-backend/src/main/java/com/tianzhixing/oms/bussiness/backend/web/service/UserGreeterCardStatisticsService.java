package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserGreeterCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserGreeterCardStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserGreeterCardStatisticsService {

	@Autowired
	private RPCUserGreeterCardStatisticsService rpcUserGreeterCardStatisticsService;

	/**
	 * 获取清单
	 *
	 * @param type
	 * @return
	 */
	public List<UserGreeterCardStatisticsMapper> list(int type) {
		Date now = new Date();
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		List<UserGreeterCardStatisticsMapper> list = new ArrayList<>();
		list.addAll(_get(type, day, hour, month, year));
		return list;
	}

	private List<UserGreeterCardStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year) {
		List<UserGreeterCardStatisticsMapper> list = new ArrayList<>();
		if (0 == type) {
			List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers = rpcUserGreeterCardStatisticsService.list(day, month, year);
			Map<String, UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMapperMap = new HashMap<>();
			for (UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper : userGreeterCardStatisticsMappers) {
				userGreeterCardStatisticsMapperMap.put(userGreeterCardStatisticsMapper.getStatisticsHour().toString(), userGreeterCardStatisticsMapper);
			}
			// 获取小时 24小时
			for (Integer i = 0; i < hour; i++) {
				UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper = userGreeterCardStatisticsMapperMap.get(i.toString());
				if (userGreeterCardStatisticsMapper == null) {
					userGreeterCardStatisticsMapper = new UserGreeterCardStatisticsMapper(null, null, null, i, day, month, year, null, null, 0, 0, null);
				}
				list.add(userGreeterCardStatisticsMapper);
			}
		} else if (1 == type) {
			List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers = rpcUserGreeterCardStatisticsService.list(null, month, year);
			Map<String, UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMapperMap = new HashMap<>();
			for (UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper : userGreeterCardStatisticsMappers) {
				userGreeterCardStatisticsMapperMap.put(userGreeterCardStatisticsMapper.getStatisticsDay().toString(), userGreeterCardStatisticsMapper);
			}
			// 获取天
			for (Integer i = 1; i <= day; i++) {
				UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper = userGreeterCardStatisticsMapperMap.get(i.toString());
				if (userGreeterCardStatisticsMapper == null) {
					userGreeterCardStatisticsMapper = new UserGreeterCardStatisticsMapper(null, null, null, null, i, month, year, null, null, 0, 0, null);
				}
				list.add(userGreeterCardStatisticsMapper);
			}
		} else if (2 == type) {

			List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers = rpcUserGreeterCardStatisticsService.list(null, null, year);
			Map<String, UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMapperMap = new HashMap<>();
			for (UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper : userGreeterCardStatisticsMappers) {
				userGreeterCardStatisticsMapperMap.put(userGreeterCardStatisticsMapper.getStatisticsMonth().toString(), userGreeterCardStatisticsMapper);
			}
			// 获取月
			for (Integer i = 1; i <= month; i++) {
				UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper = userGreeterCardStatisticsMapperMap.get(i.toString());
				if (userGreeterCardStatisticsMapper == null) {
					userGreeterCardStatisticsMapper = new UserGreeterCardStatisticsMapper(null, null, null, null, null, i, year, null, null, 0, 0, null);
				}
				list.add(userGreeterCardStatisticsMapper);
			}
		}
		return list;
	}
	
}
