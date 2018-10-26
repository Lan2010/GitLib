package com.tianzhixing.app.pojo;

public class Task {

	private Integer task_id;// 任务ID
	private Integer userId;// 任务ID
	private String starPoint;
	private String order_no; // 单号
	private Double task_radius;// 任务范围
	private String lat; // 纬度
	private String lng; // 经度
	private Integer task_level;// 任务级别 0 普通 1紧急
	private String task_award;// 任务的期望值
	private String task_remark;// 任务简介
	private String task_name; // 任务名字
	private String task_lcon;// 标志的图片
	private String city;// 城市
	private String city_code;// 城市代码
	private String begin_time;
	private String end_time;
	private String keyword;// 关键字
	private String status;

	private Integer tl_id;
	private String name;
	private String address;
	private String distance;

	public Task() {
		super();
	}

	public Task(String lat, String lng) {
		super();
		this.lat = lat;
		this.lng = lng;
	}

	public Integer getTask_id() {
		return task_id;
	}

	public void setTask_id(Integer task_id) {
		this.task_id = task_id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getStarPoint() {
		return starPoint;
	}

	public void setStarPoint(String starPoint) {
		this.starPoint = starPoint;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public Double getTask_radius() {
		return task_radius;
	}

	public void setTask_radius(Double task_radius) {
		this.task_radius = task_radius;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public Integer getTask_level() {
		return task_level;
	}

	public void setTask_level(Integer task_level) {
		this.task_level = task_level;
	}

	public String getTask_award() {
		return task_award;
	}

	public void setTask_award(String task_award) {
		this.task_award = task_award;
	}

	public String getTask_remark() {
		return task_remark;
	}

	public void setTask_remark(String task_remark) {
		this.task_remark = task_remark;
	}

	public String getTask_name() {
		return task_name;
	}

	public void setTask_name(String task_name) {
		this.task_name = task_name;
	}

	public String getTask_lcon() {
		return task_lcon;
	}

	public void setTask_lcon(String task_lcon) {
		this.task_lcon = task_lcon;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCity_code() {
		return city_code;
	}

	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}

	public String getBegin_time() {
		return begin_time;
	}

	public void setBegin_time(String begin_time) {
		this.begin_time = begin_time;
	}

	public String getEnd_time() {
		return end_time;
	}

	public void setEnd_time(String end_time) {
		this.end_time = end_time;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getTl_id() {
		return tl_id;
	}

	public void setTl_id(Integer tl_id) {
		this.tl_id = tl_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDistance() {
		return distance;
	}

	public void setDistance(String distance) {
		this.distance = distance;
	}

}
