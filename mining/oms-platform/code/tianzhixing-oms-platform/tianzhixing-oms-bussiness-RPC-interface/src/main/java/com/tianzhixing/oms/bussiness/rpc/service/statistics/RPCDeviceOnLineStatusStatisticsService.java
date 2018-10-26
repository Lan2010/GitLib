package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOnlineStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface RPCDeviceOnLineStatusStatisticsService {

    /**
     * 插入数据并更新任务
     *
     * @param deviceOnlineStatusStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<DeviceOnlineStatusStatisticsMapper> deviceOnlineStatusStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<DeviceOnlineStatusStatisticsMapper> list(int type,Integer hour, Integer day, Integer month,Integer year,String value);
    
    /**
     * 计算数量
     *
     * @return
     */
    long count();
}
