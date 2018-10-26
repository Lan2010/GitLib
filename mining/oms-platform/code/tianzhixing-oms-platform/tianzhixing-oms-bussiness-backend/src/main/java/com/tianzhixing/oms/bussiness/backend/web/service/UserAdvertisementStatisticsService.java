package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAdvertisementStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAdvertisementStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserAdvertisementStatisticsService {

    @Autowired
    private RPCUserAdvertisementStatisticsService rpcUserAdvertisementStatisticsService;

    /**
     * 获取清单
     *
     * @param pagerMapping
     * @return
     */
    public List<UserAdvertisementStatisticsMapper> list(String advId) {
        return rpcUserAdvertisementStatisticsService.list(advId);
    }
    
    public long count() {
        return rpcUserAdvertisementStatisticsService.count();
    }
}
