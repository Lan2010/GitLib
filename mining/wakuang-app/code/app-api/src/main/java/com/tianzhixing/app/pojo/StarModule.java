package com.tianzhixing.app.pojo;

/**
 * 用户与星星奖励模块的关联信息
 * @author dev-teng
 * @date 2018年8月13日
 */
public class StarModule {
	private Integer smId;//自增ID
	private Integer userId;//用户ID
	private String moduleCode;//0 未启动 1认证过
	private Integer status;//1 是有效 0 是无效
	private Integer addDatetime;//添加时间
	public Integer getSmId() {
		return smId;
	}
	public void setSmId(Integer smId) {
		this.smId = smId;
	}
	public Integer getUserId() {
		return userId;
	}
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	public String getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(String moduleCode) {
		this.moduleCode = moduleCode;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Integer getAddDatetime() {
		return addDatetime;
	}
	public void setAddDatetime(Integer addDatetime) {
		this.addDatetime = addDatetime;
	}
	
}
