package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserTopicVo implements Serializable {

	private Integer id;

	private Integer userId;

	private Integer tzxUserId;

	private Integer type;

	private String title;

	private String content;

	private String imgUrl;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date dt;

	private Integer redStatus;

	private Integer delStatus;

	private Integer repStatus;
	
	private String userName;
	
	private String tzxUserName;

	public UserTopicVo() {
		super();
	}

	public UserTopicVo(Integer id, Integer userId, Integer tzxUserId, Integer type, String title, String content, String imgUrl, Date dt, Integer redStatus, Integer delStatus, Integer repStatus, String userName, String tzxUserName) {
		super();
		this.id = id;
		this.userId = userId;
		this.tzxUserId = tzxUserId;
		this.type = type;
		this.title = title;
		this.content = content;
		this.imgUrl = imgUrl;
		this.dt = dt;
		this.redStatus = redStatus;
		this.delStatus = delStatus;
		this.repStatus = repStatus;
		this.userName = userName;
		this.tzxUserName = tzxUserName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getTzxUserId() {
		return tzxUserId;
	}

	public void setTzxUserId(Integer tzxUserId) {
		this.tzxUserId = tzxUserId;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getImgUrl() {
		return imgUrl;
	}

	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
	}

	public Integer getRedStatus() {
		return redStatus;
	}

	public void setRedStatus(Integer redStatus) {
		this.redStatus = redStatus;
	}

	public Integer getDelStatus() {
		return delStatus;
	}

	public void setDelStatus(Integer delStatus) {
		this.delStatus = delStatus;
	}

	public Integer getRepStatus() {
		return repStatus;
	}

	public void setRepStatus(Integer repStatus) {
		this.repStatus = repStatus;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTzxUserName() {
		return tzxUserName;
	}

	public void setTzxUserName(String tzxUserName) {
		this.tzxUserName = tzxUserName;
	}

}
