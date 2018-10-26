package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;

import com.tianzhixing.app.pojo.UserBank;

public interface UserBankMapper {
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
}
