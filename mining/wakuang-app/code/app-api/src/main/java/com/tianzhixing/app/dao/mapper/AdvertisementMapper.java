package com.tianzhixing.app.dao.mapper;

import java.sql.SQLException;
import java.util.List;

import com.tianzhixing.app.pojo.AdvertisementModel;

public interface AdvertisementMapper {

	List<AdvertisementModel> getAds(AdvertisementModel model) throws SQLException;

	AdvertisementModel getAd(String order_no) throws SQLException;


	void setDec(String advertId) throws SQLException;


	void addNum(AdvertisementModel model) throws SQLException;

	void insert(AdvertisementModel data)throws SQLException;

	void update(AdvertisementModel data)throws SQLException;


}
