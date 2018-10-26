package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallProductDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCMallProductDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class MallProductDimensionService {

    @Autowired
    private RPCMallProductDimensionService rpcMallProductDimensionService;

    
    public long count() {
        return rpcMallProductDimensionService.count();
    }

    public List<MallProductDimensionMapper> list(PagerMapping pagerMapping) {
        return rpcMallProductDimensionService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public MallProductDimensionMapper add(MallProductDimensionMapper deviceDimensionMapper) {
        return rpcMallProductDimensionService.add(deviceDimensionMapper);
    }

    public MallProductDimensionMapper getById(long id) {
        return rpcMallProductDimensionService.getById(id);
    }

    public void update(MallProductDimensionMapper deviceDimensionMapper) {
    	MallProductDimensionMapper am = rpcMallProductDimensionService.getById(deviceDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcMallProductDimensionService.update(deviceDimensionMapper);
    }

    public List<MallProductDimensionMapper> list(boolean enable) {
        return rpcMallProductDimensionService.list(enable);
    }
}
