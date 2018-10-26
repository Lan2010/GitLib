package com.tianzhixing.app.pojo;

public class Ranking {
	private String num;
	private String phone;
	private String starPoint;

	
	public Ranking() {
		super();
	}

	public Ranking(String num, String phone, String starPoint) {
		super();
		this.num = num;
		this.phone = phone;
		this.starPoint = starPoint;
	}

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getStarPoint() {
		return starPoint;
	}

	public void setStarPoint(String starPoint) {
		this.starPoint = starPoint;
	}

}
