package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianzhixing.app.dao.mapper.UserActionLogMapper;
import com.tianzhixing.app.service.UserLogService;

@Service
public class UserLogServiceImpl implements UserLogService {
	@Resource
	private UserActionLogMapper userActionLogMapper;

	@Override
	public boolean addLoginLog(Integer userId, String phone, String ip, String action, String result, Short terminal) throws SQLException {
		if (phone == null) {
			return false;
		}
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("add_datetime", System.currentTimeMillis()/1000);
		map.put("user_id", userId);
		map.put("phone", phone);
		map.put("log_ip", ip);
		map.put("action", action);
		map.put("ret", result);
		map.put("terminal", terminal);
		Integer count = userActionLogMapper.addLoginLog(map);
		if(count>0) {
			return true;
		}
		return false;
	}

	public void loginSucceedCallback(Map<String, Object> map) throws SQLException {
		map.put("equipment", map.get("login_type")+"|"+map.get("device_id")+"|"+map.get("token"));
		userActionLogMapper.callLoginlog(map);
		map.remove("equipment");
	}
	
}
