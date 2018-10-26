package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class DeviceDimensionService {

    @Autowired
    private RPCDeviceDimensionService rpcDeviceDimensionService;

    
    public long count() {
        return rpcDeviceDimensionService.count();
    }

    public List<DeviceDimensionMapper> list(PagerMapping pagerMapping) {
        return rpcDeviceDimensionService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public DeviceDimensionMapper add(DeviceDimensionMapper deviceDimensionMapper) {
        return rpcDeviceDimensionService.add(deviceDimensionMapper);
    }

    public DeviceDimensionMapper getById(long id) {
        return rpcDeviceDimensionService.getById(id);
    }

    public void update(DeviceDimensionMapper deviceDimensionMapper) {
    	DeviceDimensionMapper am = rpcDeviceDimensionService.getById(deviceDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcDeviceDimensionService.update(deviceDimensionMapper);
    }

    public List<DeviceDimensionMapper> list(boolean enable) {
        return rpcDeviceDimensionService.list(enable);
    }
}
