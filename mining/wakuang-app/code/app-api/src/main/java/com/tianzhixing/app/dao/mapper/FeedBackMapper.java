package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;

import org.apache.ibatis.annotations.Param;

import com.tianzhixing.app.pojo.FeedBack;

public interface FeedBackMapper {

	Integer getOneDayTimes(@Param(value = "deviceID") String deviceID, @Param(value = "startTime") long startTime,
			@Param(value = "endTime") long endTime) throws SQLException;
	
	/**
	 * 添加用户反馈信息
	 * @param feedBack
	 * @return
	 * @throws SQLException
	 */
	Integer addFeedBack(FeedBack feedBack)throws SQLException;
}
