package com.tianzhixing.app.service;

import java.sql.SQLException;
import java.util.List;

import com.tianzhixing.app.pojo.AdvertisementModel;
import com.tianzhixing.app.pojo.HomeBanner;

public interface AdvertisementLogic {
	/**
	 * 生成或者插入
	 * @throws SQLException 
	 */
	void setAdvertisement(AdvertisementModel data) throws SQLException;
	
	AdvertisementModel getAd(String order_no) throws SQLException ;
	
	/**
	 * 获取列表
	 * @param cityCode
	 * @return
	 * @throws SQLException 
	 */
	List<AdvertisementModel> getAds(AdvertisementModel model) throws SQLException;
	
	
	Integer getTotalPage();
	
	void update(AdvertisementModel data) throws SQLException;
	
	
	void setDec(String advertId)throws SQLException;

	
	void addNum(String advertId, String onceStarPoint) throws SQLException;
	
	/**
	 * 获取到有效的信息
	 * @param cityCode
	 * @return
	 * @throws SQLException 
	 */
	List<AdvertisementModel> getEffectiveAd(String cityCode) throws SQLException;
	
	
	/**
	 * 获取到随机
	 * @param data
	 * @return
	 */
	AdvertisementModel getAd(AdvertisementModel data) ;
	
	/**
	 * 获取到默认的图片
	 * @param cityCode
	 * @return
	 * @throws SQLException 
	 */
	List<AdvertisementModel> getDefault(String cityCode) throws SQLException;

	/**
	 * 获取到随机
	 * @param ad_data
	 * @return
	 * @throws SQLException
	 */
	AdvertisementModel getRandAd(List<AdvertisementModel> ad_data);

	/**
	 * 获取轮播图
	 * @return
	 * @throws SQLException
	 */
	List<HomeBanner> getRepeatRollImgs(Integer dicType) throws SQLException;

}
