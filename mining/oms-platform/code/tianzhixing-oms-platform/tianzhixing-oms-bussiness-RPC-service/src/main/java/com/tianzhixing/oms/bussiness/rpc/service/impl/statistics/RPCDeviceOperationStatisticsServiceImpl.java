package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceOperationStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceOperationStatisticsService;
import com.tianzhixing.oms.bussiness.service.DeviceOperationStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCDeviceOperationStatisticsService")
public class RPCDeviceOperationStatisticsServiceImpl implements RPCDeviceOperationStatisticsService {

    @Autowired
    private DeviceOperationStatisticsService deviceOperationStatisticsService;

    @Override
    public void insert(List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (deviceOperationStatisticsMappers != null) {
            List<DeviceOperationStatisticsModel> deviceOperationStatisticsModels = new ArrayList<>();
            for(DeviceOperationStatisticsMapper deviceOperationStatisticsMapper : deviceOperationStatisticsMappers){
                DeviceOperationStatisticsModel deviceOperationStatisticsModel = new DeviceOperationStatisticsModel();
                BeanUtils.copyProperties(deviceOperationStatisticsMapper, deviceOperationStatisticsModel);
                deviceOperationStatisticsModels.add(deviceOperationStatisticsModel);
            }
            deviceOperationStatisticsService.insert(deviceOperationStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<DeviceOperationStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName , String deviceType) {
        List<Map<String, Object>> listMap = deviceOperationStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, deviceType);
        List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            deviceOperationStatisticsMappers.add(new DeviceOperationStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformFrom,map.get("deviceBindCount") == null ? 0 : Integer.valueOf(map.get("deviceBindCount").toString()), map.get("deviceUnBindCount") == null ? 0 : Integer.valueOf(map.get("deviceUnBindCount").toString()), deviceType,null, platformName));
        }
        return deviceOperationStatisticsMappers;
    }
}
