package com.tianzhixing.kernel.service.impl;

import com.tianzhixing.kernel.commons.em.DeviceStatus;
import com.tianzhixing.kernel.dao.device.DeviceInfoDao;
import com.tianzhixing.kernel.model.device.DeviceInfoModel;
import com.tianzhixing.kernel.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("deviceInfoService")
public class DeviceInfoServiceImpl implements DeviceInfoService {

    @Autowired
    private DeviceInfoDao deviceInfoDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<DeviceInfoModel> listByDeviceId(String deviceId, String org) {
        return deviceInfoDao.listByDeviceId(deviceId, org);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public DeviceInfoModel add(DeviceInfoModel deviceInfoModel) {
        return deviceInfoDao.add(deviceInfoModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateDeviceStatus(Long id, DeviceStatus status, Date operationTime, Date updateTime, Integer version) {
        deviceInfoDao.updateDeviceStatus(id, status, operationTime, updateTime, version);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public DeviceInfoModel getByDeviceIdAndAccount(String deviceId, Long accountId) {
        return deviceInfoDao.getByDeviceIdAndAccount(deviceId, accountId);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<DeviceInfoModel> listDevicesByAccountIdAndStatus(long accountId, DeviceStatus status) {
        return deviceInfoDao.listDevicesIdsByAccountIdAndStatus(accountId, status);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long distinctCount() {
        return deviceInfoDao.distinctCount();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<String> listWithGroup(int from, int pageSize) {
        return deviceInfoDao.listWithGroup(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Long> listBindAccountIdsByDeviceId(String deviceId) {
        return deviceInfoDao.listBindAccountIdsByDeviceId(deviceId);
    }

}
