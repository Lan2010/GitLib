package com.tianzhixing.oms.bussiness.backend.web.vo;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserWechatVo implements Serializable {

	private Integer id;

	private Integer userId;

	private String mpOpenId;

	private String nickname;

	private String phoneNum;

	private Integer gender;

	private String province;

	private String city;

	private String country;

	private String headimgUrl;

	private String language;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date followDt;

	private String unionId;
	
	private String miniappOpenId;

	public UserWechatVo() {
	}

	public UserWechatVo(Integer id, Integer userId, String mpOpenId, String nickname, String phoneNum, Integer gender, String province, String city, String country, String headimgUrl, String language, Date followDt, String unionId, String miniappOpenId) {
		super();
		this.id = id;
		this.userId = userId;
		this.mpOpenId = mpOpenId;
		this.nickname = nickname;
		this.phoneNum = phoneNum;
		this.gender = gender;
		this.province = province;
		this.city = city;
		this.country = country;
		this.headimgUrl = headimgUrl;
		this.language = language;
		this.followDt = followDt;
		this.unionId = unionId;
		this.miniappOpenId = miniappOpenId;
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

	public String getMpOpenId() {
		return mpOpenId;
	}

	public void setMpOpenId(String mpOpenId) {
		this.mpOpenId = mpOpenId;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
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

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Date getFollowDt() {
		return followDt;
	}

	public void setFollowDt(Date followDt) {
		this.followDt = followDt;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public String getMiniappOpenId() {
		return miniappOpenId;
	}

	public void setMiniappOpenId(String miniappOpenId) {
		this.miniappOpenId = miniappOpenId;
	}

}
