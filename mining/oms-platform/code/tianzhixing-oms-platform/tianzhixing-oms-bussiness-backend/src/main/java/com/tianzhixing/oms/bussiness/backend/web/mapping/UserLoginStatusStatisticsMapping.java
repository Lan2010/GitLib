package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserLoginStatusStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class UserLoginStatusStatisticsMapping {

    private ApplicationDimensionMapper applicationDimensionMapper;

    private List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers;

    public UserLoginStatusStatisticsMapping() {
    }

    public UserLoginStatusStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers) {
        this.applicationDimensionMapper = applicationDimensionMapper;
        this.userLoginStatusStatisticsMappers = userLoginStatusStatisticsMappers;
    }

    public ApplicationDimensionMapper getApplicationDimensionMapper() {
        return applicationDimensionMapper;
    }

    public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
        this.applicationDimensionMapper = applicationDimensionMapper;
    }

    public List<UserLoginStatusStatisticsMapper> getUserLoginStatusStatisticsMappers() {
        return userLoginStatusStatisticsMappers;
    }

    public void setUserLoginStatusStatisticsMappers(List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers) {
        this.userLoginStatusStatisticsMappers = userLoginStatusStatisticsMappers;
    }
}
