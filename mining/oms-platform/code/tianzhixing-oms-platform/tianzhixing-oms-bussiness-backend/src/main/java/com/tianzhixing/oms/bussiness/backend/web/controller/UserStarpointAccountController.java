package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.service.UserStarpointAccountService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserStarpointAccountVo;
import com.tianzhixing.appmall.rpc.bean.UserStarpointAccountBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


/**
 * Created by routine.k on 2018/6/24.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/user/userStarpointAccount")
public class UserStarpointAccountController extends BaseController {

	@Autowired
	private UserStarpointAccountService userStarpointAccountService;

	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserStarpointAccountVo> detail(@RequestParam("id") Integer id) {
		UserStarpointAccountBean userStarpointAccountBean = userStarpointAccountService.getByUserId(id);
		if (userStarpointAccountBean == null) {
			return responseEntity(301, "userStarpointAccount.not.found");
		}
		UserStarpointAccountVo vo = _toVo(userStarpointAccountBean);
		return responseEntity(200, vo, "request.suc");
	}

	private UserStarpointAccountBean _toBean(UserStarpointAccountVo userStarpointAccountVo) {
		return new UserStarpointAccountBean(userStarpointAccountVo.getId(),userStarpointAccountVo.getWkappMobile(),userStarpointAccountVo.getWkappAccountToken(),userStarpointAccountVo.getUserId(),userStarpointAccountVo.getEnable(),userStarpointAccountVo.getWkappOrg(),userStarpointAccountVo.getSelfAccountToken(),userStarpointAccountVo.getSelfAccountOrg());
	}

	private UserStarpointAccountVo _toVo(UserStarpointAccountBean userStarpointAccountBean) {
		return new UserStarpointAccountVo(userStarpointAccountBean.getId(),userStarpointAccountBean.getWkappMobile(),userStarpointAccountBean.getWkappAccountToken(),userStarpointAccountBean.getUserId(),userStarpointAccountBean.getEnable(),userStarpointAccountBean.getWkappOrg(),userStarpointAccountBean.getSelfAccountToken(),userStarpointAccountBean.getSelfAccountOrg());
	}
}
