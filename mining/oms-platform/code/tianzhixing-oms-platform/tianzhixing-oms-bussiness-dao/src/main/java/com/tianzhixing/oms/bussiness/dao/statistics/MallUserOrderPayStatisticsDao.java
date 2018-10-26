package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.MallUserOrderPayStatisticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/7.
 */
@Repository("mallUserOrderPayStatisticsDao")
public class MallUserOrderPayStatisticsDao extends GenericBaseDao<MallUserOrderPayStatisticsModel, Long> {

	/**
	 * 获取统计值（sum）
	 * 
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> list(final int type, final Integer hour, final Integer day, final Integer month, final Integer year, final String platformName) {
		StringBuffer conditionBuffer = new StringBuffer("select sum(already_pay_count) as alreadyPayCount,sum(already_pay_amount) as alreadyPayAmount,sum(await_pay_count) as awaitPayCount,sum(await_pay_amount) as awaitPayAmount,sum(failed_pay_count) as failedPayCount,sum(failed_pay_amount) as failedPayAmount");
		StringBuffer groupBuffer = new StringBuffer("");
		List<String> properties = new ArrayList<>();
		properties.add("alreadyPayCount");
		properties.add("alreadyPayAmount");
		properties.add("awaitPayCount");
		properties.add("awaitPayAmount");
		properties.add("failedPayCount");
		properties.add("failedPayAmount");
		String sql = _conditions(conditionBuffer.append(" from mall_user_order_pay_statistics where 1 = 1"), type, hour, day, month, year, platformName) + groupBuffer.toString();
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

	/**
	 * 获取统计值（sum）
	 * 
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> listSumByType(final int type, final Integer hour, final Integer day, final Integer month, final Integer year, final String value) {
		StringBuffer conditionBuffer = new StringBuffer("select sum(already_pay_count) as alreadyPayCount,sum(already_pay_amount) as alreadyPayAmount,sum(await_pay_count) as awaitPayCount,sum(await_pay_amount) as awaitPayAmount,sum(failed_pay_count) as failedPayCount,sum(failed_pay_amount) as failedPayAmount");
		StringBuffer groupBuffer = new StringBuffer("");
		List<String> properties = new ArrayList<>();
		properties.add("alreadyPayCount");
		properties.add("alreadyPayAmount");
		properties.add("awaitPayCount");
		properties.add("awaitPayAmount");
		properties.add("failedPayCount");
		properties.add("failedPayAmount");
		String sql = _condition(conditionBuffer.append(" from mall_user_order_pay_statistics where 1 = 1"), type, hour, day, month, year, value) + groupBuffer.toString();
		return super.listProperties(sql, properties.toArray(new String[properties.size()]));
	}

	public long count() {
		StringBuffer conditionBuffer = new StringBuffer("select count(*) ");
		String sql = conditionBuffer.append(" from mall_product_dimension where 1 = 1 and enable = true").toString();
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
			sql.append(" and product_type = '").append(value).append("'");
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

	/**
	 * 获取统计值（sum）
	 * 
	 * @param from
	 * @param pageSize
	 * @return
	 */
	public List<Map<String, Object>> listByAppAndType(final int type, final Integer hour, final Integer day, final Integer month, final Integer year, final String platformName, final String value) {
		StringBuffer conditionBuffer = new StringBuffer("select sum(already_pay_count) as alreadyPayCount,sum(already_pay_amount) as alreadyPayAmount,sum(await_pay_count) as awaitPayCount,sum(await_pay_amount) as awaitPayAmount,sum(failed_pay_count) as failedPayCount,sum(failed_pay_amount) as failedPayAmount");
		StringBuffer groupBuffer = new StringBuffer("");
		List<String> properties = new ArrayList<>();
		properties.add("alreadyPayCount");
		properties.add("alreadyPayAmount");
		properties.add("awaitPayCount");
		properties.add("awaitPayAmount");
		properties.add("failedPayCount");
		properties.add("failedPayAmount");
		String sql = condition(conditionBuffer.append(" from mall_user_order_pay_statistics where 1 = 1"), type, hour, day, month, year, platformName,value) + groupBuffer.toString();
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
	private String condition(StringBuffer sql, int type, Integer hour, Integer day, Integer month, Integer year, String platformName, final String value) {
		if (StringUtils.isNotEmpty(platformName)) {
			sql.append(" and platform_name = '").append(platformName).append("'");
		}
		if (StringUtils.isNotEmpty(value)) {
			sql.append(" and product_type = '").append(value).append("'");
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
