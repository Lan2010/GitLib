package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.app.AppSuspendInfoDao;
import com.tianzhixing.oms.bussiness.model.app.AppSuspendInfoModel;
import com.tianzhixing.oms.bussiness.service.AppSuspendInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("appSuspendInfoService")
public class AppSuspendInfoServiceImpl implements AppSuspendInfoService {

    @Autowired
    private AppSuspendInfoDao appSuspendInfoDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return appSuspendInfoDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AppSuspendInfoModel> list(int from, int pageSize) {
        return appSuspendInfoDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(AppSuspendInfoModel appSuspendInfoModel) {
        appSuspendInfoDao.insert(appSuspendInfoModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateIsSend(boolean isSend, AppSuspendInfoModel appSuspendInfoModel) {
        appSuspendInfoDao.updateIsSend(isSend, appSuspendInfoModel.getId(), appSuspendInfoModel.getVersion());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AppSuspendInfoModel getById(long id) {
        return appSuspendInfoDao.get(id);
    }
}
