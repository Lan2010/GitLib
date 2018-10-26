package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserPostCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface RPCUserPostCardStatisticsService {

    /**
     * insert
     *
     * @param userPostCardStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserPostCardStatisticsMapper> userPostCardStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
    /**
     * 获取时段内统计数据(sum)
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    List<UserPostCardStatisticsMapper> list(Integer day, Integer month, Integer year);
}
