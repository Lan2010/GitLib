package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceCheckinStatisticsModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface DeviceCheckinStatisticsService {

    /**
     * 插入记录，并更新任务
     *
     * @param deviceCheckinStatisticsModels
     * @param taskAllotId
     */
    void insert(List<DeviceCheckinStatisticsModel> deviceCheckinStatisticsModels, Long taskAllotId);
}
