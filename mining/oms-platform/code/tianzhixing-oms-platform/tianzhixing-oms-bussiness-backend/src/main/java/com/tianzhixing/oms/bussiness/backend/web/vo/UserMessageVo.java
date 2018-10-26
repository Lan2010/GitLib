package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserMessageVo implements Serializable {

	private Integer id;

	private Integer topicId;

	private Integer userId;

	private Integer tzxUserId;

	private Integer type;

	private String content;

	private Integer redStatus;

	private Integer delStatus;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date dt;

	private String userName;

	private String tzxUserName;

	public UserMessageVo() {
		super();
	}

	public UserMessageVo(Integer id, Integer topicId, Integer userId, Integer tzxUserId, Integer type, String content, Integer redStatus, Integer delStatus, Date dt, String userName, String tzxUserName) {
		super();
		this.id = id;
		this.topicId = topicId;
		this.userId = userId;
		this.tzxUserId = tzxUserId;
		this.type = type;
		this.content = content;
		this.redStatus = redStatus;
		this.delStatus = delStatus;
		this.dt = dt;
		this.userName = userName;
		this.tzxUserName = tzxUserName;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getTopicId() {
		return topicId;
	}

	public void setTopicId(Integer topicId) {
		this.topicId = topicId;
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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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

	public Date getDt() {
		return dt;
	}

	public void setDt(Date dt) {
		this.dt = dt;
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
