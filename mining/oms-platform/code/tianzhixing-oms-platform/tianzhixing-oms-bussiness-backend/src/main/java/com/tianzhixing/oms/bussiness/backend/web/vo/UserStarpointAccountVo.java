package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserStarpointAccountVo implements Serializable {

	private Integer id;

	private String wkappMobile;

	private String wkappAccountToken;

	private Integer userId;

	private Integer enable;

	private String wkappOrg;

	private String selfAccountToken;

	private String selfAccountOrg;

	public UserStarpointAccountVo() {
	}

	public UserStarpointAccountVo(Integer id, String wkappMobile, String wkappAccountToken, Integer userId, Integer enable, String wkappOrg, String selfAccountToken, String selfAccountOrg) {
		super();
		this.id = id;
		this.wkappMobile = wkappMobile;
		this.wkappAccountToken = wkappAccountToken;
		this.userId = userId;
		this.enable = enable;
		this.wkappOrg = wkappOrg;
		this.selfAccountToken = selfAccountToken;
		this.selfAccountOrg = selfAccountOrg;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getWkappMobile() {
		return wkappMobile;
	}

	public void setWkappMobile(String wkappMobile) {
		this.wkappMobile = wkappMobile;
	}

	public String getWkappAccountToken() {
		return wkappAccountToken;
	}

	public void setWkappAccountToken(String wkappAccountToken) {
		this.wkappAccountToken = wkappAccountToken;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getEnable() {
		return enable;
	}

	public void setEnable(Integer enable) {
		this.enable = enable;
	}

	public String getWkappOrg() {
		return wkappOrg;
	}

	public void setWkappOrg(String wkappOrg) {
		this.wkappOrg = wkappOrg;
	}

	public String getSelfAccountToken() {
		return selfAccountToken;
	}

	public void setSelfAccountToken(String selfAccountToken) {
		this.selfAccountToken = selfAccountToken;
	}

	public String getSelfAccountOrg() {
		return selfAccountOrg;
	}

	public void setSelfAccountOrg(String selfAccountOrg) {
		this.selfAccountOrg = selfAccountOrg;
	}

}
