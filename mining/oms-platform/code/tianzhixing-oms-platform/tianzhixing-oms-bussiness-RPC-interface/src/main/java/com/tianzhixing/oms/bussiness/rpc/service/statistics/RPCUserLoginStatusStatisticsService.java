package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserLoginStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface RPCUserLoginStatusStatisticsService {

    /**
     * insert
     *
     * @param userLoginStatusStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
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
    List<UserLoginStatusStatisticsMapper> list(Integer day, Integer month, Integer year, String clientPlatformType, String platformFrom, String platformName);
}
