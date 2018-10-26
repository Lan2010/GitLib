package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.utils.RemoteServerPushUtil;
import com.tianzhixing.oms.bussiness.rpc.mapper.advertisement.AdvertisementMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppPushRecordsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.advertisement.RPCAdvertisementService;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppPushRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class AdvertisementService {

    @Autowired
    private RPCAdvertisementService rpcAdvertisementService;

    @Autowired
    private RPCAppPushRecordsService rpcAppPushRecordsService;

    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return rpcAdvertisementService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    public List<AdvertisementMapper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, PagerMapping pagerMapping) {
        return rpcAdvertisementService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public AdvertisementMapper add(AdvertisementMapper advertisementMapper) {
        advertisementMapper.setCity(BaiduMapConfig.city(advertisementMapper.getCity()));
        return rpcAdvertisementService.add(advertisementMapper);
    }

    public AdvertisementMapper getById(long id) {
        return rpcAdvertisementService.getById(id);
    }

    public void update(AdvertisementMapper advertisementMapper) {
        AdvertisementMapper am = rpcAdvertisementService.getById(advertisementMapper.getId());
        if (am == null) throw new RequestException("advert.not.found");
        advertisementMapper.setCity(BaiduMapConfig.city(advertisementMapper.getCity()));
        rpcAdvertisementService.update(advertisementMapper);
    }

    public void push(long id, String operUser) {
        AdvertisementMapper am = rpcAdvertisementService.getById(id);
        Assert.notNull(am, "advert.not.found");
        boolean status = new RemoteServerPushUtil().pushAdvertisementToWkAPP(am);
        Assert.isTrue(status, "push.remote.failed");
        //添加推送日志
        AppPushRecordsMapper appPushRecordsMapper = new AppPushRecordsMapper();
        appPushRecordsMapper.setUpdateTime(new Date());
        appPushRecordsMapper.setCreateTime(new Date());
        appPushRecordsMapper.setId(null);
        appPushRecordsMapper.setOperUser(operUser);
        appPushRecordsMapper.setPushType(2);
        appPushRecordsMapper.setThirdId(am.getId());
        appPushRecordsMapper.setVersion(0);
        rpcAppPushRecordsService.add(appPushRecordsMapper);
        if (!am.getIsSend()) {
            rpcAdvertisementService.updateIsSend(true, id);
        }
    }
}
