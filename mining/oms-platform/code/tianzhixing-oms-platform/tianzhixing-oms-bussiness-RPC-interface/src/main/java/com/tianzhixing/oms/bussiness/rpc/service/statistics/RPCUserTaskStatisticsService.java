package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserTaskStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface RPCUserTaskStatisticsService {

    /**
     * insert
     *
     * @param userTaskStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserTaskStatisticsMapper> userTaskStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<UserTaskStatisticsMapper> list(String taskId);
    
    /**
     * 计算数量
     *
     * @return
     */
    long count();
    
    /**
     * 获取时段内统计数据(sum)
     *
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @param userFromType
     * @return
     */
    List<UserTaskStatisticsMapper> list(Integer day, Integer month, Integer year, String clientPlatformType, String platformFrom, String platformName,String taskId);
}
