package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.ApplicationOperationInfoModel;
import com.tianzhixing.oms.model.DeviceCheckinInfoModel;
import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;
import com.tianzhixing.oms.rpc.mapper.DeviceCheckinInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceCheckinService;
import com.tianzhixing.oms.scylladb.DeviceCheckinRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCDeviceCheckinService")
public class RPCDeviceCheckinServiceImpl implements RPCDeviceCheckinService {

    @Autowired
    private DeviceCheckinRepository deviceCheckinRepository;


    @Override
    public void insert(DeviceCheckinInfoMapper deviceCheckinInfoMapper) {
        DeviceCheckinInfoModel deviceCheckinInfoModel = new DeviceCheckinInfoModel();
        BeanUtils.copyProperties(deviceCheckinInfoMapper, deviceCheckinInfoModel);
        deviceCheckinRepository.insert(deviceCheckinInfoModel);
    }

    @Override
    public List<DeviceCheckinInfoMapper> list(Date beginTime, Date endTime) {
        List<DeviceCheckinInfoModel> deviceCheckinInfoModels = deviceCheckinRepository.list(beginTime.getTime(), endTime.getTime());
        List<DeviceCheckinInfoMapper> list = new ArrayList<>();
        if (deviceCheckinInfoModels != null) {
            for (DeviceCheckinInfoModel deviceCheckinInfoModel : deviceCheckinInfoModels) {
                list.add(_toMapper(deviceCheckinInfoModel));
            }
        }
        return list;
    }

    private DeviceCheckinInfoMapper _toMapper(DeviceCheckinInfoModel deviceCheckinInfoModel) {
        return new DeviceCheckinInfoMapper(deviceCheckinInfoModel.getId(), deviceCheckinInfoModel.getCreateTime(), deviceCheckinInfoModel.getPlatformFrom(), deviceCheckinInfoModel.getClientPlatformType(), deviceCheckinInfoModel.getMobile(), deviceCheckinInfoModel.getDeviceId(), deviceCheckinInfoModel.getDeviceType(), deviceCheckinInfoModel.getDeviceMac(), deviceCheckinInfoModel.getDeviceIp(), deviceCheckinInfoModel.getDeviceModel(), deviceCheckinInfoModel.getDeviceOperType(), deviceCheckinInfoModel.getCheckinTime(), deviceCheckinInfoModel.getBindStatus());
    }
}
