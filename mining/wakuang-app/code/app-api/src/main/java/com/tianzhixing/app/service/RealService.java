package com.tianzhixing.app.service;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.UserBank;
import com.tianzhixing.app.pojo.UserRealName;
import com.tianzhixing.auth.grpc.proto.idcard.AuthResult;

public interface RealService {
	
	/**
	 * 获取指定银行卡号的账户信息
	 * 
	 * @return
	 */
	UserBank getBankByCode(String bank_card_no);

	/**
	 * 添加用户银行卡信息
	 * @param userBank
	 * @return
	 * @throws SQLException
	 */
	Integer addUserBank(UserBank userBank) throws SQLException;
	
	/**
	 * 获取用户实名信息
	 * @return
	 * @throws SQLException
	 */
	UserRealName getRealByCardID(String cardID)throws SQLException;

	/**
	 * 添加用户实名信息
	 * @param userRealName
	 * @throws SQLException 
	 */
	Integer addRealName(UserRealName userRealName) throws SQLException;

	/**
	 * 获取用户实名信息
	 * @param userId
	 * @return
	 */
	public UserRealName getRealByUserID(Integer userId)throws SQLException;
	
	
}
