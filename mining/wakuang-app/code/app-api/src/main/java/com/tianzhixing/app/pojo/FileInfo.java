package com.tianzhixing.app.pojo;

public class FileInfo {
	private Integer user_id;
	private String path;
	private String add_detatime;
	private String type;
	private String name;

	public FileInfo() {
		super();
	}

	public FileInfo(Integer user_id, String path, String type, String name) {
		super();
		this.user_id = user_id;
		this.path = path;
		this.type = type;
		this.name = name;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getAdd_detatime() {
		return add_detatime;
	}

	public void setAdd_detatime(String add_detatime) {
		this.add_detatime = add_detatime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
