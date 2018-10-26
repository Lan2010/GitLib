package com.tianzhixing.oms.bussiness.backend.web.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * Created by routine.k on 2018/6/22.
 */
public class UserVo implements Serializable {

	private Integer id;

	private String phoneNum;

	private String passwd;

	private String email;

	private Byte gender;

	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	private Date birthday;

	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm", timezone = "GMT+8")
	private Date createdt;

	private String mpOpenId;

	private String nickname;

	private String headimgUrl;

	private String unionId;

	private BigDecimal bonus;

	private String regPlatform;

	private String userChannel;

	private String miniappOpenId;

	private String youzanUserId;

	private String youzanFansId;

	public UserVo() {
		super();
	}

	public UserVo(Integer id, String phoneNum, String passwd, String email, Byte gender, Date birthday, Date createdt, String mpOpenId, String nickname, String headimgUrl, String unionId, BigDecimal bonus, String regPlatform, String userChannel, String miniappOpenId, String youzanUserId, String youzanFansId) {
		super();
		this.id = id;
		this.phoneNum = phoneNum;
		this.passwd = passwd;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.createdt = createdt;
		this.mpOpenId = mpOpenId;
		this.nickname = nickname;
		this.headimgUrl = headimgUrl;
		this.unionId = unionId;
		this.bonus = bonus;
		this.regPlatform = regPlatform;
		this.userChannel = userChannel;
		this.miniappOpenId = miniappOpenId;
		this.youzanUserId = youzanUserId;
		this.youzanFansId = youzanFansId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Byte getGender() {
		return gender;
	}

	public void setGender(Byte gender) {
		this.gender = gender;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreatedt() {
		return createdt;
	}

	public void setCreatedt(Date createdt) {
		this.createdt = createdt;
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

	public String getHeadimgUrl() {
		return headimgUrl;
	}

	public void setHeadimgUrl(String headimgUrl) {
		this.headimgUrl = headimgUrl;
	}

	public String getUnionId() {
		return unionId;
	}

	public void setUnionId(String unionId) {
		this.unionId = unionId;
	}

	public BigDecimal getBonus() {
		return bonus;
	}

	public void setBonus(BigDecimal bonus) {
		this.bonus = bonus;
	}

	public String getRegPlatform() {
		return regPlatform;
	}

	public void setRegPlatform(String regPlatform) {
		this.regPlatform = regPlatform;
	}

	public String getUserChannel() {
		return userChannel;
	}

	public void setUserChannel(String userChannel) {
		this.userChannel = userChannel;
	}

	public String getMiniappOpenId() {
		return miniappOpenId;
	}

	public void setMiniappOpenId(String miniappOpenId) {
		this.miniappOpenId = miniappOpenId;
	}

	public String getYouzanUserId() {
		return youzanUserId;
	}

	public void setYouzanUserId(String youzanUserId) {
		this.youzanUserId = youzanUserId;
	}

	public String getYouzanFansId() {
		return youzanFansId;
	}

	public void setYouzanFansId(String youzanFansId) {
		this.youzanFansId = youzanFansId;
	}

}
