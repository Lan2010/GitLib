package com.tianzhixing.oms.bussiness.backend.web.controller;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.conf.RequestConf;
import com.tianzhixing.oms.bussiness.backend.web.controller.base.BaseController;
import com.tianzhixing.oms.bussiness.backend.web.entity.ResponseEntity;
import com.tianzhixing.oms.bussiness.backend.web.mapping.DeviceOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.service.DeviceDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.service.DeviceOperationStatisticsService;
import com.tianzhixing.oms.bussiness.backend.web.service.StatisticsDimensionService;
import com.tianzhixing.oms.bussiness.backend.web.vo.ChartLineVo;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;

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
public class DeviceOperationStatisticsController extends BaseController {
	
    @Autowired
    private DeviceOperationStatisticsService deviceOperationStatisticsService;
    
    @Autowired
    private StatisticsDimensionService statisticsDimensionService;
    
    @Autowired
    private DeviceDimensionService deviceDimensionService;

    /**
     * 首页
     *
     * @return
     */
    @RequestMapping(value = "/device/index", method = RequestMethod.GET)
    public ModelAndView index() {
        return configModelAndView("statistics/device");
    }

    @RequestMapping(value = "/device/chart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity chart(@RequestParam("type") int type) {
        List<DeviceOperationStatisticsMapping> deviceOperationStatisticsMappings = deviceOperationStatisticsService.list(type);
        //labels
        List<String> labels = new ArrayList<>();
        List<Integer> totalCountList = new ArrayList<>();
        List<String> dates = new ArrayList<>();
        Date now = new Date();
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        Integer maxValue = Integer.MIN_VALUE;
        for (DeviceOperationStatisticsMapping deviceOperationStatisticsMapping : deviceOperationStatisticsMappings) {
            ApplicationDimensionMapper applicationDimensionMapper = deviceOperationStatisticsMapping.getApplicationDimensionMapper();
            labels.add(applicationDimensionMapper.getName());
            List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMappers = deviceOperationStatisticsMapping.getDeviceOperationStatisticsMappers();
            Integer appStartTotalCount = 0;
            List<Integer> aosList = new ArrayList<>();
            for (DeviceOperationStatisticsMapper deviceOperationStatisticsMapper : deviceOperationStatisticsMappers) {

                String date = null;
                if (0 == type) {
                    date = deviceOperationStatisticsMapper.getStatisticsYear() + "-" + deviceOperationStatisticsMapper.getStatisticsMonth() + "-" + deviceOperationStatisticsMapper.getStatisticsDay() + " " + deviceOperationStatisticsMapper.getStatisticsHour();
                } else if (1 == type) {
                    //获取天
                    date = deviceOperationStatisticsMapper.getStatisticsYear() + "-" + deviceOperationStatisticsMapper.getStatisticsMonth() + "-" + deviceOperationStatisticsMapper.getStatisticsDay();
                } else if (2 == type) {
                    date = deviceOperationStatisticsMapper.getStatisticsYear() + "-" + deviceOperationStatisticsMapper.getStatisticsMonth();
                }
                if (!dates.contains(date)) {
                    dates.add(date);
                }

                appStartTotalCount += deviceOperationStatisticsMapper.getDeviceBindCount();
                aosList.add(deviceOperationStatisticsMapper.getDeviceBindCount());
                if (maxValue < deviceOperationStatisticsMapper.getDeviceBindCount()) {
                    maxValue = deviceOperationStatisticsMapper.getDeviceBindCount();
                }
            }
            totalCountList.add(appStartTotalCount);
            chartLineVos.add(new ChartLineVo(applicationDimensionMapper.getName(), true, aosList));
        }
        List list = new ArrayList();
        list.add(labels);
        list.add(totalCountList);
        list.add(chartLineVos);
        list.add(maxValue);
        return responseEntity(200, dates, "request.suc", 0, list);
    }
    
    @RequestMapping(value = "/device/lineChart", method = RequestMethod.GET)
    @ResponseBody
    public ResponseEntity scatterChart(@RequestParam("type") int type) {
        List<String> dates = new ArrayList<>();
        List<ChartLineVo> chartLineVos = new ArrayList<>();
        Integer maxValue = 0;
        Map<Long, ApplicationDimensionMapper> applicationDimensionMapperMap = new HashMap<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = statisticsDimensionService.list(true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            applicationDimensionMapperMap.put(applicationDimensionMapper.getId(), applicationDimensionMapper);
        }
        List<DeviceDimensionMapper> deviceDimensionMappers = deviceDimensionService.list(true);
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
            for (DeviceDimensionMapper deviceDimensionMapper : deviceDimensionMappers) {
                ApplicationDimensionMapper applicationDimensionMapperParent = applicationDimensionMapper;
                if (StatisticsDimension.CLIENT.equals(applicationDimensionMapperParent.getStatisticsDimension())) {
                    applicationDimensionMapperParent = applicationDimensionMapperMap.get(applicationDimensionMapperParent.getParentId());
                    if (applicationDimensionMapperParent == null) {
                        applicationDimensionMapperParent = statisticsDimensionService.getById(applicationDimensionMapper.getParentId());
                        applicationDimensionMapperMap.put(applicationDimensionMapperParent.getId(), applicationDimensionMapperParent);
                    }
                    applicationDimensionMapper.setName(applicationDimensionMapperParent.getName() + "-" + appName);
                }
                String channelName = applicationDimensionMapper.getName() + "(" + deviceDimensionMapper.getName() + ")";
                List<DeviceOperationStatisticsMapper> deviceOperationStatisticsMapperList = deviceOperationStatisticsService.listByDeviceDimensionAndApplication(type, applicationDimensionMapperParent, StatisticsDimension.CLIENT.equals(applicationDimensionMapper.getStatisticsDimension()) ? applicationDimensionMapper : null, deviceDimensionMapper);
                List<Integer> aosList = new ArrayList<>();
                for (DeviceOperationStatisticsMapper deviceOperationStatisticsMapper : deviceOperationStatisticsMapperList) {
                    //统计渠道
                    String date = null;
                    if (0 == type) {
                        date = deviceOperationStatisticsMapper.getStatisticsYear() + "-" + deviceOperationStatisticsMapper.getStatisticsMonth() + "-" + deviceOperationStatisticsMapper.getStatisticsDay() + " " + deviceOperationStatisticsMapper.getStatisticsHour();
                    } else if (1 == type) {
                        //获取天
                        date = deviceOperationStatisticsMapper.getStatisticsYear() + "-" + deviceOperationStatisticsMapper.getStatisticsMonth() + "-" + deviceOperationStatisticsMapper.getStatisticsDay();
                    } else if (2 == type) {
                        date = deviceOperationStatisticsMapper.getStatisticsYear() + "-" + deviceOperationStatisticsMapper.getStatisticsMonth();
                    }
                    if (!dates.contains(date)) {
                        dates.add(date);
                    }
                    aosList.add(deviceOperationStatisticsMapper.getDeviceBindCount());
                    if (maxValue < deviceOperationStatisticsMapper.getDeviceBindCount()) {
                    	maxValue = deviceOperationStatisticsMapper.getDeviceBindCount();
                    }
                }
                chartLineVos.add(new ChartLineVo(channelName, false, aosList));
            }
        }
        List list = new ArrayList();
        list.add(maxValue);
        list.add(chartLineVos);
        return responseEntity(200, dates, "request.suc", 0, list);
    }
}
