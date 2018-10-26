package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserChannelDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserChannelDimensionService {

    @Autowired
    private RPCUserChannelDimensionService rpcUserChannelDimensionService;


    public long count() {
        return rpcUserChannelDimensionService.count();
    }

    public List<UserChannelDimensionMapper> list(PagerMapping pagerMapping) {
        return rpcUserChannelDimensionService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public UserChannelDimensionMapper add(UserChannelDimensionMapper userChannelDimensionMapper) {
        return rpcUserChannelDimensionService.add(userChannelDimensionMapper);
    }

    public UserChannelDimensionMapper getById(long id) {
        return rpcUserChannelDimensionService.getById(id);
    }

    public void update(UserChannelDimensionMapper userChannelDimensionMapper) {
        UserChannelDimensionMapper am = rpcUserChannelDimensionService.getById(userChannelDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcUserChannelDimensionService.update(userChannelDimensionMapper);
    }

    public List<UserChannelDimensionMapper> list(boolean enable) {
        return rpcUserChannelDimensionService.list(enable);
    }
}
