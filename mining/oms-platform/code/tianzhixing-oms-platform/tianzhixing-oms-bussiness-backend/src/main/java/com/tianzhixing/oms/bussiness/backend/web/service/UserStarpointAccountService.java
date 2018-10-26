package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserStarpointAccountBean;
import com.tianzhixing.appmall.rpc.service.RPCUserStarpointAccountService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserStarpointAccountService {

	@Autowired
	private RPCUserStarpointAccountService rpcUserStarpointAccountService;

	public long count(UserStarpointAccountBean userStarpointAccountBean) {
		return rpcUserStarpointAccountService.count(userStarpointAccountBean);
	}

	public List<UserStarpointAccountBean> list(PagerMapping pagerMapping, UserStarpointAccountBean userStarpointAccountBean) {
		return rpcUserStarpointAccountService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userStarpointAccountBean);
	}

	public void add(UserStarpointAccountBean userStarpointAccountBean) {
		rpcUserStarpointAccountService.add(userStarpointAccountBean);
	}

	public UserStarpointAccountBean getById(Integer id) {
		return rpcUserStarpointAccountService.getById(id);
	}

	public void update(UserStarpointAccountBean userStarpointAccountBean) {
		UserStarpointAccountBean am = rpcUserStarpointAccountService.getById(userStarpointAccountBean.getId());
		if (am == null)
			throw new RequestException("userStarpointAccount.not.found");
		rpcUserStarpointAccountService.update(userStarpointAccountBean);
	}
	
	public UserStarpointAccountBean getByUserId(Integer userId) {
		return rpcUserStarpointAccountService.getByUserId(userId);
	}
}
