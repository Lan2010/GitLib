package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.utils.RemoteServerPushUtil;
import com.tianzhixing.oms.bussiness.rpc.mapper.advertisement.AdvertisementMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppPushRecordsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppSuspendInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppPushRecordsService;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppSuspendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service
public class AppSuspendService {

    @Autowired
    private RPCAppSuspendService rpcAppSuspendService;

    @Autowired
    private RPCAppPushRecordsService rpcAppPushRecordsService;

    public long count() {
        return rpcAppSuspendService.count();
    }

    public List<AppSuspendInfoMapper> list(PagerMapping pagerMapping) {
        return rpcAppSuspendService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public void add(AppSuspendInfoMapper appSuspendInfoMapper) {
        rpcAppSuspendService.add(appSuspendInfoMapper);
    }

    public void push(long id, String account) {
        AppSuspendInfoMapper am = rpcAppSuspendService.getById(id);
        Assert.notNull(am, "app.suspend.not.found");
        boolean status = new RemoteServerPushUtil().pushSuspend(am.getPicUrl(), am.getPicLink(), am.getBeginTime(), am.getEndTime());
        Assert.isTrue(status, "push.remote.failed");
        //添加推送日志
        AppPushRecordsMapper appPushRecordsMapper = new AppPushRecordsMapper();
        appPushRecordsMapper.setUpdateTime(new Date());
        appPushRecordsMapper.setCreateTime(new Date());
        appPushRecordsMapper.setId(null);
        appPushRecordsMapper.setOperUser(account);
        appPushRecordsMapper.setPushType(3);
        appPushRecordsMapper.setThirdId(am.getId());
        appPushRecordsMapper.setVersion(0);
        rpcAppPushRecordsService.add(appPushRecordsMapper);
        if (!am.getIsSend()) {
            rpcAppSuspendService.updateIsSend(true, id);
        }
    }
}
