package com.tianzhixing.oms.bussiness.backend.web.service;


import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.appmall.rpc.bean.UserWechatBean;
import com.tianzhixing.appmall.rpc.service.RPCUserWechatService;



/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class UserWechatService {

	@Autowired
	private RPCUserWechatService rpcUserWechatService;

	public long count(UserWechatBean userBean,Date sBeginTime, Date sEndTime) {
		return rpcUserWechatService.count(userBean,sBeginTime,sEndTime);
	}

	public List<UserWechatBean> list(PagerMapping pagerMapping, UserWechatBean userBean, Date sBeginTime, Date sEndTime) {
		return rpcUserWechatService.list(pagerMapping.getFrom(), pagerMapping.getPageSize(), userBean,sBeginTime,sEndTime);
	}

	public UserWechatBean getById(Integer id) {
		return rpcUserWechatService.getById(id);
	}

	public void update(UserWechatBean userBean) {
		UserWechatBean am = rpcUserWechatService.getById(userBean.getId());
		if (am == null)
			throw new RequestException("user.not.found");
		rpcUserWechatService.update(userBean);
	}
	
	public UserWechatBean getByUserId(Integer userId) {
		return rpcUserWechatService.getByUserId(userId);
	}
}
