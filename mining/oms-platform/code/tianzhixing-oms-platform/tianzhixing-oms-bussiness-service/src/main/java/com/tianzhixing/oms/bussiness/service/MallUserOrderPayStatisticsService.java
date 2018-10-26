package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.MallUserOrderPayStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface MallUserOrderPayStatisticsService {

    /**
     * 插入记录，并更新任务
     *
     * @param mallUserOrderPayStatisticsModels
     * @param taskAllotId
     */
    void insert(List<MallUserOrderPayStatisticsModel> mallUserOrderPayStatisticsModels, Long taskAllotId);
    
    /**
   	 * 获取统计值（sum）
   	 *
   	 * @param from
   	 * @param pageSize
   	 * @return
   	 */
   	List<Map<String, Object>> listSum(int type, Integer hour, Integer day, Integer month, Integer year,String platformName);

   	/**
	 * 获取统计值（sum）
	 *
	 * @param from
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> listSumByType(int type, Integer hour, Integer day, Integer month, Integer year,String value);

	/**
	 * 计算数量
	 *
	 * @return
	 */
	long count();
	
	/**
	 * 获取统计值（sum）
	 *
	 * @param from
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> listByAppAndType(int type, Integer hour, Integer day, Integer month, Integer year,String platformName,String value);
}
