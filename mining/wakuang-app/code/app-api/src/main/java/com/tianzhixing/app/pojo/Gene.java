package com.tianzhixing.app.pojo;

public class Gene {
	private Integer gene_id;//
	private String name;// 名字
	private Integer birth_date;// 生日
	private String native_place;// 出生地
	private Integer exercise_time;// 运动时间 以秒为单位
	private Long add_datetime;// 添加时间
	private Integer user_id;// 用户的ID
	private Integer sex;// 性别 0 女 1男
	private String ill;// 遗传病
	private Double height;// 身高 单位米
	private Double weight;// 体重 单位千克
	
	public Integer getGene_id() {
		return gene_id;
	}
	public void setGene_id(Integer gene_id) {
		this.gene_id = gene_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getBirth_date() {
		return birth_date;
	}
	public void setBirth_date(Integer birth_date) {
		this.birth_date = birth_date;
	}
	public String getNative_place() {
		return native_place;
	}
	public void setNative_place(String native_place) {
		this.native_place = native_place;
	}
	public Integer getExercise_time() {
		return exercise_time;
	}
	public void setExercise_time(Integer exercise_time) {
		this.exercise_time = exercise_time;
	}
	public Long getAdd_datetime() {
		return add_datetime;
	}
	public void setAdd_datetime(Long add_datetime) {
		this.add_datetime = add_datetime;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public String getIll() {
		return ill;
	}
	public void setIll(String ill) {
		this.ill = ill;
	}
	public Double getHeight() {
		return height;
	}
	public void setHeight(Double height) {
		this.height = height;
	}
	public Double getWeight() {
		return weight;
	}
	public void setWeight(Double weight) {
		this.weight = weight;
	}
	
}
