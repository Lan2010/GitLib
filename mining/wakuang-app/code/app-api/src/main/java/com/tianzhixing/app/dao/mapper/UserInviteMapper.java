package com.tianzhixing.app.dao.mapper;

import org.apache.ibatis.annotations.Param;

public interface UserInviteMapper {
	/**
	 * 
	 * @param userId
	 * @return
	 */
	Integer getInviteCount(@Param(value = "userId") Integer userId, @Param(value = "startDate") Integer startDate,
			@Param(value = "endDate") Integer endDate);
}
