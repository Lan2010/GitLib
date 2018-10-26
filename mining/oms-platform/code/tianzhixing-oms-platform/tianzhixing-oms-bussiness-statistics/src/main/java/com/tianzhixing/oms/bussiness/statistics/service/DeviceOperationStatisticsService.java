package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceOperationStatisticsService;
import com.tianzhixing.oms.rpc.mapper.DeviceOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceOperationService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service
public class DeviceOperationStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCDeviceDimensionService rpcDeviceDimensionService;

    @Autowired
    private RPCDeviceOperationService rpcDeviceOperationService;

    @Autowired
    private RPCDeviceOperationStatisticsService rpcDeviceOperationStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<DeviceDimensionMapper> deviceDimensionMappers = rpcDeviceDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<DeviceOperationInfoMapper> deviceOperationInfoMappers = rpcDeviceOperationService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        deviceOperationStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, deviceOperationInfoMappers, deviceDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    deviceOperationStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, deviceOperationInfoMappers, deviceDimensionMappers));
                }

            }
        }
        rpcDeviceOperationStatisticsService.insert(deviceOperationStatisticsMappers, taskAllotMapper);
    }

    private List<DeviceOperationStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<DeviceOperationInfoMapper> deviceOperationInfoMappers, List<DeviceDimensionMapper> deviceDimensionMappers) {
        List<DeviceOperationStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (DeviceDimensionMapper deviceDimensionMapper : deviceDimensionMappers) {
            DeviceOperationStatisticsMapper deviceOperationStatisticsMapper = new DeviceOperationStatisticsMapper();
            deviceOperationStatisticsMapper.setId(null);
            deviceOperationStatisticsMapper.setVersion(0);
            deviceOperationStatisticsMapper.setStatisticsHour(hour);
            deviceOperationStatisticsMapper.setStatisticsDay(day);
            deviceOperationStatisticsMapper.setStatisticsMonth(month);
            deviceOperationStatisticsMapper.setStatisticsYear(year);
            deviceOperationStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            deviceOperationStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            deviceOperationStatisticsMapper.setDeviceType(deviceDimensionMapper.getValue());
            deviceOperationStatisticsMapper.setDeviceTypeName(deviceDimensionMapper.getName());
            deviceOperationStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            //计算
            int deviceBindCount = 0;//设备绑定数
            int deviceUnBindCount = 0;//设备解绑数
            for (DeviceOperationInfoMapper deviceOperationInfoMapper : deviceOperationInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(deviceOperationInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(deviceOperationInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (StringUtils.isEmpty(deviceOperationInfoMapper.getDeviceType()) || !deviceDimensionMapper.getValue().equals(deviceOperationInfoMapper.getDeviceType())) {
                    continue;
                }
                if (deviceOperationInfoMapper.getOperationType() == 0) {
                    //解除绑定
                    deviceUnBindCount++;
                } else {
                    //绑定
                    deviceBindCount++;
                }
            }
            deviceOperationStatisticsMapper.setDeviceBindCount(deviceBindCount);
            deviceOperationStatisticsMapper.setDeviceUnBindCount(deviceUnBindCount);
            list.add(deviceOperationStatisticsMapper);
        }
        return list;
    }
}
