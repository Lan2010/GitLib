package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserGeneInterprService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserGeneInterprVo;
import com.tianzhixing.appmall.rpc.bean.UserGeneInterprBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/user/userGeneInterprBean")
public class UserGeneInterprController extends BaseController {

	@Autowired
	private UserGeneInterprService userGeneInterprBeanService;

	/**
	 * 首页
	 *
	 * @return
	 */
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index() {
		return configModelAndView("user/userGeneInterprBean");
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
	public ResponseEntity<UserGeneInterprVo> list(@RequestParam("pageNo") int pageNo, @RequestParam("pageSize") int pageSize, @ModelAttribute("userGeneInterprBeanVo") UserGeneInterprVo userGeneInterprBeanVo) {

		PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
		List<UserGeneInterprVo> userGeneInterprBeanVos = new ArrayList<>();
		long count = userGeneInterprBeanService.count(_toBean(userGeneInterprBeanVo));
		if (count > 0) {
			List<UserGeneInterprBean> UserGeneInterprBeans = userGeneInterprBeanService.list(pagerMapping, _toBean(userGeneInterprBeanVo));
			for (UserGeneInterprBean userGeneInterprBeanBean : UserGeneInterprBeans) {
				userGeneInterprBeanVos.add(_toVo(userGeneInterprBeanBean));
			}
		}
		return responseEntity(200, "request.suc", count, userGeneInterprBeanVos);
	}

	private UserGeneInterprBean _toBean(UserGeneInterprVo userGeneInterprBeanVo) {
		return new UserGeneInterprBean(userGeneInterprBeanVo.getId(),userGeneInterprBeanVo.getSampleTubeCode(),userGeneInterprBeanVo.getGeneChipFileDt(),userGeneInterprBeanVo.getGeneInterprFile(),userGeneInterprBeanVo.getGeneInterprJson(),userGeneInterprBeanVo.getGeneInterprCreateDt(),userGeneInterprBeanVo.getGeneInterprUpdateDt(),userGeneInterprBeanVo.getIsValidSample(),userGeneInterprBeanVo.getIsProcessing(),userGeneInterprBeanVo.getResultHtml(),userGeneInterprBeanVo.getResultPdf(),userGeneInterprBeanVo.getProductType());
	}

	private UserGeneInterprVo _toVo(UserGeneInterprBean userGeneInterprBeanBean) {
		return new UserGeneInterprVo(userGeneInterprBeanBean.getId(),userGeneInterprBeanBean.getSampleTubeCode(),userGeneInterprBeanBean.getGeneChipFileDt(),userGeneInterprBeanBean.getGeneInterprFile(),userGeneInterprBeanBean.getGeneInterprJson(),userGeneInterprBeanBean.getGeneInterprCreateDt(),userGeneInterprBeanBean.getGeneInterprUpdateDt(),userGeneInterprBeanBean.getIsValidSample(),userGeneInterprBeanBean.getIsProcessing(),userGeneInterprBeanBean.getResultHtml(),userGeneInterprBeanBean.getResultPdf(),userGeneInterprBeanBean.getProductType());
	}
}
