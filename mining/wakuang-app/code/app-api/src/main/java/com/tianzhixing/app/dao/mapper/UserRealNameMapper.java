package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.UserRealName;

public interface UserRealNameMapper {
	/**
	 * 获取用户实名信息
	 * @return
	 * @throws SQLException
	 */
	UserRealName getRealByCardID(String cardID)throws SQLException;

	/**
	 * 新增yoghurt实名信息
	 * @param userRealName
	 */
	Integer addRealName(UserRealName userRealName)throws SQLException;

	/**
	 * 获取用户实名信息
	 * @param userId
	 * @return
	 */
	UserRealName getRealByUserID(Integer userId)throws SQLException;
}
