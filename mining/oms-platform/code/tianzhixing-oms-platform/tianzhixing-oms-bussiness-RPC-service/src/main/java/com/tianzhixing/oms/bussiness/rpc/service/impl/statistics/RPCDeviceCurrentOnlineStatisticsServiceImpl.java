package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceCurrentOnlineStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceCurrentOnlineStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceCurrentOnlineStatisticsService;
import com.tianzhixing.oms.bussiness.service.DeviceCurrentOnlineStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
@Service("RPCDeviceCurrentOnlineStatisticsService")
public class RPCDeviceCurrentOnlineStatisticsServiceImpl implements RPCDeviceCurrentOnlineStatisticsService {

    @Autowired
    private DeviceCurrentOnlineStatisticsService deviceCurrentOnlineStatisticsService;


    @Override
    public void insert(DeviceCurrentOnlineStatisticsMapper deviceCurrentOnlineStatisticsMapper) {
        deviceCurrentOnlineStatisticsService.insert(_toModel(deviceCurrentOnlineStatisticsMapper));
    }

    @Override
    public DeviceCurrentOnlineStatisticsMapper getByDeviceId(String deviceId, String platform) {
        DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel = deviceCurrentOnlineStatisticsService.getByDeviceId(deviceId, platform);
        return deviceCurrentOnlineStatisticsModel == null ? null : _toMapper(deviceCurrentOnlineStatisticsModel);
    }

    @Override
    public void changeStatus(Integer status, String deviceId, String platform) {
        DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel = deviceCurrentOnlineStatisticsService.getByDeviceId(deviceId, platform);
        if (deviceCurrentOnlineStatisticsModel != null)
            deviceCurrentOnlineStatisticsService.changeStatus(deviceCurrentOnlineStatisticsModel, status);
    }

    @Override
    public List<DeviceCurrentOnlineStatisticsMapper> listByStatus(Integer status, int from, int pageSize) {
        List<DeviceCurrentOnlineStatisticsModel> deviceCurrentOnlineStatisticsModels = deviceCurrentOnlineStatisticsService.list(status, from, pageSize);
        List<DeviceCurrentOnlineStatisticsMapper> deviceCurrentOnlineStatisticsMappers = new ArrayList<>();
        if (deviceCurrentOnlineStatisticsModels != null) {
            for (DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel : deviceCurrentOnlineStatisticsModels)
                deviceCurrentOnlineStatisticsMappers.add(_toMapper(deviceCurrentOnlineStatisticsModel));
        }
        return deviceCurrentOnlineStatisticsMappers;
    }

    @Override
    public Long countByStatus(Integer status) {
        return deviceCurrentOnlineStatisticsService.countByStatus(status);
    }

    private DeviceCurrentOnlineStatisticsModel _toModel(DeviceCurrentOnlineStatisticsMapper deviceCurrentOnlineStatisticsMapper) {
        DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel = new DeviceCurrentOnlineStatisticsModel();
        deviceCurrentOnlineStatisticsModel.setClientPlatformType(deviceCurrentOnlineStatisticsMapper.getClientPlatformType());
        deviceCurrentOnlineStatisticsModel.setCreateTime(new Date());
        deviceCurrentOnlineStatisticsModel.setDeviceId(deviceCurrentOnlineStatisticsMapper.getDeviceId());
        deviceCurrentOnlineStatisticsModel.setId(deviceCurrentOnlineStatisticsMapper.getId());
        deviceCurrentOnlineStatisticsModel.setLat(deviceCurrentOnlineStatisticsMapper.getLat());
        deviceCurrentOnlineStatisticsModel.setLng(deviceCurrentOnlineStatisticsMapper.getLng());
        deviceCurrentOnlineStatisticsModel.setPlatformFrom(deviceCurrentOnlineStatisticsMapper.getPlatformFrom());
        deviceCurrentOnlineStatisticsModel.setStatus(deviceCurrentOnlineStatisticsMapper.getStatus());
        deviceCurrentOnlineStatisticsModel.setUpdateTime(new Date());
        deviceCurrentOnlineStatisticsModel.setDeviceType(deviceCurrentOnlineStatisticsMapper.getDeviceType());
        deviceCurrentOnlineStatisticsModel.setVersion(0);
        return deviceCurrentOnlineStatisticsModel;
    }

    private DeviceCurrentOnlineStatisticsMapper _toMapper(DeviceCurrentOnlineStatisticsModel deviceCurrentOnlineStatisticsModel) {
        return new DeviceCurrentOnlineStatisticsMapper(deviceCurrentOnlineStatisticsModel.getId(), deviceCurrentOnlineStatisticsModel.getVersion(), deviceCurrentOnlineStatisticsModel.getCreateTime(), deviceCurrentOnlineStatisticsModel.getUpdateTime(), deviceCurrentOnlineStatisticsModel.getClientPlatformType(), deviceCurrentOnlineStatisticsModel.getPlatformFrom(), deviceCurrentOnlineStatisticsModel.getDeviceId(), deviceCurrentOnlineStatisticsModel.getStatus(), deviceCurrentOnlineStatisticsModel.getLng(), deviceCurrentOnlineStatisticsModel.getLat(), deviceCurrentOnlineStatisticsModel.getDeviceType());
    }
}
