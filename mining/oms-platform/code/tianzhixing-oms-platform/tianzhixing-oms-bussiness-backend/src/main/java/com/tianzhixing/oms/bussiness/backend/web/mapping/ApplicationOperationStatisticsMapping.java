package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class ApplicationOperationStatisticsMapping {

    private ApplicationDimensionMapper applicationDimensionMapper;

    private List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers;

    public ApplicationOperationStatisticsMapping() {
    }

    public ApplicationOperationStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers) {
        this.applicationDimensionMapper = applicationDimensionMapper;
        this.applicationOperationStatisticsMappers = applicationOperationStatisticsMappers;
    }

    public ApplicationDimensionMapper getApplicationDimensionMapper() {
        return applicationDimensionMapper;
    }

    public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
        this.applicationDimensionMapper = applicationDimensionMapper;
    }

    public List<ApplicationOperationStatisticsMapper> getApplicationOperationStatisticsMappers() {
        return applicationOperationStatisticsMappers;
    }

    public void setApplicationOperationStatisticsMappers(List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers) {
        this.applicationOperationStatisticsMappers = applicationOperationStatisticsMappers;
    }
}
