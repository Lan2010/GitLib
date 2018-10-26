package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class StatisticsDimensionService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;


    public long count() {
        return rpcApplicationDimensionService.count();
    }

    public List<ApplicationDimensionMapper> list(PagerMapping pagerMapping) {
        return rpcApplicationDimensionService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public ApplicationDimensionMapper add(ApplicationDimensionMapper applicationDimensionMapper) {
        return rpcApplicationDimensionService.add(applicationDimensionMapper);
    }

    public List<ApplicationDimensionMapper> list(Boolean enable) {
        return rpcApplicationDimensionService.list(null, true);
    }

    public ApplicationDimensionMapper getById(long id) {
        return rpcApplicationDimensionService.getById(id);
    }

    public void update(ApplicationDimensionMapper applicationDimensionMapper) {
        ApplicationDimensionMapper am = rpcApplicationDimensionService.getById(applicationDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcApplicationDimensionService.update(applicationDimensionMapper);
    }

}
