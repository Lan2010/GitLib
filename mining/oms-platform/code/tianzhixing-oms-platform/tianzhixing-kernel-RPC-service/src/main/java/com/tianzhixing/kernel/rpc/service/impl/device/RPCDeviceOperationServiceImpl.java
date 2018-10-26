package com.tianzhixing.kernel.rpc.service.impl.device;

import com.tianzhixing.kernel.commons.em.DeviceStatus;
import com.tianzhixing.kernel.commons.ex.DeviceOperationException;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.device.DeviceInfoModel;
import com.tianzhixing.kernel.redis.service.RedisDeviceService;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.service.device.RPCDeviceOperationService;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.DeviceInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("RPCDeviceOperationService")
public class RPCDeviceOperationServiceImpl implements RPCDeviceOperationService {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private RedisDeviceService redisDeviceService;

    @Override
    public void bind(AccountInfoMapper accountInfoMapper, String deviceId, String deviceMAC, String deviceType, String deviceModel, Date bindTime) {
        //check
        DeviceInfoModel deviceInfoModel = deviceInfoService.getByDeviceIdAndAccount(deviceId, accountInfoMapper.getId());
        com.tianzhixing.kernel.commons.utils.Assert.isTrue(deviceInfoModel == null || DeviceStatus.UNBIND.equals(deviceInfoModel.getDeviceStatus()), new DeviceOperationException("device[" + deviceId + "] already bind by self, please unbind first..."));
        List<DeviceInfoModel> deviceInfoModels = deviceInfoService.listByDeviceId(deviceId, accountInfoMapper.getOrg());
        if (deviceInfoModels != null) {
            for (DeviceInfoModel dim : deviceInfoModels) {
                Assert.isTrue(dim.getAccountId() == accountInfoMapper.getId() || DeviceStatus.UNBIND.equals(dim.getDeviceStatus()), "device[" + deviceId + "] already bind by other account, please unbind first...");
            }
        }
        if (deviceInfoModel == null) {
            deviceInfoModel = new DeviceInfoModel();
            deviceInfoModel.setVersion(0);
            deviceInfoModel.setCreateTime(new Date());
            deviceInfoModel.setUpdateTime(new Date());
            deviceInfoModel.setDeviceId(deviceId);
            deviceInfoModel.setDeviceType(deviceType);
            deviceInfoModel.setDeviceMac(deviceMAC);
            deviceInfoModel.setDeviceModel(deviceModel);
            deviceInfoModel.setOperationTime(bindTime);
            deviceInfoModel.setDeviceStatus(DeviceStatus.BIND);
            deviceInfoModel.setOrg(accountInfoMapper.getOrg());
            deviceInfoModel.setAccountId(accountInfoMapper.getId());
            deviceInfoService.add(deviceInfoModel);
        } else {
            if (!DeviceStatus.BIND.equals(deviceInfoModel.getDeviceStatus())) {
                deviceInfoService.updateDeviceStatus(deviceInfoModel.getId(), DeviceStatus.BIND, bindTime, new Date(), deviceInfoModel.getVersion());
            }
        }
        //cache
        redisDeviceService.cacheAccountAndDeviceRelation(accountInfoMapper.getId(), deviceInfoModel.getDeviceId());
    }

    @Override
    public void unBind(AccountInfoMapper accountInfoMapper, String deviceId, Date unBindTime) {
        //check
        DeviceInfoModel deviceInfoModel = deviceInfoService.getByDeviceIdAndAccount(deviceId, accountInfoMapper.getId());
        Assert.notNull(deviceInfoModel, "... unbind device[" + deviceId + "] failed, device not found ...");
        if (!DeviceStatus.UNBIND.equals(deviceInfoModel.getDeviceStatus())) {
            deviceInfoService.updateDeviceStatus(deviceInfoModel.getId(), DeviceStatus.UNBIND, unBindTime, new Date(), deviceInfoModel.getVersion());
        }
        //remove from cache
        redisDeviceService.removeAccountAndDeviceRelation(accountInfoMapper.getId(), deviceInfoModel.getDeviceId());
    }
}
