package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.MallProductDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.MallProductDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallProductDimensionMapper;
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
public class MallProductDimensionController extends BaseController {

    @Autowired
    private MallProductDimensionService mallProductDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/mallProduct/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/mallProduct");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/mallProduct/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MallProductDimensionVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = mallProductDimensionService.count();
        List<MallProductDimensionVo> mallProductDimensionVos = new ArrayList<>();
        if (count > 0) {
            List<MallProductDimensionMapper> mallProductDimensionMappers = mallProductDimensionService.list(pagerMapping);
            for (MallProductDimensionMapper mallProductDimensionMapper : mallProductDimensionMappers) {
            	mallProductDimensionVos.add(_toVo(mallProductDimensionMapper));
            }
        }
        return responseEntity(200, "request.suc", count, mallProductDimensionVos);
    }

    /**
     * 添加
     *
     * @param MallProductDimensionVo
     * @return
     */
    @RequestMapping(value = "/mallProduct/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MallProductDimensionVo> add(@ModelAttribute("mallProductDimensionVo") MallProductDimensionVo mallProductDimensionVo) {
        if (StringUtils.isEmpty(mallProductDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(mallProductDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
       
        try {
        	mallProductDimensionVo.setEnable(true);
        	mallProductDimensionVo.setCreateTime(new Date());
        	mallProductDimensionVo.setVersion(0);
        	MallProductDimensionMapper mallProductDimensionMapper = mallProductDimensionService.add(_toMapper(mallProductDimensionVo));
        	mallProductDimensionVo.setId(mallProductDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, mallProductDimensionVo, ex.getMessage());
        }
        return responseEntity(200, mallProductDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param MallProductDimensionVo
     * @return
     */
    @RequestMapping(value = "/mallProduct/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<MallProductDimensionVo> update(@ModelAttribute("mallProductDimensionVo") MallProductDimensionVo mallProductDimensionVo) {
        if (mallProductDimensionVo.getId() == null || mallProductDimensionVo.getId() == 0) {
            return responseEntity(301, "dimension.not.found");
        }
        if (StringUtils.isEmpty(mallProductDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        } 
        
        if (StringUtils.isEmpty(mallProductDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
        if (StringUtils.isEmpty(mallProductDimensionVo.getEnable())) {
            return responseEntity(301, "dimension.enable.empty");
        }
        try {
        	mallProductDimensionService.update(_toMapper(mallProductDimensionVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, mallProductDimensionVo, ex.getMessage());
        }
        return responseEntity(200, mallProductDimensionVo, "request.suc");
    }
    
    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/mallProduct/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<MallProductDimensionVo> detail(@RequestParam("id") long id) {
    	MallProductDimensionMapper mallProductDimensionMapper = mallProductDimensionService.getById(id);
        if (mallProductDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        MallProductDimensionVo vo = _toVo(mallProductDimensionMapper);
        return responseEntity(200, vo, "request.suc");
    }

    private MallProductDimensionMapper _toMapper(MallProductDimensionVo mallProductDimensionVo) {
        return new MallProductDimensionMapper(mallProductDimensionVo.getId(),mallProductDimensionVo.getVersion(),mallProductDimensionVo.getCreateTime(),mallProductDimensionVo.getName(),mallProductDimensionVo.getValue(),mallProductDimensionVo.getEnable());
    }

    private MallProductDimensionVo _toVo(MallProductDimensionMapper mallProductDimensionMapper) {
        return new MallProductDimensionVo(mallProductDimensionMapper.getId(),mallProductDimensionMapper.getVersion(),mallProductDimensionMapper.getCreateTime(),mallProductDimensionMapper.getEnable(),mallProductDimensionMapper.getName(),mallProductDimensionMapper.getValue());
    }
}
