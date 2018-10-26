package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.util.Calendar;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tianzhixing.app.common.AreaConfig;
import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.component.grpc.GrpcConnection;
import com.tianzhixing.app.dao.mapper.UserBankMapper;
import com.tianzhixing.app.dao.mapper.UserRealNameMapper;
import com.tianzhixing.app.pojo.Bank;
import com.tianzhixing.app.pojo.UserBank;
import com.tianzhixing.app.pojo.UserRealName;
import com.tianzhixing.app.service.RealService;
import com.tianzhixing.app.util.CommonUtils;

@Service
public class RealServiceImpl implements RealService {
	@Resource
	private GrpcConnection grpcConnection;
	@Resource
	private UserBankMapper userBankMapper;
	@Resource
	private UserRealNameMapper userRealNameMapper;

	@Override
	public UserBank getBankByCode(String bank_card_no) {
		if(bank_card_no==null || bank_card_no.isEmpty()) {
			return null;
		}
		return userBankMapper.getBankByCode(bank_card_no);
	}

	@Override
	public Integer addUserBank(UserBank userBank) throws SQLException {
		Bank bank = validateBank(userBank.getBank_card_no());// 校验银行卡有效性
		if (bank == null) {
			return 0;
		}
		userBank.setAdd_datetime(System.currentTimeMillis()/1000);
		userBank.setBank_name(bank.getBankName());
		userBank.setBranch(bank.getBankName());
		userBank.setStatus((short) 0);
		userBank.setBank_img(bank.getBankImg());
		userBank.setBank_code(bank.getBank());
		return userBankMapper.addUserBank(userBank);
	}

	/**
	 * 校验银行卡有效性,如果有效，放回Bank，无效返回null
	 * 
	 * @param cardNum
	 * @return
	 */
	private Bank validateBank(String cardNum) {
		String result = CommonUtils
				.httpGet("https://ccdcapi.alipay.com/validateAndCacheCardInfo.json?_input_charset=utf-8&cardNo="
						+ cardNum + "&cardBinCheck=true");
		Bank bank = null;
		if (result != null && !result.isEmpty()) {
			JSONObject json = JSON.parseObject(result);
			if (!json.getBoolean("validated")) {
				return null;
			} else {
				bank = new Bank();
				bank.setBank(json.getString("bank"));//
				bank.setValidated(json.getBoolean("validated"));
				bank.setBankName(Constant.BANK.containsKey(json.getString("bank")) == true
						? Constant.BANK.get(json.getString("bank"))
						: "");
				bank.setBankImg(getBankImg(json.getString("bank")));
				bank.setCardType(json.getString("cardType"));
				bank.setCardTypeName(getCardTypeName(json.getString("cardType")));
			}
		}
		return bank;
	}

	private String getCardTypeName(String cardType) {
		String name = "";
		switch (cardType) {
		case "CC":
			name = "信用卡";
			break;
		case "DC":
			name = "储蓄卡";
			break;
		default:
			break;
		}
		return name;
	}

	private String getBankImg(String bank) {
		return "https://apimg.alipay.com/combo.png?d=cashier&t=" + bank;
	}

	@Override
	public UserRealName getRealByCardID(String cardID) throws SQLException {
		if (cardID == null) {
			return null;
		}
		return userRealNameMapper.getRealByCardID(cardID);
	}

	@Override
	public Integer addRealName(UserRealName userRealName) throws SQLException {
		String cardID = userRealName.getCard_id();
		String card_address = getCardArea(cardID);
		Short user_age = getCardAge(cardID);
		String birth_day = getCardBirthday(cardID);
		String birth_year = getCardBirthYear(cardID);
		userRealName.setCard_address(card_address);
		userRealName.setUser_age(user_age);
		userRealName.setBirth_day(birth_day);
		if(birth_year!=null) {
			userRealName.setBirth_year(Integer.valueOf(birth_year));
		}
		userRealName.setCard_type((short) 1);
		userRealName.setAudit_status((short) 0);
		userRealName.setUser_sex(getSex(cardID));
		userRealName.setAdd_datetime(System.currentTimeMillis() / 1000);
		return userRealNameMapper.addRealName(userRealName);
	}

	private String getCardArea(String cardID) {
		if (cardID == null) {
			return null;
		}
		return AreaConfig.AREA.get(cardID.substring(0, 6));
	}

	private Short getCardAge(String cardID) {
		if (cardID == null) {
			return null;
		}
		Calendar date = Calendar.getInstance();
		Integer nowYear = date.get(Calendar.YEAR);
		return (short) (nowYear - Integer.valueOf(cardID.substring(6, 10)));
	}

	private String getCardBirthday(String cardID) {
		if (cardID == null) {
			return null;
		}
		return cardID.substring(10, 14);
	}

	private String getCardBirthYear(String cardID) {
		if (cardID == null) {
			return null;
		}
		return cardID.substring(6, 10);
	}

	private Short getSex(String cardID) {
		if (cardID == null) {
			return null;
		}
		int sex = Integer.valueOf(cardID.substring(16, 17));
		if (sex % 2 == 1) {
			return 1;
		} else {
			return 2;
		}
	}

	@Override
	public UserRealName getRealByUserID(Integer userId) throws SQLException {
		UserRealName userRealName = userRealNameMapper.getRealByUserID(userId);
		if (userRealName == null) {
			return null;
		} else {
			// 隐藏身份证
			userRealName.setCard_id("**************" + userRealName.getCard_id().substring(14, 18));
			String real_name = userRealName.getReal_name();
			userRealName.setReal_name(real_name.substring(0, 1)
					+ real_name.substring(1, real_name.length()).replaceAll("[\u4e00-\u9fa5]", "*"));
			return userRealName;
		}
	}
}
