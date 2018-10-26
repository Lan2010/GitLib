package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.StarPointConsumeDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.StarPointConsumeDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.StarPointConsumeDimensionMapper;

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
@RequestMapping(value = "/dimension/starPointConsumeDimension")
public class StarPointConsumeDimensionController extends BaseController {

    @Autowired
    private StarPointConsumeDimensionService starPointConsumeDimensionService;

    /**
     * 首页
     * 
     * 
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/starPointConsumeDimension");
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
    public ResponseEntity<StarPointConsumeDimensionVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = starPointConsumeDimensionService.count();
        List<StarPointConsumeDimensionVo> starPointConsumeDimensionVos = new ArrayList<>();
        if (count > 0) {
            List<StarPointConsumeDimensionMapper> starPointConsumeDimensionMappers = starPointConsumeDimensionService.list(pagerMapping);
            for (StarPointConsumeDimensionMapper starPointConsumeDimensionMapper : starPointConsumeDimensionMappers) {
            	starPointConsumeDimensionVos.add(_toVo(starPointConsumeDimensionMapper));
            }
        }
        return responseEntity(200, "request.suc", count, starPointConsumeDimensionVos);
    }

    /**
     * 添加
     *
     * @param StarPointConsumeDimensionVo
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StarPointConsumeDimensionVo> add(@ModelAttribute("starPointConsumeDimensionVo") StarPointConsumeDimensionVo starPointConsumeDimensionVo) {
        if (StringUtils.isEmpty(starPointConsumeDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(starPointConsumeDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
       
        try {
        	starPointConsumeDimensionVo.setEnable(true);
        	starPointConsumeDimensionVo.setCreateTime(new Date());
        	starPointConsumeDimensionVo.setVersion(0);
        	StarPointConsumeDimensionMapper starPointConsumeDimensionMapper = starPointConsumeDimensionService.add(_toMapper(starPointConsumeDimensionVo));
        	starPointConsumeDimensionVo.setId(starPointConsumeDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, starPointConsumeDimensionVo, ex.getMessage());
        }
        return responseEntity(200, starPointConsumeDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param StarPointConsumeDimensionVo
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<StarPointConsumeDimensionVo> update(@ModelAttribute("starPointConsumeDimensionVo") StarPointConsumeDimensionVo starPointConsumeDimensionVo) {
        if (starPointConsumeDimensionVo.getId() == null || starPointConsumeDimensionVo.getId() == 0) {
            return responseEntity(301, "dimension.not.found");
        }
        if (StringUtils.isEmpty(starPointConsumeDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        } 
        
        if (StringUtils.isEmpty(starPointConsumeDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
        if (StringUtils.isEmpty(starPointConsumeDimensionVo.getEnable())) {
            return responseEntity(301, "dimension.enable.empty");
        }
        try {
        	starPointConsumeDimensionService.update(_toMapper(starPointConsumeDimensionVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, starPointConsumeDimensionVo, ex.getMessage());
        }
        return responseEntity(200, starPointConsumeDimensionVo, "request.suc");
    }
    
    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<StarPointConsumeDimensionVo> detail(@RequestParam("id") long id) {
    	StarPointConsumeDimensionMapper starPointConsumeDimensionMapper = starPointConsumeDimensionService.getById(id);
        if (starPointConsumeDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        StarPointConsumeDimensionVo vo = _toVo(starPointConsumeDimensionMapper);
        return responseEntity(200, vo, "request.suc");
    }

    private StarPointConsumeDimensionMapper _toMapper(StarPointConsumeDimensionVo starPointConsumeDimensionVo) {
        return new StarPointConsumeDimensionMapper(starPointConsumeDimensionVo.getId(),starPointConsumeDimensionVo.getVersion(),starPointConsumeDimensionVo.getCreateTime(),starPointConsumeDimensionVo.getName(),starPointConsumeDimensionVo.getValue(),starPointConsumeDimensionVo.getEnable());
    }

    private StarPointConsumeDimensionVo _toVo(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper) {
        return new StarPointConsumeDimensionVo(starPointConsumeDimensionMapper.getId(),starPointConsumeDimensionMapper.getVersion(),starPointConsumeDimensionMapper.getCreateTime(),starPointConsumeDimensionMapper.getEnable(),starPointConsumeDimensionMapper.getName(),starPointConsumeDimensionMapper.getValue());
    }
}
