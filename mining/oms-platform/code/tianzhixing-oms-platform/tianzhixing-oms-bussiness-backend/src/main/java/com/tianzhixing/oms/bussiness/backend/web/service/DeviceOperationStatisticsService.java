package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.DeviceOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceOperationStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class DeviceOperationStatisticsService {

    @Autowired
    private RPCDeviceOperationStatisticsService rpcDeviceOperationStatisticsService;

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    /**
     * 获取清单
     *
     * @param type
     * @return
     */
    public List<DeviceOperationStatisticsMapping> list(int type) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<DeviceOperationStatisticsMapping> deviceOperationStatisticsMappings = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
            if (subList != null && !subList.isEmpty()) {
                for (ApplicationDimensionMapper adm : subList) {
                    DeviceOperationStatisticsMapping deviceOperationStatisticsMapping = new DeviceOperationStatisticsMapping();
                    adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
                    List<DeviceOperationStatisticsMapper> list = new ArrayList<>();
                    list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, adm.getName(),null));
                    deviceOperationStatisticsMapping.setApplicationDimensionMapper(adm);
                    deviceOperationStatisticsMapping.setDeviceOperationStatisticsMappers(list);
                    deviceOperationStatisticsMappings.add(deviceOperationStatisticsMapping);
                }
            } else {
                //未配置客户端
                DeviceOperationStatisticsMapping deviceOperationStatisticsMapping = new DeviceOperationStatisticsMapping();
                List<DeviceOperationStatisticsMapper> list = new ArrayList<>();
                list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, applicationDimensionMapper.getName(),null));
                deviceOperationStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
                deviceOperationStatisticsMapping.setDeviceOperationStatisticsMappers(list);
                deviceOperationStatisticsMappings.add(deviceOperationStatisticsMapping);
            }

        }
        return deviceOperationStatisticsMappings;
    }

    private List<DeviceOperationStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName,String deviceType) {
        List<DeviceOperationStatisticsMapper> list = new ArrayList<>();
        if (0 == type) {
            List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = rpcDeviceOperationStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(),applicationDimensionMapper == null ? null : applicationDimensionMapper.getName(),deviceType);
            Map<String, DeviceOperationStatisticsMapper> deviceOperationStatisticsMapperMap = new HashMap<>();
            for (DeviceOperationStatisticsMapper deviceOperationStatisticsMapper : deviceOperationStatisticsMappers) {
                deviceOperationStatisticsMapperMap.put(deviceOperationStatisticsMapper.getStatisticsHour().toString(), deviceOperationStatisticsMapper);
            }
            //获取小时 24小时
            for (Integer i = 0; i < hour; i++) {
                DeviceOperationStatisticsMapper deviceOperationStatisticsMapper = deviceOperationStatisticsMapperMap.get(i.toString());
                if (deviceOperationStatisticsMapper == null) {
                    deviceOperationStatisticsMapper = new DeviceOperationStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(),applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, 0, deviceType, null, platformName);
                }
                list.add(deviceOperationStatisticsMapper);
            }
        } else if (1 == type) {
            List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = rpcDeviceOperationStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(),applicationDimensionMapper == null ? null : applicationDimensionMapper.getName(),deviceType);
            Map<String, DeviceOperationStatisticsMapper> deviceOperationStatisticsMapperMap = new HashMap<>();
            for (DeviceOperationStatisticsMapper deviceOperationStatisticsMapper : deviceOperationStatisticsMappers) {
                deviceOperationStatisticsMapperMap.put(deviceOperationStatisticsMapper.getStatisticsDay().toString(), deviceOperationStatisticsMapper);
            }
            //获取天
            for (Integer i = 1; i <= day; i++) {
                DeviceOperationStatisticsMapper deviceOperationStatisticsMapper = deviceOperationStatisticsMapperMap.get(i.toString());
                if (deviceOperationStatisticsMapper == null) {
                    deviceOperationStatisticsMapper = new DeviceOperationStatisticsMapper(null, null, null, null, i, month, year, adm == null ? null : adm.getValue(),applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, 0, deviceType, null, platformName);
                }
                list.add(deviceOperationStatisticsMapper);
            }

        } else if (2 == type) {

            List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = rpcDeviceOperationStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(),applicationDimensionMapper == null ? null : applicationDimensionMapper.getName(),deviceType);
            Map<String, DeviceOperationStatisticsMapper> deviceOperationStatisticsMapperMap = new HashMap<>();
            for (DeviceOperationStatisticsMapper deviceOperationStatisticsMapper : deviceOperationStatisticsMappers) {
                deviceOperationStatisticsMapperMap.put(deviceOperationStatisticsMapper.getStatisticsMonth().toString(), deviceOperationStatisticsMapper);
            }
            //获取月
            for (Integer i = 1; i <= month; i++) {
                DeviceOperationStatisticsMapper deviceOperationStatisticsMapper = deviceOperationStatisticsMapperMap.get(i.toString());
                if (deviceOperationStatisticsMapper == null) {
                    deviceOperationStatisticsMapper = new DeviceOperationStatisticsMapper(null, null, null, null, null, i, year, adm == null ? null : adm.getValue(),applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, 0, deviceType, null, platformName);
                }
                list.add(deviceOperationStatisticsMapper);
            }

        }
        return list;
    }
    
    public List<DeviceOperationStatisticsMapper> listByDeviceDimensionAndApplication(int type, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, DeviceDimensionMapper deviceDimensionMapper) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = new ArrayList<>();
        deviceOperationStatisticsMappers.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, applicationDimensionMapper.getName(),deviceDimensionMapper.getValue()));
        return deviceOperationStatisticsMappers;
    }
}
