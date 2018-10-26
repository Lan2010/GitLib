package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserMessageBean;
import com.tianzhixing.appmall.rpc.service.RPCUserMessageService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserMessageService {

	@Autowired
	private RPCUserMessageService rpcUserMessageService;

	public long count(UserMessageBean userMessageBean) {
		return rpcUserMessageService.count(userMessageBean);
	}

	public List<UserMessageBean> list(PagerMapping pagerMapping, UserMessageBean userMessageBean) {
		return rpcUserMessageService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userMessageBean);
	}

	public void add(UserMessageBean userMessageBean) {
		rpcUserMessageService.add(userMessageBean);
	}

	public UserMessageBean getById(Integer id) {
		return rpcUserMessageService.getById(id);
	}

	public void update(UserMessageBean userMessageBean) {
		UserMessageBean am = rpcUserMessageService.getById(userMessageBean.getId());
		if (am == null)
			throw new RequestException("userMessage.not.found");
		rpcUserMessageService.update(userMessageBean);
	}
	
	public List<UserMessageBean> getList(UserMessageBean userMessageBean) {
		return rpcUserMessageService.getList(userMessageBean);
	}
}
