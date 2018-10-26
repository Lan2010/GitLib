package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserChannelDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Repository("userChannelDimensionDao")
public class UserChannelDimensionDao extends GenericBaseDao<UserChannelDimensionModel, Long> {

    /**
     * list
     *
     * @param enable
     * @return
     */
    public List<UserChannelDimensionModel> list(final boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
    
    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        StringBuffer sql = new StringBuffer("select count(*) from user_channel_dimension where 1 = 1 ");
        return super.count(sql.toString());
    }

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<UserChannelDimensionModel> list(final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from user_channel_dimension where 1 = 1 ");
        return super.list(new StringBuffer(sql).append(" limit ").append(from).append(",").append(pageSize).toString());
    }


    /**
     * 添加
     *
     * @param UserChannelDimensionModel
     * @return
     */
    public UserChannelDimensionModel add(final UserChannelDimensionModel userChannelDimensionModel) {
        long id = super.insert(userChannelDimensionModel);
        userChannelDimensionModel.setId(id);
        return userChannelDimensionModel;
    }
}
