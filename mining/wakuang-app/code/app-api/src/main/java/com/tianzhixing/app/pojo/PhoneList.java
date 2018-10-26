package com.tianzhixing.app.pojo;

/**
 * 用户的通讯录信息,对应d_user_maillist表
 * @author dev-teng
 * @date 2018年8月14日
 */
public class PhoneList {
	private Integer maillistId;//自增ID
	private Integer userId;//用户的ID
	private String phone;//通讯录里的电话号码
	private String name;//通讯录里的姓名
	private Long addDatetime;//添加时间
	private Integer steta;//注册状态，1注册过 0为注册
	public Integer getMaillistId() {
		return maillistId;
	}
	public void setMaillistId(Integer maillistId) {
		this.maillistId = maillistId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Long getAddDatetime() {
		return addDatetime;
	}
	public void setAddDatetime(Long addDatetime) {
		this.addDatetime = addDatetime;
	}
	public Integer getSteta() {
		return steta;
	}
	public void setSteta(Integer steta) {
		this.steta = steta;
	}
	
}
