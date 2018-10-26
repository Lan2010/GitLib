package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserGeneInterprBean;
import com.tianzhixing.appmall.rpc.service.RPCUserGeneInterprService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserGeneInterprService {

	@Autowired
	private RPCUserGeneInterprService rpcUserGeneInterprService;

	public long count(UserGeneInterprBean userGeneInterprBean) {
		return rpcUserGeneInterprService.count(userGeneInterprBean);
	}

	public List<UserGeneInterprBean> list(PagerMapping pagerMapping, UserGeneInterprBean userGeneInterprBean) {
		return rpcUserGeneInterprService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userGeneInterprBean);
	}

	public void add(UserGeneInterprBean userGeneInterprBean) {
		rpcUserGeneInterprService.add(userGeneInterprBean);
	}

	public UserGeneInterprBean getById(Integer id) {
		return rpcUserGeneInterprService.getById(id);
	}

	public void update(UserGeneInterprBean userGeneInterprBean) {
		UserGeneInterprBean am = rpcUserGeneInterprService.getById(userGeneInterprBean.getId());
		if (am == null)
			throw new RequestException("userGeneInterpr.not.found");
		rpcUserGeneInterprService.update(userGeneInterprBean);
	}
}
