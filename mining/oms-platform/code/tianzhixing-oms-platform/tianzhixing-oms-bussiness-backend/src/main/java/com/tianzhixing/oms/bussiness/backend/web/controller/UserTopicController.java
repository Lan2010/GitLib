package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserTopicService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserTopicVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.staff.StaffInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.staff.RPCStaffInfoService;
import com.tianzhixing.oms.web.security.service.entity.UserInfo;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import com.tianzhixing.appmall.rpc.bean.UserBean;
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
@RequestMapping(value = "/user/userTopic")
public class UserTopicController extends BaseController {

	@Autowired
	private UserTopicService userTopicService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
    private RPCStaffInfoService rpcStaffInfoService;
	
	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/userTopic");
	}

	/**
	 * 列表
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserTopicVo> list(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @ModelAttribute("userTopicVo") UserTopicVo userTopicVo) {

		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
		List<UserTopicVo> userTopicVos = new ArrayList<>();
		long count = userTopicService.count(_toBean(userTopicVo));
		if (count > 0) {
			List<UserTopicBean> userTopicBeans = userTopicService.list(pagerMapping, _toBean(userTopicVo));
			for (UserTopicBean userTopicBean : userTopicBeans) {
				UserBean userBean = userService.getById(userTopicBean.getUserId());
				String userName;
				String tzxUserName;
				if (userBean == null) {
					userName = "";
				} else {
					userName = userBean.getNickname();
				}
				StaffInfoMapper staffInfoMapper = rpcStaffInfoService.getById(userTopicBean.getTzxUserId().longValue());
				if (staffInfoMapper == null) {
					tzxUserName = "";
				} else {
					tzxUserName = staffInfoMapper.getName();
				}
				userTopicVos.add(_toVo(userTopicBean,userName,tzxUserName));
			}
		}
		return responseEntity(200, "request.suc", count, userTopicVos);
	}

	/**
	 * 添加
	 *
	 * @param UserTopicVo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserTopicVo> add(@ModelAttribute("userTopicVo") UserTopicVo userTopicVo) {
		if (StringUtils.isEmpty(userTopicVo.getUserId())) {
			return responseEntity(301, "userTopic.userId.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getType())) {
			return responseEntity(301, "userTopic.type.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getTitle())) {
			return responseEntity(301, "userTopic.title.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getContent())) {
			return responseEntity(301, "userTopic.queContent.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getImgUrl())) {
			return responseEntity(301, "userTopic.imgUrl.empty");
		}
		try {
			UserInfo userInfo = SecurityUtil.currentLogin();
			if (userInfo == null) {
				throw new RuntimeException("failed.found.current.login");
			}
			userTopicVo.setTzxUserId(userInfo.getId().intValue());
			userTopicVo.setRepStatus(1);
			userTopicVo.setDelStatus(1);
			userTopicVo.setRedStatus(0);
			userTopicVo.setDt(new Date());
			userTopicService.add(_toBean(userTopicVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, userTopicVo, ex.getMessage());
		}
		return responseEntity(200, userTopicVo, "request.suc");
	}

	/**
	 * update
	 *
	 * @param UserTopicVo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserTopicVo> update(@ModelAttribute("userTopicVo") UserTopicVo userTopicVo) {
		if (userTopicVo.getId() == null || userTopicVo.getId() == 0) {
			return responseEntity(301, "userTopic.not.found");
		}
		if (StringUtils.isEmpty(userTopicVo.getType())) {
			return responseEntity(301, "userTopic.type.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getTitle())) {
			return responseEntity(301, "userTopic.title.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getContent())) {
			return responseEntity(301, "userTopic.queContent.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getImgUrl())) {
			return responseEntity(301, "userTopic.imgUrl.empty");
		}
		if (StringUtils.isEmpty(userTopicVo.getDelStatus())) {
			return responseEntity(301, "userTopic.repStatus.empty");
		}
		try {
			UserInfo userInfo = SecurityUtil.currentLogin();
			if (userInfo == null) {
				throw new RuntimeException("failed.found.current.login");
			}
			userTopicVo.setTzxUserId(userInfo.getId().intValue());
			userTopicVo.setRedStatus(0);
			userTopicService.update(_toBean(userTopicVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, userTopicVo, ex.getMessage());
		}
		return responseEntity(200, userTopicVo, "request.suc");
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserTopicVo> detail(@RequestParam("id") Integer id) {
		UserTopicBean userTopicBean = userTopicService.getById(id);
		if (userTopicBean == null) {
			return responseEntity(301, "userTopic.not.found");
		}
		UserBean userBean = userService.getById(userTopicBean.getUserId());
		String userName;
		String tzxUserName;
		if (userBean == null) {
			userName = "";
		} else {
			userName = userBean.getNickname();
		}
		StaffInfoMapper staffInfoMapper = rpcStaffInfoService.getById(userTopicBean.getTzxUserId().longValue());
		if (staffInfoMapper == null) {
			tzxUserName = "";
		} else {
			tzxUserName = staffInfoMapper.getName();
		}
		UserTopicVo vo = _toVo(userTopicBean,userName,tzxUserName);
		return responseEntity(200, vo, "request.suc");
	}

	private UserTopicBean _toBean(UserTopicVo userTopicVo) {
		return new UserTopicBean(userTopicVo.getId(), userTopicVo.getUserId(), userTopicVo.getTzxUserId(), userTopicVo.getType(),userTopicVo.getTitle(), userTopicVo.getContent(),userTopicVo.getImgUrl(), userTopicVo.getDt(),userTopicVo.getRedStatus(), userTopicVo.getDelStatus(), userTopicVo.getRepStatus());
	}

	private UserTopicVo _toVo(UserTopicBean userTopicBean,String userName,String tzxUserName) {
		return new UserTopicVo(userTopicBean.getId(), userTopicBean.getUserId(), userTopicBean.getTzxUserId(), userTopicBean.getType(),userTopicBean.getTitle(), userTopicBean.getContent(),userTopicBean.getImgUrl(), userTopicBean.getDt(),userTopicBean.getRedStatus(), userTopicBean.getDelStatus(), userTopicBean.getRepStatus(),userName,tzxUserName);
	}
}
