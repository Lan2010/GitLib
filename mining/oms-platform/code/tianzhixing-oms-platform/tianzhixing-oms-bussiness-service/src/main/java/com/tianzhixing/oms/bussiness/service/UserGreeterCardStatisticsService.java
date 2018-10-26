package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserGreeterCardStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface UserGreeterCardStatisticsService {

    /**
     * insert
     *
     * @param userGreeterCardStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserGreeterCardStatisticsModel> userGreeterCardStatisticsModels, Long taskAllotId);
    
    /**
     * 获取统计值（sum）
     *
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @return
     */
    List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear);
}
