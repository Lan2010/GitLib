package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAdvertisementStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface RPCUserAdvertisementStatisticsService {

    /**
     * insert
     *
     * @param userAdvertisementStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<UserAdvertisementStatisticsMapper> list(String advId);
    
    /**
     * 计算数量
     *
     * @return
     */
    long count();
}
