package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserBasicStatisticsModel;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/10.
 */
@Repository("userBasicStatisticsDao")
public class UserBasicStatisticsDao extends GenericBaseDao<UserBasicStatisticsModel, Long> {

    /**
     * 获取统计值（sum）
     *
     * @param statisticsHour
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @return
     */
    public Map<String, Object> getSum(final Integer statisticsHour, final Integer statisticsDay, final Integer statisticsMonth, final Integer statisticsYear, final String clientPlatformType, final String platformFrom, final String userFromType) {
        String sql = "select sum(user_count) as userCount from user_basic_statistics where 1 = 1 ";
        return super.getProperties(_condition(new StringBuffer(sql), statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, userFromType), new String[]{"userCount"});
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
     * @param userFromType
     * @return
     */
    private String _condition(StringBuffer sql, Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType) {
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
        if (StringUtils.isNotEmpty(userFromType)) {
            sql.append(" and user_from_type = '").append(userFromType).append("'");
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
     * @param userFromType
     * @return
     */
    public List<Map<String, Object>> listSum(final Integer statisticsDay, final Integer statisticsMonth, final Integer statisticsYear, final String clientPlatformType, final String platformFrom, final String userFromType) {
        StringBuffer conditionBuffer = new StringBuffer("select sum(user_count) as userCount ");
        StringBuffer groupBuffer = new StringBuffer(" group by statistics_year, statistics_month ");
        List<String> properties = new ArrayList<>();
        properties.add("userCount");
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
        String sql = _condition(conditionBuffer.append(" from user_basic_statistics where 1 = 1 "), null, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, userFromType) + groupBuffer.toString();
        return super.listProperties(sql, properties.toArray(new String[properties.size()]));
    }
}
