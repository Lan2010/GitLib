package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserPromotionCodeBean;
import com.tianzhixing.appmall.rpc.service.RPCUserPromotionCodeService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserPromotionCodeService {

//	@Autowired
//	private RPCUserPromotionCodeService rpcUserPromotionCodeService;
//
//	public long count(UserPromotionCodeBean userPromotionCodeBean) {
//		return rpcUserPromotionCodeService.count(userPromotionCodeBean);
//	}
//
//	public List<UserPromotionCodeBean> list(PagerMapping pagerMapping, UserPromotionCodeBean userPromotionCodeBean) {
//		return rpcUserPromotionCodeService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userPromotionCodeBean);
//	}
//
//	public void add(UserPromotionCodeBean userPromotionCodeBean) {
//		rpcUserPromotionCodeService.add(userPromotionCodeBean);
//	}
//
//	public UserPromotionCodeBean getById(Integer id) {
//		return rpcUserPromotionCodeService.getById(id);
//	}
//
//	public void update(UserPromotionCodeBean userPromotionCodeBean) {
//		UserPromotionCodeBean am = rpcUserPromotionCodeService.getById(userPromotionCodeBean.getId());
//		if (am == null)
//			throw new RequestException("userPromotionCode.not.found");
//		rpcUserPromotionCodeService.update(userPromotionCodeBean);
//	}
}
