package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.AdvertisementDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface AdvertisementDimensionService {

    /**
     * list
     * @param enable
     * @return
     */
    List<AdvertisementDimensionModel> list(boolean enable);
}
