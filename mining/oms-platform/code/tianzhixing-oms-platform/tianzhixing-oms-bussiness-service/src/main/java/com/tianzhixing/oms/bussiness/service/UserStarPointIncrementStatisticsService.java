package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointIncrementStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface UserStarPointIncrementStatisticsService {

    /**
     * insert
     *
     * @param userStarPointIncrementStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserStarPointIncrementStatisticsModel> userStarPointIncrementStatisticsModels, Long taskAllotId);
    
    /**
     * 获取统计值（sum）
     *
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @return
     */
    List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, Integer incrementType);

    /**
	 * 获取统计值（sum）
	 *
	 * @param from
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> listSum(int type, Integer hour, Integer day, Integer month, Integer year,String platformName);
}
