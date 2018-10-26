package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserGreeterCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface RPCUserGreeterCardStatisticsService {

    /**
     * insert
     *
     * @param userGreeterCardStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
    /**
     * 获取时段内统计数据(sum)
     *
     * @param day
     * @param month
     * @param year
     * @return
     */
    List<UserGreeterCardStatisticsMapper> list(Integer day, Integer month, Integer year);
}
