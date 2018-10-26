package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/6.
 */
public interface RPCApplicationOperationStatisticsService {

    /**
     * 添加记录
     *
     * @param applicationOperationStatisticsMapperList
     * @param taskAllotMapper
     */
    void insert(List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMapperList, TaskAllotMapper taskAllotMapper);

    /**
     * 获取时段内统计数据(sum)
     *
     * @param statisticsHour
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @param platformName
     * @return
     */
    ApplicationOperationStatisticsMapper get(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName);

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
    List<ApplicationOperationStatisticsMapper> list(Integer day, Integer month, Integer year, String clientPlatformType, String platformFrom, String platformName);
}
