package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.DeviceDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.DeviceDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
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
public class DeviceDimensionController extends BaseController {

    @Autowired
    private DeviceDimensionService deviceDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/deviceDimension/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/deviceDimension");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/deviceDimension/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DeviceDimensionVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = deviceDimensionService.count();
        List<DeviceDimensionVo> deviceDimensionVos = new ArrayList<>();
        if (count > 0) {
            List<DeviceDimensionMapper> deviceDimensionMappers = deviceDimensionService.list(pagerMapping);
            for (DeviceDimensionMapper deviceDimensionMapper : deviceDimensionMappers) {
            	deviceDimensionVos.add(_toVo(deviceDimensionMapper));
            }
        }
        return responseEntity(200, "request.suc", count, deviceDimensionVos);
    }

    /**
     * 添加
     *
     * @param DeviceDimensionVo
     * @return
     */
    @RequestMapping(value = "/deviceDimension/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeviceDimensionVo> add(@ModelAttribute("deviceDimensionVo") DeviceDimensionVo deviceDimensionVo) {
        if (StringUtils.isEmpty(deviceDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(deviceDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
       
        try {
        	deviceDimensionVo.setEnable(true);
        	deviceDimensionVo.setCreateTime(new Date());
        	deviceDimensionVo.setVersion(0);
        	DeviceDimensionMapper deviceDimensionMapper = deviceDimensionService.add(_toMapper(deviceDimensionVo));
        	deviceDimensionVo.setId(deviceDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, deviceDimensionVo, ex.getMessage());
        }
        return responseEntity(200, deviceDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param DeviceDimensionVo
     * @return
     */
    @RequestMapping(value = "/deviceDimension/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<DeviceDimensionVo> update(@ModelAttribute("deviceDimensionVo") DeviceDimensionVo deviceDimensionVo) {
        if (deviceDimensionVo.getId() == null || deviceDimensionVo.getId() == 0) {
            return responseEntity(301, "dimension.not.found");
        }
        if (StringUtils.isEmpty(deviceDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        } 
        
        if (StringUtils.isEmpty(deviceDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
        if (StringUtils.isEmpty(deviceDimensionVo.getEnable())) {
            return responseEntity(301, "dimension.enable.empty");
        }
        try {
        	deviceDimensionService.update(_toMapper(deviceDimensionVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, deviceDimensionVo, ex.getMessage());
        }
        return responseEntity(200, deviceDimensionVo, "request.suc");
    }
    
    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/deviceDimension/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DeviceDimensionVo> detail(@RequestParam("id") long id) {
    	DeviceDimensionMapper deviceDimensionMapper = deviceDimensionService.getById(id);
        if (deviceDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        DeviceDimensionVo vo = _toVo(deviceDimensionMapper);
        return responseEntity(200, vo, "request.suc");
    }

    private DeviceDimensionMapper _toMapper(DeviceDimensionVo deviceDimensionVo) {
        return new DeviceDimensionMapper(deviceDimensionVo.getId(),deviceDimensionVo.getVersion(),deviceDimensionVo.getCreateTime(),deviceDimensionVo.getName(),deviceDimensionVo.getValue(),deviceDimensionVo.getEnable());
    }

    private DeviceDimensionVo _toVo(DeviceDimensionMapper deviceDimensionMapper) {
        return new DeviceDimensionVo(deviceDimensionMapper.getId(),deviceDimensionMapper.getVersion(),deviceDimensionMapper.getCreateTime(),deviceDimensionMapper.getEnable(),deviceDimensionMapper.getName(),deviceDimensionMapper.getValue());
    }
}
