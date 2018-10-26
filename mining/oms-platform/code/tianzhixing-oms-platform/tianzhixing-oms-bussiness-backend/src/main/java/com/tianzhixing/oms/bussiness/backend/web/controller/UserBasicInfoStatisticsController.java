package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.CalculateObject;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserBasicStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.StatisticsDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserBasicInfoStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.service.UserChannelDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.backend.web.vo.ScatterLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserBasicStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;
import org.apache.commons.lang3.StringUtils;
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
 * Created by routine.k on 2018/7/16.
 */
@Controller
@Scope(RequestConf.REQUEST_SCOPE_PROTOTYPE)
@RequestMapping(value = "/statistics")
public class UserBasicInfoStatisticsController extends BaseController {

    @Autowired
    private UserBasicInfoStatisticsService userBasicInfoStatisticsService;

    @Autowired
    private UserChannelDimensionService userChannelDimensionService;

    @Autowired
    private StatisticsDimensionService statisticsDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/userinfo/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("statistics/userinfo");
    }

    @RequestMapping(value = "/userinfo/chart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity chart(@RequestParam("type") int type) {
        //labels
        List<String> labels = new ArrayList<>();
        List<Integer> totalCountList = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Integer maxValue = Integer.MIN_VALUE;
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        List<UserBasicStatisticsMapping> userBasicStatisticsMappings = userBasicInfoStatisticsService.list(type);
        for (UserBasicStatisticsMapping userBasicStatisticsMapping : userBasicStatisticsMappings) {
            ApplicationDimensionMapper applicationDimensionMapper = userBasicStatisticsMapping.getApplicationDimensionMapper();
            labels.add(applicationDimensionMapper.getName());
            List<UserBasicStatisticsMapper> userBasicStatisticsMappers = userBasicStatisticsMapping.getUserBasicStatisticsMappers();
            Integer userCount = 0;
            List<Integer> aosList = new ArrayList<>();
            for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMappers) {
                //userBasicStatisticsMapper.setUserFromTypeName(applicationDimensionMapper.getName() + "(" + userBasicStatisticsMapper.getUserFromTypeName() + ")");
                //统计总数
                userCount += userBasicStatisticsMapper.getUserCount();
                aosList.add(userBasicStatisticsMapper.getUserCount());
                if (maxValue < userBasicStatisticsMapper.getUserCount()) {
                    maxValue = userBasicStatisticsMapper.getUserCount();
                }
                //统计渠道
                String date = null;
                if (0 == type) {
                    date = userBasicStatisticsMapper.getStatisticsYear() + "-" + userBasicStatisticsMapper.getStatisticsMonth() + "-" + userBasicStatisticsMapper.getStatisticsDay() + " " + userBasicStatisticsMapper.getStatisticsHour();
                } else if (1 == type) {
                    //获取天
                    date = userBasicStatisticsMapper.getStatisticsYear() + "-" + userBasicStatisticsMapper.getStatisticsMonth() + "-" + userBasicStatisticsMapper.getStatisticsDay();
                } else if (2 == type) {
                    date = userBasicStatisticsMapper.getStatisticsYear() + "-" + userBasicStatisticsMapper.getStatisticsMonth();
                }
                if (!dates.contains(date)) {
                    dates.add(date);
                }
            }
            totalCountList.add(userCount);
            chartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosList));
        }
        //统计渠道数据
        //渠道清单
        Map<String, List<UserBasicStatisticsMapper>> map = new HashMap<>();
        Integer maxUserCountValue = Integer.MIN_VALUE;
        List<String> userFromTypes = new ArrayList<>();
        List<UserChannelDimensionMapper> userChannelDimensionMappers = userChannelDimensionService.list(true);
        //渠道数据统计
        List<Integer> userFromTypesCounts = new ArrayList<>();
        List<ChartLineVo> chartLineChanelVos = new ArrayList<>();
        for (UserChannelDimensionMapper channelDimensionMapper : userChannelDimensionMappers) {
            String channelName = channelDimensionMapper.getName();
            if (!userFromTypes.contains(channelName)) {
                userFromTypes.add(channelName);
            }
            List<UserBasicStatisticsMapper> userBasicStatisticsMappers = userBasicInfoStatisticsService.listByUserChannel(type, channelDimensionMapper.getValue(), channelDimensionMapper.getName());
            map.put(channelDimensionMapper.getValue(), userBasicStatisticsMappers);
            Integer userFromTypeCount = 0;
            List<Integer> aosList = new ArrayList<>();
            for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMappers) {
                userFromTypeCount += userBasicStatisticsMapper.getUserCount();
                aosList.add(userBasicStatisticsMapper.getUserCount());
                if (maxUserCountValue < userBasicStatisticsMapper.getUserCount()) {
                    maxUserCountValue = userBasicStatisticsMapper.getUserCount();
                }
            }
            userFromTypesCounts.add(userFromTypeCount);
            chartLineChanelVos.add(new ChartLineVo(channelName, true, aosList));
        }


        List list = new ArrayList();
        list.add(labels);
        list.add(totalCountList);
        list.add(maxValue);
        list.add(chartLineVos);

        list.add(userFromTypes);
        list.add(userFromTypesCounts);
        list.add(maxUserCountValue);
        list.add(chartLineChanelVos);

        return responseEntity(200, dates, "request.suc", 0, list);
    }

    @RequestMapping(value = "/userinfo/scatter/chart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity scatterChart(@RequestParam("type") int type) {
        List<String> dates = new ArrayList<>();
        //组装散点图
        List<String> userFromTypesScatter = new ArrayList<>();
        //渠道数据统计
        List<ChartLineVo> userScatterChartLineChanelVos = new ArrayList<>();
        Integer maxUserScatterCount = 0;
        Map<Long, ApplicationDimensionMapper> applicationDimensionMapperMap = new HashMap<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = statisticsDimensionService.list(true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            applicationDimensionMapperMap.put(applicationDimensionMapper.getId(), applicationDimensionMapper);
        }
        List<UserChannelDimensionMapper> userChannelDimensionMappers = userChannelDimensionService.list(true);
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
            for (UserChannelDimensionMapper channelDimensionMapper : userChannelDimensionMappers) {
                ApplicationDimensionMapper applicationDimensionMapperParent = applicationDimensionMapper;
                if (StatisticsDimension.CLIENT.equals(applicationDimensionMapperParent.getStatisticsDimension())) {
                    applicationDimensionMapperParent = applicationDimensionMapperMap.get(applicationDimensionMapperParent.getParentId());
                    if (applicationDimensionMapperParent == null) {
                        applicationDimensionMapperParent = statisticsDimensionService.getById(applicationDimensionMapper.getParentId());
                        applicationDimensionMapperMap.put(applicationDimensionMapperParent.getId(), applicationDimensionMapperParent);
                    }
                    applicationDimensionMapper.setName(applicationDimensionMapperParent.getName() + "-" + appName);
                }
                String channelName = applicationDimensionMapper.getName() + "(" + channelDimensionMapper.getName() + ")";
                if (!userFromTypesScatter.contains(channelName)) {
                    userFromTypesScatter.add(channelName);
                }
                List<UserBasicStatisticsMapper> userBasicStatisticsMapperList = userBasicInfoStatisticsService.listByUserChannelAndApplication(type, applicationDimensionMapperParent, StatisticsDimension.CLIENT.equals(applicationDimensionMapper.getStatisticsDimension()) ? applicationDimensionMapper : null, channelDimensionMapper);
                List<Integer> aosList = new ArrayList<>();
                for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMapperList) {
                    //统计渠道
                    String date = null;
                    if (0 == type) {
                        date = userBasicStatisticsMapper.getStatisticsYear() + "-" + userBasicStatisticsMapper.getStatisticsMonth() + "-" + userBasicStatisticsMapper.getStatisticsDay() + " " + userBasicStatisticsMapper.getStatisticsHour();
                    } else if (1 == type) {
                        //获取天
                        date = userBasicStatisticsMapper.getStatisticsYear() + "-" + userBasicStatisticsMapper.getStatisticsMonth() + "-" + userBasicStatisticsMapper.getStatisticsDay();
                    } else if (2 == type) {
                        date = userBasicStatisticsMapper.getStatisticsYear() + "-" + userBasicStatisticsMapper.getStatisticsMonth();
                    }
                    if (!dates.contains(date)) {
                        dates.add(date);
                    }
                    aosList.add(userBasicStatisticsMapper.getUserCount());
                    if (maxUserScatterCount < userBasicStatisticsMapper.getUserCount()) {
                        maxUserScatterCount = userBasicStatisticsMapper.getUserCount();
                    }
                }
                userScatterChartLineChanelVos.add(new ChartLineVo(channelName, false, aosList));

            }
        }
        List list = new ArrayList();
        //list.add(userFromTypesScatter);
        list.add(maxUserScatterCount);
        list.add(userScatterChartLineChanelVos);
        return responseEntity(200, dates, "request.suc", 0, list);
    }
}
