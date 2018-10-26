package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceOnlineStatusStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface DeviceOnlineStatusStatisticsService {

	/**
	 * 插入记录，更新任务
	 *
	 * @param deviceOnlineStatusStatisticsModels
	 * @param taskAllotId
	 */
	void insert(List<DeviceOnlineStatusStatisticsModel> deviceOnlineStatusStatisticsModels, Long taskAllotId);

	/**
	 * 获取统计值（sum）
	 *
	 * @param from
	 * @param pageSize
	 * @return
	 */
	List<Map<String, Object>> listSum(int type, Integer hour, Integer day, Integer month, Integer year,String value);

	/**
	 * 计算数量
	 *
	 * @return
	 */
	long count();
}
