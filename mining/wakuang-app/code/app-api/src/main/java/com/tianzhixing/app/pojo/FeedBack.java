package com.tianzhixing.app.pojo;

public class FeedBack {
	private Integer feback_id;// 反馈ID,
	private Integer user_id;// 用户ID,
	private String phone;// 用户填写的电话,
	private Integer feback_type;// 0 : 默认 ,
	private String feback_info;// 用户反馈的信息,
	private Short terminal;// 操作终端 1 PC 2 安卓 3 IOS 4 微信,
	private String versions;// 版本号,
	private String equipment_id;// 设备ID,
	private String remark;// 处理信息,
	private Integer status;// 0:未处理 1:已处理,
	private Long add_datetime;// 添加时间,
	private Integer edit_datetime;// 处理时间,
	private String edit_user_name;// 处理人,
	private Integer edit_user_id;// 处理人ID,

	public Integer getFeback_id() {
		return feback_id;
	}

	public void setFeback_id(Integer feback_id) {
		this.feback_id = feback_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public Integer getFeback_type() {
		return feback_type;
	}

	public void setFeback_type(Integer feback_type) {
		this.feback_type = feback_type;
	}

	public String getFeback_info() {
		return feback_info;
	}

	public void setFeback_info(String feback_info) {
		this.feback_info = feback_info;
	}

	public Short getTerminal() {
		return terminal;
	}

	public void setTerminal(Short terminal) {
		this.terminal = terminal;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Long getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(Long add_datetime) {
		this.add_datetime = add_datetime;
	}

	public Integer getEdit_datetime() {
		return edit_datetime;
	}

	public void setEdit_datetime(Integer edit_datetime) {
		this.edit_datetime = edit_datetime;
	}

	public String getEdit_user_name() {
		return edit_user_name;
	}

	public void setEdit_user_name(String edit_user_name) {
		this.edit_user_name = edit_user_name;
	}

	public Integer getEdit_user_id() {
		return edit_user_id;
	}

	public void setEdit_user_id(Integer edit_user_id) {
		this.edit_user_id = edit_user_id;
	}

}
