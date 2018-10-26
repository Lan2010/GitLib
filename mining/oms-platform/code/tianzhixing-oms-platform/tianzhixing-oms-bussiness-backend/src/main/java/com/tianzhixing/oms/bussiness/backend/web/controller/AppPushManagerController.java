package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.exception.RequestException;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagerMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.AppSuspendService;
import com.tianzhixing.oms.bussiness.backend.web.utils.RemoteServerPushUtil;
import com.tianzhixing.oms.bussiness.backend.web.vo.AdvertisementVo;
import com.tianzhixing.oms.bussiness.backend.web.vo.AppSuspendVo;
import com.tianzhixing.oms.bussiness.backend.web.vo.SystemParamVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppSuspendInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.system.SystemParamMapper;
import com.tianzhixing.oms.web.security.util.SecurityUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/app")
public class AppPushManagerController extends BaseController {

    @Autowired
    private AppSuspendService appSuspendService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/wksuspend/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("app/suspend_index");
    }

    /**
     * 列表
     *
     * @param pageNo
     * @param pageSize
     * @return
     */
    @RequestMapping(value = "/wksuspend/list", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity<AppSuspendVo> list(@RequestParam("pageNo") int pageNo,
                                             @RequestParam("pageSize") int pageSize) {

        PagerMapping pagerMapping = new PagerMapping(pageNo, pageSize);
        long count = appSuspendService.count();
        List<AppSuspendVo> appSuspendVos = new ArrayList<>();
        if (count > 0) {
            List<AppSuspendInfoMapper> appSuspendInfoMappers = appSuspendService.list(pagerMapping);
            for (AppSuspendInfoMapper appSuspendInfoMapper : appSuspendInfoMappers) {
                appSuspendVos.add(_toVo(appSuspendInfoMapper));
            }
        }
        return responseEntity(200, "app.suspend.list.suc", count, appSuspendVos);
    }

    /**
     * app悬浮入口添加
     *
     * @return
     */
    @RequestMapping(value = "/wksuspend/add", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity add(@ModelAttribute("appSuspendVo") AppSuspendVo appSuspendVo) {
        if (StringUtils.isEmpty(appSuspendVo.getPicUrl())) {
            return responseEntity(300, "upload.file.empty");
        }
        if (appSuspendVo.getBeginTime() == null) {
            return responseEntity(300, "begintime.empty");
        }
        if (appSuspendVo.getEndTime() == null) {
            return responseEntity(300, "endtime.empty");
        }
        appSuspendService.add(_toMapper(appSuspendVo));
        return responseEntity(200, "request.suc");
    }

    /**
     * app悬浮入口推送
     *
     * @return
     */
    @RequestMapping(value = "/wksuspend/push", method = RequestMethod.POST)
    @ResponseBody
    public ResponseEntity push(@RequestParam("id") long id) {
        try {
            appSuspendService.push(id, SecurityUtil.currentLogin().getAccount());
        } catch (IllegalArgumentException ex) {
            ex.printStackTrace();
            return responseEntity(301, ex.getMessage());
        } catch (RequestException ex) {
            ex.printStackTrace();
            return responseEntity(301, ex.getMessage());
        }
        return responseEntity(200, "request.suc");
    }

    private AppSuspendInfoMapper _toMapper(AppSuspendVo appSuspendVo) {
        return new AppSuspendInfoMapper(appSuspendVo.getId(), 0, new Date(), new Date(), SecurityUtil.currentLogin().getAccount(), SecurityUtil.currentLogin().getAccount(), appSuspendVo.getBeginTime(), appSuspendVo.getEndTime(), appSuspendVo.getPicUrl(), appSuspendVo.getPicLink(), false);
    }

    private AppSuspendVo _toVo(AppSuspendInfoMapper appSuspendInfoMapper) {
        return new AppSuspendVo(appSuspendInfoMapper.getId(), appSuspendInfoMapper.getCreateTime(), appSuspendInfoMapper.getBeginTime(), appSuspendInfoMapper.getEndTime(), appSuspendInfoMapper.getPicUrl(), appSuspendInfoMapper.getPicLink(), appSuspendInfoMapper.getIsSend());
    }
}
