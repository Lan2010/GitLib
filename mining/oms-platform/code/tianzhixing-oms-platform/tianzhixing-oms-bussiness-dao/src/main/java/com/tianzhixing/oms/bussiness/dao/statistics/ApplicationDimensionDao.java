package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.ApplicationDimensionModel;

import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/6.
 */
@Repository("applicationDimensionDao")
public class ApplicationDimensionDao extends GenericBaseDao<ApplicationDimensionModel, Long> {

    /**
     * 获取清单
     *
     * @param parentId
     * @param statisticsDimension
     * @param enable
     * @return
     */
    public List<ApplicationDimensionModel> list(final Long parentId, final StatisticsDimension statisticsDimension, final Boolean enable) {
        StringBuffer sql = new StringBuffer("select * from application_dimension where 1 = 1 ");
        return super.list(new StringBuffer(_condition(sql, parentId, statisticsDimension, enable)).toString());
    }


    /**
     * 组装条件
     *
     * @param sql
     * @param parentId
     * @param statisticsDimension
     * @param enable
     * @return
     */
    private String _condition(StringBuffer sql, Long parentId, StatisticsDimension statisticsDimension, Boolean enable) {
        if (parentId != null && parentId != 0) {
            sql.append(" and parent_id = ").append(parentId);
        }
        if (statisticsDimension != null) {
            sql.append(" and statistics_dimension = '").append(statisticsDimension.name()).append("'");
        }
        if (enable != null) {
            sql.append(" and enable = ").append(enable ? 1 : 0);
        }
        return sql.toString();
    }
    
    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        StringBuffer sql = new StringBuffer("select count(*) from application_dimension where 1 = 1 ");
        return super.count(sql.toString());
    }

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<ApplicationDimensionModel> list(final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from application_dimension where 1 = 1 ");
        return super.list(new StringBuffer(sql).append(" limit ").append(from).append(",").append(pageSize).toString());
    }


    /**
     * 添加
     *
     * @param ApplicationDimensionModel
     * @return
     */
    public ApplicationDimensionModel add(final ApplicationDimensionModel applicationDimensionModel) {
        long id = super.insert(applicationDimensionModel);
        applicationDimensionModel.setId(id);
        return applicationDimensionModel;
    }
}
