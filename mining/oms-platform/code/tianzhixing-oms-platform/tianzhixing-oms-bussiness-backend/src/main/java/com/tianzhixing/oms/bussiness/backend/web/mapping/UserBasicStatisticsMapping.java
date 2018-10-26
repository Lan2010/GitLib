package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserBasicStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class UserBasicStatisticsMapping {

    private ApplicationDimensionMapper applicationDimensionMapper;

    private List<UserBasicStatisticsMapper> userBasicStatisticsMappers;

    public UserBasicStatisticsMapping() {
    }

    public UserBasicStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<UserBasicStatisticsMapper> userBasicStatisticsMappers) {
        this.applicationDimensionMapper = applicationDimensionMapper;
        this.userBasicStatisticsMappers = userBasicStatisticsMappers;
    }

    public ApplicationDimensionMapper getApplicationDimensionMapper() {
        return applicationDimensionMapper;
    }

    public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
        this.applicationDimensionMapper = applicationDimensionMapper;
    }

    public List<UserBasicStatisticsMapper> getUserBasicStatisticsMappers() {
        return userBasicStatisticsMappers;
    }

    public void setUserBasicStatisticsMappers(List<UserBasicStatisticsMapper> userBasicStatisticsMappers) {
        this.userBasicStatisticsMappers = userBasicStatisticsMappers;
    }
}
