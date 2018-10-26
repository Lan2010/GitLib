package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceOnlineStatusStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOnlineStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceOnLineStatusStatisticsService;
import com.tianzhixing.oms.bussiness.service.DeviceOnlineStatusStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCDeviceOnLineStatusStatisticsService")
public class RPCDeviceOnLineStatusStatisticsServiceImpl implements RPCDeviceOnLineStatusStatisticsService {

    @Autowired
    private DeviceOnlineStatusStatisticsService deviceOnlineStatusStatisticsService;

    @Override
    public void insert(List<DeviceOnlineStatusStatisticsMapper> deviceOnlineStatusStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (deviceOnlineStatusStatisticsMappers != null) {
            List<DeviceOnlineStatusStatisticsModel> deviceOnlineStatusStatisticsModels = new ArrayList<>();
            for(DeviceOnlineStatusStatisticsMapper deviceOnlineStatusStatisticsMapper : deviceOnlineStatusStatisticsMappers){
                DeviceOnlineStatusStatisticsModel deviceOnlineStatusStatisticsModel = new DeviceOnlineStatusStatisticsModel();
                BeanUtils.copyProperties(deviceOnlineStatusStatisticsMapper, deviceOnlineStatusStatisticsModel);
                deviceOnlineStatusStatisticsModels.add(deviceOnlineStatusStatisticsModel);
            }
            deviceOnlineStatusStatisticsService.insert(deviceOnlineStatusStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<DeviceOnlineStatusStatisticsMapper> list(int type,Integer hour, Integer day, Integer month,Integer year,String value) {
        List<Map<String, Object>> listMap = deviceOnlineStatusStatisticsService.listSum(type,hour,day,month,year,value);
        List<DeviceOnlineStatusStatisticsMapper> userLoginStatusStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userLoginStatusStatisticsMappers.add(new DeviceOnlineStatusStatisticsMapper(null, null, null,null,null,null,null,null,null,null,
            				map.get("bindDeviceOnlineCount") == null ? 0 : Integer.valueOf(map.get("bindDeviceOnlineCount").toString()),
            				map.get("bindDeviceDiffIDOnlineCount") == null ? 0 : Integer.valueOf(map.get("bindDeviceDiffIDOnlineCount").toString()),
            				map.get("unBindDeviceOnlineCount") == null ? 0 : Integer.valueOf(map.get("unBindDeviceOnlineCount").toString()),
            				map.get("unBindDeviceDiffIDOnlineCount") == null ? 0 : Integer.valueOf(map.get("unBindDeviceDiffIDOnlineCount").toString()),
            				map.get("bindDeviceOfflineCount") == null ? 0 : Integer.valueOf(map.get("bindDeviceOfflineCount").toString()),
            				map.get("bindDeviceDiffIDOfflineCount") == null ? 0 : Integer.valueOf(map.get("bindDeviceDiffIDOfflineCount").toString()),
            				map.get("unBindDeviceOfflineCount") == null ? 0 : Integer.valueOf(map.get("unBindDeviceOfflineCount").toString()),
            				map.get("unBindDeviceDiffIDOfflineCount") == null ? 0 : Integer.valueOf(map.get("unBindDeviceDiffIDOfflineCount").toString()),
            				map.get("unBindDeviceCurrentOnlineCount") == null ? 0 : Integer.valueOf(map.get("unBindDeviceCurrentOnlineCount").toString()),
            				map.get("bindDeviceCurrentOnlineCount") == null ? 0 : Integer.valueOf(map.get("bindDeviceCurrentOnlineCount").toString()),
            				null,
            				null
            				));
        }
        return userLoginStatusStatisticsMappers;
    }
    
    @Override
    public long count() {
        return deviceOnlineStatusStatisticsService.count();
    }
}
