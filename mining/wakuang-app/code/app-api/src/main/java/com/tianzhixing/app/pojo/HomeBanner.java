package com.tianzhixing.app.pojo;

/**
 * 轮滚广告图片
 * @author dev-teng
 * @date 2018年8月17日
 */
public class HomeBanner {
	private Integer bannerId;// 轮滚图ID
	private String bannerName;// 轮滚图名字
	private Integer dicType;// 类型 字典值
	private String dicName;// 类型名称
	private String bannerUrl;// 图片路径
	private String linkUrl;// 链接URL
	private String bannerTitle;// title
	private Short inLink;// 是否内链 0 非内链 1 内链
	private Integer bannerSort;// 排序
	private Short bannerStatus;// 轮滚图状态 0 删除 1 启用
	private Integer bannerStarttime;// 0 不限制开始时间 
	private Integer bannerEndTime;// 0 不限制结束时间 
	private String remark;// 备注
	private Integer addUserId;// 创建人ID
	private String addUserName;// 创建人
	private Integer addDatetime;// 创建时间
	private Integer editUserId;// 修改人ID
	private String editUserName;// 修改人
	private Integer editDatetime;// 修改时间
	private String operateIp;// 操作IP
	public Integer getBannerId() {
		return bannerId;
	}
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	public String getBannerName() {
		return bannerName;
	}
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	public Integer getDicType() {
		return dicType;
	}
	public void setDicType(Integer dicType) {
		this.dicType = dicType;
	}
	public String getDicName() {
		return dicName;
	}
	public void setDicName(String dicName) {
		this.dicName = dicName;
	}
	public String getBannerUrl() {
		return bannerUrl;
	}
	public void setBannerUrl(String bannerUrl) {
		this.bannerUrl = bannerUrl;
	}
	public String getLinkUrl() {
		return linkUrl;
	}
	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}
	public String getBannerTitle() {
		return bannerTitle;
	}
	public void setBannerTitle(String bannerTitle) {
		this.bannerTitle = bannerTitle;
	}
	public Short getInLink() {
		return inLink;
	}
	public void setInLink(Short inLink) {
		this.inLink = inLink;
	}
	public Integer getBannerSort() {
		return bannerSort;
	}
	public void setBannerSort(Integer bannerSort) {
		this.bannerSort = bannerSort;
	}
	public Short getBannerStatus() {
		return bannerStatus;
	}
	public void setBannerStatus(Short bannerStatus) {
		this.bannerStatus = bannerStatus;
	}
	public Integer getBannerStarttime() {
		return bannerStarttime;
	}
	public void setBannerStarttime(Integer bannerStarttime) {
		this.bannerStarttime = bannerStarttime;
	}
	public Integer getBannerEndTime() {
		return bannerEndTime;
	}
	public void setBannerEndTime(Integer bannerEndTime) {
		this.bannerEndTime = bannerEndTime;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getAddUserId() {
		return addUserId;
	}
	public void setAddUserId(Integer addUserId) {
		this.addUserId = addUserId;
	}
	public String getAddUserName() {
		return addUserName;
	}
	public void setAddUserName(String addUserName) {
		this.addUserName = addUserName;
	}
	public Integer getAddDatetime() {
		return addDatetime;
	}
	public void setAddDatetime(Integer addDatetime) {
		this.addDatetime = addDatetime;
	}
	public Integer getEditUserId() {
		return editUserId;
	}
	public void setEditUserId(Integer editUserId) {
		this.editUserId = editUserId;
	}
	public String getEditUserName() {
		return editUserName;
	}
	public void setEditUserName(String editUserName) {
		this.editUserName = editUserName;
	}
	public Integer getEditDatetime() {
		return editDatetime;
	}
	public void setEditDatetime(Integer editDatetime) {
		this.editDatetime = editDatetime;
	}
	public String getOperateIp() {
		return operateIp;
	}
	public void setOperateIp(String operateIp) {
		this.operateIp = operateIp;
	}
	
}
