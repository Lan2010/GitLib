package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.StatisticsDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ApplicationDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
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
@RequestMapping(value = "/dimension")
public class StatisticsDimensionController extends BaseController {

    @Autowired
    private StatisticsDimensionService dimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/applicationDimension/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/applicationDimension");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/applicationDimension/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ApplicationDimensionVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = dimensionService.count();
        List<ApplicationDimensionVo> applicationDimensionVos = new ArrayList<>();
        if (count > 0) {
            List<ApplicationDimensionMapper> applicationDimensionMappers = dimensionService.list(pagerMapping);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            	ApplicationDimensionMapper ad = dimensionService.getById(applicationDimensionMapper.getParentId());
            	String parentName;
            	if(ad==null){
            		parentName = "";
            	}else{
            		parentName = ad.getName();
            	}
            	applicationDimensionVos.add(_toVo(applicationDimensionMapper,parentName));
            }
        }
        return responseEntity(200, "request.suc", count, applicationDimensionVos);
    }

    /**
     * 添加
     *
     * @param ApplicationDimensionVo
     * @return
     */
    @RequestMapping(value = "/applicationDimension/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApplicationDimensionVo> add(@ModelAttribute("applicationDimensionVo") ApplicationDimensionVo applicationDimensionVo) {
        if (StringUtils.isEmpty(applicationDimensionVo.getStatisticsDimension())) {
            return responseEntity(301, "dimension.statisticsDimension.empty");
        }
        if (StringUtils.isEmpty(applicationDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(applicationDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
       
        try {
        	applicationDimensionVo.setEnable(true);
        	applicationDimensionVo.setCreateTime(new Date());
        	applicationDimensionVo.setVersion(0);
        	ApplicationDimensionMapper applicationDimensionMapper = dimensionService.add(_toMapper(applicationDimensionVo));
        	applicationDimensionVo.setId(applicationDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, applicationDimensionVo, ex.getMessage());
        }
        return responseEntity(200, applicationDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param ApplicationDimensionVo
     * @return
     */
    @RequestMapping(value = "/applicationDimension/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<ApplicationDimensionVo> update(@ModelAttribute("applicationDimensionVo") ApplicationDimensionVo applicationDimensionVo) {
        if (applicationDimensionVo.getId() == null || applicationDimensionVo.getId() == 0) {
            return responseEntity(301, "dimension.not.found");
        }
        if (StringUtils.isEmpty(applicationDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        } 
        
        if (StringUtils.isEmpty(applicationDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
        if (StringUtils.isEmpty(applicationDimensionVo.getEnable())) {
            return responseEntity(301, "dimension.enable.empty");
        }
        if (StringUtils.isEmpty(applicationDimensionVo.getStatisticsDimension())) {
            return responseEntity(301, "dimension.statisticsDimension.empty");
        }
        try {
        	dimensionService.update(_toMapper(applicationDimensionVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, applicationDimensionVo, ex.getMessage());
        }
        return responseEntity(200, applicationDimensionVo, "request.suc");
    }
    
    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/applicationDimension/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<ApplicationDimensionVo> detail(@RequestParam("id") long id) {
    	ApplicationDimensionMapper applicationDimensionMapper = dimensionService.getById(id);
        if (applicationDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        ApplicationDimensionMapper ad = dimensionService.getById(applicationDimensionMapper.getParentId());
    	String parentName;
    	if(ad==null){
    		parentName = applicationDimensionMapper.getName();
    	}else{
    		parentName = ad.getName();
    	}
        ApplicationDimensionVo vo = _toVo(applicationDimensionMapper,parentName);
        for (StatisticsDimension statisticsDimension : StatisticsDimension.values()) {
            if (statisticsDimension.getValue().equals(vo.getStatisticsDimension())) {
                vo.setStatisticsDimension(statisticsDimension.name());
            }
        }
        return responseEntity(200, vo, "request.suc");
    }

    private ApplicationDimensionMapper _toMapper(ApplicationDimensionVo applicationDimensionVo) {
        return new ApplicationDimensionMapper(applicationDimensionVo.getId(),applicationDimensionVo.getVersion(),applicationDimensionVo.getCreateTime(),StatisticsDimension.valueOf(applicationDimensionVo.getStatisticsDimension()),applicationDimensionVo.getParentId(),applicationDimensionVo.getEnable(),applicationDimensionVo.getName(),applicationDimensionVo.getValue());
    }

    private ApplicationDimensionVo _toVo(ApplicationDimensionMapper applicationDimensionMapper,String parentName) {
        return new ApplicationDimensionVo(applicationDimensionMapper.getId(),applicationDimensionMapper.getVersion(),applicationDimensionMapper.getCreateTime(),applicationDimensionMapper.getStatisticsDimension().getValue(),parentName,applicationDimensionMapper.getEnable(),applicationDimensionMapper.getName(),applicationDimensionMapper.getValue());
    }
}
