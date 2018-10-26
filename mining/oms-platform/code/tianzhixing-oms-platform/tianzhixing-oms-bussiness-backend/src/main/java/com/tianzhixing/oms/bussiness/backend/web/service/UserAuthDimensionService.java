package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAuthDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserAuthDimensionService {

    @Autowired
    private RPCUserAuthDimensionService rpcUserAuthDimensionService;

    
    public long count() {
        return rpcUserAuthDimensionService.count();
    }

    public List<UserAuthDimensionMapper> list(PagerMapping pagerMapping) {
        return rpcUserAuthDimensionService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public UserAuthDimensionMapper add(UserAuthDimensionMapper userAuthDimensionMapper) {
        return rpcUserAuthDimensionService.add(userAuthDimensionMapper);
    }

    public UserAuthDimensionMapper getById(long id) {
        return rpcUserAuthDimensionService.getById(id);
    }

    public void update(UserAuthDimensionMapper userAuthDimensionMapper) {
    	UserAuthDimensionMapper am = rpcUserAuthDimensionService.getById(userAuthDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcUserAuthDimensionService.update(userAuthDimensionMapper);
    }

}
