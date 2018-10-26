package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.StarPointConsumeDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Repository("starPointDimensionDao")
public class StarPointDimensionDao extends GenericBaseDao<StarPointConsumeDimensionModel, Long> {

    /**
     * list
     *
     * @param enable
     * @return
     */
    public List<StarPointConsumeDimensionModel> list(final boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
    
    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        StringBuffer sql = new StringBuffer("select count(*) from star_point_consume_dimension where 1 = 1 ");
        return super.count(sql.toString());
    }

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<StarPointConsumeDimensionModel> list(final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from star_point_consume_dimension where 1 = 1 ");
        return super.list(new StringBuffer(sql).append(" limit ").append(from).append(",").append(pageSize).toString());
    }


    /**
     * 添加
     *
     * @param StarPointConsumeDimensionModel
     * @return
     */
    public StarPointConsumeDimensionModel add(final StarPointConsumeDimensionModel starPointConsumeDimensionModel) {
        long id = super.insert(starPointConsumeDimensionModel);
        starPointConsumeDimensionModel.setId(id);
        return starPointConsumeDimensionModel;
    }
}
