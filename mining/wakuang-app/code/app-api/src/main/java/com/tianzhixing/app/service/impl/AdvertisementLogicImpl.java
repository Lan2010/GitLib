package com.tianzhixing.app.service.impl;

import java.sql.SQLException;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.tianzhixing.app.dao.mapper.AdvertisementMapper;
import com.tianzhixing.app.dao.mapper.HomeBannerMapper;
import com.tianzhixing.app.pojo.AdvertisementModel;
import com.tianzhixing.app.pojo.HomeBanner;
import com.tianzhixing.app.service.AdvertisementLogic;

@Service
public class AdvertisementLogicImpl implements AdvertisementLogic {

	@Resource
	private AdvertisementMapper advertisementMapper;
	@Resource
	private HomeBannerMapper homeBannerMapper;

	@Override
	public void setAdvertisement(AdvertisementModel data) throws SQLException {
		advertisementMapper.insert(data);
	}

	@Override
	public AdvertisementModel getAd(String order_no) throws SQLException {
		return advertisementMapper.getAd(order_no);
	}

	@Override
	public Integer getTotalPage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(AdvertisementModel data) throws SQLException {
		advertisementMapper.update(data);
	}

	@Override
	public void setDec(String advertId) throws SQLException {
		advertisementMapper.setDec(advertId);
	}

	@Override
	public void addNum(String advertId, String onceStarPoint) throws SQLException {
		AdvertisementModel model = new AdvertisementModel();
		model.setOrder_no(advertId);
		model.setOnceStarPoint(onceStarPoint);
		advertisementMapper.addNum(model);
	}

	@Override
	public List<AdvertisementModel> getEffectiveAd(String cityCode) throws SQLException {
		AdvertisementModel model = new AdvertisementModel();
		model.setCityCode(cityCode);
		model.setAdvertisement_attribute("ADVERTISER");
		return advertisementMapper.getAds(model);
	}

	@Override
	public AdvertisementModel getAd(AdvertisementModel data) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<AdvertisementModel> getDefault(String cityCode) throws SQLException {
		AdvertisementModel model = new AdvertisementModel();
		model.setCityCode(cityCode);
		model.setAdvertisement_attribute("DEFAULT");
		return advertisementMapper.getAds(model);
	}

	@Override
	public AdvertisementModel getRandAd(List<AdvertisementModel> ad_data) {
		if (ad_data.isEmpty()) {
			return null;
		}
		if (ad_data.size() <= 1) {
			return ad_data.get(0);
		}
		int rand_num = (int) (Math.random() * (ad_data.size()));
		return ad_data.get(rand_num);
	}

	@Override
	public List<AdvertisementModel> getAds(AdvertisementModel model) throws SQLException {
		return advertisementMapper.getAds(model);
	}

	@Override
	public List<HomeBanner> getRepeatRollImgs(Integer dicType) throws SQLException {
		if(dicType==null) {
			return null;
		}
		return homeBannerMapper.getAll(dicType);
	}

	
	
}
