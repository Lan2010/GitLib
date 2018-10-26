package com.tianzhixing.app.pojo;

/**
 * 用户信息的bean
 * @author dev-teng
 * @date 2018年7月30日
 */
public class User {
	private Integer userId;// 自增ID
	private String userKey;// 用户编码，或者KEY值
	private String phone;// 电话(账号)
	private String userNickname;// 客户昵称
	private String password; // 密码
	private String smsCode; // 短信验证码
	private String payPassword;// 客户交易密码
	private Integer userStatus;// 客户状态 1 正常 0 注销
	private String realName;// 客户姓名
	private Short userSex;// 性别 0 未知 1 男 2 女
	private String headUrl;// 用户头像图片路径
	private Short phoneStatus;// 电话认证状态 0 未认证，1已认证
	private Long regDatetime;// 注册时间
	private Short regTerminal;// 注册终端 1 PC 2 安卓 3 IOS 4 微信
	private String regIp;// 注册IP
	private String openId;// 微信编码ID
	private Long editDatetime;// 修改时间
	private String invitationCode;// 邀请码
	private String userToken;// 用户Token
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getUserKey() {
		return userKey;
	}
	public void setUserKey(String userKey) {
		this.userKey = userKey;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getUserNickname() {
		return userNickname;
	}
	public void setUserNickname(String userNickname) {
		this.userNickname = userNickname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPayPassword() {
		return payPassword;
	}
	public void setPayPassword(String payPassword) {
		this.payPassword = payPassword;
	}
	public Integer getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(Integer userStatus) {
		this.userStatus = userStatus;
	}
	public String getRealName() {
		return realName;
	}
	public void setRealName(String realName) {
		this.realName = realName;
	}
	public Short getUserSex() {
		return userSex;
	}
	public void setUserSex(Short userSex) {
		this.userSex = userSex;
	}
	public String getHeadUrl() {
		return headUrl;
	}
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	public Short getPhoneStatus() {
		return phoneStatus;
	}
	public void setPhoneStatus(Short phoneStatus) {
		this.phoneStatus = phoneStatus;
	}
	public Short getRegTerminal() {
		return regTerminal;
	}
	public void setRegTerminal(Short regTerminal) {
		this.regTerminal = regTerminal;
	}
	public String getRegIp() {
		return regIp;
	}
	public void setRegIp(String regIp) {
		this.regIp = regIp;
	}
	public String getOpenId() {
		return openId;
	}
	public void setOpenId(String openId) {
		this.openId = openId;
	}
	public String getInvitationCode() {
		return invitationCode;
	}
	public void setInvitationCode(String invitationCode) {
		this.invitationCode = invitationCode;
	}
	public String getUserToken() {
		return userToken;
	}
	public void setUserToken(String userToken) {
		this.userToken = userToken;
	}
	public String getSmsCode() {
		return smsCode;
	}
	public void setSmsCode(String smsCode) {
		this.smsCode = smsCode;
	}
	public Long getRegDatetime() {
		return regDatetime;
	}
	public void setRegDatetime(Long regDatetime) {
		this.regDatetime = regDatetime;
	}
	public Long getEditDatetime() {
		return editDatetime;
	}
	public void setEditDatetime(Long editDatetime) {
		this.editDatetime = editDatetime;
	}
	
}
