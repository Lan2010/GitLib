package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserGreeterCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserGreeterCardStatisticsService;
import com.tianzhixing.oms.rpc.mapper.UserGreeterCardInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserGreeterCardService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service
public class UserGreeterCardStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCUserGreeterCardService rpcUserGreeterCardService;

    @Autowired
    private RPCUserGreeterCardStatisticsService rpcUserGreeterCardStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserGreeterCardInfoMapper> userGreeterCardInfoMappers = rpcUserGreeterCardService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userGreeterCardStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userGreeterCardInfoMappers));
                    }
                } else {
                    //未配置客户端
                    userGreeterCardStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userGreeterCardInfoMappers));
                }

            }
        }
        rpcUserGreeterCardStatisticsService.insert(userGreeterCardStatisticsMappers, taskAllotMapper);
    }

    private List<UserGreeterCardStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserGreeterCardInfoMapper> userGreeterCardInfoMappers) {
        List<UserGreeterCardStatisticsMapper> list = new ArrayList<>();
        //设备类型
        UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper = new UserGreeterCardStatisticsMapper();
        userGreeterCardStatisticsMapper.setId(null);
        userGreeterCardStatisticsMapper.setVersion(0);
        userGreeterCardStatisticsMapper.setStatisticsHour(hour);
        userGreeterCardStatisticsMapper.setStatisticsDay(day);
        userGreeterCardStatisticsMapper.setStatisticsMonth(month);
        userGreeterCardStatisticsMapper.setStatisticsYear(year);
        userGreeterCardStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
        userGreeterCardStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
        userGreeterCardStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
        //计算
        int createCount = 0;//用户登录数
        int shareCount = 0;//用户登录数（独立账号）
        for (UserGreeterCardInfoMapper userGreeterCardInfoMapper : userGreeterCardInfoMappers) {
            if (!applicationDimensionMapper.getValue().equals(userGreeterCardInfoMapper.getPlatformFrom())) {
                continue;
            }
            if (adm != null && !adm.getValue().equals(userGreeterCardInfoMapper.getClientPlatformType())) {
                continue;
            }
            if (0 == userGreeterCardInfoMapper.getOperationType()) {
                shareCount++;
            } else {
                createCount++;
            }
        }
        userGreeterCardStatisticsMapper.setCreateCount(createCount);
        userGreeterCardStatisticsMapper.setShareCount(shareCount);
        list.add(userGreeterCardStatisticsMapper);
        return list;
    }
}
