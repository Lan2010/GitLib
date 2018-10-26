package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.ApplicationOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.ApplicationOperationStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
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
public class ApplicationOperationStatisticsController extends BaseController {

    @Autowired
    private ApplicationOperationStatisticsService applicationOperationStatisticsService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/application/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("statistics/application");
    }

    @RequestMapping(value = "/application/chart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity chart(@RequestParam("type") int type) {
        List<ApplicationOperationStatisticsMapping> applicationOperationStatisticsMappings = applicationOperationStatisticsService.list(type);
        //labels
        List<String> labels = new ArrayList<>();
        List<Integer> totalCountList = new ArrayList<>();
        List<Integer> diffIPCountList = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Date now = new Date();
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
        Integer maxDiffIPValue = Integer.MIN_VALUE;
        Integer maxValue = Integer.MIN_VALUE;
        for (ApplicationOperationStatisticsMapping applicationOperationStatisticsMapping : applicationOperationStatisticsMappings) {
            ApplicationDimensionMapper applicationDimensionMapper = applicationOperationStatisticsMapping.getApplicationDimensionMapper();
            labels.add(applicationDimensionMapper.getName());
            List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers = applicationOperationStatisticsMapping.getApplicationOperationStatisticsMappers();
            Integer appStartTotalCount = 0;
            Integer appDownTotalCount = 0;
            Integer appStartDiffIPTotalCount = 0;
            Integer appDownDiffIPTotalCount = 0;
            List<Integer> aosList = new ArrayList<>();
            List<Integer> aosDiffIPList = new ArrayList<>();
            for (ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper : applicationOperationStatisticsMappers) {

                String date = null;
                if (0 == type) {
                    date = applicationOperationStatisticsMapper.getStatisticsYear() + "-" + applicationOperationStatisticsMapper.getStatisticsMonth() + "-" + applicationOperationStatisticsMapper.getStatisticsDay() + " " + applicationOperationStatisticsMapper.getStatisticsHour();
                } else if (1 == type) {
                    //获取天
                    date = applicationOperationStatisticsMapper.getStatisticsYear() + "-" + applicationOperationStatisticsMapper.getStatisticsMonth() + "-" + applicationOperationStatisticsMapper.getStatisticsDay();
                } else if (2 == type) {
                    date = applicationOperationStatisticsMapper.getStatisticsYear() + "-" + applicationOperationStatisticsMapper.getStatisticsMonth();
                }
                if (!dates.contains(date)) {
                    dates.add(date);
                }

                appStartTotalCount += applicationOperationStatisticsMapper.getAppStartTotalCount();
                appDownTotalCount += applicationOperationStatisticsMapper.getAppDownTotalCount();
                appStartDiffIPTotalCount += applicationOperationStatisticsMapper.getAppStartDiffIPTotalCount();
                appDownDiffIPTotalCount += applicationOperationStatisticsMapper.getAppDownDiffIPTotalCount();
                aosList.add(applicationOperationStatisticsMapper.getAppStartTotalCount());
                aosDiffIPList.add(applicationOperationStatisticsMapper.getAppStartDiffIPTotalCount());
                if (maxDiffIPValue < applicationOperationStatisticsMapper.getAppStartDiffIPTotalCount()) {
                    maxDiffIPValue = applicationOperationStatisticsMapper.getAppStartDiffIPTotalCount();
                }
                if (maxValue < applicationOperationStatisticsMapper.getAppStartTotalCount()) {
                    maxValue = applicationOperationStatisticsMapper.getAppStartTotalCount();
                }
            }
            totalCountList.add(appStartTotalCount);
            diffIPCountList.add(appStartDiffIPTotalCount);
            chartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosList));
            chartLineDiffIPVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosDiffIPList));
        }
        List list = new ArrayList();
        list.add(labels);
        list.add(totalCountList);
        list.add(diffIPCountList);
        list.add(chartLineVos);
        list.add(chartLineDiffIPVos);
        list.add(maxValue);
        list.add(maxDiffIPValue);
        return responseEntity(200, dates, "request.suc", 0, list);
    }
}
