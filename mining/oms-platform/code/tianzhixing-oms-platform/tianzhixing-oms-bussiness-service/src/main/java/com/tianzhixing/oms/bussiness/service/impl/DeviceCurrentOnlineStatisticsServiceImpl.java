package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.DeviceCurrentOnlineStatisticsDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceCurrentOnlineStatisticsModel;
import com.tianzhixing.oms.bussiness.service.DeviceCurrentOnlineStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
@Service("deviceCurrentOnlineStatisticsService")
public class DeviceCurrentOnlineStatisticsServiceImpl implements DeviceCurrentOnlineStatisticsService {

    @Autowired
    private DeviceCurrentOnlineStatisticsDao deviceCurrentOnlineStatisticsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel) {
        deviceCurrentOnlineStatisticsDao.insert(deviceCurrentOnlineStatisticsModel);
    }

    @Override
    public DeviceCurrentOnlineStatisticsModel getByDeviceId(String deviceId, String platform) {
        return deviceCurrentOnlineStatisticsDao.getByDeviceId(deviceId, platform);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void changeStatus(DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel, Integer status) {
        deviceCurrentOnlineStatisticsDao.changeStatus(deviceCurrentOnlineStatisticsModel.getId(), deviceCurrentOnlineStatisticsModel.getVersion(), status);
    }

    @Override
    public List<DeviceCurrentOnlineStatisticsModel> list(Integer status, int from, int pageSize) {
        return deviceCurrentOnlineStatisticsDao.list(status, from, pageSize);
    }

    @Override
    public Long countByStatus(Integer status) {
        return deviceCurrentOnlineStatisticsDao.count(status);
    }
}
