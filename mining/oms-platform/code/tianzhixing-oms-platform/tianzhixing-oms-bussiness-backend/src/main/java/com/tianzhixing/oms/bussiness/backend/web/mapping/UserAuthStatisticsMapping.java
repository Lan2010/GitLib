package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class UserAuthStatisticsMapping {

	private ApplicationDimensionMapper applicationDimensionMapper;

	private UserAuthDimensionMapper userAuthDimensionMapper;

	private List<UserAuthStatisticsMapper> userAuthStatisticsMappers;

	public UserAuthStatisticsMapping() {
	}

	public UserAuthStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<UserAuthStatisticsMapper> userAuthStatisticsMappers) {
		this.applicationDimensionMapper = applicationDimensionMapper;
		this.userAuthStatisticsMappers = userAuthStatisticsMappers;
	}

	public ApplicationDimensionMapper getApplicationDimensionMapper() {
		return applicationDimensionMapper;
	}

	public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
		this.applicationDimensionMapper = applicationDimensionMapper;
	}

	public List<UserAuthStatisticsMapper> getUserAuthStatisticsMappers() {
		return userAuthStatisticsMappers;
	}

	public void setUserAuthStatisticsMappers(List<UserAuthStatisticsMapper> userAuthStatisticsMappers) {
		this.userAuthStatisticsMappers = userAuthStatisticsMappers;
	}

	public UserAuthDimensionMapper getUserAuthDimensionMapper() {
		return userAuthDimensionMapper;
	}

	public void setUserAuthDimensionMapper(UserAuthDimensionMapper userAuthDimensionMapper) {
		this.userAuthDimensionMapper = userAuthDimensionMapper;
	}

	public UserAuthStatisticsMapping(UserAuthDimensionMapper userAuthDimensionMapper, List<UserAuthStatisticsMapper> userAuthStatisticsMappers) {
		this.userAuthDimensionMapper = userAuthDimensionMapper;
		this.userAuthStatisticsMappers = userAuthStatisticsMappers;
	}

}
