package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;

import com.tianzhixing.app.pojo.ChargeInfo;

public interface ChargeMapper {

	/**
	 * 获取绑定的信息
	 * 
	 * @param userId
	 * @return
	 */
	List<ChargeInfo> getInfoByUser(Integer userId) throws SQLException;

	ChargeInfo getBindInfo(String devid) throws SQLException;

	/**
	 * 记入日志
	 * 
	 * @param chargeInfo
	 * @return
	 * @throws SQLException
	 */
	Integer chargeOpLog(ChargeInfo chargeInfo) throws SQLException;

	/**
	 * 绑定
	 * 
	 * @param chargeInfo
	 * @return
	 * @throws SQLException
	 */
	Integer updateBindInfo(ChargeInfo chargeInfo) throws SQLException;

	/**
	 * 通过用户ID获取其绑定的充电宝信息
	 * 
	 * @param userId
	 * @return
	 */
	ChargeInfo getByUserId(Integer userId);
}
