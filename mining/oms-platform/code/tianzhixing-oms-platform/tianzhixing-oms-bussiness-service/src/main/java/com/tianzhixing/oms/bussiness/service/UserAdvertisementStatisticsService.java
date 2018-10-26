package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserAdvertisementStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface UserAdvertisementStatisticsService {

    /**
     * insert
     *
     * @param userAdvertisementStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserAdvertisementStatisticsModel> userAdvertisementStatisticsModels, Long taskAllotId);
    
    /**
     * 获取统计值（sum）
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<Map<String, Object>> listSum(String advId);
   
    /**
     * 计算数量
     *
     * @return
     */
    long count();
    
}
