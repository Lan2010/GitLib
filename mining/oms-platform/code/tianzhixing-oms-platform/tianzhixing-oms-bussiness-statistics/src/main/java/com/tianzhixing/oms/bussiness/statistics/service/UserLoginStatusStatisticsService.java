package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserLoginStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserLoginStatusStatisticsService;
import com.tianzhixing.oms.rpc.mapper.UserLoginStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserLoginStatusService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service
public class UserLoginStatusStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCUserLoginStatusService rpcUserLoginStatusService;

    @Autowired
    private RPCUserLoginStatusStatisticsService rpcUserLoginStatusStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserLoginStatusInfoMapper> userLoginStatusInfoMappers = rpcUserLoginStatusService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userLoginStatusStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userLoginStatusInfoMappers));
                    }
                } else {
                    //未配置客户端
                    userLoginStatusStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userLoginStatusInfoMappers));
                }

            }
        }
        rpcUserLoginStatusStatisticsService.insert(userLoginStatusStatisticsMappers, taskAllotMapper);
    }

    private List<UserLoginStatusStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserLoginStatusInfoMapper> userLoginStatusInfoMappers) {
        List<UserLoginStatusStatisticsMapper> list = new ArrayList<>();
        //设备类型
        UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper = new UserLoginStatusStatisticsMapper();
        userLoginStatusStatisticsMapper.setId(null);
        userLoginStatusStatisticsMapper.setVersion(0);
        userLoginStatusStatisticsMapper.setStatisticsHour(hour);
        userLoginStatusStatisticsMapper.setStatisticsDay(day);
        userLoginStatusStatisticsMapper.setStatisticsMonth(month);
        userLoginStatusStatisticsMapper.setStatisticsYear(year);
        userLoginStatusStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
        userLoginStatusStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
        userLoginStatusStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
        //计算
        int userLoginCount = 0;//用户登录数
        int userLoginDiffAccountCount = 0;//用户登录数（独立账号）
        int userLogoutCount = 0;//用户登出数
        int userLogoutDiffAccountCount = 0;//用户登出数（独立账号）
        int userOnlineCount = 0;//用户在线数
        Set<String> loginDiffIds = new HashSet<>();
        Set<String> logoutDiffIds = new HashSet<>();
        for (UserLoginStatusInfoMapper userLoginStatusInfoMapper : userLoginStatusInfoMappers) {
            if (!applicationDimensionMapper.getValue().equals(userLoginStatusInfoMapper.getPlatformFrom())) {
                continue;
            }
            if (adm != null && !adm.getValue().equals(userLoginStatusInfoMapper.getClientPlatformType())) {
                continue;
            }
            String userToken = StringUtils.isNotEmpty(userLoginStatusInfoMapper.getMobile()) ? userLoginStatusInfoMapper.getMobile() : (StringUtils.isNotEmpty(userLoginStatusInfoMapper.getWxID()) ? userLoginStatusInfoMapper.getWxID() : null);
            if (0 == userLoginStatusInfoMapper.getOperationType()) {
                userLogoutCount++;
                if (userToken != null && !logoutDiffIds.contains(userLoginStatusInfoMapper.getPlatformFrom() + userToken)) {
                    logoutDiffIds.add(userLoginStatusInfoMapper.getPlatformFrom() + userToken);
                    userLogoutDiffAccountCount++;
                }
            } else {
                userLoginCount++;
                if (userToken != null && !loginDiffIds.contains(userLoginStatusInfoMapper.getPlatformFrom() + userToken)) {
                    loginDiffIds.add(userLoginStatusInfoMapper.getPlatformFrom() + userToken);
                    userLoginDiffAccountCount++;
                }
            }
        }
        userOnlineCount = userLoginDiffAccountCount - userLogoutDiffAccountCount;
        userLoginStatusStatisticsMapper.setUserLoginCount(userLoginCount);
        userLoginStatusStatisticsMapper.setUserLoginDiffAccountCount(userLoginDiffAccountCount);
        userLoginStatusStatisticsMapper.setUserLogoutCount(userLogoutCount);
        userLoginStatusStatisticsMapper.setUserLogoutDiffAccountCount(userLogoutDiffAccountCount);
        userLoginStatusStatisticsMapper.setUserOnlineCount(userOnlineCount);
        list.add(userLoginStatusStatisticsMapper);
        return list;
    }
}
