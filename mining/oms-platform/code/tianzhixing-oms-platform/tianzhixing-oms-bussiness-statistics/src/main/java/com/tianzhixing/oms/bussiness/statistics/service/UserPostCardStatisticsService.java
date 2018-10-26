package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserPostCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserPostCardStatisticsService;
import com.tianzhixing.oms.rpc.mapper.UserPostCardInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserPostCardService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service
public class UserPostCardStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCUserPostCardService rpcUserPostCardService;

    @Autowired
    private RPCUserPostCardStatisticsService rpcUserPostCardStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserPostCardStatisticsMapper> userPostCardStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserPostCardInfoMapper> userPostCardInfoMappers = rpcUserPostCardService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userPostCardStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userPostCardInfoMappers));
                    }
                } else {
                    //未配置客户端
                    userPostCardStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userPostCardInfoMappers));
                }

            }
        }
        rpcUserPostCardStatisticsService.insert(userPostCardStatisticsMappers, taskAllotMapper);
    }

    private List<UserPostCardStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserPostCardInfoMapper> userPostCardInfoMappers) {
        List<UserPostCardStatisticsMapper> list = new ArrayList<>();
        //设备类型
        UserPostCardStatisticsMapper userPostCardStatisticsMapper = new UserPostCardStatisticsMapper();
        userPostCardStatisticsMapper.setId(null);
        userPostCardStatisticsMapper.setVersion(0);
        userPostCardStatisticsMapper.setStatisticsHour(hour);
        userPostCardStatisticsMapper.setStatisticsDay(day);
        userPostCardStatisticsMapper.setStatisticsMonth(month);
        userPostCardStatisticsMapper.setStatisticsYear(year);
        userPostCardStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
        userPostCardStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
        userPostCardStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
        //计算
        int createCount = 0;//用户登录数
        int shareCount = 0;//用户登录数（独立账号）
        for (UserPostCardInfoMapper userPostCardInfoMapper : userPostCardInfoMappers) {
            if (!applicationDimensionMapper.getValue().equals(userPostCardInfoMapper.getPlatformFrom())) {
                continue;
            }
            if (adm != null && !adm.getValue().equals(userPostCardInfoMapper.getClientPlatformType())) {
                continue;
            }
            if (0 == userPostCardInfoMapper.getOperationType()) {
                shareCount++;
            } else {
                createCount++;
            }
        }
        userPostCardStatisticsMapper.setCreateCount(createCount);
        userPostCardStatisticsMapper.setShareCount(shareCount);
        list.add(userPostCardStatisticsMapper);
        return list;
    }
}
