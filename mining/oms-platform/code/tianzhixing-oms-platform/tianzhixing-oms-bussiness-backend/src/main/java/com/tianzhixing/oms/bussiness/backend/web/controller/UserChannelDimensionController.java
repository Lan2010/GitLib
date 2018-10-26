package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserChannelDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.UserChannelDimensionVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;

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
public class UserChannelDimensionController extends BaseController {

    @Autowired
    private UserChannelDimensionService userChannelDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/userChannelDimension/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("dimension/userChannelDimension");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/userChannelDimension/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserChannelDimensionVo> list(@RequestParam("pageNo") int pageNo,
                                                @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = userChannelDimensionService.count();
        List<UserChannelDimensionVo> userChannelDimensionVos = new ArrayList<>();
        if (count > 0) {
            List<UserChannelDimensionMapper> userChannelDimensionMappers = userChannelDimensionService.list(pagerMapping);
            for (UserChannelDimensionMapper userChannelDimensionMapper : userChannelDimensionMappers) {
            	userChannelDimensionVos.add(_toVo(userChannelDimensionMapper));
            }
        }
        return responseEntity(200, "request.suc", count, userChannelDimensionVos);
    }

    /**
     * 添加
     *
     * @param UserChannelDimensionVo
     * @return
     */
    @RequestMapping(value = "/userChannelDimension/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserChannelDimensionVo> add(@ModelAttribute("userChannelDimensionVo") UserChannelDimensionVo userChannelDimensionVo) {
        if (StringUtils.isEmpty(userChannelDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        }
        if (StringUtils.isEmpty(userChannelDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
       
        try {
        	userChannelDimensionVo.setEnable(true);
        	userChannelDimensionVo.setCreateTime(new Date());
        	userChannelDimensionVo.setVersion(0);
        	UserChannelDimensionMapper userChannelDimensionMapper = userChannelDimensionService.add(_toMapper(userChannelDimensionVo));
        	userChannelDimensionVo.setId(userChannelDimensionMapper.getId());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, userChannelDimensionVo, ex.getMessage());
        }
        return responseEntity(200, userChannelDimensionVo, "request.suc");
    }

    /**
     * update
     *
     * @param UserChannelDimensionVo
     * @return
     */
    @RequestMapping(value = "/userChannelDimension/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<UserChannelDimensionVo> update(@ModelAttribute("userChannelDimensionVo") UserChannelDimensionVo userChannelDimensionVo) {
        if (userChannelDimensionVo.getId() == null || userChannelDimensionVo.getId() == 0) {
            return responseEntity(301, "dimension.not.found");
        }
        if (StringUtils.isEmpty(userChannelDimensionVo.getName())) {
            return responseEntity(301, "dimension.name.empty");
        } 
        
        if (StringUtils.isEmpty(userChannelDimensionVo.getValue())) {
            return responseEntity(301, "dimension.value.empty");
        }
        if (StringUtils.isEmpty(userChannelDimensionVo.getEnable())) {
            return responseEntity(301, "dimension.enable.empty");
        }
        try {
        	userChannelDimensionService.update(_toMapper(userChannelDimensionVo));
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, userChannelDimensionVo, ex.getMessage());
        }
        return responseEntity(200, userChannelDimensionVo, "request.suc");
    }
    
    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/userChannelDimension/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<UserChannelDimensionVo> detail(@RequestParam("id") long id) {
    	UserChannelDimensionMapper userChannelDimensionMapper = userChannelDimensionService.getById(id);
        if (userChannelDimensionMapper == null) {
            return responseEntity(301, "dimension.not.found");
        }
        UserChannelDimensionVo vo = _toVo(userChannelDimensionMapper);
        return responseEntity(200, vo, "request.suc");
    }

    private UserChannelDimensionMapper _toMapper(UserChannelDimensionVo userChannelDimensionVo) {
        return new UserChannelDimensionMapper(userChannelDimensionVo.getId(),userChannelDimensionVo.getVersion(),userChannelDimensionVo.getCreateTime(),userChannelDimensionVo.getName(),userChannelDimensionVo.getValue(),userChannelDimensionVo.getEnable());
    }

    private UserChannelDimensionVo _toVo(UserChannelDimensionMapper userChannelDimensionMapper) {
        return new UserChannelDimensionVo(userChannelDimensionMapper.getId(),userChannelDimensionMapper.getVersion(),userChannelDimensionMapper.getCreateTime(),userChannelDimensionMapper.getEnable(),userChannelDimensionMapper.getName(),userChannelDimensionMapper.getValue());
    }
}
