package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.ProductService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserPromotionCodeService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserPromotionCodeVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.appmall.rpc.bean.UserPromotionCodeBean;
import com.tianzhixing.appmall.rpc.bean.ProductBean;

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
@RequestMapping(value = "/user/userPromotionCode")
public class UserPromotionCodeController extends BaseController {
//
//	@Autowired
//	private UserPromotionCodeService userPromotionCodeService;
//
//	@Autowired
//	private ProductService productService;
//
//	/**
//	 * 首页
//	 *
//	 * @return
//	 */
//	@RequestMapping(value = "/index", method = RequestMethod.GET)
//	public ModelAndView index() {
//		return configModelAndView("user/userPromotionCode");
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
//	public ResponseEntity<UserPromotionCodeVo> list(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @ModelAttribute("userPromotionCodeVo") UserPromotionCodeVo userPromotionCodeVo) {
//
//		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
//		List<UserPromotionCodeVo> userPromotionCodeVos = new ArrayList<>();
//		long count = userPromotionCodeService.count(_toBean(userPromotionCodeVo));
//		if (count > 0) {
//			List<UserPromotionCodeBean> UserPromotionCodeBeans = userPromotionCodeService.list(pagerMapping, _toBean(userPromotionCodeVo));
//			for (UserPromotionCodeBean userPromotionCodeBean : UserPromotionCodeBeans) {
//				ProductBean productBean = productService.getById(userPromotionCodeBean.getForProductionId());
//				String productName;
//				if (productBean == null) {
//					productName = "";
//				} else {
//					productName = productBean.getProductName();
//				}
//				userPromotionCodeVos.add(_toVo(userPromotionCodeBean, productName));
//			}
//		}
//		return responseEntity(200, "request.suc", count, userPromotionCodeVos);
//	}
//
//	/**
//	 * 添加
//	 *
//	 * @param UserPromotionCodeVo
//	 * @return
//	 */
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<UserPromotionCodeVo> add(@ModelAttribute("userPromotionCodeVo") UserPromotionCodeVo userPromotionCodeVo) {
//		if (StringUtils.isEmpty(userPromotionCodeVo.getPromotionCode())) {
//			return responseEntity(301, "userPromotionCode.userPromotionCode.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getPromotionRules())) {
//			return responseEntity(301, "userPromotionCode.promotionRules.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "userPromotionCode.expiryDt.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getForProductionId())) {
//			return responseEntity(301, "userPromotionCode.forProductionId.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getForUserId())) {
//			return responseEntity(301, "userPromotionCode.forUserId.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getPromotionCodeType())) {
//			return responseEntity(301, "userPromotionCode.userPromotionCodeType.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getAllowComposition())) {
//			return responseEntity(301, "userPromotionCode.allowComposition.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getBeginDt())) {
//			return responseEntity(301, "userPromotionCode.beginDt.empty");
//		}
//		if (CalendarUtil.isAfter(userPromotionCodeVo.getBeginDt(), userPromotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "product.begin.end.time.error");
//		}
//		try {
//			userPromotionCodeVo.setStatus(1);
//			userPromotionCodeVo.setCreateDt(new Date());
//			userPromotionCodeService.add(_toBean(userPromotionCodeVo));
//		} catch (RequestException ex) {
//			ex.printStackTrace();
//			return responseEntity(301, userPromotionCodeVo, ex.getMessage());
//		}
//		return responseEntity(200, userPromotionCodeVo, "request.suc");
//	}
//
//	/**
//	 * update
//	 *
//	 * @param UserPromotionCodeVo
//	 * @return
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<UserPromotionCodeVo> update(@ModelAttribute("userPromotionCodeVo") UserPromotionCodeVo userPromotionCodeVo) {
//		if (StringUtils.isEmpty(userPromotionCodeVo.getPromotionCode())) {
//			return responseEntity(301, "userPromotionCode.userPromotionCode.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getPromotionRules())) {
//			return responseEntity(301, "userPromotionCode.promotionRules.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "userPromotionCode.expiryDt.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getForProductionId())) {
//			return responseEntity(301, "userPromotionCode.forProductionId.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getForUserId())) {
//			return responseEntity(301, "userPromotionCode.forUserId.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getPromotionCodeType())) {
//			return responseEntity(301, "userPromotionCode.userPromotionCodeType.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getAllowComposition())) {
//			return responseEntity(301, "userPromotionCode.allowComposition.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getBeginDt())) {
//			return responseEntity(301, "userPromotionCode.beginDt.empty");
//		}
//		if (StringUtils.isEmpty(userPromotionCodeVo.getStatus())) {
//			return responseEntity(301, "userPromotionCode.isExpired.empty");
//		}
//		if (CalendarUtil.isAfter(userPromotionCodeVo.getBeginDt(), userPromotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "product.begin.end.time.error");
//		}
//		try {
//			userPromotionCodeService.update(_toBean(userPromotionCodeVo));
//		} catch (RequestException ex) {
//			ex.printStackTrace();
//			return responseEntity(301, userPromotionCodeVo, ex.getMessage());
//		}
//		return responseEntity(200, userPromotionCodeVo, "request.suc");
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
//	public ResponseEntity<UserPromotionCodeVo> detail(@RequestParam("id") Integer id) {
//		UserPromotionCodeBean userPromotionCodeBean = userPromotionCodeService.getById(id);
//		if (userPromotionCodeBean == null) {
//			return responseEntity(301, "userPromotionCode.not.found");
//		}
//		ProductBean productBean = productService.getById(userPromotionCodeBean.getForProductionId());
//		String productName;
//		if (productBean == null) {
//			productName = "";
//		} else {
//			productName = productBean.getProductName();
//		}
//		UserPromotionCodeVo vo = _toVo(userPromotionCodeBean, productName);
//		return responseEntity(200, vo, "request.suc");
//	}
//
//	private UserPromotionCodeBean _toBean(UserPromotionCodeVo userPromotionCodeVo) {
//		return new UserPromotionCodeBean(userPromotionCodeVo.getId(),userPromotionCodeVo.getPromotionCode(),userPromotionCodeVo.getPromotionRules(),userPromotionCodeVo.getStatus(),userPromotionCodeVo.getExpiryDt(),userPromotionCodeVo.getCreateDt(),userPromotionCodeVo.getForUserId(),userPromotionCodeVo.getForOpenId(),userPromotionCodeVo.getForProductionId(),userPromotionCodeVo.getVoucherAmount(),userPromotionCodeVo.getDiscountRate(),userPromotionCodeVo.getPromotionCodeType(),userPromotionCodeVo.getAllowComposition(),userPromotionCodeVo.getBeginDt());
//	}
//
//	private UserPromotionCodeVo _toVo(UserPromotionCodeBean userPromotionCodeBean, String productName) {
//		return new UserPromotionCodeVo(userPromotionCodeBean.getId(),userPromotionCodeBean.getPromotionCode(),userPromotionCodeBean.getPromotionRules(),userPromotionCodeBean.getStatus(),userPromotionCodeBean.getExpiryDt(),userPromotionCodeBean.getCreateDt(),userPromotionCodeBean.getForUserId(),userPromotionCodeBean.getForOpenId(),userPromotionCodeBean.getForProductionId(),userPromotionCodeBean.getVoucherAmount(),userPromotionCodeBean.getDiscountRate(),userPromotionCodeBean.getPromotionCodeType(),userPromotionCodeBean.getAllowComposition(),userPromotionCodeBean.getBeginDt(),productName);
//	}
}
