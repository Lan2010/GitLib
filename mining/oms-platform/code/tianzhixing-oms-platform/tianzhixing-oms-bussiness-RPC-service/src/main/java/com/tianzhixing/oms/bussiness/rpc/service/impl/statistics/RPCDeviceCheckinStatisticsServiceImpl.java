package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.ApplicationOperationStatisticsModel;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceCheckinStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceCheckinStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceCheckinStatisticsService;
import com.tianzhixing.oms.bussiness.service.DeviceCheckinStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCDeviceCheckinStatisticsService")
public class RPCDeviceCheckinStatisticsServiceImpl implements RPCDeviceCheckinStatisticsService {

    @Autowired
    private DeviceCheckinStatisticsService deviceCheckinStatisticsService;

    @Override
    public void insert(List<DeviceCheckinStatisticsMapper> deviceCheckinStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (deviceCheckinStatisticsMappers != null) {
            List<DeviceCheckinStatisticsModel> deviceCheckinStatisticsModels = new ArrayList<>();
            for(DeviceCheckinStatisticsMapper deviceCheckinStatisticsMapper : deviceCheckinStatisticsMappers){
                DeviceCheckinStatisticsModel deviceCheckinStatisticsModel = new DeviceCheckinStatisticsModel();
                BeanUtils.copyProperties(deviceCheckinStatisticsMapper, deviceCheckinStatisticsModel);
                deviceCheckinStatisticsModels.add(deviceCheckinStatisticsModel);
            }
            deviceCheckinStatisticsService.insert(deviceCheckinStatisticsModels, taskAllotMapper.getId());
        }
    }
}
