package com.tianzhixing.app.pojo;

public class UserRealName {
	 private Integer real_id;//,
	 private Integer user_id;//用户ID',
	 private String order_no ;//单据编号',
	 private String real_name;//姓名',
	 private Short card_type;//证件类型 1 二代身份证',
	 private String card_id;//身份证号码',
	 private Short audit_status;//审核状态 0 申请中 1 审核通过 2 审核不通过',
	 private Short user_age;//年龄',
	 private Short user_sex;//性别 0 未知 1 男 2 女',
	 private Integer birth_year;//生日年份',
	 private String birth_day;//生日',
	 private String card_address;//证件地址',
	 private Integer audit_user_id;//审核人ID',
	 private String audit_user_name;//
	 private Integer audit_datetime;//审核时间',
	 private String audit_remark;//审核备注',
	 private Long add_datetime;//创建时间',
	 private Short terminal;//操作终端 1 PC 2 安卓 3 IOS 4 微信',
	 private String operate_ip;//操作IP',
	public Integer getReal_id() {
		return real_id;
	}
	public void setReal_id(Integer real_id) {
		this.real_id = real_id;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public String getOrder_no() {
		return order_no;
	}
	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}
	public String getReal_name() {
		return real_name;
	}
	public void setReal_name(String real_name) {
		this.real_name = real_name;
	}
	public Short getCard_type() {
		return card_type;
	}
	public void setCard_type(Short card_type) {
		this.card_type = card_type;
	}
	public String getCard_id() {
		return card_id;
	}
	public void setCard_id(String card_id) {
		this.card_id = card_id;
	}
	public Short getAudit_status() {
		return audit_status;
	}
	public void setAudit_status(Short audit_status) {
		this.audit_status = audit_status;
	}
	public Short getUser_age() {
		return user_age;
	}
	public void setUser_age(Short user_age) {
		this.user_age = user_age;
	}
	public Short getUser_sex() {
		return user_sex;
	}
	public void setUser_sex(Short user_sex) {
		this.user_sex = user_sex;
	}
	public Integer getBirth_year() {
		return birth_year;
	}
	public void setBirth_year(Integer birth_year) {
		this.birth_year = birth_year;
	}
	public String getBirth_day() {
		return birth_day;
	}
	public void setBirth_day(String birth_day) {
		this.birth_day = birth_day;
	}
	public String getCard_address() {
		return card_address;
	}
	public void setCard_address(String card_address) {
		this.card_address = card_address;
	}
	public Integer getAudit_user_id() {
		return audit_user_id;
	}
	public void setAudit_user_id(Integer audit_user_id) {
		this.audit_user_id = audit_user_id;
	}
	public String getAudit_user_name() {
		return audit_user_name;
	}
	public void setAudit_user_name(String audit_user_name) {
		this.audit_user_name = audit_user_name;
	}
	public Integer getAudit_datetime() {
		return audit_datetime;
	}
	public void setAudit_datetime(Integer audit_datetime) {
		this.audit_datetime = audit_datetime;
	}
	public String getAudit_remark() {
		return audit_remark;
	}
	public void setAudit_remark(String audit_remark) {
		this.audit_remark = audit_remark;
	}
	public Long getAdd_datetime() {
		return add_datetime;
	}
	public void setAdd_datetime(Long add_datetime) {
		this.add_datetime = add_datetime;
	}
	public Short getTerminal() {
		return terminal;
	}
	public void setTerminal(Short terminal) {
		this.terminal = terminal;
	}
	public String getOperate_ip() {
		return operate_ip;
	}
	public void setOperate_ip(String operate_ip) {
		this.operate_ip = operate_ip;
	}
	 
}
