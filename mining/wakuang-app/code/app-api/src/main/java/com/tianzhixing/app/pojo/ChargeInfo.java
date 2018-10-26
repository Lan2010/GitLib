package com.tianzhixing.app.pojo;

public class ChargeInfo {
	private Integer charge_id; // 充电宝地址池ID
	private String device_id; // 设备ID
	private String device_type; // 设备类型
	private String device_model; // 设备型号
	private String device_mac; // 充电设备MAC
	private Short state; // 充电宝状态 1默认未绑定 2 已经绑定 0 删除
	private String bind_user_id; //
	private String bind_datetime; // 绑定时间
	private String add_datetime; // 添加时间
	private String remark; // 处理信息
	private String op_type; // 操作类型 bind 绑定 unbind 解绑

	public String getOp_type() {
		return op_type;
	}

	public void setOp_type(String op_type) {
		this.op_type = op_type;
	}

	public Integer getCharge_id() {
		return charge_id;
	}

	public void setCharge_id(Integer charge_id) {
		this.charge_id = charge_id;
	}

	public String getDevice_id() {
		return device_id;
	}

	public void setDevice_id(String device_id) {
		this.device_id = device_id;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public String getDevice_model() {
		return device_model;
	}

	public void setDevice_model(String device_model) {
		this.device_model = device_model;
	}

	public String getDevice_mac() {
		return device_mac;
	}

	public void setDevice_mac(String device_mac) {
		this.device_mac = device_mac;
	}

	public Short getState() {
		return state;
	}

	public void setState(Short state) {
		this.state = state;
	}

	public String getBind_user_id() {
		return bind_user_id;
	}

	public void setBind_user_id(String bind_user_id) {
		this.bind_user_id = bind_user_id;
	}

	public String getBind_datetime() {
		return bind_datetime;
	}

	public void setBind_datetime(String bind_datetime) {
		this.bind_datetime = bind_datetime;
	}

	public String getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(String add_datetime) {
		this.add_datetime = add_datetime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
