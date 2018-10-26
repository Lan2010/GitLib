package com.tianzhixing.app.dao.mapper;

import java.util.List;

import com.tianzhixing.app.pojo.AppVersions;

public interface AppVersionsMapper {

	/**
	 * 获取App最新版本信息
	 * @param terminal
	 * @return
	 */
	List<AppVersions> getAppVersions(Short terminal);
	
	/**
	 * 获取Android版本的app的校验码（MD5值，IOS不需要此项）
	 * @return
	 */
	List<String> getCheckCodes();

}
