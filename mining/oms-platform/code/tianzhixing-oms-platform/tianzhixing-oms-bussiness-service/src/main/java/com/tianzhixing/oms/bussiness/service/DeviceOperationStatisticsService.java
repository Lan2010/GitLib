package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceOperationStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface DeviceOperationStatisticsService {

    /**
     * insert
     *
     * @param deviceOperationStatisticsModels
     * @param taskAllotId
     */
    void insert(List<DeviceOperationStatisticsModel> deviceOperationStatisticsModels, Long taskAllotId);
    
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
    List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String deviceType);
}
