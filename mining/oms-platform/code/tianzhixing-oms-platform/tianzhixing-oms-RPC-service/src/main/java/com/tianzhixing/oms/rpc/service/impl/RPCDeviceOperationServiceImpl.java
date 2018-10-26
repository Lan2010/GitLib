package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.DeviceOnlineStatusInfoModel;
import com.tianzhixing.oms.model.DeviceOperationInfoModel;
import com.tianzhixing.oms.rpc.mapper.DeviceOnlineStatusInfoMapper;
import com.tianzhixing.oms.rpc.mapper.DeviceOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceOperationService;
import com.tianzhixing.oms.scylladb.DeviceOperationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCDeviceOperationService")
public class RPCDeviceOperationServiceImpl implements RPCDeviceOperationService {

    @Autowired
    private DeviceOperationRepository deviceOperationRepository;

    @Override
    public void insert(DeviceOperationInfoMapper deviceOperationInfoMapper) {
        DeviceOperationInfoModel model = new DeviceOperationInfoModel();
        BeanUtils.copyProperties(deviceOperationInfoMapper, model);
        deviceOperationRepository.insert(model);
    }

    @Override
    public List<DeviceOperationInfoMapper> list(Date beginTime, Date endTime) {
        List<DeviceOperationInfoModel> deviceOnlineStatusInfoModels = deviceOperationRepository.list(beginTime.getTime(), endTime.getTime());
        List<DeviceOperationInfoMapper> list = new ArrayList<>();
        if (deviceOnlineStatusInfoModels != null) {
            for (DeviceOperationInfoModel deviceOperationInfoModel : deviceOnlineStatusInfoModels) {
                list.add(_toMapper(deviceOperationInfoModel));
            }
        }
        return list;
    }

    private DeviceOperationInfoMapper _toMapper(DeviceOperationInfoModel deviceOperationInfoModel) {
        return new DeviceOperationInfoMapper(deviceOperationInfoModel.getId(), deviceOperationInfoModel.getCreateTime(), deviceOperationInfoModel.getPlatformFrom(), deviceOperationInfoModel.getClientPlatformType(), deviceOperationInfoModel.getDeviceType(), deviceOperationInfoModel.getDeviceId(), deviceOperationInfoModel.getDeviceType(), deviceOperationInfoModel.getDeviceMac(), deviceOperationInfoModel.getDeviceIp(), deviceOperationInfoModel.getDeviceModel(), deviceOperationInfoModel.getDeviceOperType(), deviceOperationInfoModel.getOperationTime(), deviceOperationInfoModel.getOperationType());
    }
}
