package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.AdvertisementDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCAdvertisementDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class AdvertisementDimensionService {

    @Autowired
    private RPCAdvertisementDimensionService rpcAdvertisementDimensionService;

    public List<AdvertisementDimensionMapper> list(boolean enable) {
        return rpcAdvertisementDimensionService.list(enable);
    }
}
