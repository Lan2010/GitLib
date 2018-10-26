package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagesOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.PagesDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.PagesOperationStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.service.StatisticsDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesOperationStatisticsMapper;
import com.tianzhixing.oms.utils.CalendarUtil;
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
public class PagesOperationStatisticsController extends BaseController {

    @Autowired
    private PagesOperationStatisticsService pagesOperationStatisticsService;
    
    @Autowired
    private StatisticsDimensionService statisticsDimensionService;
    
    @Autowired
    private PagesDimensionService pagesDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/pages/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("statistics/pages");
    }

    @RequestMapping(value = "/pages/chart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity chart(@RequestParam("type") int type) {
        List<PagesOperationStatisticsMapping> pagesOperationStatisticsMappings = pagesOperationStatisticsService.list(type);
        //labels
        List<String> labels = new ArrayList<>();
        List<Integer> totalCountList = new ArrayList<>();
        List<Integer> diffIPCountList = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        if (0 == type) {
            //获取小时 24小时
            for (int i = 0; i < hour; i++) {
                dates.add(year + "-" + month + "-" + day + "-" + i);
            }
        } else if (1 == type) {
            //获取天
            for (int i = 1; i <= day; i++) {
                dates.add(year + "-" + month + "-" + i);
            }
        } else if (2 == type) {
            //获取月
            for (int i = 1; i <= month; i++) {
                dates.add(year + "-" + i);
            }
        }
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
        Integer maxDiffIPValue = Integer.MIN_VALUE;
        Integer maxValue = Integer.MIN_VALUE;
        for(PagesOperationStatisticsMapping pagesOperationStatisticsMapping : pagesOperationStatisticsMappings){
            ApplicationDimensionMapper applicationDimensionMapper = pagesOperationStatisticsMapping.getApplicationDimensionMapper();
            labels.add(applicationDimensionMapper.getName());
            List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = pagesOperationStatisticsMapping.getPagesOperationStatisticsMappers();
            Integer pagesOperationCount  = 0;
            Integer pagesOperationDiffIPCount = 0;
            List<Integer> aosList = new ArrayList<>();
            List<Integer> aosDiffIPList = new ArrayList<>();
            for (PagesOperationStatisticsMapper pagesOperationStatisticsMapper : pagesOperationStatisticsMappers) {
            	pagesOperationCount += pagesOperationStatisticsMapper.getPagesOperationCount();
            	pagesOperationDiffIPCount += pagesOperationStatisticsMapper.getPagesOperationDiffIPCount();
                aosList.add(pagesOperationStatisticsMapper.getPagesOperationCount());
                aosDiffIPList.add(pagesOperationStatisticsMapper.getPagesOperationDiffIPCount());
                if (maxDiffIPValue < pagesOperationStatisticsMapper.getPagesOperationDiffIPCount()) {
                    maxDiffIPValue = pagesOperationStatisticsMapper.getPagesOperationDiffIPCount();
                }
                if (maxValue < pagesOperationStatisticsMapper.getPagesOperationCount()) {
                    maxValue = pagesOperationStatisticsMapper.getPagesOperationCount();
                }
            }
            totalCountList.add(pagesOperationCount);
            diffIPCountList.add(pagesOperationDiffIPCount);
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
    
    @RequestMapping(value = "/pages/scatter", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity scatterChart(@RequestParam("type") int type) {
        List<String> dates = new ArrayList<>();
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        List<ChartLineVo> chartLineDiffIPVos = new ArrayList<>();
        Integer maxValue = 0;
        Integer maxDiffIPValue = 0;
        Map<Long, ApplicationDimensionMapper> applicationDimensionMapperMap = new HashMap<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = statisticsDimensionService.list(true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            applicationDimensionMapperMap.put(applicationDimensionMapper.getId(), applicationDimensionMapper);
        }
        List<PagesDimensionMapper> pagesDimensionMappers = pagesDimensionService.list(true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            //如果为平台，且存在子类，则跳出
            boolean breakFor = false;
            if (StatisticsDimension.PLATFORM.equals(applicationDimensionMapper.getStatisticsDimension())) {
                for (ApplicationDimensionMapper adm : applicationDimensionMappers) {
                    if (adm.getParentId() == applicationDimensionMapper.getId()) {
                        breakFor = true;
                        break;
                    }
                }
            }
            if (breakFor) continue;
            String appName = applicationDimensionMapper.getName();
            for (PagesDimensionMapper pagesDimensionMapper : pagesDimensionMappers) {
                ApplicationDimensionMapper applicationDimensionMapperParent = applicationDimensionMapper;
                if (StatisticsDimension.CLIENT.equals(applicationDimensionMapperParent.getStatisticsDimension())) {
                    applicationDimensionMapperParent = applicationDimensionMapperMap.get(applicationDimensionMapperParent.getParentId());
                    if (applicationDimensionMapperParent == null) {
                        applicationDimensionMapperParent = statisticsDimensionService.getById(applicationDimensionMapper.getParentId());
                        applicationDimensionMapperMap.put(applicationDimensionMapperParent.getId(), applicationDimensionMapperParent);
                    }
                    applicationDimensionMapper.setName(applicationDimensionMapperParent.getName() + "-" + appName);
                }
                String channelName = applicationDimensionMapper.getName() + "(" + pagesDimensionMapper.getName() + ")";
                String name = applicationDimensionMapper.getName() + "(" + pagesDimensionMapper.getName() + ")";
                List<PagesOperationStatisticsMapper> pagesOperationStatisticsMapperList = pagesOperationStatisticsService.listByPagesDimensionAndApplication(type, applicationDimensionMapperParent, StatisticsDimension.CLIENT.equals(applicationDimensionMapper.getStatisticsDimension()) ? applicationDimensionMapper : null, pagesDimensionMapper);
                List<Integer> aosList = new ArrayList<>();
                List<Integer> aosDiffIPList = new ArrayList<>();
                for (PagesOperationStatisticsMapper pagesOperationStatisticsMapper : pagesOperationStatisticsMapperList) {
                    //统计渠道
                    String date = null;
                    if (0 == type) {
                        date = pagesOperationStatisticsMapper.getStatisticsYear() + "-" + pagesOperationStatisticsMapper.getStatisticsMonth() + "-" + pagesOperationStatisticsMapper.getStatisticsDay() + " " + pagesOperationStatisticsMapper.getStatisticsHour();
                    } else if (1 == type) {
                        //获取天
                        date = pagesOperationStatisticsMapper.getStatisticsYear() + "-" + pagesOperationStatisticsMapper.getStatisticsMonth() + "-" + pagesOperationStatisticsMapper.getStatisticsDay();
                    } else if (2 == type) {
                        date = pagesOperationStatisticsMapper.getStatisticsYear() + "-" + pagesOperationStatisticsMapper.getStatisticsMonth();
                    }
                    if (!dates.contains(date)) {
                        dates.add(date);
                    }
                    aosList.add(pagesOperationStatisticsMapper.getPagesOperationCount());
                    aosDiffIPList.add(pagesOperationStatisticsMapper.getPagesOperationDiffIPCount());
                    if (maxValue < pagesOperationStatisticsMapper.getPagesOperationCount()) {
                    	maxValue = pagesOperationStatisticsMapper.getPagesOperationCount();
                    }
                    if (maxDiffIPValue < pagesOperationStatisticsMapper.getPagesOperationDiffIPCount()) {
                        maxDiffIPValue = pagesOperationStatisticsMapper.getPagesOperationDiffIPCount();
                    }
                }
                chartLineVos.add(new ChartLineVo(channelName, false, aosList));
                chartLineDiffIPVos.add(new ChartLineVo(name, false, aosDiffIPList));

            }
        }
        List list = new ArrayList();
        list.add(maxDiffIPValue);
        list.add(maxValue);
        list.add(chartLineVos);
        list.add(chartLineDiffIPVos);
        return responseEntity(200, dates, "request.suc", 0, list);
    }
}
