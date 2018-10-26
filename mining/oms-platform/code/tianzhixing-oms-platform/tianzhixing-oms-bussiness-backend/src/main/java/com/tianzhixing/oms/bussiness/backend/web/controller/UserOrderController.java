package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserOrderVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import com.tianzhixing.appmall.rpc.bean.UserOrderBean;

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
@RequestMapping(value = "/user/userOrder")
public class UserOrderController extends BaseController {

//	@Autowired
//	private UserOrderService userOrderService;
//
//	/**
//	 * 首页
//	 *
//	 * @return
//	 */
//	@RequestMapping(value = "/index", method = RequestMethod.GET)
//	public ModelAndView index() {
//		return configModelAndView("user/userOrder");
//	}
//
//	/**
//	 * 列表
//	 *
//	 * @param pageNo
//	 * @param pageSize
//	 * @return
//	 */
//	@RequestMapping(value = "/list", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<UserOrderVo> list(@RequestParam("pageNo") int pageNo, 
//										@RequestParam("pageSize") int pageSize, 
//										@ModelAttribute("userOrderVo") UserOrderVo userOrderVo,
//										@RequestParam(value = "beginTime", required = false) String beginTime,
//                                        @RequestParam(value = "endTime", required = false) String endTime) {
//
//		String timeFormat = "yyyy-MM-dd";
//		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
//		Date sBeginTime = null;
//		if (StringUtil.check(beginTime)) {
//			sBeginTime = CalendarUtil.calBeginTimeForThisTime(CalendarUtil.stringToDate(beginTime, timeFormat));
//		}
//		Date sEndTime = null;
//		if (StringUtil.check(endTime)) {
//			sEndTime = CalendarUtil.calEndTimeForThisTime(CalendarUtil.stringToDate(endTime, timeFormat));
//		}
//		List<UserOrderVo> userOrderVos = new ArrayList<>();
//		long count = userOrderService.count(_toBean(userOrderVo),sBeginTime,sEndTime);
//		if (count > 0) {
//			List<UserOrderBean> UserOrderBeans = userOrderService.list(pagerMapping, _toBean(userOrderVo),sBeginTime,sEndTime);
//			for (UserOrderBean userOrderBean : UserOrderBeans) {
//				userOrderVos.add(_toVo(userOrderBean));
//			}
//		}
//		return responseEntity(200, "request.suc", count, userOrderVos);
//	}
//
//	/**
//	 * update
//	 *
//	 * @param UserOrderVo
//	 * @return
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<UserOrderVo> update(@ModelAttribute("userOrderVo") UserOrderVo userOrderVo) {
//		if (StringUtils.isEmpty(userOrderVo.getPasswd())) {
//			return responseEntity(301, "userOrder.passwd.empty");
//		}
//		if (StringUtils.isEmpty(userOrderVo.getOpenId())) {
//			return responseEntity(301, "userOrder.openId.empty");
//		}
//		if (StringUtils.isEmpty(userOrderVo.getUnionId())) {
//			return responseEntity(301, "userOrder.unionId.empty");
//		}
//		if (StringUtils.isEmpty(userOrderVo.getRegPlatform())) {
//			return responseEntity(301, "userOrder.regPlatform.empty");
//		}
//		if (StringUtils.isEmpty(userOrderVo.getUserOrderChannel())) {
//			return responseEntity(301, "userOrder.userOrderChannel.empty");
//		}
//		try {
//			userOrderService.update(_toBean(userOrderVo));
//		} catch (RequestException ex) {
//			ex.printStackTrace();
//			return responseEntity(301, userOrderVo, ex.getMessage());
//		}
//		return responseEntity(200, userOrderVo, "request.suc");
//	}
//
//	/**
//	 * 详情
//	 *
//	 * @param id
//	 * @return
//	 */
//	@RequestMapping(value = "/detail", method = RequestMethod.GET)
//	@ResponseBody
//	public ResponseEntity<UserOrderVo> detail(@RequestParam("id") Integer id) {
//		UserOrderBean userOrderBean = userOrderService.getById(id);
//		if (userOrderBean == null) {
//			return responseEntity(301, "userOrder.not.found");
//		}
//		UserOrderVo vo = _toVo(userOrderBean);
//		return responseEntity(200, vo, "request.suc");
//	}
//
//	private UserOrderBean _toBean(UserOrderVo userOrderVo) {
//		return new UserOrderBean(userOrderVo.getId(), userOrderVo.getPhoneNum(), userOrderVo.getPasswd(), userOrderVo.getEmail(), userOrderVo.getGender(), userOrderVo.getBirthday(), userOrderVo.getCreatedt(), userOrderVo.getOpenId(), userOrderVo.getNickname(), userOrderVo.getHeadimgUrl(), userOrderVo.getUnionId(), userOrderVo.getBonus(), userOrderVo.getRegPlatform(), userOrderVo.getUserOrderChannel());
//	}
//
//	private UserOrderVo _toVo(UserOrderBean userOrderBean) {
//		return new UserOrderVo(userOrderBean.getId(), userOrderBean.getPhoneNum(), userOrderBean.getPasswd(), userOrderBean.getEmail(), userOrderBean.getGender(), userOrderBean.getBirthday(), userOrderBean.getCreatedt(), userOrderBean.getOpenId(), userOrderBean.getNickname(), userOrderBean.getHeadimgUrl(), userOrderBean.getUnionId(), userOrderBean.getBonus(), userOrderBean.getRegPlatform(), userOrderBean.getUserOrderChannel());
//	}
}
