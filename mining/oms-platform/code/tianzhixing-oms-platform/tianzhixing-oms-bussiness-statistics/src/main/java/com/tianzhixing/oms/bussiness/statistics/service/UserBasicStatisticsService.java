package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.*;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.*;
import com.tianzhixing.oms.rpc.mapper.UserBasicInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserBasicInfoService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service
public class UserBasicStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCUserChannelDimensionService rpcUserChannelDimensionService;

    @Autowired
    private RPCUserBasicInfoService rpcUserBasicInfoService;

    @Autowired
    private RPCUserBasicStatisticsService rpcUserBasicStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserBasicStatisticsMapper> userBasicStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<UserChannelDimensionMapper> userChannelDimensionMappers = rpcUserChannelDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserBasicInfoMapper> userBasicInfoMappers = rpcUserBasicInfoService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userBasicStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userBasicInfoMappers, userChannelDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    userBasicStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userBasicInfoMappers, userChannelDimensionMappers));
                }

            }
        }
        rpcUserBasicStatisticsService.insert(userBasicStatisticsMappers, taskAllotMapper);
    }

    private List<UserBasicStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserBasicInfoMapper> userBasicInfoMappers, List<UserChannelDimensionMapper> userChannelDimensionMappers) {
        List<UserBasicStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (UserChannelDimensionMapper userChannelDimensionMapper : userChannelDimensionMappers) {
            UserBasicStatisticsMapper userBasicStatisticsMapper = new UserBasicStatisticsMapper();
            userBasicStatisticsMapper.setId(null);
            userBasicStatisticsMapper.setVersion(0);
            userBasicStatisticsMapper.setStatisticsHour(hour);
            userBasicStatisticsMapper.setStatisticsDay(day);
            userBasicStatisticsMapper.setStatisticsMonth(month);
            userBasicStatisticsMapper.setStatisticsYear(year);
            userBasicStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            userBasicStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            userBasicStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            userBasicStatisticsMapper.setUserFromType(userChannelDimensionMapper.getValue());
            userBasicStatisticsMapper.setUserFromTypeName(userChannelDimensionMapper.getName());
            //计算
            int userCount = 0;//用户数
            for (UserBasicInfoMapper userBasicInfoMapper : userBasicInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userBasicInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userBasicInfoMapper.getClientPlatformType())) {
                    continue;
                }
                userCount++;
            }
            userBasicStatisticsMapper.setUserCount(userCount);
            list.add(userBasicStatisticsMapper);
        }
        return list;
    }
}
