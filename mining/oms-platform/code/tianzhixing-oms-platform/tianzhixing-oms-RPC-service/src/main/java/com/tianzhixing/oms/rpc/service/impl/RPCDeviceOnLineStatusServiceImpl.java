package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.DeviceCheckinInfoModel;
import com.tianzhixing.oms.model.DeviceOnlineStatusInfoModel;
import com.tianzhixing.oms.rpc.mapper.DeviceCheckinInfoMapper;
import com.tianzhixing.oms.rpc.mapper.DeviceOnlineStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceOnLineStatusService;
import com.tianzhixing.oms.scylladb.DeviceOnLineStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCDeviceOnLineStatusService")
public class RPCDeviceOnLineStatusServiceImpl implements RPCDeviceOnLineStatusService {

    @Autowired
    private DeviceOnLineStatusRepository deviceOnLineStatusRepository;

    @Override
    public void insert(DeviceOnlineStatusInfoMapper deviceOnlineStatusInfoMapper) {
        DeviceOnlineStatusInfoModel model = new DeviceOnlineStatusInfoModel();
        BeanUtils.copyProperties(deviceOnlineStatusInfoMapper, model);
        deviceOnLineStatusRepository.insert(model);
    }

    @Override
    public List<DeviceOnlineStatusInfoMapper> list(Date beginTime, Date endTime) {
        List<DeviceOnlineStatusInfoModel> deviceOnlineStatusInfoModels = deviceOnLineStatusRepository.list(beginTime.getTime(), endTime.getTime());
        List<DeviceOnlineStatusInfoMapper> list = new ArrayList<>();
        if (deviceOnlineStatusInfoModels != null) {
            for (DeviceOnlineStatusInfoModel deviceOnlineStatusInfoModel : deviceOnlineStatusInfoModels) {
                list.add(_toMapper(deviceOnlineStatusInfoModel));
            }
        }
        return list;
    }

    private DeviceOnlineStatusInfoMapper _toMapper(DeviceOnlineStatusInfoModel deviceOnlineStatusInfoModel) {
        return new DeviceOnlineStatusInfoMapper(deviceOnlineStatusInfoModel.getId(), deviceOnlineStatusInfoModel.getCreateTime(), deviceOnlineStatusInfoModel.getPlatformFrom(), deviceOnlineStatusInfoModel.getClientPlatformType(), deviceOnlineStatusInfoModel.getMobile(), deviceOnlineStatusInfoModel.getDeviceId(), deviceOnlineStatusInfoModel.getDeviceType(), deviceOnlineStatusInfoModel.getDeviceMac(), deviceOnlineStatusInfoModel.getDeviceIp(), deviceOnlineStatusInfoModel.getDeviceModel(), deviceOnlineStatusInfoModel.getDeviceOperType(),deviceOnlineStatusInfoModel.getOperationTime(), deviceOnlineStatusInfoModel.getOperationType(), deviceOnlineStatusInfoModel.getLng(), deviceOnlineStatusInfoModel.getLat(), deviceOnlineStatusInfoModel.getBindStatus());
    }
}
