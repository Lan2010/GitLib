package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.ProductService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ProductVo;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.HttpClientUtil;
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
@RequestMapping(value = "/user/product")
public class ProductController extends BaseController {

	@Autowired
	private ProductService productService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/product");
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
	public ResponseEntity<ProductVo> list(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @ModelAttribute("productVo") ProductVo productVo) {

		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
		List<ProductVo> productVos = new ArrayList<>();
		long count = productService.count(_toBean(productVo));
		if (count > 0) {
			List<ProductBean> ProductBeans = productService.list(pagerMapping, _toBean(productVo));
			for (ProductBean productBean : ProductBeans) {
				productVos.add(_toVo(productBean));
			}
		}
		return responseEntity(200, "request.suc", count, productVos);
	}

	/**
	 * 添加
	 *
	 * @param ProductVo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProductVo> add(@ModelAttribute("productVo") ProductVo productVo) {
		if (StringUtils.isEmpty(productVo.getProductName())) {
			return responseEntity(301, "product.productName.empty");
		}
		if (StringUtils.isEmpty(productVo.getPrice())) {
			return responseEntity(301, "product.price.empty");
		}
		if (StringUtils.isEmpty(productVo.getProductStatus())) {
			return responseEntity(301, "product.productStatus.empty");
		}
		if (StringUtils.isEmpty(productVo.getSampleTubeCnt())) {
			productVo.setSampleTubeCnt(0);
		}
		if (StringUtils.isEmpty(productVo.getProductType())) {
			return responseEntity(301, "product.productType.empty");
		}
		if (StringUtils.isEmpty(productVo.getSellDt())) {
			return responseEntity(301, "product.sellDt.empty");
		}
		if (StringUtils.isEmpty(productVo.getNoSellDt())) {
			return responseEntity(301, "product.noSellDt.empty");
		}
		if (CalendarUtil.isAfter(productVo.getSellDt(), productVo.getNoSellDt())) {
			return responseEntity(301, "product.begin.end.time.error");
		}
		if (StringUtils.isEmpty(productVo.getProductCreatePlatform())) {
			return responseEntity(301, "product.productCreatePlatform.empty");
		}
		if (StringUtils.isEmpty(productVo.getProductPic())) {
			return responseEntity(301, "product.productPic.empty");
		}
		if (StringUtils.isEmpty(productVo.getYouzanItemId())) {
			return responseEntity(301, "product.youzanItemId.empty");
		}
		if (StringUtils.isEmpty(productVo.getProductComment())) {
			return responseEntity(301, "product.productComment.empty");
		}
		try {
			PagerMapping pagerMapping = new PagerMapping(1, 1);
			ProductBean productBean = new ProductBean();
			productBean.setProductName(productVo.getProductName());
			List<ProductBean> productBeans = productService.list(pagerMapping, productBean);
			if (productBeans.size() > 0) {
				return responseEntity(301, "产品名称已存在");
			}
			if (productVo.getProductStatus() == 1) {
				productVo.setSellDt(new Date());
			}
			productService.add(_toBean(productVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, productVo, ex.getMessage());
		}
		return responseEntity(200, productVo, "request.suc");
	}

	/**
	 * update
	 *
	 * @param ProductVo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ProductVo> update(@ModelAttribute("productVo") ProductVo productVo) {
		if (productVo.getId() == null || productVo.getId() == 0) {
			return responseEntity(301, "product.not.found");
		}
		if (StringUtils.isEmpty(productVo.getProductName())) {
			return responseEntity(301, "product.productName.empty");
		}
		if (StringUtils.isEmpty(productVo.getPrice())) {
			return responseEntity(301, "product.price.empty");
		}
		if (StringUtils.isEmpty(productVo.getPromotionPrice())) {
			return responseEntity(301, "product.promotionPrice.empty");
		}
		if (StringUtils.isEmpty(productVo.getProductStatus())) {
			return responseEntity(301, "product.productStatus.empty");
		}
		if (StringUtils.isEmpty(productVo.getSampleTubeCnt())) {
			productVo.setSampleTubeCnt(0);
		}
		if (StringUtils.isEmpty(productVo.getProductType())) {
			return responseEntity(301, "product.productType.empty");
		}
		if (StringUtils.isEmpty(productVo.getSellDt())) {
			return responseEntity(301, "product.sellDt.empty");
		}
		if (StringUtils.isEmpty(productVo.getNoSellDt())) {
			return responseEntity(301, "product.noSellDt.empty");
		}
		if (CalendarUtil.isAfter(productVo.getSellDt(), productVo.getNoSellDt())) {
			return responseEntity(301, "product.begin.end.time.error");
		}
		if (StringUtils.isEmpty(productVo.getProductCreatePlatform())) {
			return responseEntity(301, "product.productCreatePlatform.empty");
		}
		try {
			ProductBean productBean = productService.getByName(_toBean(productVo));
			if (productBean != null) {
				return responseEntity(301, "产品名称已存在");
			}
			if (productVo.getProductStatus() == 1) {
				productVo.setSellDt(new Date());
			}
			if (productVo.getProductStatus() == 2) {
				productVo.setNoSellDt(new Date());
			}
			productVo.setLastModifyDt(new Date());
			productService.update(_toBean(productVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, productVo, ex.getMessage());
		}
		return responseEntity(200, productVo, "request.suc");
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ProductVo> detail(@RequestParam("id") Integer id) {
		ProductBean productBean = productService.getById(id);
		if (productBean == null) {
			return responseEntity(301, "product.not.found");
		}
		ProductVo vo = _toVo(productBean);
		return responseEntity(200, vo, "request.suc");
	}

	/**
	 * 列表
	 *
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@RequestMapping(value = "/youzanList", method = RequestMethod.GET, produces = "text/html;charset=UTF-8")
	@ResponseBody
	public String youzanList(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize) {
		String url = "http://127.0.0.1:8089/youzan/item/list?from=" + pageNo + "&pageSize=" + pageSize;
		String result = HttpClientUtil.doGetp(url);
		return result;
	}

	private ProductBean _toBean(ProductVo productVo) {
		return new ProductBean(productVo.getId(), productVo.getProductName(), productVo.getPrice(), productVo.getPromotionPrice(), productVo.getProductStatus(), productVo.getSellDt(), productVo.getLastModifyDt(), productVo.getNoSellDt(), productVo.getSampleTubeCnt(), productVo.getProductType(), productVo.getProductCreatePlatform(), productVo.getYouzanItemId(), productVo.getProductPic(), productVo.getProductComment());
	}

	private ProductVo _toVo(ProductBean productBean) {
		return new ProductVo(productBean.getId(), productBean.getProductName(), productBean.getPrice(), productBean.getPromotionPrice(), productBean.getProductStatus(), productBean.getSellDt(), productBean.getLastModifyDt(), productBean.getNoSellDt(), productBean.getSampleTubeCnt(), productBean.getProductType(), productBean.getProductCreatePlatform(), productBean.getYouzanItemId(), productBean.getProductPic(), productBean.getProductComment());
	}
}
