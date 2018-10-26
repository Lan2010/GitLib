package com.tianzhixing.app.pojo;

import com.alibaba.fastjson.annotation.JSONField;

public class AdvertisementModel {
	private Integer ad_id;
	private String advertName;// 广告名字
	private String area;// 广告宣传语
	private String city;//
	private String cityCode;//
	private String advertisementType;// 广告类型 ALert 弹出 view消失
	private String totalCount;// 点击总数
	private String advertPic;// 广告图片
	private String advertIcon;// 广告图标
	private String totalStarPoint;// 广告的总点击次数
	private String advertRemark;// 备注
	private String onceStarPoint;//
	private String beginTime;// 添加时间
	private String order_no;// 单号
	private String endTime;//
	private String ad_url;// 广告链接
	private String advertisement_attribute;// 类型
	private String advertisement_describe;// 描述
	private String totalClickStarPoint;// 总的点击星星数
	private String onceClickStarPoint;// 点击一次的星星数量
	private Integer total_count_view;// 总点击数用于展示和记录
	private String user_browse_starpoint;// 用户浏览的获取到的总星数
	private String user_click_starpoint;// 用户点击的获取到的星数
	private String Lat;//
	private String Lng;//

	public Integer getAd_id() {
		return ad_id;
	}

	public void setAd_id(Integer ad_id) {
		this.ad_id = ad_id;
	}

	@JSONField(name = "Lat")
	public String getLat() {
		return Lat;
	}

	public void setLat(String lat) {
		Lat = lat;
	}

	@JSONField(name = "Lng")
	public String getLng() {
		return Lng;
	}

	public void setLng(String lng) {
		Lng = lng;
	}

	public String getAdvertName() {
		return advertName;
	}

	public void setAdvertName(String advertName) {
		this.advertName = advertName;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public String getAdvertisementType() {
		return advertisementType;
	}

	public void setAdvertisementType(String advertisementType) {
		this.advertisementType = advertisementType;
	}

	public String getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(String totalCount) {
		this.totalCount = totalCount;
	}

	public String getAdvertPic() {
		return advertPic;
	}

	public void setAdvertPic(String advertPic) {
		this.advertPic = advertPic;
	}

	public String getAdvertIcon() {
		return advertIcon;
	}

	public void setAdvertIcon(String advertIcon) {
		this.advertIcon = advertIcon;
	}

	public String getTotalStarPoint() {
		return totalStarPoint;
	}

	public void setTotalStarPoint(String totalStarPoint) {
		this.totalStarPoint = totalStarPoint;
	}

	public String getAdvertRemark() {
		return advertRemark;
	}

	public void setAdvertRemark(String advertRemark) {
		this.advertRemark = advertRemark;
	}

	public String getOnceStarPoint() {
		return onceStarPoint;
	}

	public void setOnceStarPoint(String onceStarPoint) {
		this.onceStarPoint = onceStarPoint;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getOrder_no() {
		return order_no;
	}

	public void setOrder_no(String order_no) {
		this.order_no = order_no;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getAd_url() {
		return ad_url;
	}

	public void setAd_url(String ad_url) {
		this.ad_url = ad_url;
	}

	public String getAdvertisement_attribute() {
		return advertisement_attribute;
	}

	public void setAdvertisement_attribute(String advertisement_attribute) {
		this.advertisement_attribute = advertisement_attribute;
	}

	public String getAdvertisement_describe() {
		return advertisement_describe;
	}

	public void setAdvertisement_describe(String advertisement_describe) {
		this.advertisement_describe = advertisement_describe;
	}

	public String getTotalClickStarPoint() {
		return totalClickStarPoint;
	}

	public void setTotalClickStarPoint(String totalClickStarPoint) {
		this.totalClickStarPoint = totalClickStarPoint;
	}

	public String getOnceClickStarPoint() {
		return onceClickStarPoint;
	}

	public void setOnceClickStarPoint(String onceClickStarPoint) {
		this.onceClickStarPoint = onceClickStarPoint;
	}

	public Integer getTotal_count_view() {
		return total_count_view;
	}

	public void setTotal_count_view(Integer total_count_view) {
		this.total_count_view = total_count_view;
	}

	public String getUser_browse_starpoint() {
		return user_browse_starpoint;
	}

	public void setUser_browse_starpoint(String user_browse_starpoint) {
		this.user_browse_starpoint = user_browse_starpoint;
	}

	public String getUser_click_starpoint() {
		return user_click_starpoint;
	}

	public void setUser_click_starpoint(String user_click_starpoint) {
		this.user_click_starpoint = user_click_starpoint;
	}

}
