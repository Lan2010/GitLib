package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.*;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.*;
import com.tianzhixing.oms.rpc.mapper.UserStarPointIncrementInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointIncrementService;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service
public class UserStarPointIncrementStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCTaskDimensionService rpcTaskDimensionService;

    @Autowired
    private RPCUserStarPointIncrementService rpcUserStarPointIncrementService;

    @Autowired
    private RPCUserStarPointIncrementStatisticsService rpcUserStarPointIncrementStatisticsService;

    @Autowired
    private RPCAdvertisementDimensionService rpcAdvertisementDimensionService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserStarPointIncrementStatisticsMapper> userStarPointIncrementStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<TaskDimensionMapper> taskDimensionMappers = rpcTaskDimensionService.list(true);
        List<AdvertisementDimensionMapper> advertisementDimensionMappers = rpcAdvertisementDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserStarPointIncrementInfoMapper> userStarPointIncrementInfoMappers = rpcUserStarPointIncrementService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userStarPointIncrementStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userStarPointIncrementInfoMappers, taskDimensionMappers, advertisementDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    userStarPointIncrementStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userStarPointIncrementInfoMappers, taskDimensionMappers, advertisementDimensionMappers));
                }

            }
        }
        rpcUserStarPointIncrementStatisticsService.insert(userStarPointIncrementStatisticsMappers, taskAllotMapper);
    }

    private List<UserStarPointIncrementStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserStarPointIncrementInfoMapper> userStarPointIncrementInfoMappers, List<TaskDimensionMapper> taskDimensionMappers, List<AdvertisementDimensionMapper> advertisementDimensionMappers) {
        List<UserStarPointIncrementStatisticsMapper> list = new ArrayList<>();
        //统计任务的星点
        for (TaskDimensionMapper taskDimensionMapper : taskDimensionMappers) {
            if (!CalendarUtil.isBetween(new Date(), taskDimensionMapper.getBeginTime(), taskDimensionMapper.getEndTime())) {
                continue;
            }
            double taskCount = 0d;//任务增长数量
            UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = _getUserStarPointIncrementStatisticsMapper(hour, day, month, year, applicationDimensionMapper, adm, taskDimensionMapper.getTaskId(), taskDimensionMapper.getName(), 1);
            for (UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper : userStarPointIncrementInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userStarPointIncrementInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userStarPointIncrementInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (StringUtils.isEmpty(userStarPointIncrementInfoMapper.getThirdId()) || !taskDimensionMapper.getTaskId().equals(userStarPointIncrementInfoMapper.getThirdId())) {
                    continue;
                }
                if (1 != userStarPointIncrementInfoMapper.getIncrementType()) {
                    continue;
                }
                taskCount = Double.valueOf(CalculateUtil.plus(taskCount, StringUtils.isEmpty(userStarPointIncrementInfoMapper.getIncrementCount()) ? 0d : Double.valueOf(userStarPointIncrementInfoMapper.getIncrementCount()), 4));
            }
            userStarPointIncrementStatisticsMapper.setIncrementCount(taskCount);
            list.add(userStarPointIncrementStatisticsMapper);
        }
        //统计广告
        for (AdvertisementDimensionMapper advertisementDimensionMapper : advertisementDimensionMappers) {
            if (!CalendarUtil.isBetween(new Date(), advertisementDimensionMapper.getBeginTime(), advertisementDimensionMapper.getEndTime())) {
                continue;
            }
            double advertisementCount = 0d; //广告增长数量
            UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = _getUserStarPointIncrementStatisticsMapper(hour, day, month, year, applicationDimensionMapper, adm, advertisementDimensionMapper.getAdvertisementId(), advertisementDimensionMapper.getName(), 2);
            for (UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper : userStarPointIncrementInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userStarPointIncrementInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userStarPointIncrementInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (StringUtils.isEmpty(userStarPointIncrementInfoMapper.getThirdId()) || !advertisementDimensionMapper.getAdvertisementId().equals(userStarPointIncrementInfoMapper.getThirdId())) {
                    continue;
                }
                if (2 != userStarPointIncrementInfoMapper.getIncrementType()) {
                    continue;
                }
                advertisementCount = Double.valueOf(CalculateUtil.plus(advertisementCount, StringUtils.isEmpty(userStarPointIncrementInfoMapper.getIncrementCount()) ? 0d : Double.valueOf(userStarPointIncrementInfoMapper.getIncrementCount()), 4));
            }
            userStarPointIncrementStatisticsMapper.setIncrementCount(advertisementCount);
            list.add(userStarPointIncrementStatisticsMapper);
        }
        //掉落的星点
        double randomCount = 0d;//掉落的增长数量
        UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = _getUserStarPointIncrementStatisticsMapper(hour, day, month, year, applicationDimensionMapper, adm, null, "Random", 0);
        for (UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper : userStarPointIncrementInfoMappers) {
            if (!applicationDimensionMapper.getValue().equals(userStarPointIncrementInfoMapper.getPlatformFrom())) {
                continue;
            }
            if (adm != null && !adm.getValue().equals(userStarPointIncrementInfoMapper.getClientPlatformType())) {
                continue;
            }
            if (0 != userStarPointIncrementInfoMapper.getIncrementType()) {
                continue;
            }
            randomCount = Double.valueOf(CalculateUtil.plus(randomCount, StringUtils.isEmpty(userStarPointIncrementInfoMapper.getIncrementCount()) ? 0d : Double.valueOf(userStarPointIncrementInfoMapper.getIncrementCount()), 4));
        }
        userStarPointIncrementStatisticsMapper.setIncrementCount(randomCount);
        list.add(userStarPointIncrementStatisticsMapper);
        return list;
    }

    private UserStarPointIncrementStatisticsMapper _getUserStarPointIncrementStatisticsMapper(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String cause, String causeName, int incrementType) {
        UserStarPointIncrementStatisticsMapper userStarPointIncrementStatisticsMapper = new UserStarPointIncrementStatisticsMapper();
        userStarPointIncrementStatisticsMapper.setId(null);
        userStarPointIncrementStatisticsMapper.setVersion(0);
        userStarPointIncrementStatisticsMapper.setStatisticsHour(hour);
        userStarPointIncrementStatisticsMapper.setStatisticsDay(day);
        userStarPointIncrementStatisticsMapper.setStatisticsMonth(month);
        userStarPointIncrementStatisticsMapper.setStatisticsYear(year);
        userStarPointIncrementStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
        userStarPointIncrementStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
        userStarPointIncrementStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
        userStarPointIncrementStatisticsMapper.setIncrementCause(cause);
        userStarPointIncrementStatisticsMapper.setIncrementCauseName(causeName);
        userStarPointIncrementStatisticsMapper.setIncrementType(incrementType);
        return userStarPointIncrementStatisticsMapper;
    }
}
