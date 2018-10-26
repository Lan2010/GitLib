package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.AdvertisementDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.AdvertisementDimensionModel;
import com.tianzhixing.oms.bussiness.service.AdvertisementDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("advertisementDimensionService")
public class AdvertisementDimensionServiceImpl implements AdvertisementDimensionService {

    @Autowired
    private AdvertisementDimensionDao advertisementDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AdvertisementDimensionModel> list(boolean enable) {
        return advertisementDimensionDao.list(enable);
    }
}
