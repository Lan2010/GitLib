package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.PromotionCodeBean;
import com.tianzhixing.appmall.rpc.service.RPCPromotionCodeService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class PromotionCodeService {

//	@Autowired
//	private RPCPromotionCodeService rpcPromotionCodeService;
//
//	public long count(PromotionCodeBean promotionCodeBean) {
//		return rpcPromotionCodeService.count(promotionCodeBean);
//	}
//
//	public List<PromotionCodeBean> list(PagerMapping pagerMapping, PromotionCodeBean promotionCodeBean) {
//		return rpcPromotionCodeService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), promotionCodeBean);
//	}
//
//	public void add(PromotionCodeBean promotionCodeBean) {
//		rpcPromotionCodeService.add(promotionCodeBean);
//	}
//
//	public PromotionCodeBean getById(Integer id) {
//		return rpcPromotionCodeService.getById(id);
//	}
//
//	public void update(PromotionCodeBean promotionCodeBean) {
//		PromotionCodeBean am = rpcPromotionCodeService.getById(promotionCodeBean.getId());
//		if (am == null)
//			throw new RequestException("promotionCode.not.found");
//		rpcPromotionCodeService.update(promotionCodeBean);
//	}
}
