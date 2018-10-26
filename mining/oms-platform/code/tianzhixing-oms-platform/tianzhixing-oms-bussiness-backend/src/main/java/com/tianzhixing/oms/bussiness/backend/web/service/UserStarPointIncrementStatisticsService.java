package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserStarPointIncrementStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointIncrementStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserStarPointIncrementStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserStarPointIncrementStatisticsService {

	@Autowired
	private RPCUserStarPointIncrementStatisticsService rpcUserStarPointIncrementStatisticsService;

	@Autowired
	private RPCApplicationDimensionService rpcApplicationDimensionService;

	/**
	 * 获取清单
	 *
	 * @param type
	 * @return
	 */
	public List<UserStarPointIncrementStatisticsMapping> list(int type) {
		Date now = new Date();
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		List<UserStarPointIncrementStatisticsMapping> userStarPointIncrementStatisticsMappings = new ArrayList<>();
		List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
		for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
			List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
			if (subList != null && !subList.isEmpty()) {
				for (ApplicationDimensionMapper adm : subList) {
					UserStarPointIncrementStatisticsMapping userStarPointIncrementStatisticsMapping = new UserStarPointIncrementStatisticsMapping();
					adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
					List<UserStarPointIncrementStatisticsMapper> list = new ArrayList<>();
					list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, adm.getName(), null));
					userStarPointIncrementStatisticsMapping.setApplicationDimensionMapper(adm);
					userStarPointIncrementStatisticsMapping.setUserStarPointIncrementStatisticsMappers(list);
					userStarPointIncrementStatisticsMappings.add(userStarPointIncrementStatisticsMapping);
				}
			} else {
				// 未配置客户端
				UserStarPointIncrementStatisticsMapping userStarPointIncrementStatisticsMapping = new UserStarPointIncrementStatisticsMapping();
				List<UserStarPointIncrementStatisticsMapper> list = new ArrayList<>();
				list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, applicationDimensionMapper.getName(), null));
				userStarPointIncrementStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
				userStarPointIncrementStatisticsMapping.setUserStarPointIncrementStatisticsMappers(list);
				userStarPointIncrementStatisticsMappings.add(userStarPointIncrementStatisticsMapping);
			}

		}
		return userStarPointIncrementStatisticsMappings;
	}

	private List<UserStarPointIncrementStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName, Integer incrementType) {
		List<UserStarPointIncrementStatisticsMapper> list = new ArrayList<>();
		if (0 == type) {
			List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = rpcUserStarPointIncrementStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getName(), incrementType);
			Map<String, UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMapperMap = new HashMap<>();
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : userStarPointIncrementStatisticsMappers) {
				userStarPointIncrementStatisticsMapperMap.put(userStarPointIncrementStatisticsMapper.getStatisticsHour().toString(), userStarPointIncrementStatisticsMapper);
			}
			// 获取小时 24小时
			for (Integer i = 0; i < hour; i++) {
				UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = userStarPointIncrementStatisticsMapperMap.get(i.toString());
				if (userStarPointIncrementStatisticsMapper == null) {
					userStarPointIncrementStatisticsMapper = new UserStarPointIncrementStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0.0d, null, incrementType, null, platformName);
				}
				list.add(userStarPointIncrementStatisticsMapper);
			}
		} else if (1 == type) {
			List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = rpcUserStarPointIncrementStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getName(), incrementType);
			Map<String, UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMapperMap = new HashMap<>();
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : userStarPointIncrementStatisticsMappers) {
				userStarPointIncrementStatisticsMapperMap.put(userStarPointIncrementStatisticsMapper.getStatisticsDay().toString(), userStarPointIncrementStatisticsMapper);
			}
			// 获取天
			for (Integer i = 1; i <= day; i++) {
				UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = userStarPointIncrementStatisticsMapperMap.get(i.toString());
				if (userStarPointIncrementStatisticsMapper == null) {
					userStarPointIncrementStatisticsMapper = new UserStarPointIncrementStatisticsMapper(null, null, null, null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0.0d, null, incrementType, null, platformName);
				}
				list.add(userStarPointIncrementStatisticsMapper);
			}
		} else if (2 == type) {

			List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = rpcUserStarPointIncrementStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getName(), incrementType);
			Map<String, UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMapperMap = new HashMap<>();
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : userStarPointIncrementStatisticsMappers) {
				userStarPointIncrementStatisticsMapperMap.put(userStarPointIncrementStatisticsMapper.getStatisticsMonth().toString(), userStarPointIncrementStatisticsMapper);
			}
			// 获取月
			for (Integer i = 1; i <= month; i++) {
				UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = userStarPointIncrementStatisticsMapperMap.get(i.toString());
				if (userStarPointIncrementStatisticsMapper == null) {
					userStarPointIncrementStatisticsMapper = new UserStarPointIncrementStatisticsMapper(null, null, null, null, null, i, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0.0d, null, incrementType, null, platformName);
				}
				list.add(userStarPointIncrementStatisticsMapper);
			}

		}
		return list;
	}

	/**
	 * 获取清单
	 *
	 * @param type
	 * @return
	 */
	public List<UserStarPointIncrementStatisticsMapping> listByIncrementType(int type) {
		Date now = new Date();
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		List<UserStarPointIncrementStatisticsMapping> userStarPointIncrementStatisticsMappings = new ArrayList<>();
		List<Integer> incrementTypeList = new ArrayList<>();
		incrementTypeList.add(0);
		incrementTypeList.add(1);
		incrementTypeList.add(2);
		for (Integer incrementType : incrementTypeList) {
			UserStarPointIncrementStatisticsMapping userStarPointIncrementStatisticsMapping = new UserStarPointIncrementStatisticsMapping();
			List<UserStarPointIncrementStatisticsMapper> list = new ArrayList<>();
			list.addAll(_get(type, day, hour, month, year, null, null, null, incrementType));
			userStarPointIncrementStatisticsMapping.setUserStarPointIncrementStatisticsMappers(list);
			userStarPointIncrementStatisticsMapping.setIncrementType(incrementType);
			userStarPointIncrementStatisticsMappings.add(userStarPointIncrementStatisticsMapping);

		}
		return userStarPointIncrementStatisticsMappings;
	}
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
	public List<UserStarPointIncrementStatisticsMapper> listByType(int type,String platformName) {
		Date now = new Date();
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		return rpcUserStarPointIncrementStatisticsService.list(type,hour,day,month,year,platformName);
	}
}
