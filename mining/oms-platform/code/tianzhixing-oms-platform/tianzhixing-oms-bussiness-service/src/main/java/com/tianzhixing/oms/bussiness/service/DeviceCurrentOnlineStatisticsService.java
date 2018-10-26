package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceCurrentOnlineStatisticsModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
public interface DeviceCurrentOnlineStatisticsService {

    /**
     * insert
     *
     * @param deviceCurrentOnlineStatisticsModel
     */
    void insert(DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel);

    /**
     * 通过deviceid & platform获取
     *
     * @param deviceId
     * @param platform
     * @return
     */
    DeviceCurrentOnlineStatisticsModel getByDeviceId(String deviceId, String platform);

    /**
     * 修改状态
     *
     * @param deviceCurrentOnlineStatisticsModel
     * @param status
     */
    void changeStatus(DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel, Integer status);

    /**
     * 根据状态分页获取
     *
     * @param status
     * @param from
     * @param pageSize
     * @return
     */
    List<DeviceCurrentOnlineStatisticsModel> list(Integer status, int from, int pageSize);

    /**
     * 根据状态获取数量
     *
     * @param status
     * @return
     */
    Long countByStatus(Integer status);
}
