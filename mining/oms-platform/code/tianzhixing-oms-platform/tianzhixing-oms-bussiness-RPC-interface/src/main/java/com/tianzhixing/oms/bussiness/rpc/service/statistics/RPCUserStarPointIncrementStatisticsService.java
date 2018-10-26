package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointIncrementStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface RPCUserStarPointIncrementStatisticsService {

    /**
     * insert
     *
     * @param userStarPointIncrementStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
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
    List<UserStarPointIncrementStatisticsMapper> list(Integer day, Integer month, Integer year, String clientPlatformType, String platformFrom, String platformName,Integer incrementType);
    
    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<UserStarPointIncrementStatisticsMapper> list(int type,Integer hour, Integer day, Integer month,Integer year,String platformName);
}
