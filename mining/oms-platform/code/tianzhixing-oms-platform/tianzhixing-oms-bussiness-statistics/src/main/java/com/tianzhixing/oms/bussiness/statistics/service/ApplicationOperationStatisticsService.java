package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationOperationStatisticsService;
import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCApplicationOperationService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/5.
 */
@Service
public class ApplicationOperationStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCApplicationOperationStatisticsService rpcApplicationOperationStatisticsService;

    @Autowired
    private RPCApplicationOperationService rpcApplicationOperationService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMapperList = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<ApplicationOperationInfoMapper> applicationOperationInfoMappers = rpcApplicationOperationService.list(beginTime, endTime);
            //获取client清单数据
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        applicationOperationStatisticsMapperList.add(_statistics(hour, day, month, year, applicationDimensionMapper, adm, applicationOperationInfoMappers));
                    }
                } else {
                    //未配置客户端
                    applicationOperationStatisticsMapperList.add(_statistics(hour, day, month, year, applicationDimensionMapper, null, applicationOperationInfoMappers));
                }

            }
        }
        rpcApplicationOperationStatisticsService.insert(applicationOperationStatisticsMapperList, taskAllotMapper);
    }

    private ApplicationOperationStatisticsMapper _statistics(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, ApplicationDimensionMapper platform, ApplicationDimensionMapper clientPlatform, List<ApplicationOperationInfoMapper> applicationOperationInfoMappers) {
        ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper = new ApplicationOperationStatisticsMapper();
        applicationOperationStatisticsMapper.setVersion(0);
        applicationOperationStatisticsMapper.setCreateTime(new Date());
        applicationOperationStatisticsMapper.setStatisticsHour(statisticsHour);
        applicationOperationStatisticsMapper.setStatisticsDay(statisticsDay);
        applicationOperationStatisticsMapper.setStatisticsMonth(statisticsMonth);
        applicationOperationStatisticsMapper.setStatisticsYear(statisticsYear);
        applicationOperationStatisticsMapper.setClientPlatformType(clientPlatform == null ? null : clientPlatform.getValue());
        applicationOperationStatisticsMapper.setPlatformFrom(platform.getValue());
        applicationOperationStatisticsMapper.setPlatformName(platform.getName() + (clientPlatform == null ? "" : ("-" + clientPlatform.getName())));
        Set<String> startIps = new HashSet<>();
        Set<String> downIps = new HashSet<>();
        int appStartTotalCount = 0;
        int appDownTotalCount = 0;
        int appStartDiffIPTotalCount = 0;
        int appDownDiffIPTotalCount = 0;
        for (ApplicationOperationInfoMapper applicationOperationInfoMapper : applicationOperationInfoMappers) {
            if (!platform.getValue().equals(applicationOperationInfoMapper.getPlatformFrom())) {
                continue;
            }
            if (clientPlatform!= null && !clientPlatform.getValue().equals(applicationOperationInfoMapper.getClientPlatformType())) {
                continue;
            }
            if (0 == applicationOperationInfoMapper.getOperationType()) {
                appDownTotalCount++;
                if (StringUtils.isNotEmpty(applicationOperationInfoMapper.getIp()) && !downIps.contains(applicationOperationInfoMapper.getIp())) {
                    downIps.add(applicationOperationInfoMapper.getIp());
                    appDownDiffIPTotalCount++;
                }
            } else {
                appStartTotalCount++;
                if (StringUtils.isNotEmpty(applicationOperationInfoMapper.getIp()) && !startIps.contains(applicationOperationInfoMapper.getIp())) {
                    startIps.add(applicationOperationInfoMapper.getIp());
                    appStartDiffIPTotalCount++;
                }
            }
        }
        applicationOperationStatisticsMapper.setAppDownDiffIPTotalCount(appDownDiffIPTotalCount);
        applicationOperationStatisticsMapper.setAppDownTotalCount(appDownTotalCount);
        applicationOperationStatisticsMapper.setAppStartDiffIPTotalCount(appStartDiffIPTotalCount);
        applicationOperationStatisticsMapper.setAppStartTotalCount(appStartTotalCount);
        return applicationOperationStatisticsMapper;
    }

}
