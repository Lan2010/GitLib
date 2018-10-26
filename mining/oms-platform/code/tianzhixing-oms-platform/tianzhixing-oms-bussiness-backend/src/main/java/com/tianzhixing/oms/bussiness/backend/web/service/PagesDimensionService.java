package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCPagesDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class PagesDimensionService {

    @Autowired
    private RPCPagesDimensionService rpcPagesDimensionService;

    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return rpcPagesDimensionService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    public List<PagesDimensionMapper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, PagerMapping pagerMapping) {
        return rpcPagesDimensionService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public PagesDimensionMapper add(PagesDimensionMapper pagesDimensionMapper) {
        return rpcPagesDimensionService.add(pagesDimensionMapper);
    }

    public PagesDimensionMapper getById(long id) {
        return rpcPagesDimensionService.getById(id);
    }

    public void update(PagesDimensionMapper pagesDimensionMapper) {
        PagesDimensionMapper am = rpcPagesDimensionService.getById(pagesDimensionMapper.getId());
        if (am == null) throw new RequestException("dimension.not.found");
        rpcPagesDimensionService.update(pagesDimensionMapper);
    }
    
    public List<PagesDimensionMapper> list(boolean enable) {
        return rpcPagesDimensionService.list(enable);
    }

}
