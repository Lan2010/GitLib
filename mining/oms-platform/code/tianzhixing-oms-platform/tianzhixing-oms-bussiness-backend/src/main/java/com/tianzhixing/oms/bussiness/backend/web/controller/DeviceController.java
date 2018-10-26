package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.vo.DevicePointVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/6/23.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/device")
public class DeviceController extends BaseController {

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/map", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("device/map");
    }

    /**
     * 加载资源菜单
     *
     * @return
     */
    @RequestMapping(value = "/map/point", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<DevicePointVo> list() {
        List<DevicePointVo> list = new ArrayList<DevicePointVo>();
        list.add(new DevicePointVo("113.960226", "22.53407"));
        list.add(new DevicePointVo("114.028865", "22.540638"));
        list.add(new DevicePointVo("114.055074", "22.548041"));
        list.add(new DevicePointVo("113.954728", "22.535848"));
        return responseEntity(200, "device.map.point.list.suc", list.size(), list);
    }
}
