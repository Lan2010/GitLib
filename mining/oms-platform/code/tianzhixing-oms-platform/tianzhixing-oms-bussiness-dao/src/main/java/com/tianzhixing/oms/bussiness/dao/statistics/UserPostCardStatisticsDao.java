package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserPostCardStatisticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/11.
 */
@Repository("userPostCardStatisticsDao")
public class UserPostCardStatisticsDao extends GenericBaseDao<UserPostCardStatisticsModel, Long> {
	
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
    private String _condition(StringBuffer sql, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear) {
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
        return sql.toString();
    }

    /**
     * 获取统计值（sum）
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @return
     */
    public List<Map<String, Object>> listSum(final Integer statisticsDay, final Integer statisticsMonth, final Integer statisticsYear) {
        StringBuffer conditionBuffer = new StringBuffer("select sum(create_count) as createCount, sum(share_count) as shareCount");
        StringBuffer groupBuffer = new StringBuffer(" group by statistics_year, statistics_month ");
        List<String> properties = new ArrayList<>();
        properties.add("createCount");
        properties.add("shareCount");
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
        String sql = _condition(conditionBuffer.append(" from user_post_card_statistics where 1 = 1 "), null, statisticsDay, statisticsMonth, statisticsYear) + groupBuffer.toString();
        return super.listProperties(sql, properties.toArray(new String[properties.size()]));
    }
}
