package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserTaskStatisticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/9.
 */
@Repository("userTaskStatisticsDao")
public class UserTaskStatisticsDao extends GenericBaseDao<UserTaskStatisticsModel, Long> {
	

    /**
     * 获取统计值（sum）
     * @param from
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> listSum(final String taskId) {
        StringBuffer conditionBuffer = new StringBuffer("select sum(accept_count) as acceptCount, sum(cancel_count) as cancelCount");
        StringBuffer groupBuffer = new StringBuffer("");
        List<String> properties = new ArrayList<>();
        properties.add("acceptCount");
        properties.add("cancelCount");
        String sql = conditionBuffer.append(" from user_task_statistics where 1 = 1").toString() + groupBuffer.append(" and task_id = '").append(taskId).append("'").toString();
        return super.listProperties(sql, properties.toArray(new String[properties.size()]));
    }
    
    public long count() {
    	StringBuffer conditionBuffer = new StringBuffer("select count(*) ");
    	String sql = conditionBuffer.append(" from task_dimension where 1 = 1 and enable = true").toString() ;
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
     * @param clientPlatformType
     * @param platformFrom
     * @return
     */
    private String _condition(StringBuffer sql, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String taskId) {
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

        if (StringUtils.isNotEmpty(clientPlatformType)) {
            sql.append(" and client_platform_type = '").append(clientPlatformType).append("'");
        }
        if (StringUtils.isNotEmpty(platformFrom)) {
            sql.append(" and platform_from = '").append(platformFrom).append("'");
        }
        if (StringUtils.isNotEmpty(taskId)) {
            sql.append(" and task_id = '").append(taskId).append("'");
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
    public List<Map<String, Object>> listSum(final Integer statisticsDay, final Integer statisticsMonth, final Integer statisticsYear, final String clientPlatformType, final String platformFrom, final String taskId) {
        StringBuffer conditionBuffer = new StringBuffer("select sum(accept_count) as acceptCount,sum(cancel_count) as cancelCount ");
        StringBuffer groupBuffer = new StringBuffer(" group by statistics_year, statistics_month ");
        List<String> properties = new ArrayList<>();
        properties.add("acceptCount");
        properties.add("cancelCount");
        properties.add("statisticsMonth");
        properties.add("statisticsYear");
        if (statisticsDay != null) {
            //查询小时
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
        String sql = _condition(conditionBuffer.append(" from user_task_statistics where 1 = 1 "), null, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,taskId) + groupBuffer.toString();
        return super.listProperties(sql, properties.toArray(new String[properties.size()]));
    }
}
