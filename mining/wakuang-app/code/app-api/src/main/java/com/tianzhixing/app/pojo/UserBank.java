package com.tianzhixing.app.pojo;

public class UserBank {
	private Integer bank_id;// 账户银行ID,
	private Integer user_id;// 用户ID,
	private String real_name;// 姓名,
	private String order_no;// 绑定单号,
	private String bank_code;// 银行编码,
	private String bank_name;// 银行名称,
	private String bank_card_no;// 银行卡号,
	private String branch;// 支行名称,
	private Short status;// 状态标识 0 初始状态 1 审核中 2 审核通过 3 审核不通过 4 已变更,
	private Short terminal;// 操作终端 1 PC 2 安卓 3 IOS 4 微信,
	private String remark;// 备注,
	private String return_content;// 第三方返回内容,
	private Integer edit_datetime;// 审核时间,
	private Long add_datetime;// 创建时间,
	private String bank_img;//

	public Integer getBank_id() {
		return bank_id;
	}

	public void setBank_id(Integer bank_id) {
		this.bank_id = bank_id;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getReal_name() {
		return real_name;
	}

	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_card_no() {
		return bank_card_no;
	}

	public void setBank_card_no(String bank_card_no) {
		this.bank_card_no = bank_card_no;
	}

	public String getBranch() {
		return branch;
	}

	public void setBranch(String branch) {
		this.branch = branch;
	}

	public Short getStatus() {
		return status;
	}

	public void setStatus(Short status) {
		this.status = status;
	}

	public Short getTerminal() {
		return terminal;
	}

	public void setTerminal(Short terminal) {
		this.terminal = terminal;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReturn_content() {
		return return_content;
	}

	public void setReturn_content(String return_content) {
		this.return_content = return_content;
	}

	public Integer getEdit_datetime() {
		return edit_datetime;
	}

	public void setEdit_datetime(Integer edit_datetime) {
		this.edit_datetime = edit_datetime;
	}

	public Long getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(Long add_datetime) {
		this.add_datetime = add_datetime;
	}

	public String getBank_img() {
		return bank_img;
	}

	public void setBank_img(String bank_img) {
		this.bank_img = bank_img;
	}

}
