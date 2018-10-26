package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOperationStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class DeviceOperationStatisticsMapping {

    private ApplicationDimensionMapper applicationDimensionMapper;

    private List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers;

    public DeviceOperationStatisticsMapping() {
    }

    public DeviceOperationStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers) {
        this.applicationDimensionMapper = applicationDimensionMapper;
        this.deviceOperationStatisticsMappers = deviceOperationStatisticsMappers;
    }

    public ApplicationDimensionMapper getApplicationDimensionMapper() {
        return applicationDimensionMapper;
    }

    public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
        this.applicationDimensionMapper = applicationDimensionMapper;
    }

    public List<DeviceOperationStatisticsMapper> getDeviceOperationStatisticsMappers() {
        return deviceOperationStatisticsMappers;
    }

    public void setDeviceOperationStatisticsMappers(List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers) {
        this.deviceOperationStatisticsMappers = deviceOperationStatisticsMappers;
    }
}
