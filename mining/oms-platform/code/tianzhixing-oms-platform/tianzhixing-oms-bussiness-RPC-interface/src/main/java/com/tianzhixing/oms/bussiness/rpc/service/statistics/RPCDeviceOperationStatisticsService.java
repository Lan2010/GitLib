package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface RPCDeviceOperationStatisticsService {

    /**
     * insert
     *
     * @param deviceOperationStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers, TaskAllotMapper taskAllotMapper);

    /**
     * 获取时段内统计数据(sum)
     *
     * @param day
     * @param month
     * @param year
     * @param clientPlatformType
     * @param platformFrom
     * @param platformName
     * @return
     */
    List<DeviceOperationStatisticsMapper> list(Integer day, Integer month, Integer year, String clientPlatformType, String platformFrom, String platformName,String deviceType);
}
