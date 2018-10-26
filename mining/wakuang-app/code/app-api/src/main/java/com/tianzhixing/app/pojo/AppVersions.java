package com.tianzhixing.app.pojo;

public class AppVersions {
	private Integer ver_id;// 主键,
	private String version_name;// 版本名称,
	private String version_code;// 版本号,
	private Short terminal;// 终端类型（ 2 安卓 3 IOS）,
	private String check_code;// 校验码（MD5值 IOS不需要此项）,
	private Float app_size;// APP大小,
	private String update_desc;// 更新说明,
	private String update_url;// 更新地址（用于安卓）,
	private Short is_forced;// 是否强制更新（0：不强制更新 1：强制更新）,
	private Short status;// 状态（0：删除 1：有效 2：过往版本）,
	private Integer add_datetime;// 添加时间,
	private Integer add_user_id;// 添加用户ID,
	private String add_user_name;// 添加用户名,
	private Integer edit_datetime;// 编辑时间,
	private Integer edit_user_id;// 编辑用户ID,
	private String edit_user_name;// 编辑用户名,
	public Integer getVer_id() {
		return ver_id;
	}
	public void setVer_id(Integer ver_id) {
		this.ver_id = ver_id;
	}
	public String getVersion_name() {
		return version_name;
	}
	public void setVersion_name(String version_name) {
		this.version_name = version_name;
	}
	public String getVersion_code() {
		return version_code;
	}
	public void setVersion_code(String version_code) {
		this.version_code = version_code;
	}
	public Short getTerminal() {
		return terminal;
	}
	public void setTerminal(Short terminal) {
		this.terminal = terminal;
	}
	public String getCheck_code() {
		return check_code;
	}
	public void setCheck_code(String check_code) {
		this.check_code = check_code;
	}
	public Float getApp_size() {
		return app_size;
	}
	public void setApp_size(Float app_size) {
		this.app_size = app_size;
	}
	public String getUpdate_desc() {
		return update_desc;
	}
	public void setUpdate_desc(String update_desc) {
		this.update_desc = update_desc;
	}
	public String getUpdate_url() {
		return update_url;
	}
	public void setUpdate_url(String update_url) {
		this.update_url = update_url;
	}
	public Short getIs_forced() {
		return is_forced;
	}
	public void setIs_forced(Short is_forced) {
		this.is_forced = is_forced;
	}
	public Short getStatus() {
		return status;
	}
	public void setStatus(Short status) {
		this.status = status;
	}
	public Integer getAdd_datetime() {
		return add_datetime;
	}
	public void setAdd_datetime(Integer add_datetime) {
		this.add_datetime = add_datetime;
	}
	public Integer getAdd_user_id() {
		return add_user_id;
	}
	public void setAdd_user_id(Integer add_user_id) {
		this.add_user_id = add_user_id;
	}
	public String getAdd_user_name() {
		return add_user_name;
	}
	public void setAdd_user_name(String add_user_name) {
		this.add_user_name = add_user_name;
	}
	public Integer getEdit_datetime() {
		return edit_datetime;
	}
	public void setEdit_datetime(Integer edit_datetime) {
		this.edit_datetime = edit_datetime;
	}
	public Integer getEdit_user_id() {
		return edit_user_id;
	}
	public void setEdit_user_id(Integer edit_user_id) {
		this.edit_user_id = edit_user_id;
	}
	public String getEdit_user_name() {
		return edit_user_name;
	}
	public void setEdit_user_name(String edit_user_name) {
		this.edit_user_name = edit_user_name;
	}
	
	
}
