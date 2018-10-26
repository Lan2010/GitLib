package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserAuthStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAuthDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAuthStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserAuthStatisticsService {

	@Autowired
	private RPCUserAuthStatisticsService rpcUserAuthStatisticsService;

	@Autowired
	private RPCApplicationDimensionService rpcApplicationDimensionService;
	
	@Autowired
	private RPCUserAuthDimensionService rpcUserAuthDimensionService;

	/**
	 * 获取清单
	 *
	 * @param type
	 * @return
	 */
	public List<UserAuthStatisticsMapping> list(int type) {
		Date now = new Date();
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		List<UserAuthStatisticsMapping> userAuthStatisticsMappings = new ArrayList<>();
		List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
		for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
			List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
			if (subList != null && !subList.isEmpty()) {
				for (ApplicationDimensionMapper adm : subList) {
					UserAuthStatisticsMapping userAuthStatisticsMapping = new UserAuthStatisticsMapping();
					adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
					List<UserAuthStatisticsMapper> list = new ArrayList<>();
					list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, adm.getName(),null));
					userAuthStatisticsMapping.setApplicationDimensionMapper(adm);
					userAuthStatisticsMapping.setUserAuthStatisticsMappers(list);
					userAuthStatisticsMappings.add(userAuthStatisticsMapping);
				}
			} else {
				// 未配置客户端
				UserAuthStatisticsMapping userAuthStatisticsMapping = new UserAuthStatisticsMapping();
				List<UserAuthStatisticsMapper> list = new ArrayList<>();
				list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, applicationDimensionMapper.getName(),null));
				userAuthStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
				userAuthStatisticsMapping.setUserAuthStatisticsMappers(list);
				userAuthStatisticsMappings.add(userAuthStatisticsMapping);
			}

		}
		return userAuthStatisticsMappings;
	}

	private List<UserAuthStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName , String authType) {
		List<UserAuthStatisticsMapper> list = new ArrayList<>();
		if (0 == type) {
			List<UserAuthStatisticsMapper> userAuthStatisticsMappers = rpcUserAuthStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper == null ? null :applicationDimensionMapper.getName(),authType);
			Map<String, UserAuthStatisticsMapper> userAuthStatisticsMapperMap = new HashMap<>();
			for (UserAuthStatisticsMapper userAuthStatisticsMapper : userAuthStatisticsMappers) {
				userAuthStatisticsMapperMap.put(userAuthStatisticsMapper.getStatisticsHour().toString(), userAuthStatisticsMapper);
			}
			// 获取小时 24小时
			for (Integer i = 0; i < hour; i++) {
				UserAuthStatisticsMapper userAuthStatisticsMapper = userAuthStatisticsMapperMap.get(i.toString());
				if (userAuthStatisticsMapper == null) {
					userAuthStatisticsMapper = new UserAuthStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, 0, 0, null, authType, platformName);
				}
				list.add(userAuthStatisticsMapper);
			}
		} else if (1 == type) {
			List<UserAuthStatisticsMapper> userAuthStatisticsMappers = rpcUserAuthStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(),applicationDimensionMapper == null ? null :applicationDimensionMapper.getName(),authType);
			Map<String, UserAuthStatisticsMapper> userAuthStatisticsMapperMap = new HashMap<>();
			for (UserAuthStatisticsMapper userAuthStatisticsMapper : userAuthStatisticsMappers) {
				userAuthStatisticsMapperMap.put(userAuthStatisticsMapper.getStatisticsDay().toString(), userAuthStatisticsMapper);
			}
			// 获取天
			for (Integer i = 1; i <= day; i++) {
				UserAuthStatisticsMapper userAuthStatisticsMapper = userAuthStatisticsMapperMap.get(i.toString());
				if (userAuthStatisticsMapper == null) {
					userAuthStatisticsMapper = new UserAuthStatisticsMapper(null, null, null, null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, 0, 0, null, authType, platformName);
				}
				list.add(userAuthStatisticsMapper);
			}

		} else if (2 == type) {

			List<UserAuthStatisticsMapper> userAuthStatisticsMappers = rpcUserAuthStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper == null ? null :applicationDimensionMapper.getName(),authType);
			Map<String, UserAuthStatisticsMapper> userAuthStatisticsMapperMap = new HashMap<>();
			for (UserAuthStatisticsMapper userAuthStatisticsMapper : userAuthStatisticsMappers) {
				userAuthStatisticsMapperMap.put(userAuthStatisticsMapper.getStatisticsMonth().toString(), userAuthStatisticsMapper);
			}
			// 获取月
			for (Integer i = 1; i <= month; i++) {
				UserAuthStatisticsMapper userAuthStatisticsMapper = userAuthStatisticsMapperMap.get(i.toString());
				if (userAuthStatisticsMapper == null) {
					userAuthStatisticsMapper = new UserAuthStatisticsMapper(null, null, null, null, null, i, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, 0, 0, null, authType, platformName);
				}
				list.add(userAuthStatisticsMapper);
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
	public List<UserAuthStatisticsMapping> listByAuthType(int type) {
		Date now = new Date();
		Integer day = Integer.valueOf(CalendarUtil.day(now));
		Integer hour = Integer.valueOf(CalendarUtil.hour(now));
		Integer month = Integer.valueOf(CalendarUtil.month(now));
		Integer year = Integer.valueOf(CalendarUtil.year(now));
		List<UserAuthStatisticsMapping> userAuthStatisticsMappings = new ArrayList<>();
		List<UserAuthDimensionMapper> userAuthDimensionMappers = rpcUserAuthDimensionService.list(true);
		for (UserAuthDimensionMapper userAuthDimensionMapper : userAuthDimensionMappers) {
			UserAuthStatisticsMapping userAuthStatisticsMapping = new UserAuthStatisticsMapping();
			List<UserAuthStatisticsMapper> list = new ArrayList<>();
			list.addAll(_get(type, day, hour, month, year, null, null, null , userAuthDimensionMapper.getValue()));
			userAuthStatisticsMapping.setUserAuthDimensionMapper(userAuthDimensionMapper);
			userAuthStatisticsMapping.setUserAuthStatisticsMappers(list);
			userAuthStatisticsMappings.add(userAuthStatisticsMapping);

		}
		return userAuthStatisticsMappings;
	}
}
