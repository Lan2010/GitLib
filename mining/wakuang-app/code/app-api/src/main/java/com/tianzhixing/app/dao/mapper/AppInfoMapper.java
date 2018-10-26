package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.AppInfo;

public interface AppInfoMapper {
	/**
	 * 
	 * @param appInfo
	 * @return
	 * @throws SQLException
	 */
	Integer addAppInfo(AppInfo appInfo) throws SQLException;
}
