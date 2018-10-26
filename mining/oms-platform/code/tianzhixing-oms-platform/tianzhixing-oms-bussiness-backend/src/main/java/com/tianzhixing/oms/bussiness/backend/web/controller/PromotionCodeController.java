package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.ProductService;
import com.tianzhixing.oms.bussiness.backend.web.service.PromotionCodeService;
import com.tianzhixing.oms.bussiness.backend.web.vo.PromotionCodeVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.appmall.rpc.bean.PromotionCodeBean;
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
@RequestMapping(value = "/user/promotionCode")
public class PromotionCodeController extends BaseController {
//
//	@Autowired
//	private PromotionCodeService promotionCodeService;
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
//		return configModelAndView("user/promotionCode");
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
//	public ResponseEntity<PromotionCodeVo> list(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @ModelAttribute("promotionCodeVo") PromotionCodeVo promotionCodeVo) {
//
//		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
//		List<PromotionCodeVo> promotionCodeVos = new ArrayList<>();
//		long count = promotionCodeService.count(_toBean(promotionCodeVo));
//		if (count > 0) {
//			List<PromotionCodeBean> PromotionCodeBeans = promotionCodeService.list(pagerMapping, _toBean(promotionCodeVo));
//			for (PromotionCodeBean promotionCodeBean : PromotionCodeBeans) {
//				ProductBean productBean = productService.getById(promotionCodeBean.getForProductionId());
//				String productName;
//				if (productBean == null) {
//					productName = "";
//				} else {
//					productName = productBean.getProductName();
//				}
//				promotionCodeVos.add(_toVo(promotionCodeBean, productName));
//			}
//		}
//		return responseEntity(200, "request.suc", count, promotionCodeVos);
//	}
//
//	/**
//	 * 添加
//	 *
//	 * @param PromotionCodeVo
//	 * @return
//	 */
//	@RequestMapping(value = "/add", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<PromotionCodeVo> add(@ModelAttribute("promotionCodeVo") PromotionCodeVo promotionCodeVo) {
//		if (StringUtils.isEmpty(promotionCodeVo.getPromotionCode())) {
//			return responseEntity(301, "promotionCode.promotionCode.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getPromotionRules())) {
//			return responseEntity(301, "promotionCode.promotionRules.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "promotionCode.expiryDt.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getForProductionId())) {
//			return responseEntity(301, "promotionCode.forProductionId.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getPromotionCodeType())) {
//			return responseEntity(301, "promotionCode.promotionCodeType.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getAllowComposition())) {
//			return responseEntity(301, "promotionCode.allowComposition.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getBeginDt())) {
//			return responseEntity(301, "promotionCode.beginDt.empty");
//		}
//		if (CalendarUtil.isAfter(promotionCodeVo.getBeginDt(), promotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "product.begin.end.time.error");
//		}
//		try {
//			promotionCodeVo.setIsExpired((byte) 1);
//			promotionCodeVo.setCreateDt(new Date());
//			promotionCodeService.add(_toBean(promotionCodeVo));
//		} catch (RequestException ex) {
//			ex.printStackTrace();
//			return responseEntity(301, promotionCodeVo, ex.getMessage());
//		}
//		return responseEntity(200, promotionCodeVo, "request.suc");
//	}
//
//	/**
//	 * update
//	 *
//	 * @param PromotionCodeVo
//	 * @return
//	 */
//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	@ResponseBody
//	public ResponseEntity<PromotionCodeVo> update(@ModelAttribute("promotionCodeVo") PromotionCodeVo promotionCodeVo) {
//		if (StringUtils.isEmpty(promotionCodeVo.getPromotionCode())) {
//			return responseEntity(301, "promotionCode.promotionCode.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getPromotionRules())) {
//			return responseEntity(301, "promotionCode.promotionRules.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "promotionCode.expiryDt.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getForProductionId())) {
//			return responseEntity(301, "promotionCode.forProductionId.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getPromotionCodeType())) {
//			return responseEntity(301, "promotionCode.promotionCodeType.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getAllowComposition())) {
//			return responseEntity(301, "promotionCode.allowComposition.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getBeginDt())) {
//			return responseEntity(301, "promotionCode.beginDt.empty");
//		}
//		if (StringUtils.isEmpty(promotionCodeVo.getIsExpired())) {
//			return responseEntity(301, "promotionCode.isExpired.empty");
//		}
//		if (CalendarUtil.isAfter(promotionCodeVo.getBeginDt(), promotionCodeVo.getExpiryDt())) {
//			return responseEntity(301, "product.begin.end.time.error");
//		}
//		try {
//			promotionCodeService.update(_toBean(promotionCodeVo));
//		} catch (RequestException ex) {
//			ex.printStackTrace();
//			return responseEntity(301, promotionCodeVo, ex.getMessage());
//		}
//		return responseEntity(200, promotionCodeVo, "request.suc");
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
//	public ResponseEntity<PromotionCodeVo> detail(@RequestParam("id") Integer id) {
//		PromotionCodeBean promotionCodeBean = promotionCodeService.getById(id);
//		if (promotionCodeBean == null) {
//			return responseEntity(301, "promotionCode.not.found");
//		}
//		ProductBean productBean = productService.getById(promotionCodeBean.getForProductionId());
//		String productName;
//		if (productBean == null) {
//			productName = "";
//		} else {
//			productName = productBean.getProductName();
//		}
//		PromotionCodeVo vo = _toVo(promotionCodeBean, productName);
//		return responseEntity(200, vo, "request.suc");
//	}
//
//	private PromotionCodeBean _toBean(PromotionCodeVo promotionCodeVo) {
//		return new PromotionCodeBean(promotionCodeVo.getId(),promotionCodeVo.getPromotionCode(),promotionCodeVo.getPromotionRules(),promotionCodeVo.getIsExpired(),promotionCodeVo.getExpiryDt(),promotionCodeVo.getCreateDt(),promotionCodeVo.getForProductionId(),promotionCodeVo.getVoucherAmount(),promotionCodeVo.getDiscountRate(),promotionCodeVo.getPromotionCodeType(),promotionCodeVo.getAllowComposition(),promotionCodeVo.getBeginDt());
//	}
//
//	private PromotionCodeVo _toVo(PromotionCodeBean promotionCodeBean, String productName) {
//		return new PromotionCodeVo(promotionCodeBean.getId(),promotionCodeBean.getPromotionCode(),promotionCodeBean.getPromotionRules(),promotionCodeBean.getIsExpired(),promotionCodeBean.getExpiryDt(),promotionCodeBean.getCreateDt(),promotionCodeBean.getForProductionId(),promotionCodeBean.getVoucherAmount(),promotionCodeBean.getDiscountRate(),promotionCodeBean.getPromotionCodeType(),promotionCodeBean.getAllowComposition(),promotionCodeBean.getBeginDt(),productName);
//	}
}
