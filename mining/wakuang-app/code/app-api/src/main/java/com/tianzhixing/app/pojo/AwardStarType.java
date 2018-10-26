package com.tianzhixing.app.pojo;

import java.math.BigDecimal;

public class AwardStarType {
	private Integer type_id;// 积分类型自增ID
	private String type_name;// 星星奖励来源类型名称
	private String type_code;// 类型代码
	private BigDecimal type_value;// 奖励星星值
	private Short isSystem;// 用户积分参数表 0代表为底层 1 代表APP显示的类型 2 代表APP无需显示的类型
	private Short mark;// 状态标识，0是停用，默认是1
	private String remark;// 备注
	private Integer add_user_id;// 创建人ID
	private Integer edit_user_id;// 修改人ID
	private Integer add_datetime;// 创建时间
	private Integer edit_datetime;// 修改时间
	private String add_user_name;// 创建人
	private String edit_user_name;// 修改人
	private Integer status;

	public AwardStarType() {
		super();
	}

	public AwardStarType(String type_name, String type_code) {
		super();
		this.type_name = type_name;
		this.type_code = type_code;
	}

	public Integer getType_id() {
		return type_id;
	}

	public void setType_id(Integer type_id) {
		this.type_id = type_id;
	}

	public String getType_name() {
		return type_name;
	}

	public void setType_name(String type_name) {
		this.type_name = type_name;
	}

	public String getType_code() {
		return type_code;
	}

	public void setType_code(String type_code) {
		this.type_code = type_code;
	}

	public BigDecimal getType_value() {
		return type_value;
	}

	public void setType_value(BigDecimal type_value) {
		this.type_value = type_value;
	}

	public Short getIsSystem() {
		return isSystem;
	}

	public void setIsSystem(Short isSystem) {
		this.isSystem = isSystem;
	}

	public Short getMark() {
		return mark;
	}

	public void setMark(Short mark) {
		this.mark = mark;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getAdd_user_id() {
		return add_user_id;
	}

	public void setAdd_user_id(Integer add_user_id) {
		this.add_user_id = add_user_id;
	}

	public Integer getEdit_user_id() {
		return edit_user_id;
	}

	public void setEdit_user_id(Integer edit_user_id) {
		this.edit_user_id = edit_user_id;
	}

	public Integer getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(Integer add_datetime) {
		this.add_datetime = add_datetime;
	}

	public Integer getEdit_datetime() {
		return edit_datetime;
	}

	public void setEdit_datetime(Integer edit_datetime) {
		this.edit_datetime = edit_datetime;
	}

	public String getAdd_user_name() {
		return add_user_name;
	}

	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}

	public String getEdit_user_name() {
		return edit_user_name;
	}

	public void setEdit_user_name(String edit_user_name) {
		this.edit_user_name = edit_user_name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
