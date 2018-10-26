package com.tianzhixing.app.controller;

import java.sql.SQLException;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.tianzhixing.app.exception.BadRequestException;
import com.tianzhixing.app.pojo.AdvertisementModel;
import com.tianzhixing.app.pojo.ResultMessage;
import com.tianzhixing.app.service.AdvertisementLogic;

@Controller
@RequestMapping("/MobileApi/index")
public class IndexController {
	@Resource
	private AdvertisementLogic advertisementLogic;
	
	/**
	 * 
	 * @Description:被动接受广告 --接受广告
	 *  @param request
	 *  @param response
	 *  @param model
	 *  @param id
	 *  @param advertisementDescribe
	 *  @param link
	 *  @param advertisementAttribute   
	 * @return void
	 * @throws SQLException 
	 * @throws BadRequestException 
	 */
	@RequestMapping(value="/setAdvertisement", produces = "application/json; charset=utf-8", method = RequestMethod.POST)
	public void setAd(HttpServletRequest request, HttpServletResponse response,AdvertisementModel model,String id,String advertisementDescribe,String link,String advertisementAttribute) throws SQLException, BadRequestException {
		if(StringUtils.isEmpty(id)) {
			throw new BadRequestException(new ResultMessage(4010, "参数传递错误").toString());
		}
		System.out.println("-------setAd-----");
		model.setOrder_no(id);
		model.setAdvertisement_describe(advertisementDescribe);
		model.setAd_url(link);
		model.setAdvertisement_attribute(advertisementAttribute);
		if(!StringUtils.isEmpty(model.getTotalCount())) {
			model.setTotal_count_view(Integer.valueOf(model.getTotalCount()));
		}
		AdvertisementModel res= advertisementLogic.getAd(id);
		if (res != null) {
			// 更新广告
			advertisementLogic.update(model);
		}else {
			model.setUser_browse_starpoint("0.0000");
			model.setUser_click_starpoint("0.0000");
			// 插入广告
			advertisementLogic.setAdvertisement(model);
		}
	}
	 
}
