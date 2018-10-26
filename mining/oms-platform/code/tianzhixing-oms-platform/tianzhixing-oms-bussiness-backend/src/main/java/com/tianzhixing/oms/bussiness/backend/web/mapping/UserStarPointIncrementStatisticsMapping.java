package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointIncrementStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class UserStarPointIncrementStatisticsMapping {

    private ApplicationDimensionMapper applicationDimensionMapper;

    private List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers;
    
    private Integer incrementType;

    public UserStarPointIncrementStatisticsMapping() {
    }

    public UserStarPointIncrementStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers) {
        this.applicationDimensionMapper = applicationDimensionMapper;
        this.userStarPointIncrementStatisticsMappers = userStarPointIncrementStatisticsMappers;
    }

    public ApplicationDimensionMapper getApplicationDimensionMapper() {
        return applicationDimensionMapper;
    }

    public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
        this.applicationDimensionMapper = applicationDimensionMapper;
    }

    public List<UserStarPointIncrementStatisticsMapper> getUserStarPointIncrementStatisticsMappers() {
        return userStarPointIncrementStatisticsMappers;
    }

    public void setUserStarPointIncrementStatisticsMappers(List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers) {
        this.userStarPointIncrementStatisticsMappers = userStarPointIncrementStatisticsMappers;
    }

	public Integer getIncrementType() {
		return incrementType;
	}

	public void setIncrementType(Integer incrementType) {
		this.incrementType = incrementType;
	}

	public UserStarPointIncrementStatisticsMapping(List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers, Integer incrementType) {
		this.userStarPointIncrementStatisticsMappers = userStarPointIncrementStatisticsMappers;
		this.incrementType = incrementType;
	}

}
