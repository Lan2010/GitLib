package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallUserOrderPayStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface RPCMallUserOrderPayStatisticsService {

    /**
     * 插入数据
     *
     * @param mallUserOrderPayStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<MallUserOrderPayStatisticsMapper> mallUserOrderPayStatisticsMappers, TaskAllotMapper taskAllotMapper);
    
    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<MallUserOrderPayStatisticsMapper> list(int type,Integer hour, Integer day, Integer month,Integer year,String platformName);

    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<MallUserOrderPayStatisticsMapper> listByType(int type,Integer hour, Integer day, Integer month,Integer year,String value);
    
    /**
     * 计算数量
     *
     * @return
     */
    long count();
    
    /**
     * 获取统计数据(sum)
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<MallUserOrderPayStatisticsMapper> listByAppAndType(int type,Integer hour, Integer day, Integer month,Integer year,String platformName,String value);
}
