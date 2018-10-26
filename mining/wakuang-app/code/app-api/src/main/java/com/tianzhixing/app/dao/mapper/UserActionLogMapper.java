package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.Map;

public interface UserActionLogMapper {
	/**
	 * 添加用户登录日志
	 * @return
	 * @throws SQLException
	 */
	Integer addLoginLog(Map<String,Object> map) throws SQLException;
	
	
	/**
	 * 调用存储过程
	 * @param loginMap
	 */
	void callLoginlog(Map<String,Object> loginMap);
}
