package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.rpc.mapper.staff.StaffInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.staff.RPCStaffInfoService;
import com.tianzhixing.oms.utils.MD5Util;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * Index
 * Created by routine.k on 16/8/12.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/")
public class IndexController extends BaseController {

    @Autowired
    private RPCStaffInfoService rpcStaffInfoService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("index");
    }

    @RequestMapping(value = "/users/info", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity userInfo() {
        return responseEntity(200, "request.suc");
    }

    @RequestMapping(value = "/users/update/password", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity updatePassword(@RequestParam("oldPassword") String oldPassword, @RequestParam("newPassword") String newPassword, @RequestParam("enterPassword") String enterPassword) {
        StaffInfoMapper staffInfoMapper = rpcStaffInfoService.getByAccount(SecurityUtil.currentLogin().getAccount());
        if (!MD5Util.digest(oldPassword).equals(staffInfoMapper.getPassword())) {
            return responseEntity(301, "user.old.pwd.error");
        }
        if (StringUtils.isEmpty(newPassword) || StringUtils.isEmpty(enterPassword) || !enterPassword.equals(newPassword)) {
            return responseEntity(301, "user.pwd.error");
        }
        rpcStaffInfoService.updateUserPassword(staffInfoMapper.getId(), MD5Util.digest(newPassword));
        return responseEntity(200, "request.suc");
    }


}
