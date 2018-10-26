package com.tianzhixing.app.pojo;

public class Bank {
	private boolean validated;//是否验证通过
	private String bank;//银行代码
	private String bankName;//银行名称
	private String bankImg;//银行卡图片
	private String cardType;//银行卡类型, CC 信用卡, DC 储蓄卡
	private String cardTypeName;
	public boolean isValidated() {
		return validated;
	}
	public void setValidated(boolean validated) {
		this.validated = validated;
	}
	public String getBank() {
		return bank;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankImg() {
		return bankImg;
	}
	public void setBankImg(String bankImg) {
		this.bankImg = bankImg;
	}
	public String getCardType() {
		return cardType;
	}
	public void setCardType(String cardType) {
		this.cardType = cardType;
	}
	public String getCardTypeName() {
		return cardTypeName;
	}
	public void setCardTypeName(String cardTypeName) {
		this.cardTypeName = cardTypeName;
	}
	
}
