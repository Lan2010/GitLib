package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserWechatService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserWechatVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import com.tianzhixing.appmall.rpc.bean.UserWechatBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
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
@RequestMapping(value = "/user/userWechat")
public class UserWechatController extends BaseController {

	@Autowired
	private UserWechatService userWechatService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/userWechat");
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
	public ResponseEntity<UserWechatVo> list(@RequestParam("pageNo") int pageNo, 
										@RequestParam("pageSize") int pageSize, 
										@ModelAttribute("userWechatVo") UserWechatVo userWechatVo,
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
		List<UserWechatVo> userWechatVos = new ArrayList<>();
		long count = userWechatService.count(_toBean(userWechatVo),sBeginTime,sEndTime);
		if (count > 0) {
			List<UserWechatBean> UserWechatBeans = userWechatService.list(pagerMapping, _toBean(userWechatVo),sBeginTime,sEndTime);
			for (UserWechatBean userWechatBean : UserWechatBeans) {
				userWechatVos.add(_toVo(userWechatBean));
			}
		}
		return responseEntity(200, "request.suc", count, userWechatVos);
	}
	
	@RequestMapping(value = "/getDetailByUserId", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserWechatVo> detail(@RequestParam("id") Integer id) {
		UserWechatBean userWechatBean = userWechatService.getByUserId(id);
		if (userWechatBean == null) {
			return responseEntity(301, "userWechat.not.found");
		}
		UserWechatVo vo = _toVo(userWechatBean);
		return responseEntity(200, vo, "request.suc");
	}

	private UserWechatBean _toBean(UserWechatVo userWechatVo) {
		return new UserWechatBean(userWechatVo.getId(),userWechatVo.getUserId(),userWechatVo.getMpOpenId(),userWechatVo.getNickname(),userWechatVo.getPhoneNum(),userWechatVo.getGender(),userWechatVo.getProvince(),userWechatVo.getCity(),userWechatVo.getCountry(),userWechatVo.getHeadimgUrl(),userWechatVo.getLanguage(),userWechatVo.getFollowDt(),userWechatVo.getUnionId(),userWechatVo.getMiniappOpenId());
	}

	private UserWechatVo _toVo(UserWechatBean userWechatBean) {
		return new UserWechatVo(userWechatBean.getId(),userWechatBean.getUserId(),userWechatBean.getMpOpenId(),userWechatBean.getNickname(),userWechatBean.getPhoneNum(),userWechatBean.getGender(),userWechatBean.getProvince(),userWechatBean.getCity(),userWechatBean.getCountry(),userWechatBean.getHeadimgUrl(),userWechatBean.getLanguage(),userWechatBean.getFollowDt(),userWechatBean.getUnionId(),userWechatBean.getMiniappOpenId());
	}
}
