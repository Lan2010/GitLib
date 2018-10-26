package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserTaskStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface UserTaskStatisticsService {

    /**
     * insert
     *
     * @param userTaskStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserTaskStatisticsModel> userTaskStatisticsModels, Long taskAllotId);
    
    /**
     * 获取统计值（sum）
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> listSum(String taskId);
   
    /**
     * 计算数量
     *
     * @return
     */
    long count();
    
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
    List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String taskId);
}
