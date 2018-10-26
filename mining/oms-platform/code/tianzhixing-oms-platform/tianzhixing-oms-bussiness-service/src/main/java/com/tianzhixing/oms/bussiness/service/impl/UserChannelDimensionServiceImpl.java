package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserChannelDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserChannelDimensionModel;
import com.tianzhixing.oms.bussiness.service.UserChannelDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("userChannelDimensionService")
public class UserChannelDimensionServiceImpl implements UserChannelDimensionService {

    @Autowired
    private UserChannelDimensionDao userChannelDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<UserChannelDimensionModel> list(boolean enable) {
        return userChannelDimensionDao.list(enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public UserChannelDimensionModel getById(Long id) {
        return userChannelDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return userChannelDimensionDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<UserChannelDimensionModel> list(int from, int pageSize) {
        return userChannelDimensionDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public UserChannelDimensionModel add(UserChannelDimensionModel deviceDimensionModel) {
    	deviceDimensionModel = userChannelDimensionDao.add(deviceDimensionModel);
        return deviceDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(UserChannelDimensionModel deviceDimensionModel) {
        userChannelDimensionDao.update(deviceDimensionModel.getId(), deviceDimensionModel.getVersion(), deviceDimensionModel);
    }
}
