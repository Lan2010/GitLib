package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.DeviceDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceDimensionModel;
import com.tianzhixing.oms.bussiness.service.DeviceDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("deviceDimensionService")
public class DeviceDimensionServiceImpl implements DeviceDimensionService {

    @Autowired
    private DeviceDimensionDao deviceDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<DeviceDimensionModel> list(Boolean enable) {
        return deviceDimensionDao.list(enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public DeviceDimensionModel getById(Long id) {
        return deviceDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return deviceDimensionDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<DeviceDimensionModel> list(int from, int pageSize) {
        return deviceDimensionDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DeviceDimensionModel add(DeviceDimensionModel deviceDimensionModel) {
    	deviceDimensionModel = deviceDimensionDao.add(deviceDimensionModel);
        return deviceDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(DeviceDimensionModel deviceDimensionModel) {
        deviceDimensionDao.update(deviceDimensionModel.getId(), deviceDimensionModel.getVersion(), deviceDimensionModel);
    }
}
