package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserBasicStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface RPCUserBasicStatisticsService {

    /**
     * insert
     *
     * @param userBasicStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserBasicStatisticsMapper> userBasicStatisticsMappers, TaskAllotMapper taskAllotMapper);

    /**
     * 获取时段内统计数据(sum)
     *
     * @param statisticsHour
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @param userFromType
     * @return
     */
    UserBasicStatisticsMapper get(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType);

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
    List<UserBasicStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType , String userFromTypeName);
}
