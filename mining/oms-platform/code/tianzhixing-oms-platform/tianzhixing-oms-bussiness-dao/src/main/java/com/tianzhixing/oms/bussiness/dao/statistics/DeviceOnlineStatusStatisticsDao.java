package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceOnlineStatusStatisticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/7.
 */
@Repository("deviceOnlineStatusStatisticsDao")
public class DeviceOnlineStatusStatisticsDao extends GenericBaseDao<DeviceOnlineStatusStatisticsModel, Long> {

	/**
	 * 获取统计值（sum）
	 * 
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> listSum(final int type, final Integer hour, final Integer day, final Integer month, final Integer year, final String value) {
		StringBuffer conditionBuffer = new StringBuffer("select " 
				+ "sum(bind_device_online_count) as bindDeviceOnlineCount," 
				+ "sum(bind_device_diffid_online_count) as bindDeviceDiffIDOnlineCount, " 
				+ "sum(unbind_device_online_count) as unBindDeviceOnlineCount, " 
				+ "sum(unbind_device_diffid_online_count) as unBindDeviceDiffIDOnlineCount," 
				+ "sum(bind_device_offline_count) as bindDeviceOfflineCount," 
				+ "sum(bind_device_diffid_offline_count) as bindDeviceDiffIDOfflineCount, " 
				+ "sum(unbind_device_offline_count) as unBindDeviceOfflineCount, " 
				+ "sum(unbind_device_diffid_offline_count) as unBindDeviceDiffIDOfflineCount," 
				+ "sum(unbind_device_current_online_count) as unBindDeviceCurrentOnlineCount," 
				+ "sum(bind_device_current_online_count) as bindDeviceCurrentOnlineCount");
		StringBuffer groupBuffer = new StringBuffer("");
		List<String> properties = new ArrayList<>();
		properties.add("bindDeviceOnlineCount");
		properties.add("bindDeviceDiffIDOnlineCount");
		properties.add("unBindDeviceOnlineCount");
		properties.add("unBindDeviceDiffIDOnlineCount");
		properties.add("bindDeviceOfflineCount");
		properties.add("bindDeviceDiffIDOfflineCount");
		properties.add("unBindDeviceOfflineCount");
		properties.add("unBindDeviceDiffIDOfflineCount");
		properties.add("unBindDeviceCurrentOnlineCount");
		properties.add("bindDeviceCurrentOnlineCount");
		String sql = _condition(conditionBuffer.append(" from device_online_status_statistics where 1 = 1 "), type, hour, day, month, year,value) + groupBuffer.toString();
		return super.listProperties(sql, properties.toArray(new String[properties.size()]));
	}

	public long count() {
		StringBuffer conditionBuffer = new StringBuffer("select count(*) ");
		String sql = conditionBuffer.append(" from device_dimension where 1 = 1 and enable = true").toString();
		return super.count(sql);
	}

	/**
	 * 组装条件
	 *
	 * @param sql
	 * @param statisticsHour
	 * @param statisticsDay
	 * @param statisticsMonth
	 * @param statisticsYear
	 * @return
	 */
	private String _condition(StringBuffer sql, int type, Integer hour, Integer day, Integer month, Integer year, String value) {
		if (StringUtils.isNotEmpty(value)) {
			sql.append(" and device_type = '").append(value).append("'");
		}
		if (type == 0) {
			if (hour != null) {
				sql.append(" and statistics_hour <= ").append(hour);
			}
			if (day != null) {
				sql.append(" and statistics_day = ").append(day);
			}
			if (month != null) {
				sql.append(" and statistics_month = ").append(month);
			}
			if (year != null) {
				sql.append(" and statistics_year = ").append(year);
			}
		} else if (type == 1) {
			if (day != null) {
				sql.append(" and statistics_day <= ").append(day);
			}
			if (month != null) {
				sql.append(" and statistics_month = ").append(month);
			}
			if (year != null) {
				sql.append(" and statistics_year = ").append(year);
			}
		} else if (type == 2) {
			if (month != null) {
				sql.append(" and statistics_month <= ").append(month);
			}
			if (year != null) {
				sql.append(" and statistics_year = ").append(year);
			}
		}
		return sql.toString();
	}
}
