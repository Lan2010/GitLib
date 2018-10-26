package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceCurrentOnlineStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
public interface RPCDeviceCurrentOnlineStatisticsService {

    /**
     * 添加
     *
     * @param deviceCurrentOnlineStatisticsMapper
     */
    void insert(DeviceCurrentOnlineStatisticsMapper deviceCurrentOnlineStatisticsMapper);

    /**
     * 根据device id & platform 获取
     *
     * @param deviceId
     * @param platform
     * @return
     */
    DeviceCurrentOnlineStatisticsMapper getByDeviceId(String deviceId, String platform);

    /**
     * 更改状态
     *
     * @param status
     * @param deviceId
     * @param platform
     */
    void changeStatus(Integer status, String deviceId, String platform);

    /**
     * 根据状态获取分页清单
     *
     * @param status
     * @param from
     * @param pageSize
     * @return
     */
    List<DeviceCurrentOnlineStatisticsMapper> listByStatus(Integer status, int from, int pageSize);

    /**
     * 根据状态获取数量
     *
     * @param status
     * @return
     */
    Long countByStatus(Integer status);
}
