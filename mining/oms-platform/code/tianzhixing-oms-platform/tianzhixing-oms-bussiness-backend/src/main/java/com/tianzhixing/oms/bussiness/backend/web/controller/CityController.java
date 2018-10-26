package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.map.BaiduMapConfig;
import com.tianzhixing.oms.bussiness.backend.web.vo.CityVo;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/24.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/city")
public class CityController extends BaseController {

    /**
     * 消息列表
     *
     * @return
     */
    @RequestMapping(value = "/list/baidu", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<CityVo> list() {
        List<CityVo> vos = new ArrayList<CityVo>();
        Map<String, String> map = BaiduMapConfig.cities();
        for(String key : map.keySet()){
            CityVo vo = new CityVo();
            vo.setCode(key);
            vo.setName(map.get(key));
            vos.add(vo);
        }
        return responseEntity(200, "city.list.suc", vos.size(), vos);
    }
}
