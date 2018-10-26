package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Repository("deviceDimensionDao")
public class DeviceDimensionDao extends GenericBaseDao<DeviceDimensionModel, Long> {

    /**
     * 获取列表
     *
     * @param enable
     * @return
     */
    public List<DeviceDimensionModel> list(final Boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
    
    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        StringBuffer sql = new StringBuffer("select count(*) from device_dimension where 1 = 1 ");
        return super.count(sql.toString());
    }

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<DeviceDimensionModel> list(final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from device_dimension where 1 = 1 ");
        return super.list(new StringBuffer(sql).append(" limit ").append(from).append(",").append(pageSize).toString());
    }


    /**
     * 添加
     *
     * @param DeviceDimensionModel
     * @return
     */
    public DeviceDimensionModel add(final DeviceDimensionModel deviceDimensionModel) {
        long id = super.insert(deviceDimensionModel);
        deviceDimensionModel.setId(id);
        return deviceDimensionModel;
    }
}
