package com.tianzhixing.app.dao.mapper;

import java.util.List;

import com.tianzhixing.app.pojo.HomeBanner;

public interface HomeBannerMapper {
	/**
	 * 获取所有轮滚图信息
	 * @param dicType 
	 * @return
	 */
	List<HomeBanner> getAll(Integer dicType);
}
