package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserBean;
import com.tianzhixing.appmall.rpc.service.RPCUserService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserService {

	@Autowired
	private RPCUserService rpcUserService;

	public long count(UserBean userBean,Date sBeginTime, Date sEndTime) {
		return rpcUserService.count(userBean,sBeginTime,sEndTime);
	}

	public List<UserBean> list(PagerMapping pagerMapping, UserBean userBean, Date sBeginTime, Date sEndTime) {
		return rpcUserService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userBean,sBeginTime,sEndTime);
	}

	public UserBean getById(Integer id) {
		return rpcUserService.getById(id);
	}

	public void update(UserBean userBean) {
		UserBean am = rpcUserService.getById(userBean.getId());
		if (am == null)
			throw new RequestException("user.not.found");
		rpcUserService.update(userBean);
	}
}
