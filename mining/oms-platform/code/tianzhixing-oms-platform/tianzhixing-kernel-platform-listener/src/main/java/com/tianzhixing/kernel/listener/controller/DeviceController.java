package com.tianzhixing.kernel.listener.controller;

import com.tianzhixing.kernel.listener.entity.DeviceEntity;
import com.tianzhixing.kernel.listener.service.DeviceMacService;
import com.tianzhixing.oms.utils.JSONUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by routine.k on 2018/6/27.
 */
@Controller
@RequestMapping("/device")
public class DeviceController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DeviceMacService deviceMacService;

    @RequestMapping("/mac")
    @ResponseBody
    public String mac(@RequestBody DeviceEntity deviceEntity) {
        logger.info(JSONUtil.beanToJsonWithJackson(deviceEntity));
        deviceMacService.analyseMac(deviceEntity);
        return "0";
        /**
         *
         * {
         "devid":"device_a110",
         "devwifi":"00:E0:4C:3B:7D:2F",
         "devbt":"11:E0:4C:3B:7D:2F",
         "time":"2018-07-16 10:22:10",
         "gps":"113.959977,22.53462",
         "count":8
         }
         *
         *
         */
    }


}
