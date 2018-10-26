package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserAuthDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Repository("userAuthDimensionDao")
public class UserAuthDimensionDao extends GenericBaseDao<UserAuthDimensionModel, Long> {

    /**
     * list
     *
     * @param enable
     * @return
     */
    public List<UserAuthDimensionModel> list(final boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
    
    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        StringBuffer sql = new StringBuffer("select count(*) from user_auth_dimension where 1 = 1 ");
        return super.count(sql.toString());
    }

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<UserAuthDimensionModel> list(final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from user_auth_dimension where 1 = 1 ");
        return super.list(new StringBuffer(sql).append(" limit ").append(from).append(",").append(pageSize).toString());
    }


    /**
     * 添加
     *
     * @param UserAuthDimensionModel
     * @return
     */
    public UserAuthDimensionModel add(final UserAuthDimensionModel userAuthDimensionModel) {
        long id = super.insert(userAuthDimensionModel);
        userAuthDimensionModel.setId(id);
        return userAuthDimensionModel;
    }
}
