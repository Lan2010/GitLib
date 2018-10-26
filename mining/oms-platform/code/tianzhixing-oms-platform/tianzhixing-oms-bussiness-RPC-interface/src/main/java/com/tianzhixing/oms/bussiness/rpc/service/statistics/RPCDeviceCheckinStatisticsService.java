package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceCheckinStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface RPCDeviceCheckinStatisticsService {

    /**
     * 插入数据
     *
     * @param deviceCheckinStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<DeviceCheckinStatisticsMapper> deviceCheckinStatisticsMappers, TaskAllotMapper taskAllotMapper);
}
