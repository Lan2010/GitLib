package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.*;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.*;
import com.tianzhixing.oms.rpc.mapper.UserAdvertisementInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserAdvertisementService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service
public class UserAdvertisementStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCAdvertisementDimensionService rpcAdvertisementDimensionService;

    @Autowired
    private RPCUserAdvertisementService rpcUserAdvertisementService;

    @Autowired
    private RPCUserAdvertisementStatisticsService rpcUserAdvertisementStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<AdvertisementDimensionMapper> advertisementDimensionMappers = rpcAdvertisementDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserAdvertisementInfoMapper> userAdvertisementInfoMappers = rpcUserAdvertisementService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userAdvertisementStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userAdvertisementInfoMappers, advertisementDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    userAdvertisementStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userAdvertisementInfoMappers, advertisementDimensionMappers));
                }

            }
        }
        rpcUserAdvertisementStatisticsService.insert(userAdvertisementStatisticsMappers, taskAllotMapper);
    }

    private List<UserAdvertisementStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserAdvertisementInfoMapper> userAdvertisementInfoMappers, List<AdvertisementDimensionMapper> advertisementDimensionMappers) {
        List<UserAdvertisementStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (AdvertisementDimensionMapper advertisementDimensionMapper : advertisementDimensionMappers) {
            if (!CalendarUtil.isBetween(new Date(), advertisementDimensionMapper.getBeginTime(), advertisementDimensionMapper.getEndTime())) {
                continue;
            }

            UserAdvertisementStatisticsMapper userAdvertisementStatisticsMapper = new UserAdvertisementStatisticsMapper();
            userAdvertisementStatisticsMapper.setId(null);
            userAdvertisementStatisticsMapper.setVersion(0);
            userAdvertisementStatisticsMapper.setStatisticsHour(hour);
            userAdvertisementStatisticsMapper.setStatisticsDay(day);
            userAdvertisementStatisticsMapper.setStatisticsMonth(month);
            userAdvertisementStatisticsMapper.setStatisticsYear(year);
            userAdvertisementStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            userAdvertisementStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            userAdvertisementStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            userAdvertisementStatisticsMapper.setAdvettisementId(advertisementDimensionMapper.getAdvertisementId());
            userAdvertisementStatisticsMapper.setAdvettisementName(advertisementDimensionMapper.getName());
            //计算
            int accessCount = 0;//访问量
            int accessDiffIPCount = 0;//访问量(独立IP)
            int clickCount = 0;//点击量
            int clickDiffIPCount = 0;//点击量(独立IP)
            Set<String> accessDiffIds = new HashSet<>();
            Set<String> clickDiffIds = new HashSet<>();
            for (UserAdvertisementInfoMapper userAdvertisementInfoMapper : userAdvertisementInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userAdvertisementInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userAdvertisementInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if(!advertisementDimensionMapper.getAdvertisementId().equals(userAdvertisementInfoMapper.getAdvertId())){
                    continue;
                }
                if (userAdvertisementInfoMapper.getOperationType() == 0) {
                    //访问
                    accessCount++;
                    if (StringUtils.isNotEmpty(userAdvertisementInfoMapper.getIp()) && !accessDiffIds.contains(userAdvertisementInfoMapper.getIp())) {
                        accessDiffIds.add(userAdvertisementInfoMapper.getIp());
                        accessDiffIPCount++;
                    }
                } else {
                    //点击
                    clickCount++;
                    if (StringUtils.isNotEmpty(userAdvertisementInfoMapper.getIp()) && !clickDiffIds.contains(userAdvertisementInfoMapper.getIp())) {
                        clickDiffIds.add(userAdvertisementInfoMapper.getIp());
                        clickDiffIPCount++;
                    }
                }
            }
            userAdvertisementStatisticsMapper.setAccessCount(accessCount);
            userAdvertisementStatisticsMapper.setAccessDiffIPCount(accessDiffIPCount);
            userAdvertisementStatisticsMapper.setClickCount(clickCount);
            userAdvertisementStatisticsMapper.setClickDiffIPCount(clickDiffIPCount);
            list.add(userAdvertisementStatisticsMapper);
        }
        return list;
    }

}
