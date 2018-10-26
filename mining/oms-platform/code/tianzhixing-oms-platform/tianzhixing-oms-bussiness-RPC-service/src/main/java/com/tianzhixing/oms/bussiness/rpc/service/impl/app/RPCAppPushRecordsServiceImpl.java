package com.tianzhixing.oms.bussiness.rpc.service.impl.app;

import com.tianzhixing.oms.bussiness.model.app.AppPushRecordsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppPushRecordsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppPushRecordsService;
import com.tianzhixing.oms.bussiness.service.AppPushRecordsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCAppPushRecordsService")
public class RPCAppPushRecordsServiceImpl implements RPCAppPushRecordsService {

    @Autowired
    private AppPushRecordsService appPushRecordsService;

    @Override
    public void add(AppPushRecordsMapper appPushRecordsMapper) {
        AppPushRecordsModel appPushRecordsModel = new AppPushRecordsModel();
        BeanUtils.copyProperties(appPushRecordsMapper, appPushRecordsModel);
        appPushRecordsService.add(appPushRecordsModel);
    }
}
