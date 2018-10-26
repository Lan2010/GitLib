package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import com.tianzhixing.appmall.rpc.bean.UserBean;

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
@RequestMapping(value = "/user/user")
public class UserController extends BaseController {

	@Autowired
	private UserService userService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/user");
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
	public ResponseEntity<UserVo> list(@RequestParam("pageNo") int pageNo, 
										@RequestParam("pageSize") int pageSize, 
										@ModelAttribute("userVo") UserVo userVo,
										@RequestParam(value = "beginTime", required = false) String beginTime,
                                        @RequestParam(value = "endTime", required = false) String endTime) {

		String timeFormat = "yyyy-MM-dd";
		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
		Date sBeginTime = null;
		if (StringUtil.check(beginTime)) {
			sBeginTime = CalendarUtil.calBeginTimeForThisTime(CalendarUtil.stringToDate(beginTime, timeFormat));
		}
		Date sEndTime = null;
		if (StringUtil.check(endTime)) {
			sEndTime = CalendarUtil.calEndTimeForThisTime(CalendarUtil.stringToDate(endTime, timeFormat));
		}
		List<UserVo> userVos = new ArrayList<>();
		long count = userService.count(_toBean(userVo),sBeginTime,sEndTime);
		if (count > 0) {
			List<UserBean> UserBeans = userService.list(pagerMapping, _toBean(userVo),sBeginTime,sEndTime);
			for (UserBean userBean : UserBeans) {
				userVos.add(_toVo(userBean));
			}
		}
		return responseEntity(200, "request.suc", count, userVos);
	}

	/**
	 * update
	 *
	 * @param UserVo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<UserVo> update(@ModelAttribute("userVo") UserVo userVo) {
		if (StringUtils.isEmpty(userVo.getPasswd())) {
			return responseEntity(301, "user.passwd.empty");
		}
		if (StringUtils.isEmpty(userVo.getUnionId())) {
			return responseEntity(301, "user.unionId.empty");
		}
		if (StringUtils.isEmpty(userVo.getRegPlatform())) {
			return responseEntity(301, "user.regPlatform.empty");
		}
		if (StringUtils.isEmpty(userVo.getUserChannel())) {
			return responseEntity(301, "user.userChannel.empty");
		}
		try {
			userService.update(_toBean(userVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, userVo, ex.getMessage());
		}
		return responseEntity(200, userVo, "request.suc");
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserVo> detail(@RequestParam("id") Integer id) {
		UserBean userBean = userService.getById(id);
		if (userBean == null) {
			return responseEntity(301, "user.not.found");
		}
		UserVo vo = _toVo(userBean);
		return responseEntity(200, vo, "request.suc");
	}

	private UserBean _toBean(UserVo userVo) {
		return new UserBean(userVo.getId(), userVo.getPhoneNum(), userVo.getPasswd(), userVo.getEmail(), userVo.getGender(), userVo.getBirthday(), userVo.getCreatedt(), userVo.getMpOpenId(), userVo.getNickname(), userVo.getHeadimgUrl(), userVo.getUnionId(), userVo.getBonus(), userVo.getRegPlatform(), userVo.getUserChannel(),userVo.getMiniappOpenId(),userVo.getYouzanUserId(),userVo.getYouzanFansId());
	}

	private UserVo _toVo(UserBean userBean) {
		return new UserVo(userBean.getId(), userBean.getPhoneNum(), userBean.getPasswd(), userBean.getEmail(), userBean.getGender(), userBean.getBirthday(), userBean.getCreatedt(), userBean.getMpOpenId(), userBean.getNickname(), userBean.getHeadimgUrl(), userBean.getUnionId(), userBean.getBonus(), userBean.getRegPlatform(), userBean.getUserChannel(),userBean.getMiniappOpenId(),userBean.getYouzanUserId(),userBean.getYouzanFansId());
	}
}
