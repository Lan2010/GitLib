package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.*;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCPagesDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCPagesOperationStatisticsService;
import com.tianzhixing.oms.rpc.mapper.PagesOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCPagesOperationService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service
public class PagesOperationStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCPagesDimensionService rpcPagesDimensionService;

    @Autowired
    private RPCPagesOperationService rpcPagesOperationService;

    @Autowired
    private RPCPagesOperationStatisticsService rpcPagesOperationStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<PagesDimensionMapper> pagesDimensionMappers = rpcPagesDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<PagesOperationInfoMapper> pagesOperationInfoMappers = rpcPagesOperationService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        pagesOperationStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, pagesOperationInfoMappers, pagesDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    pagesOperationStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, pagesOperationInfoMappers, pagesDimensionMappers));
                }

            }
        }
        rpcPagesOperationStatisticsService.insert(pagesOperationStatisticsMappers, taskAllotMapper);
    }

    private List<PagesOperationStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper platform, ApplicationDimensionMapper clientPlatform, List<PagesOperationInfoMapper> pagesOperationInfoMappers, List<PagesDimensionMapper> pagesDimensionMappers) {
        List<PagesOperationStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (PagesDimensionMapper pagesDimensionMapper : pagesDimensionMappers) {
            if (!CalendarUtil.isBetween(new Date(), pagesDimensionMapper.getBeginTime(), pagesDimensionMapper.getEndTime())) {
                continue;
            }
            PagesOperationStatisticsMapper pagesOperationStatisticsMapper = new PagesOperationStatisticsMapper();
            pagesOperationStatisticsMapper.setId(null);
            pagesOperationStatisticsMapper.setVersion(0);
            pagesOperationStatisticsMapper.setStatisticsHour(hour);
            pagesOperationStatisticsMapper.setStatisticsDay(day);
            pagesOperationStatisticsMapper.setStatisticsMonth(month);
            pagesOperationStatisticsMapper.setStatisticsYear(year);
            pagesOperationStatisticsMapper.setClientPlatformType(clientPlatform == null ? null : clientPlatform.getValue());
            pagesOperationStatisticsMapper.setPlatformFrom(platform.getValue());
            pagesOperationStatisticsMapper.setPlatformName(platform.getName() + (clientPlatform == null ? "" : ("-" + clientPlatform.getName())));
            pagesOperationStatisticsMapper.setPageName(pagesDimensionMapper.getName());
            pagesOperationStatisticsMapper.setPagesURL(pagesDimensionMapper.getUrl());
            //计算
            int pagesOperationCount = 0; //操作页面数
            int pagesOperationDiffIPCount = 0; //操作页面数（独立IP）
            Set<String> diffIds = new HashSet<>();
            for (PagesOperationInfoMapper pagesOperationInfoMapper : pagesOperationInfoMappers) {
                if (!platform.getValue().equals(pagesOperationInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (clientPlatform != null && !clientPlatform.getValue().equals(pagesOperationInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (!pagesDimensionMapper.getUrl().equals(pagesOperationInfoMapper.getPagesURL())) {
                    continue;
                }
                pagesOperationCount++;
                if (StringUtils.isNotEmpty(pagesOperationInfoMapper.getIp()) && !diffIds.contains(pagesOperationInfoMapper.getIp())) {
                    diffIds.add(pagesOperationInfoMapper.getIp());
                    pagesOperationDiffIPCount++;
                }

            }
            pagesOperationStatisticsMapper.setPagesOperationCount(pagesOperationCount);
            pagesOperationStatisticsMapper.setPagesOperationDiffIPCount(pagesOperationDiffIPCount);
            list.add(pagesOperationStatisticsMapper);
        }
        return list;
    }
}
