package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOnlineStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceOnLineStatusStatisticsService;
import com.tianzhixing.oms.rpc.mapper.DeviceOnlineStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceOnLineStatusService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 设备上下线统计
 * Created by routine.k on 2018/7/7.
 */
@Service
public class DeviceOnlineStatusStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCDeviceDimensionService rpcDeviceDimensionService;

    @Autowired
    private RPCDeviceOnLineStatusService rpcDeviceOnLineStatusService;

    @Autowired
    private RPCDeviceOnLineStatusStatisticsService rpcDeviceOnLineStatusStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<DeviceOnlineStatusStatisticsMapper> deviceOnlineStatusStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<DeviceDimensionMapper> deviceDimensionMappers = rpcDeviceDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<DeviceOnlineStatusInfoMapper> deviceOnlineStatusInfoMappers = rpcDeviceOnLineStatusService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        deviceOnlineStatusStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, deviceOnlineStatusInfoMappers, deviceDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    deviceOnlineStatusStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, deviceOnlineStatusInfoMappers, deviceDimensionMappers));
                }

            }
        }
        rpcDeviceOnLineStatusStatisticsService.insert(deviceOnlineStatusStatisticsMappers, taskAllotMapper);
    }

    private List<DeviceOnlineStatusStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<DeviceOnlineStatusInfoMapper> deviceOnlineStatusInfoMappers, List<DeviceDimensionMapper> deviceDimensionMappers) {
        List<DeviceOnlineStatusStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (DeviceDimensionMapper deviceDimensionMapper : deviceDimensionMappers) {
            DeviceOnlineStatusStatisticsMapper deviceOnlineStatusStatisticsMapper = new DeviceOnlineStatusStatisticsMapper();
            deviceOnlineStatusStatisticsMapper.setId(null);
            deviceOnlineStatusStatisticsMapper.setVersion(0);
            deviceOnlineStatusStatisticsMapper.setStatisticsHour(hour);
            deviceOnlineStatusStatisticsMapper.setStatisticsDay(day);
            deviceOnlineStatusStatisticsMapper.setStatisticsMonth(month);
            deviceOnlineStatusStatisticsMapper.setStatisticsYear(year);
            deviceOnlineStatusStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            deviceOnlineStatusStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            deviceOnlineStatusStatisticsMapper.setDeviceType(deviceDimensionMapper.getValue());
            deviceOnlineStatusStatisticsMapper.setDeviceTypeName(deviceDimensionMapper.getName());
            deviceOnlineStatusStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            //计算
            int bindDeviceOnlineCount = 0;//已绑定设备上线数
            int bindDeviceDiffIDOnlineCount = 0;//已绑定设备上线数（独立device）
            int unBindDeviceOnlineCount = 0;//未绑定设备上线数
            int unBindDeviceDiffIDOnlineCount = 0;//未绑定设备上线数（独立device）
            int bindDeviceOfflineCount = 0;//已绑定设备下线数
            int bindDeviceDiffIDOfflineCount = 0;//已绑定设备下线数（独立device）
            int unBindDeviceOfflineCount = 0;//未绑定设备下线数
            int unBindDeviceDiffIDOfflineCount = 0;//未绑定设备下线数（独立device）
            int unBindDeviceCurrentOnlineCount = 0;
            int bindDeviceCurrentOnlineCount = 0;
            Set<String> bindOnlineIds = new HashSet<>();
            Set<String> unBindOnlineIds = new HashSet<>();
            Set<String> bindOfflineIds = new HashSet<>();
            Set<String> unBindOfflineIds = new HashSet<>();
            for (DeviceOnlineStatusInfoMapper deviceOnlineStatusInfoMapper : deviceOnlineStatusInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(deviceOnlineStatusInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(deviceOnlineStatusInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (StringUtils.isEmpty(deviceOnlineStatusInfoMapper.getDeviceType()) || !deviceDimensionMapper.getValue().equals(deviceOnlineStatusInfoMapper.getDeviceType())) {
                    continue;
                }
                if (deviceOnlineStatusInfoMapper.getBindStatus() == 0) {
                    if (deviceOnlineStatusInfoMapper.getOperationType() == 0) {
                        //未绑定下线
                        unBindDeviceOfflineCount++;
                        if (StringUtils.isNotEmpty(deviceOnlineStatusInfoMapper.getDeviceId()) && !unBindOfflineIds.contains(deviceOnlineStatusInfoMapper.getDeviceId())) {
                            unBindOfflineIds.add(deviceOnlineStatusInfoMapper.getDeviceId());
                            unBindDeviceDiffIDOfflineCount++;
                        }
                    } else {
                        //未绑定上线
                        unBindDeviceOnlineCount++;
                        if (StringUtils.isNotEmpty(deviceOnlineStatusInfoMapper.getDeviceId()) && !unBindOnlineIds.contains(deviceOnlineStatusInfoMapper.getDeviceId())) {
                            unBindOnlineIds.add(deviceOnlineStatusInfoMapper.getDeviceId());
                            unBindDeviceDiffIDOnlineCount++;
                        }
                    }
                } else {
                    if (deviceOnlineStatusInfoMapper.getOperationType() == 0) {
                        //绑定下线
                        bindDeviceOfflineCount++;
                        if (StringUtils.isNotEmpty(deviceOnlineStatusInfoMapper.getDeviceId()) && !bindOfflineIds.contains(deviceOnlineStatusInfoMapper.getDeviceId())) {
                            bindOfflineIds.add(deviceOnlineStatusInfoMapper.getDeviceId());
                            bindDeviceDiffIDOfflineCount++;
                        }
                    } else {
                        //绑定上线
                        bindDeviceOnlineCount++;
                        if (StringUtils.isNotEmpty(deviceOnlineStatusInfoMapper.getDeviceId()) && !bindOnlineIds.contains(deviceOnlineStatusInfoMapper.getDeviceId())) {
                            bindOnlineIds.add(deviceOnlineStatusInfoMapper.getDeviceId());
                            bindDeviceDiffIDOnlineCount++;
                        }
                    }
                }
            }
            //当前未绑定设备在线数（独立device), 可能出现负值
            unBindDeviceCurrentOnlineCount = unBindDeviceDiffIDOnlineCount - unBindDeviceDiffIDOfflineCount;
            //当前已绑定设备在线数（独立device）, 可能出现负值
            bindDeviceCurrentOnlineCount = bindDeviceDiffIDOnlineCount - bindDeviceDiffIDOfflineCount;

            deviceOnlineStatusStatisticsMapper.setBindDeviceOnlineCount(bindDeviceOnlineCount);
            deviceOnlineStatusStatisticsMapper.setBindDeviceDiffIDOnlineCount(bindDeviceDiffIDOnlineCount);
            deviceOnlineStatusStatisticsMapper.setUnBindDeviceOnlineCount(unBindDeviceOnlineCount);
            deviceOnlineStatusStatisticsMapper.setUnBindDeviceDiffIDOnlineCount(unBindDeviceDiffIDOnlineCount);
            deviceOnlineStatusStatisticsMapper.setBindDeviceOfflineCount(bindDeviceOfflineCount);
            deviceOnlineStatusStatisticsMapper.setBindDeviceDiffIDOfflineCount(bindDeviceDiffIDOfflineCount);
            deviceOnlineStatusStatisticsMapper.setUnBindDeviceOfflineCount(unBindDeviceOfflineCount);
            deviceOnlineStatusStatisticsMapper.setUnBindDeviceDiffIDOfflineCount(unBindDeviceDiffIDOfflineCount);
            deviceOnlineStatusStatisticsMapper.setUnBindDeviceCurrentOnlineCount(unBindDeviceCurrentOnlineCount);
            deviceOnlineStatusStatisticsMapper.setBindDeviceCurrentOnlineCount(bindDeviceCurrentOnlineCount);
            list.add(deviceOnlineStatusStatisticsMapper);
        }
        return list;
    }
}
