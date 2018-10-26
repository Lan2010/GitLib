package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.StarPointConsumeDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCStarPointConsumeDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class StarPointConsumeDimensionService {

    @Autowired
    private RPCStarPointConsumeDimensionService rpcStarPointConsumeDimensionService;

    
    public long count() {
        return rpcStarPointConsumeDimensionService.count();
    }

    public List<StarPointConsumeDimensionMapper> list(PagerMapping pagerMapping) {
        return rpcStarPointConsumeDimensionService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public StarPointConsumeDimensionMapper add(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper) {
        return rpcStarPointConsumeDimensionService.add(starPointConsumeDimensionMapper);
    }

    public StarPointConsumeDimensionMapper getById(long id) {
        return rpcStarPointConsumeDimensionService.getById(id);
    }

    public void update(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper) {
    	StarPointConsumeDimensionMapper am = rpcStarPointConsumeDimensionService.getById(starPointConsumeDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcStarPointConsumeDimensionService.update(starPointConsumeDimensionMapper);
    }

}
