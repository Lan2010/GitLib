package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.SystemParamService;
import com.tianzhixing.oms.bussiness.backend.web.utils.RemoteServerPushUtil;
import com.tianzhixing.oms.bussiness.backend.web.vo.SystemParamVo;
import com.tianzhixing.oms.bussiness.backend.web.vo.TaskInfoVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.system.SystemParamMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskInfoMappper;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.StringUtil;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/25.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/system")
public class SystemController extends BaseController {

    @Autowired
    private SystemParamService systemParamService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("system/index");
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
    public ResponseEntity<SystemParamVo> list(@RequestParam("pageNo") int pageNo,
                                              @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = systemParamService.count();
        List<SystemParamVo> systemParamVos = new ArrayList<>();
        if (count > 0) {
            List<SystemParamMapper> systemParamMappers = systemParamService.list(pagerMapping);
            for (SystemParamMapper systemParamMapper : systemParamMappers) {
                systemParamVos.add(_toVo(systemParamMapper));
            }
        }
        return responseEntity(200, "system.list.suc", count, systemParamVos);
    }

    /**
     * 详情
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/detail", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<SystemParamVo> detail(@RequestParam("id") long id) {
        SystemParamMapper systemParamMapper = systemParamService.getById(id);
        if (systemParamMapper == null) {
            return responseEntity(301, "system.param.not.found");
        }
        SystemParamVo vo = _toVo(systemParamMapper);
        return responseEntity(200, vo, "request.suc");
    }

    /**
     * 更新
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity<SystemParamVo> update(@RequestParam("id") long id, @RequestParam("systemValue") String systemValue) {
        SystemParamMapper systemParamMapper = systemParamService.getById(id);
        if (systemParamMapper == null) {
            return responseEntity(301, "system.param.not.found");
        }
        if (StringUtils.isEmpty(systemValue)) {
            return responseEntity(301, "system.param.not.found");
        }
        if (Double.valueOf(systemValue) < 0) {
            return responseEntity(301, "system.param.error");
        }
        systemParamService.update(id, systemValue, SecurityUtil.currentLogin().getAccount());
        return responseEntity(200, "request.suc");
    }

    private SystemParamVo _toVo(SystemParamMapper systemParamMapper) {
        return new SystemParamVo(systemParamMapper.getId(), systemParamMapper.getCreateTime(), systemParamMapper.getUpdateTime(), systemParamMapper.getCreateUser(), systemParamMapper.getUpdateUser(), systemParamMapper.getSystemValue(), systemParamMapper.getSystemParamType().getValue());
    }


}
