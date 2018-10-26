package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.utils.RemoteServerPushUtil;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppPushRecordsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.system.SystemParamMapper;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppPushRecordsService;
import com.tianzhixing.oms.bussiness.rpc.service.system.RPCSystemParamService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/25.
 */
@Service
public class SystemParamService {

    private static Logger LOGGER = LoggerFactory.getLogger(SystemParamService.class);

    @Autowired
    private RPCSystemParamService rpcSystemParamService;

    @Autowired
    private RPCAppPushRecordsService rpcAppPushRecordsService;

    public long count() {
        return rpcSystemParamService.count();
    }

    public List<SystemParamMapper> list(PagerMapping pagerMapping) {
        return rpcSystemParamService.list(pagerMapping.getFrom(), pagerMapping.getPageSize());
    }

    public SystemParamMapper getById(long id) {
        return rpcSystemParamService.getById(id);
    }

    public void update(long id, String value, String operUser) {
        SystemParamMapper systemParamMapper = rpcSystemParamService.getById(id);
        rpcSystemParamService.updateValue(id, value);
        LOGGER.info(".... user: " + operUser + ", update system param id[" + id + "] with value:" + value);
        if (systemParamMapper.getNeedPush()) {
            //首先进行推送，后进行更新
            systemParamMapper.setSystemValue(value);
            boolean pushStatus = new RemoteServerPushUtil().pushSystemSetting(systemParamMapper);
            Assert.isTrue(pushStatus, "update system setting failed, push remote server return false");
            //增加推送日志
            AppPushRecordsMapper appPushRecordsMapper = new AppPushRecordsMapper();
            appPushRecordsMapper.setUpdateTime(new Date());
            appPushRecordsMapper.setCreateTime(new Date());
            appPushRecordsMapper.setId(null);
            appPushRecordsMapper.setOperUser(operUser);
            appPushRecordsMapper.setPushType(2);
            appPushRecordsMapper.setThirdId(systemParamMapper.getId());
            appPushRecordsMapper.setVersion(0);
            rpcAppPushRecordsService.add(appPushRecordsMapper);
        }
    }
}
