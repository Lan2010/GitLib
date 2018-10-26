package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.app.AppPushRecordsDao;
import com.tianzhixing.oms.bussiness.model.app.AppPushRecordsModel;
import com.tianzhixing.oms.bussiness.service.AppPushRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("appPushRecordsService")
public class AppPushRecordsServiceImpl implements AppPushRecordsService {

    @Autowired
    private AppPushRecordsDao appPushRecordsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(AppPushRecordsModel appPushRecordsModel) {
        appPushRecordsDao.insert(appPushRecordsModel);
    }
}
