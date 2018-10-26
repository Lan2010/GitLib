package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.MallProductDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Repository("mallProductDimensionDao")
public class MallProductDimensionDao extends GenericBaseDao<MallProductDimensionModel, Long> {

    /**
     * list
     *
     * @param enable
     * @return
     */
    public List<MallProductDimensionModel> list(final boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
    
    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        StringBuffer sql = new StringBuffer("select count(*) from mall_product_dimension where 1 = 1 ");
        return super.count(sql.toString());
    }

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<MallProductDimensionModel> list(final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from mall_product_dimension where 1 = 1 ");
        return super.list(new StringBuffer(sql).append(" limit ").append(from).append(",").append(pageSize).toString());
    }


    /**
     * 添加
     *
     * @param MallProductDimensionModel
     * @return
     */
    public MallProductDimensionModel add(final MallProductDimensionModel mallProductDimensionModel) {
        long id = super.insert(mallProductDimensionModel);
        mallProductDimensionModel.setId(id);
        return mallProductDimensionModel;
    }
}
