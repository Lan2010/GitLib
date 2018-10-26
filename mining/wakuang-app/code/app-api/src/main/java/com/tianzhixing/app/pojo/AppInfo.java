package com.tianzhixing.app.pojo;

public class AppInfo {
	private Integer info_id;//
	private String equipment_id;// 设备ID
	private Short type;// 类型 1 安装 2 活跃
	private String jph_reg_id;// 极光推送ID
	private String versions;// 客户端APP的版本号
	private String phone_info;// 手机信息 手机的系统 机型信息
	private String latitude;// 纬度
	private String longitude;// 经度
	private Short terminal;// 终端 2 安卓 3 IOS
	private String province;// 省份
	private String city;// 城市
	private String area;// 区
	private String place;// 地址详细
	private String chan_key;// APP来源渠道KEY
	private String channel;// APP来源渠道
	private Long add_datetime;// 发生时间

	public Integer getInfo_id() {
		return info_id;
	}

	public void setInfo_id(Integer info_id) {
		this.info_id = info_id;
	}

	public String getEquipment_id() {
		return equipment_id;
	}

	public void setEquipment_id(String equipment_id) {
		this.equipment_id = equipment_id;
	}

	public Short getType() {
		return type;
	}

	public void setType(Short type) {
		this.type = type;
	}

	public String getJph_reg_id() {
		return jph_reg_id;
	}

	public void setJph_reg_id(String jph_reg_id) {
		this.jph_reg_id = jph_reg_id;
	}

	public String getVersions() {
		return versions;
	}

	public void setVersions(String versions) {
		this.versions = versions;
	}

	public String getPhone_info() {
		return phone_info;
	}

	public void setPhone_info(String phone_info) {
		this.phone_info = phone_info;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Short getTerminal() {
		return terminal;
	}

	public void setTerminal(Short terminal) {
		this.terminal = terminal;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getChan_key() {
		return chan_key;
	}

	public void setChan_key(String chan_key) {
		this.chan_key = chan_key;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public Long getAdd_datetime() {
		return add_datetime;
	}

	public void setAdd_datetime(Long add_datetime) {
		this.add_datetime = add_datetime;
	}

}
