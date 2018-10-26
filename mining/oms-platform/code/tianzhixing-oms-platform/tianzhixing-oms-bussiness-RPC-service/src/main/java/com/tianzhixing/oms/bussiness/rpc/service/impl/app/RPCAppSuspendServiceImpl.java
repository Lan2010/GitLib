package com.tianzhixing.oms.bussiness.rpc.service.impl.app;

import com.tianzhixing.oms.bussiness.model.app.AppSuspendInfoModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppSuspendInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.app.RPCAppSuspendService;
import com.tianzhixing.oms.bussiness.service.AppSuspendInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCAppSuspendService")
public class RPCAppSuspendServiceImpl implements RPCAppSuspendService {

    @Autowired
    private AppSuspendInfoService appSuspendInfoService;

    @Override
    public long count() {
        return appSuspendInfoService.count();
    }

    @Override
    public List<AppSuspendInfoMapper> list(int from, int pageSize) {
        List<AppSuspendInfoModel> list = appSuspendInfoService.list(from, pageSize);
        List<AppSuspendInfoMapper> appSuspendInfoMappers = new ArrayList<>();
        if (list != null) {
            for (AppSuspendInfoModel appSuspendInfoModel : list) {
                appSuspendInfoMappers.add(_toMapper(appSuspendInfoModel));
            }
        }
        return appSuspendInfoMappers;
    }

    @Override
    public void add(AppSuspendInfoMapper appSuspendInfoMapper) {
        appSuspendInfoService.add(_toModel(appSuspendInfoMapper));
    }

    @Override
    public void updateIsSend(boolean isSend, long id) {
        AppSuspendInfoModel appSuspendInfoModel = appSuspendInfoService.getById(id);
        Assert.notNull(appSuspendInfoModel, "app.suspend.not.found");
        appSuspendInfoService.updateIsSend(isSend, appSuspendInfoModel);
    }

    @Override
    public AppSuspendInfoMapper getById(long id) {
        AppSuspendInfoModel appSuspendInfoModel = appSuspendInfoService.getById(id);
        return _toMapper(appSuspendInfoModel);
    }

    private AppSuspendInfoModel _toModel(AppSuspendInfoMapper appSuspendInfoMapper) {
        AppSuspendInfoModel appSuspendInfoModel = new AppSuspendInfoModel();
        appSuspendInfoModel.setId(null);
        appSuspendInfoModel.setVersion(0);
        appSuspendInfoModel.setCreateTime(new Date());
        appSuspendInfoModel.setUpdateTime(new Date());
        appSuspendInfoModel.setCreateUser(appSuspendInfoMapper.getCreateUser());
        appSuspendInfoModel.setUpdateUser(appSuspendInfoMapper.getUpdateUser());
        appSuspendInfoModel.setBeginTime(appSuspendInfoMapper.getBeginTime());
        appSuspendInfoModel.setEndTime(appSuspendInfoMapper.getEndTime());
        appSuspendInfoModel.setPicLink(appSuspendInfoMapper.getPicLink());
        appSuspendInfoModel.setPicUrl(appSuspendInfoMapper.getPicUrl());
        appSuspendInfoModel.setIsSend(false);
        return appSuspendInfoModel;
    }

    private AppSuspendInfoMapper _toMapper(AppSuspendInfoModel appSuspendInfoModel) {
        return new AppSuspendInfoMapper(appSuspendInfoModel.getId(), appSuspendInfoModel.getVersion(), appSuspendInfoModel.getCreateTime(), appSuspendInfoModel.getUpdateTime(), appSuspendInfoModel.getCreateUser(), appSuspendInfoModel.getUpdateUser(), appSuspendInfoModel.getBeginTime(), appSuspendInfoModel.getEndTime(), appSuspendInfoModel.getPicUrl(), appSuspendInfoModel.getPicLink(), appSuspendInfoModel.getIsSend());
    }
}
