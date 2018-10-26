package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.*;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.*;
import com.tianzhixing.oms.commons.em.AuthStatus;
import com.tianzhixing.oms.rpc.mapper.UserAuthInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserBasicInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserAuthService;
import com.tianzhixing.oms.rpc.service.RPCUserBasicInfoService;
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
public class UserAuthStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCUserAuthDimensionService rpcUserAuthDimensionService;

    @Autowired
    private RPCUserAuthService rpcUserAuthService;

    @Autowired
    private RPCUserAuthStatisticsService rpcUserAuthStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserAuthStatisticsMapper> userAuthStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<UserAuthDimensionMapper> userAuthDimensionMappers = rpcUserAuthDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserAuthInfoMapper> userAuthInfoMappers = rpcUserAuthService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userAuthStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userAuthInfoMappers, userAuthDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    userAuthStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userAuthInfoMappers, userAuthDimensionMappers));
                }

            }
        }
        rpcUserAuthStatisticsService.insert(userAuthStatisticsMappers, taskAllotMapper);
    }

    private List<UserAuthStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserAuthInfoMapper> userAuthInfoMappers, List<UserAuthDimensionMapper> userAuthDimensionMappers) {
        List<UserAuthStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (UserAuthDimensionMapper userAuthDimensionMapper : userAuthDimensionMappers) {
            UserAuthStatisticsMapper userAuthStatisticsMapper = new UserAuthStatisticsMapper();
            userAuthStatisticsMapper.setId(null);
            userAuthStatisticsMapper.setVersion(0);
            userAuthStatisticsMapper.setStatisticsHour(hour);
            userAuthStatisticsMapper.setStatisticsDay(day);
            userAuthStatisticsMapper.setStatisticsMonth(month);
            userAuthStatisticsMapper.setStatisticsYear(year);
            userAuthStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            userAuthStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            userAuthStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            userAuthStatisticsMapper.setAuthType(userAuthDimensionMapper.getValue());
            userAuthStatisticsMapper.setAuthTypeName(userAuthDimensionMapper.getName());
            //计算
            int failedAuthCount = 0;//认证失败数量
            int sucAuthCount = 0;//成功认证的数量
            int authCount = 0;//认证总数量
            for (UserAuthInfoMapper userAuthInfoMapper : userAuthInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userAuthInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userAuthInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (!userAuthDimensionMapper.getValue().equals(userAuthInfoMapper.getAuthType())) {
                    continue;
                }
                if (0 == userAuthInfoMapper.getAuthStatus()) {
                    failedAuthCount++;
                } else {
                    sucAuthCount++;
                }
            }
            authCount = sucAuthCount + failedAuthCount;
            userAuthStatisticsMapper.setAuthCount(authCount);
            userAuthStatisticsMapper.setFailedAuthCount(failedAuthCount);
            userAuthStatisticsMapper.setSucAuthCount(sucAuthCount);
            list.add(userAuthStatisticsMapper);
        }
        return list;
    }
}
