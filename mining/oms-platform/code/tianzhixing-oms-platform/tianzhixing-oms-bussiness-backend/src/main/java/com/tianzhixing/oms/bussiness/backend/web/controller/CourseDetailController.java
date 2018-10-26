package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.CourseDetailService;
import com.tianzhixing.oms.bussiness.backend.web.service.ProductService;
import com.tianzhixing.oms.bussiness.backend.web.vo.CourseDetailVo;
import com.tianzhixing.appmall.rpc.bean.CourseDetailBean;
import com.tianzhixing.appmall.rpc.bean.ProductBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/user/courseDetail")
public class CourseDetailController extends BaseController {

	@Autowired
	private CourseDetailService courseDetailService;

	@Autowired
	private ProductService productService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/courseDetail");
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
	public ResponseEntity<CourseDetailVo> list(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @ModelAttribute("courseDetailVo") CourseDetailVo courseDetailVo) {

		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
		List<CourseDetailVo> courseDetailVos = new ArrayList<>();
		long count = courseDetailService.count(_toBean(courseDetailVo));
		if (count > 0) {
			List<CourseDetailBean> CourseDetailBeans = courseDetailService.list(pagerMapping, _toBean(courseDetailVo));
			for (CourseDetailBean courseDetailBean : CourseDetailBeans) {
				ProductBean productBean = productService.getById(courseDetailBean.getProductId());
				String productName;
				if (productBean == null) {
					productName = "";
				} else {
					productName = productBean.getProductName();
				}
				courseDetailVos.add(_toVo(courseDetailBean, productName));
			}
		}
		return responseEntity(200, "request.suc", count, courseDetailVos);
	}

	/**
	 * 添加
	 *
	 * @param CourseDetailVo
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CourseDetailVo> add(@ModelAttribute("courseDetailVo") CourseDetailVo courseDetailVo) {
		if (StringUtils.isEmpty(courseDetailVo.getProductId())) {
			return responseEntity(301, "courseDetail.productId.empty");
		}
		if (StringUtils.isEmpty(courseDetailVo.getCourseType())) {
			return responseEntity(301, "courseDetail.courseType.empty");
		}
		if (courseDetailVo.getCourseType()==9) {
			return responseEntity(301, "courseDetail.courseType.empty");
		}
		if (StringUtils.isEmpty(courseDetailVo.getVideoNum())) {
			return responseEntity(301, "courseDetail.videoNum.empty");
		}
		if (StringUtils.isEmpty(courseDetailVo.getVideoLink())) {
			return responseEntity(301, "courseDetail.videoLink.empty");
		}
		if (StringUtils.isEmpty(courseDetailVo.getVideoName())) {
			return responseEntity(301, "courseDetail.videoName.empty");
		}
		try {
			courseDetailVo.setEnable(1);
			courseDetailService.add(_toBean(courseDetailVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, courseDetailVo, ex.getMessage());
		}
		return responseEntity(200, courseDetailVo, "request.suc");
	}

	/**
	 * update
	 *
	 * @param CourseDetailVo
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<CourseDetailVo> update(@ModelAttribute("courseDetailVo") CourseDetailVo courseDetailVo) {
		if (courseDetailVo.getId() == null || courseDetailVo.getId() == 0) {
			return responseEntity(301, "courseDetail.not.found");
		}
//		if (StringUtils.isEmpty(courseDetailVo.getProductId())) {
//			return responseEntity(301, "courseDetail.productId.empty");
//		}
//		if (StringUtils.isEmpty(courseDetailVo.getCourseType())) {
//			return responseEntity(301, "courseDetail.courseType.empty");
//		}
		if (StringUtils.isEmpty(courseDetailVo.getVideoNum())) {
			return responseEntity(301, "courseDetail.videoNum.empty");
		}
		if (StringUtils.isEmpty(courseDetailVo.getVideoLink())) {
			return responseEntity(301, "courseDetail.videoLink.empty");
		}
		if (StringUtils.isEmpty(courseDetailVo.getVideoName())) {
			return responseEntity(301, "courseDetail.videoName.empty");
		}
		try {
			courseDetailService.update(_toBean(courseDetailVo));
		} catch (RequestException ex) {
			ex.printStackTrace();
			return responseEntity(301, courseDetailVo, ex.getMessage());
		}
		return responseEntity(200, courseDetailVo, "request.suc");
	}

	/**
	 * 详情
	 *
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/detail", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<CourseDetailVo> detail(@RequestParam("id") Long id) {
		CourseDetailBean courseDetailBean = courseDetailService.getById(id);
		if (courseDetailBean == null) {
			return responseEntity(301, "courseDetail.not.found");
		}
		ProductBean productBean = productService.getById(courseDetailBean.getProductId());
		String productName;
		if (productBean == null) {
			productName = "";
		} else {
			productName = productBean.getProductName();
		}
		CourseDetailVo vo = _toVo(courseDetailBean, productName);
		return responseEntity(200, vo, "request.suc");
	}

	private CourseDetailBean _toBean(CourseDetailVo courseDetailVo) {
		return new CourseDetailBean(courseDetailVo.getId(), courseDetailVo.getVideoLink(), courseDetailVo.getProductId(), courseDetailVo.getCourseType(), courseDetailVo.getVideoNum(),courseDetailVo.getCoursePic(),courseDetailVo.getCourseComment(),courseDetailVo.getEnable(),courseDetailVo.getVideoName());
	}

	private CourseDetailVo _toVo(CourseDetailBean courseDetailBean, String productName) {
		return new CourseDetailVo(courseDetailBean.getId(), courseDetailBean.getVideoLink(), courseDetailBean.getProductId(), courseDetailBean.getCourseType(), courseDetailBean.getVideoNum(),courseDetailBean.getCoursePic(),courseDetailBean.getCourseComment(),courseDetailBean.getEnable(),courseDetailBean.getVideoName() ,productName);
	}
}
