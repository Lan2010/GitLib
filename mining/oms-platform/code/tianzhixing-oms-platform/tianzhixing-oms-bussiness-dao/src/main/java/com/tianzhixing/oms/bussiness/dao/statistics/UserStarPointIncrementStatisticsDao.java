package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointIncrementStatisticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/12.
 */
@Repository("userStarPointIncrementStatisticsDao")
public class UserStarPointIncrementStatisticsDao extends GenericBaseDao<UserStarPointIncrementStatisticsModel, Long> {

	/**
	 * 组装条件
	 *
	 * @param sql
	 * @param statisticsHour
	 * @param statisticsDay
	 * @param statisticsMonth
	 * @param statisticsYear
	 * @param clientPlatformType
	 * @param platformFrom
	 * @return
	 */
	private String _condition(StringBuffer sql, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, Integer incrementType) {
		if (statisticsHour != null) {
			sql.append(" and statistics_hour = ").append(statisticsHour);
		}

		if (statisticsDay != null) {
			sql.append(" and statistics_day = ").append(statisticsDay);
		}

		if (statisticsMonth != null) {
			sql.append(" and statistics_month = ").append(statisticsMonth);
		}

		if (statisticsYear != null) {
			sql.append(" and statistics_year = ").append(statisticsYear);
		}

		if (incrementType != null) {
			sql.append(" and increment_type = ").append(incrementType);
		}

		if (StringUtils.isNotEmpty(clientPlatformType)) {
			sql.append(" and client_platform_type = '").append(clientPlatformType).append("'");
		}
		if (StringUtils.isNotEmpty(platformFrom)) {
			sql.append(" and platform_from = '").append(platformFrom).append("'");
		}
		return sql.toString();
	}

	/**
	 * 获取统计值（sum）
	 * 
	 * @param statisticsDay
	 * @param statisticsMonth
	 * @param statisticsYear
	 * @param clientPlatformType
	 * @param platformFrom
	 * @return
	 */
	public List<Map<String, Object>> listSum(final Integer statisticsDay, final Integer statisticsMonth, final Integer statisticsYear, final String clientPlatformType, final String platformFrom, final Integer incrementType) {
		StringBuffer conditionBuffer = new StringBuffer("select sum(increment_count) as incrementCount");
		StringBuffer groupBuffer = new StringBuffer(" group by statistics_year, statistics_month ");
		List<String> properties = new ArrayList<>();
		properties.add("incrementCount");
		properties.add("statisticsMonth");
		properties.add("statisticsYear");
		if (statisticsDay != null) {
			// 查询小时
			conditionBuffer.append(", statistics_hour as statisticsHour ");
			groupBuffer.append(", statistics_hour");
			properties.add("statisticsHour");
		}
		if (statisticsMonth != null) {
			conditionBuffer.append(", statistics_day as statisticsDay ");
			groupBuffer.append(", statistics_day");
			properties.add("statisticsDay");
		}
		conditionBuffer.append(", statistics_month as statisticsMonth, statistics_year as statisticsYear ");
		String sql = _condition(conditionBuffer.append(" from user_starpoint_increment_statistics where 1 = 1 "), null, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, incrementType) + groupBuffer.toString();
		return super.listProperties(sql, properties.toArray(new String[properties.size()]));
	}

	/**
	 * 获取统计值（sum）
	 * 
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> list(final int type, final Integer hour, final Integer day, final Integer month, final Integer year, final String platformName) {
		StringBuffer conditionBuffer = new StringBuffer("select sum(increment_count) as incrementCount,increment_type as incrementType");
		StringBuffer groupBuffer = new StringBuffer(" group by increment_type");
		List<String> properties = new ArrayList<>();
		properties.add("incrementCount");
		properties.add("incrementType");
		String sql = _conditions(conditionBuffer.append(" from user_starpoint_increment_statistics where 1 = 1"), type, hour, day, month, year, platformName) + groupBuffer.toString();
		return super.listProperties(sql, properties.toArray(new String[properties.size()]));
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
	private String _conditions(StringBuffer sql, int type, Integer hour, Integer day, Integer month, Integer year, String platformName) {
		if (StringUtils.isNotEmpty(platformName)) {
			sql.append(" and platform_name = '").append(platformName).append("'");
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
