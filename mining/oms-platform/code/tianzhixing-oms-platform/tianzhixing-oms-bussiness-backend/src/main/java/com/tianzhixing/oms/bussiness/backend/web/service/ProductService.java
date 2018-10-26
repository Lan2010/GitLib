package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.appmall.rpc.service.RPCProductService;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.ProductBean;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class ProductService {

	@Autowired
	private RPCProductService rpcProductService;

	public long count(ProductBean productBean) {
		return rpcProductService.count(productBean);
	}

	public List<ProductBean> list(PagerMapping pagerMapping, ProductBean productBean) {
		return rpcProductService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), productBean);
	}

	public void add(ProductBean productBean) {
		rpcProductService.add(productBean);
	}

	public ProductBean getById(Integer id) {
		return rpcProductService.getById(id);
	}

	public void update(ProductBean productBean) {
		ProductBean am = rpcProductService.getById(productBean.getId());
		if (am == null)
			throw new RequestException("product.not.found");
		rpcProductService.update(productBean);
	}
	
	public ProductBean getByName(ProductBean productBean) {
		return rpcProductService.getByName(productBean);
	}
}
