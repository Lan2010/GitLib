package com.tianzhixing.app.pojo;

public class StarRecord {
	private String type;
	private String desc;
	private String value;
	private Integer status;

	public StarRecord() {
		super();
	}

	public StarRecord(String type, String desc, String value, Integer status) {
		super();
		this.type = type;
		this.desc = desc;
		this.value = value;
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

}
