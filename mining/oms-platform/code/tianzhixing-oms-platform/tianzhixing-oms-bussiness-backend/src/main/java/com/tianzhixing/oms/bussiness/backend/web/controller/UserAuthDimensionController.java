package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserAuthDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserAuthDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;
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
public class UserAuthDimensionController extends BaseController {

    @Autowired
    private UserAuthDimensionService userAuthDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/userAuthDimension/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/userAuthDimension");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/userAuthDimension/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserAuthDimensionVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = userAuthDimensionService.count();
        List<UserAuthDimensionVo> userAuthDimensionVo = new ArrayList<>();
        if (count > 0) {
            List<UserAuthDimensionMapper> userAuthDimensionMappers = userAuthDimensionService.list(pagerMapping);
            for (UserAuthDimensionMapper userAuthDimensionMapper : userAuthDimensionMappers) {
            	userAuthDimensionVo.add(_toVo(userAuthDimensionMapper));
            }
        }
        return responseEntity(200, "request.suc", count, userAuthDimensionVo);
    }

    /**
     * 添加
     *
     * @param UserAuthDimensionVo
     * @return
     */
    @RequestMapping(value = "/userAuthDimension/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserAuthDimensionVo> add(@ModelAttribute("deviceDimensionVo") UserAuthDimensionVo deviceDimensionVo) {
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
        	UserAuthDimensionMapper userAuthDimensionMapper = userAuthDimensionService.add(_toMapper(deviceDimensionVo));
        	deviceDimensionVo.setId(userAuthDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, deviceDimensionVo, ex.getMessage());
        }
        return responseEntity(200, deviceDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param UserAuthDimensionVo
     * @return
     */
    @RequestMapping(value = "/userAuthDimension/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserAuthDimensionVo> update(@ModelAttribute("deviceDimensionVo") UserAuthDimensionVo deviceDimensionVo) {
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
        	userAuthDimensionService.update(_toMapper(deviceDimensionVo));
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
    @RequestMapping(value = "/userAuthDimension/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserAuthDimensionVo> detail(@RequestParam("id") long id) {
    	UserAuthDimensionMapper userAuthDimensionMapper = userAuthDimensionService.getById(id);
        if (userAuthDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        UserAuthDimensionVo vo = _toVo(userAuthDimensionMapper);
        return responseEntity(200, vo, "request.suc");
    }

    private UserAuthDimensionMapper _toMapper(UserAuthDimensionVo deviceDimensionVo) {
        return new UserAuthDimensionMapper(deviceDimensionVo.getId(),deviceDimensionVo.getVersion(),deviceDimensionVo.getCreateTime(),deviceDimensionVo.getName(),deviceDimensionVo.getValue(),deviceDimensionVo.getEnable());
    }

    private UserAuthDimensionVo _toVo(UserAuthDimensionMapper userAuthDimensionMapper) {
        return new UserAuthDimensionVo(userAuthDimensionMapper.getId(),userAuthDimensionMapper.getVersion(),userAuthDimensionMapper.getCreateTime(),userAuthDimensionMapper.getEnable(),userAuthDimensionMapper.getName(),userAuthDimensionMapper.getValue());
    }
}
