package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserAuthDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserAuthDimensionModel;
import com.tianzhixing.oms.bussiness.service.UserAuthDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("userAuthDimensionService")
public class UserAuthDimensionServiceImpl implements UserAuthDimensionService {

    @Autowired
    private UserAuthDimensionDao userAuthDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<UserAuthDimensionModel> list(boolean enable) {
        return userAuthDimensionDao.list(enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public UserAuthDimensionModel getById(Long id) {
        return userAuthDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return userAuthDimensionDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<UserAuthDimensionModel> list(int from, int pageSize) {
        return userAuthDimensionDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserAuthDimensionModel add(UserAuthDimensionModel deviceDimensionModel) {
    	deviceDimensionModel = userAuthDimensionDao.add(deviceDimensionModel);
        return deviceDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(UserAuthDimensionModel deviceDimensionModel) {
        userAuthDimensionDao.update(deviceDimensionModel.getId(), deviceDimensionModel.getVersion(), deviceDimensionModel);
    }
}
