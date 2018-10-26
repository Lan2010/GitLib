package com.tianzhixing.app.service;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.AppInfo;
import com.tianzhixing.app.pojo.AppVersions;
import com.tianzhixing.app.pojo.FeedBack;
import com.tianzhixing.app.pojo.ResultMessage;

public interface CommonService {

	ResultMessage getSuspensionFrame()throws SQLException;

	void pushAppOnOff2Nats(String ip, Short terminal, String phone, String operationType);
	
	/**
	 * 获取App最新版本信息
	 * @param terminal
	 * @return
	 */
	AppVersions getAppVersions(Short terminal);
	
	/**
	 * 获取Android版本的app的校验码（MD5值，IOS不需要此项）
	 * @return
	 */
	String getCheckCodes();
	
	/**
	 * 获取某台设备的用户一天内反馈信息次数
	 * @param deviceID
	 * @param now
	 * @param plus1day
	 * @throws SQLException 
	 */
	public Integer getFebackTimes(String deviceID, long now, long plus1day) throws SQLException;

	/**
	 * 添加用户返回信息
	 * @param feedBack
	 * @return
	 * @throws SQLException
	 */
	Integer addFeedBack(FeedBack feedBack) throws SQLException;
	
	/**
	 * 添加手机app信息
	 * @param appInfo
	 * @return
	 * @throws SQLException
	 */
	Integer addAppInfo(AppInfo appInfo)throws SQLException;
	
}
