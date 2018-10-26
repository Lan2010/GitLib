package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserTopicBean;
import com.tianzhixing.appmall.rpc.service.RPCUserTopicService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserTopicService {

	@Autowired
	private RPCUserTopicService rpcUserTopicService;

	public long count(UserTopicBean userTopicBean) {
		return rpcUserTopicService.count(userTopicBean);
	}

	public List<UserTopicBean> list(PagerMapping pagerMapping, UserTopicBean userTopicBean) {
		return rpcUserTopicService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userTopicBean);
	}

	public void add(UserTopicBean userTopicBean) {
		rpcUserTopicService.add(userTopicBean);
	}

	public UserTopicBean getById(Integer id) {
		return rpcUserTopicService.getById(id);
	}

	public void update(UserTopicBean userTopicBean) {
		UserTopicBean am = rpcUserTopicService.getById(userTopicBean.getId());
		if (am == null)
			throw new RequestException("userTopic.not.found");
		rpcUserTopicService.update(userTopicBean);
	}
}
