package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.AdvertisementDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface RPCAdvertisementDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<AdvertisementDimensionMapper> list(boolean enable);

}
