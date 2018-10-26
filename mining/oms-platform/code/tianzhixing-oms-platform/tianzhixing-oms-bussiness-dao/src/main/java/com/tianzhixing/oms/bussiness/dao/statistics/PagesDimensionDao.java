package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.PagesDimensionModel;
import com.tianzhixing.oms.utils.CalendarUtil;

import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/6.
 */
@Repository("pagesDimensionDao")
public class PagesDimensionDao extends GenericBaseDao<PagesDimensionModel, Long> {

    /**
     * 获取清单
     *
     * @param enable
     * @return
     */
    public List<PagesDimensionModel> list(final Boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
    
    /**
     * 获取数量
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    public long count(final Date sBeginTime, final Date sEndTime, final Date aBeginTime, final Date aEndTime) {
        StringBuffer sql = new StringBuffer("select count(*) from pages_dimension where 1 = 1 ");
        return super.count(_condition(sql, sBeginTime, sEndTime, aBeginTime, aEndTime));
    }

    /**
     * 获取数据
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    public List<PagesDimensionModel> list(final Date sBeginTime, final Date sEndTime, final Date aBeginTime, final Date aEndTime, final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from pages_dimension where 1 = 1 ");
        return super.list(new StringBuffer(_condition(sql, sBeginTime, sEndTime, aBeginTime, aEndTime)).append(" limit ").append(from).append(",").append(pageSize).toString());
    }

    /**
     * 组装条件
     *
     * @param sql
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    private String _condition(StringBuffer sql, Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        if (sBeginTime != null) {
            sql.append(" and begin_time >= '").append(CalendarUtil.dateTime2String(sBeginTime)).append("'");
        }
        if (sEndTime != null) {
            sql.append(" and begin_time <= '").append(CalendarUtil.dateTime2String(sEndTime)).append("'");
        }
        if (aBeginTime != null) {
            sql.append(" and end_time >= '").append(CalendarUtil.dateTime2String(aBeginTime)).append("'");
        }
        if (aEndTime != null) {
            sql.append(" and end_time <= '").append(CalendarUtil.dateTime2String(aEndTime)).append("'");
        }
        return sql.toString();
    }

    /**
     * 添加
     *
     * @param pagesDimensionModel
     * @return
     */
    public PagesDimensionModel add(final PagesDimensionModel pagesDimensionModel) {
        long id = super.insert(pagesDimensionModel);
        pagesDimensionModel.setId(id);
        return pagesDimensionModel;
    }
}
