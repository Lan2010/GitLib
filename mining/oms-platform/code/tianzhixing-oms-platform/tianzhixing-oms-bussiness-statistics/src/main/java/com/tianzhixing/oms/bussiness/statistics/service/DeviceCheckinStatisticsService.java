package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceCheckinStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceCheckinStatisticsService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceDimensionService;
import com.tianzhixing.oms.rpc.mapper.DeviceCheckinInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceCheckinService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 设备登记统计
 * Created by routine.k on 2018/7/7.
 */
@Service
public class DeviceCheckinStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCDeviceCheckinService rpcDeviceCheckinService;

    @Autowired
    private RPCDeviceCheckinStatisticsService rpcDeviceCheckinStatisticsService;

    @Autowired
    private RPCDeviceDimensionService rpcDeviceDimensionService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<DeviceCheckinStatisticsMapper> deviceCheckinStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<DeviceDimensionMapper> deviceDimensionMappers = rpcDeviceDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<DeviceCheckinInfoMapper> deviceCheckinInfoMappers = rpcDeviceCheckinService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        deviceCheckinStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, deviceCheckinInfoMappers, deviceDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    deviceCheckinStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, deviceCheckinInfoMappers, deviceDimensionMappers));
                }

            }
        }
        rpcDeviceCheckinStatisticsService.insert(deviceCheckinStatisticsMappers, taskAllotMapper);
    }

    private List<DeviceCheckinStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper platform, ApplicationDimensionMapper clientPlatform, List<DeviceCheckinInfoMapper> deviceCheckinInfoMappers, List<DeviceDimensionMapper> deviceDimensionMappers) {
        List<DeviceCheckinStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (DeviceDimensionMapper deviceDimensionMapper : deviceDimensionMappers) {
            DeviceCheckinStatisticsMapper deviceCheckinStatisticsMapper = new DeviceCheckinStatisticsMapper();
            deviceCheckinStatisticsMapper.setId(null);
            deviceCheckinStatisticsMapper.setVersion(0);
            deviceCheckinStatisticsMapper.setStatisticsHour(hour);
            deviceCheckinStatisticsMapper.setStatisticsDay(day);
            deviceCheckinStatisticsMapper.setStatisticsMonth(month);
            deviceCheckinStatisticsMapper.setStatisticsYear(year);
            deviceCheckinStatisticsMapper.setClientPlatformType(clientPlatform == null ? null : clientPlatform.getValue());
            deviceCheckinStatisticsMapper.setPlatformFrom(platform.getValue());
            deviceCheckinStatisticsMapper.setDeviceType(deviceDimensionMapper.getValue());
            deviceCheckinStatisticsMapper.setDeviceTypeName(deviceDimensionMapper.getName());
            deviceCheckinStatisticsMapper.setPlatformName(platform.getName() + (clientPlatform == null ? "" : ("-" + clientPlatform.getName())));
            //计算
            int deviceBindTotalCount = 0;
            int deviceUnBindTotalCount = 0;
            for (DeviceCheckinInfoMapper deviceCheckinInfoMapper : deviceCheckinInfoMappers) {
                if (!platform.getValue().equals(deviceCheckinInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (clientPlatform != null && !clientPlatform.getValue().equals(deviceCheckinInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (deviceCheckinInfoMapper.getBindStatus() == 0) {
                    deviceUnBindTotalCount++;
                } else {
                    deviceBindTotalCount++;
                }
            }
            deviceCheckinStatisticsMapper.setDeviceBindTotalCount(deviceBindTotalCount);
            deviceCheckinStatisticsMapper.setDeviceUnBindTotalCount(deviceUnBindTotalCount);
            list.add(deviceCheckinStatisticsMapper);
        }
        return list;
    }
}
