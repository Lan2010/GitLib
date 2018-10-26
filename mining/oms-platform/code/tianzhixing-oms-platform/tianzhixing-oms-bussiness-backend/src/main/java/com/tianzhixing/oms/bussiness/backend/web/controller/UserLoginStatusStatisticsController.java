package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserLoginStatusStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.UserLoginStatusStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserLoginStatusStatisticsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/statistics")
public class UserLoginStatusStatisticsController extends BaseController {

    @Autowired
    private UserLoginStatusStatisticsService userLoginStatusStatisticsService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/userLoginStatus/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("statistics/userLoginStatus");
    }

    @RequestMapping(value = "/userLoginStatus/chart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity chart(@RequestParam("type") int type) {
        List<UserLoginStatusStatisticsMapping> userLoginStatusStatisticsMappings = userLoginStatusStatisticsService.list(type);
        //labels
        List<String> labels = new ArrayList<>();
        List<Integer> totalCountList = new ArrayList<>();
        List<Integer> diffIPCountList = new ArrayList<>();
        List<Integer> onlineTotalCountList = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Date now = new Date();
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
        List<ChartLineVo> userOnlineCountChartLineVos = new ArrayList<>();
        Integer maxDiffIPValue = Integer.MIN_VALUE;
        Integer maxValue = Integer.MIN_VALUE;
        Integer maxOnlineValue = Integer.MIN_VALUE;
        for (UserLoginStatusStatisticsMapping userLoginStatusStatisticsMapping : userLoginStatusStatisticsMappings) {
            ApplicationDimensionMapper applicationDimensionMapper = userLoginStatusStatisticsMapping.getApplicationDimensionMapper();
            labels.add(applicationDimensionMapper.getName());
            List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers = userLoginStatusStatisticsMapping.getUserLoginStatusStatisticsMappers();
            Integer userLoginCount = 0;
            Integer onlineTotalCount = 0;
            Integer userLoginDiffAccountCount = 0;
            List<Integer> aosList = new ArrayList<>();
            List<Integer> aosDiffIPList = new ArrayList<>();
            List<Integer> userOnlineList = new ArrayList<>();
            for (UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper : userLoginStatusStatisticsMappers) {

                String date = null;
                if (0 == type) {
                    date = userLoginStatusStatisticsMapper.getStatisticsYear() + "-" + userLoginStatusStatisticsMapper.getStatisticsMonth() + "-" + userLoginStatusStatisticsMapper.getStatisticsDay() + " " + userLoginStatusStatisticsMapper.getStatisticsHour();
                } else if (1 == type) {
                    //获取天
                    date = userLoginStatusStatisticsMapper.getStatisticsYear() + "-" + userLoginStatusStatisticsMapper.getStatisticsMonth() + "-" + userLoginStatusStatisticsMapper.getStatisticsDay();
                } else if (2 == type) {
                    date = userLoginStatusStatisticsMapper.getStatisticsYear() + "-" + userLoginStatusStatisticsMapper.getStatisticsMonth();
                }
                if (!dates.contains(date)) {
                    dates.add(date);
                }

                userLoginCount += userLoginStatusStatisticsMapper.getUserLoginCount();
                onlineTotalCount += userLoginStatusStatisticsMapper.getUserOnlineCount();
                userLoginDiffAccountCount += userLoginStatusStatisticsMapper.getUserLoginDiffAccountCount();
                aosList.add(userLoginStatusStatisticsMapper.getUserLoginCount());
                aosDiffIPList.add(userLoginStatusStatisticsMapper.getUserLoginDiffAccountCount());
                userOnlineList.add(userLoginStatusStatisticsMapper.getUserOnlineCount());
                if (maxDiffIPValue < userLoginStatusStatisticsMapper.getUserLoginCount()) {
                    maxDiffIPValue = userLoginStatusStatisticsMapper.getUserLoginCount();
                }
                if (maxValue < userLoginStatusStatisticsMapper.getUserLoginDiffAccountCount()) {
                    maxValue = userLoginStatusStatisticsMapper.getUserLoginDiffAccountCount();
                }
                if (maxOnlineValue < userLoginStatusStatisticsMapper.getUserOnlineCount()) {
                	maxOnlineValue = userLoginStatusStatisticsMapper.getUserOnlineCount();
                }
            }
            totalCountList.add(userLoginCount);
            diffIPCountList.add(userLoginDiffAccountCount);
            onlineTotalCountList.add(onlineTotalCount);
            chartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosList));
            chartLineDiffIPVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosDiffIPList));
            userOnlineCountChartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, userOnlineList));
        }
        List list = new ArrayList();
        list.add(labels);
        list.add(totalCountList);
        list.add(diffIPCountList);
        list.add(onlineTotalCountList);
        list.add(chartLineVos);
        list.add(chartLineDiffIPVos);
        list.add(userOnlineCountChartLineVos);
        list.add(maxValue);
        list.add(maxDiffIPValue);
        list.add(maxOnlineValue);
        return responseEntity(200, dates, "request.suc", 0, list);
    }
}
