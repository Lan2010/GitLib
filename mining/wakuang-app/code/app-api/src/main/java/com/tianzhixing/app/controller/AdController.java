package com.tianzhixing.app.controller;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.tianzhixing.app.common.Constant;
import com.tianzhixing.app.common.ReturnCode;
import com.tianzhixing.app.pojo.BaseRequest;
import com.tianzhixing.app.pojo.HomeBanner;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.AdvertisementLogic;
import com.tianzhixing.app.util.EncryptUtil;

/**
 * 广告相关接口
 * 
 * @date 2018年7月31日
 */
@Controller
public class AdController {
	@Resource
	private AdvertisementLogic advertisementLogic;

	/**
	 * 启动app界面的广告列表
	 * @param type 广告类型
	 * @throws SQLException 
	 * @throws NumberFormatException 
	 * @return 
	 */
	@RequestMapping(value = "/ad/startUpAd", produces = "application/json; charset=utf-8")
	@ResponseBody
	public String startAdList(HttpServletRequest request, HttpServletResponse response, BaseRequest baseRequest) throws NumberFormatException, SQLException {
		ResultMessage rm;
		String dicType = baseRequest.getParameter("type"); // 广告类型
		Map<String, Object> info = new HashMap<String, Object>();
		List<HomeBanner> banners = advertisementLogic.getRepeatRollImgs((dicType==null||dicType.isEmpty())?null:Integer.valueOf(dicType));
		if (banners == null || banners.size() == 0) {
			rm = new ResultMessage(1, "没有启动图");
		} else {
			info.put("banner_url", Constant.UPLOAD + banners.get(0).getBannerUrl());
			info.put("version", EncryptUtil.encodeByMD5(Constant.UPLOAD + banners.get(0).getBannerUrl()));
			info.put("link_url", banners.get(0).getLinkUrl());
			info.put("banner_title", banners.get(0).getBannerTitle());
			rm = new ResultMessage(ReturnCode.OK, "请求成功");
			rm.put("info", info);
		}
		return rm.toString();
	}
}
