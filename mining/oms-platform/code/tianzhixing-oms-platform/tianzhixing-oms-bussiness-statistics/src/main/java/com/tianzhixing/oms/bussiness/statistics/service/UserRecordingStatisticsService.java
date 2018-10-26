package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserRecordingStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserRecordingStatisticsService;
import com.tianzhixing.oms.rpc.mapper.UserRecordingInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserRecordingService;
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
public class UserRecordingStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCUserRecordingService rpcUserRecordingService;

    @Autowired
    private RPCUserRecordingStatisticsService rpcUserRecordingStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserRecordingStatisticsMapper> userRecordingStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserRecordingInfoMapper> userRecordingInfoMappers = rpcUserRecordingService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userRecordingStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userRecordingInfoMappers));
                    }
                } else {
                    //未配置客户端
                    userRecordingStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userRecordingInfoMappers));
                }

            }
        }
        rpcUserRecordingStatisticsService.insert(userRecordingStatisticsMappers, taskAllotMapper);
    }

    private List<UserRecordingStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserRecordingInfoMapper> userRecordingInfoMappers) {
        List<UserRecordingStatisticsMapper> list = new ArrayList<>();
        //设备类型
        UserRecordingStatisticsMapper userRecordingStatisticsMapper = new UserRecordingStatisticsMapper();
        userRecordingStatisticsMapper.setId(null);
        userRecordingStatisticsMapper.setVersion(0);
        userRecordingStatisticsMapper.setStatisticsHour(hour);
        userRecordingStatisticsMapper.setStatisticsDay(day);
        userRecordingStatisticsMapper.setStatisticsMonth(month);
        userRecordingStatisticsMapper.setStatisticsYear(year);
        userRecordingStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
        userRecordingStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
        userRecordingStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
        //计算
        int recordingCount = 0;//用户录音数量
        for (UserRecordingInfoMapper userRecordingInfoMapper : userRecordingInfoMappers) {
            if (!applicationDimensionMapper.getValue().equals(userRecordingInfoMapper.getPlatformFrom())) {
                continue;
            }
            if (adm != null && !adm.getValue().equals(userRecordingInfoMapper.getClientPlatformType())) {
                continue;
            }
            recordingCount++;
        }
        userRecordingStatisticsMapper.setRecordingCount(recordingCount);
        list.add(userRecordingStatisticsMapper);
        return list;
    }
}
