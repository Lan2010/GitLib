package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointIncrementStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointIncrementStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserStarPointIncrementStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserStarPointIncrementStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("RPCUserStarPointIncrementStatisticsService")
public class RPCUserStarPointIncrementStatisticsServiceImpl implements RPCUserStarPointIncrementStatisticsService {

	@Autowired
	private UserStarPointIncrementStatisticsService userStarPointIncrementStatisticsService;

	@Override
	public void insert(List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers, TaskAllotMapper taskAllotMapper) {
		if (userStarPointIncrementStatisticsMappers != null) {
			List<UserStarPointIncrementStatisticsModel> userStarPointIncrementStatisticsModels = new ArrayList<>();
			for (UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper : userStarPointIncrementStatisticsMappers) {
				UserStarPointIncrementStatisticsModel userStarPointIncrementStatisticsModel = new UserStarPointIncrementStatisticsModel();
				BeanUtils.copyProperties(userStarPointIncrementStatisticsMapper, userStarPointIncrementStatisticsModel);
				userStarPointIncrementStatisticsModels.add(userStarPointIncrementStatisticsModel);
			}
			userStarPointIncrementStatisticsService.insert(userStarPointIncrementStatisticsModels, taskAllotMapper.getId());
		}
	}

	@Override
	public List<UserStarPointIncrementStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName, Integer incrementType) {
		List<Map<String, Object>> listMap = userStarPointIncrementStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, incrementType);
		List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = new ArrayList<>();
		for (Map<String, Object> map : listMap) {
			userStarPointIncrementStatisticsMappers.add(new UserStarPointIncrementStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformFrom, map.get("incrementCount") == null ? 0 : Double.valueOf(map.get("incrementCount").toString()), null, incrementType, null, platformName));
		}
		return userStarPointIncrementStatisticsMappers;
	}

	@Override
	public List<UserStarPointIncrementStatisticsMapper> list(int type, Integer hour, Integer day, Integer month, Integer year, String platformName) {
		List<Map<String, Object>> listMap = userStarPointIncrementStatisticsService.listSum(type, hour, day, month, year, platformName);
		List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = new ArrayList<>();
		for (Map<String, Object> map : listMap) {
			userStarPointIncrementStatisticsMappers.add(new UserStarPointIncrementStatisticsMapper(null, null, null, null, null, null, null, null, null, map.get("incrementCount") == null ? 0.0d : Double.valueOf(map.get("incrementCount").toString()), null, map.get("incrementType") == null ? 0 : Integer.valueOf(map.get("incrementType").toString()), null, platformName));
		}
		return userStarPointIncrementStatisticsMappers;
	}
}
