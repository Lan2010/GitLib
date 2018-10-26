package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.AdvertisementDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Repository("advertisementDimensionDao")
public class AdvertisementDimensionDao extends GenericBaseDao<AdvertisementDimensionModel, Long> {

    /**
     * list
     *
     * @param enable
     * @return
     */
    public List<AdvertisementDimensionModel> list(final boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
}
