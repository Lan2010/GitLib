package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserAdvertisementStatisticsModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/9.
 */
@Repository("userAdvertisementStatisticsDao")
public class UserAdvertisementStatisticsDao extends GenericBaseDao<UserAdvertisementStatisticsModel, Long> {
	

    /**
     * 获取统计值（sum）
     * @param from
     * @param pageSize
     * @return
     */
    public List<Map<String, Object>> listSum(final String advId) {
        StringBuffer conditionBuffer = new StringBuffer("select sum(click_count) as clickCount, sum(click_diffip_count) as clickDiffIPCount, sum(access_count) as accessCount, sum(access_diffip_count) as accessDiffIPCount");
        StringBuffer groupBuffer = new StringBuffer("");
        List<String> properties = new ArrayList<>();
        properties.add("clickCount");
        properties.add("clickDiffIPCount");
        properties.add("accessCount");
        properties.add("accessDiffIPCount");
        String sql = conditionBuffer.append(" from user_advertisement_statistics where 1 = 1").toString() + groupBuffer.append(" and advertisement_id = '").append(advId).append("'").toString();
        return super.listProperties(sql, properties.toArray(new String[properties.size()]));
    }
    
    public long count() {
    	StringBuffer conditionBuffer = new StringBuffer("select count(*) ");
    	String sql = conditionBuffer.append(" from advertisement_dimension where 1 = 1 and enable = true").toString() ;
        return super.count(sql);
    }
}
