package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.service.UserMessageService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserTopicService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserMessageVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.staff.StaffInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.staff.RPCStaffInfoService;
import com.tianzhixing.oms.web.security.service.entity.UserInfo;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import com.tianzhixing.appmall.rpc.bean.UserBean;
import com.tianzhixing.appmall.rpc.bean.UserMessageBean;
import com.tianzhixing.appmall.rpc.bean.UserTopicBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/user/userMessage")
public class UserMessageController extends BaseController {

	@Autowired
	private UserMessageService userMessageService;

	@Autowired
	private UserService userService;

	@Autowired
	private RPCStaffInfoService rpcStaffInfoService;
	
	@Autowired
	private UserTopicService userTopicService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/userMessage");
	}

	/**
	 * 列表
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/getList", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserMessageVo> getList(@RequestParam("id") Integer id) {
		UserMessageVo userMessageVo = new UserMessageVo();
		userMessageVo.setTopicId(id);
		List<UserMessageVo> userMessageVos = new ArrayList<>();
		List<UserMessageBean> userMessageBeans = userMessageService.getList(_toBean(userMessageVo));
		for (UserMessageBean userMessageBean : userMessageBeans) {
			UserBean userBean = userService.getById(userMessageBean.getUserId());
			String userName;
			String tzxUserName;
			if (userBean == null) {
				userName = "";
			} else {
				userName = userBean.getNickname();
			}
			StaffInfoMapper staffInfoMapper = rpcStaffInfoService.getById(userMessageBean.getTzxUserId().longValue());
			if (staffInfoMapper == null) {
				tzxUserName = "";
			} else {
				tzxUserName = staffInfoMapper.getName();
			}
			userMessageVos.add(_toVo(userMessageBean, userName, tzxUserName));
		}
		return responseEntity(200, "request.suc", 0, userMessageVos);
	}

	/**
	 * 添加
	 *
	 * @param UserMessageVo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserMessageVo> add(@ModelAttribute("userMessageVo") UserMessageVo userMessageVo) {
		if (StringUtils.isEmpty(userMessageVo.getTopicId())) {
			return responseEntity(301, "userMessage.topicId.empty");
		}
		if (StringUtils.isEmpty(userMessageVo.getContent())) {
			return responseEntity(301, "userMessage.queContent.empty");
		}
		try {
			UserInfo userInfo = SecurityUtil.currentLogin();
			if (userInfo == null) {
				throw new RuntimeException("failed.found.current.login");
			}
			userMessageVo.setTzxUserId(userInfo.getId().intValue());
			userMessageVo.setDelStatus(1);
			userMessageVo.setDt(new Date());
			userMessageVo.setType(1);
			userMessageVo.setUserId(0);
			userMessageVo.setRedStatus(0);
			userMessageService.add(_toBean(userMessageVo));
			UserTopicBean userTopicBean = new UserTopicBean();
			userTopicBean.setId(userMessageVo.getTopicId());
			userTopicBean.setRepStatus(1);
			userTopicService.update(userTopicBean);
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, userMessageVo, ex.getMessage());
		}
		return responseEntity(200, userMessageVo, "request.suc");
	}

	private UserMessageBean _toBean(UserMessageVo userMessageVo) {
		return new UserMessageBean(userMessageVo.getId(),userMessageVo.getTopicId(),userMessageVo.getUserId(),userMessageVo.getTzxUserId(),userMessageVo.getType(),userMessageVo.getContent(),userMessageVo.getRedStatus(),userMessageVo.getDelStatus(),userMessageVo.getDt());
	}

	private UserMessageVo _toVo(UserMessageBean userMessageBean, String userName, String tzxUserName) {
		return new UserMessageVo(userMessageBean.getId(),userMessageBean.getTopicId(),userMessageBean.getUserId(),userMessageBean.getTzxUserId(),userMessageBean.getType(),userMessageBean.getContent(),userMessageBean.getRedStatus(),userMessageBean.getDelStatus(),userMessageBean.getDt(),userName,tzxUserName);
	}
}
