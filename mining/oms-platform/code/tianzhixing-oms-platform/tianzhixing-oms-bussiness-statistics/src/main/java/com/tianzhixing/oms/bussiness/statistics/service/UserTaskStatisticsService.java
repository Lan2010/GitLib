package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.TaskDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserTaskStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCTaskDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserTaskStatisticsService;
import com.tianzhixing.oms.rpc.mapper.UserTaskStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserTaskStatusService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service
public class UserTaskStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCTaskDimensionService rpcTaskDimensionService;

    @Autowired
    private RPCUserTaskStatusService rpcUserTaskStatusService;

    @Autowired
    private RPCUserTaskStatisticsService rpcUserTaskStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserTaskStatisticsMapper> userTaskStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<TaskDimensionMapper> taskDimensionMappers = rpcTaskDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserTaskStatusInfoMapper> userTaskInfoMappers = rpcUserTaskStatusService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userTaskStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userTaskInfoMappers, taskDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    userTaskStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userTaskInfoMappers, taskDimensionMappers));
                }

            }
        }
        rpcUserTaskStatisticsService.insert(userTaskStatisticsMappers, taskAllotMapper);
    }

    private List<UserTaskStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserTaskStatusInfoMapper> userTaskInfoMappers, List<TaskDimensionMapper> taskDimensionMappers) {
        List<UserTaskStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (TaskDimensionMapper taskDimensionMapper : taskDimensionMappers) {
            if (!CalendarUtil.isBetween(new Date(), taskDimensionMapper.getBeginTime(), taskDimensionMapper.getEndTime())) {
                continue;
            }

            UserTaskStatisticsMapper userTaskStatisticsMapper = new UserTaskStatisticsMapper();
            userTaskStatisticsMapper.setId(null);
            userTaskStatisticsMapper.setVersion(0);
            userTaskStatisticsMapper.setStatisticsHour(hour);
            userTaskStatisticsMapper.setStatisticsDay(day);
            userTaskStatisticsMapper.setStatisticsMonth(month);
            userTaskStatisticsMapper.setStatisticsYear(year);
            userTaskStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            userTaskStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            userTaskStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            userTaskStatisticsMapper.setTaskId(taskDimensionMapper.getTaskId());
            userTaskStatisticsMapper.setTaskName(taskDimensionMapper.getName());
            //计算
            int acceptCount = 0;//接受数量
            int cancelCount = 0;//取消数量
            for (UserTaskStatusInfoMapper userTaskInfoMapper : userTaskInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userTaskInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userTaskInfoMapper.getClientPlatformType())) {
                    continue;
                }

                if (!taskDimensionMapper.getTaskId().equals(userTaskInfoMapper.getTaskId())) {
                    continue;
                }
                if (userTaskInfoMapper.getOperationType() == 0) {
                    //取消
                    cancelCount++;
                } else {
                    //接受
                    acceptCount++;
                }
            }
            userTaskStatisticsMapper.setAcceptCount(acceptCount);
            userTaskStatisticsMapper.setCancelCount(cancelCount);
            list.add(userTaskStatisticsMapper);
        }
        return list;
    }

}
