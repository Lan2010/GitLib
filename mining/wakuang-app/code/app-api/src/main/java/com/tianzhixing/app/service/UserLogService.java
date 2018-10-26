package com.tianzhixing.app.service;

import java.sql.SQLException;
import java.util.Map;

public interface UserLogService {
	/**
	 * 添加登录日志
	 * @param userId
	 * @param phone
	 * @param ip
	 * @param act
	 * @param rs
	 * @param terminal
	 */
	boolean addLoginLog(Integer userId,String phone,String ip,String action,String result,Short terminal)throws SQLException;
	
	/**
	 * 调用存储过程，插入用户登录日志
	 * @param map
	 * @throws SQLException
	 */
	public void loginSucceedCallback(Map<String, Object> map) throws SQLException;
}
