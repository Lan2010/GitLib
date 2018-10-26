package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserBasicStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface UserBasicStatisticsService {

    /**
     * list
     *
     * @param userBasicStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserBasicStatisticsModel> userBasicStatisticsModels, Long taskAllotId);

    /**
     * 获取统计值（sum）
     *
     * @param statisticsHour
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @param userFromType
     * @return
     */
    Map<String, Object> getSum(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType);

    /**
     * 获取统计值（sum）
     *
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @param userFromType
     * @return
     */
    List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType);

}
