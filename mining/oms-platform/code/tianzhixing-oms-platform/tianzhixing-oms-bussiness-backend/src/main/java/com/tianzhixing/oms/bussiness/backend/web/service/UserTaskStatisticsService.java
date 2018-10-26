package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.TaskDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserTaskStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserTaskStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserTaskStatisticsService {

    @Autowired
    private RPCUserTaskStatisticsService rpcUserTaskStatisticsService;

    /**
     * 获取清单
     *
     * @param pagerMapping
     * @return
     */
    public List<UserTaskStatisticsMapper> list(String taskId) {
        return rpcUserTaskStatisticsService.list(taskId);
    }
    
    public long count() {
        return rpcUserTaskStatisticsService.count();
    }
    
    private List<UserTaskStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName,String taskId) {
        List<UserTaskStatisticsMapper> list = new ArrayList<>();
        if (0 == type) {
            List<UserTaskStatisticsMapper> userTaskStatisticsMappers = rpcUserTaskStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName(),taskId);
            Map<String, UserTaskStatisticsMapper> userTaskStatisticsMapperMap = new HashMap<>();
            for (UserTaskStatisticsMapper userTaskStatisticsMapper : userTaskStatisticsMappers) {
                userTaskStatisticsMapperMap.put(userTaskStatisticsMapper.getStatisticsHour().toString(), userTaskStatisticsMapper);
            }
            //获取小时 24小时
            for (Integer i = 0; i < hour; i++) {
                UserTaskStatisticsMapper userTaskStatisticsMapper = userTaskStatisticsMapperMap.get(i.toString());
                if (userTaskStatisticsMapper == null) {
                    userTaskStatisticsMapper = new UserTaskStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(), taskId,null,0,0, platformName);
                }
                list.add(userTaskStatisticsMapper);
            }
        } else if (1 == type) {
            List<UserTaskStatisticsMapper> userTaskStatisticsMappers = rpcUserTaskStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName(),taskId);
            Map<String, UserTaskStatisticsMapper> userTaskStatisticsMapperMap = new HashMap<>();
            for (UserTaskStatisticsMapper userTaskStatisticsMapper : userTaskStatisticsMappers) {
                userTaskStatisticsMapperMap.put(userTaskStatisticsMapper.getStatisticsDay().toString(), userTaskStatisticsMapper);
            }
            //获取天
            for (Integer i = 1; i <= day; i++) {
                UserTaskStatisticsMapper userTaskStatisticsMapper = userTaskStatisticsMapperMap.get(i.toString());
                if (userTaskStatisticsMapper == null) {
                    userTaskStatisticsMapper = new UserTaskStatisticsMapper(null, null, null,null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(), taskId,null,0,0, platformName);
                }
                list.add(userTaskStatisticsMapper);
            }
        } else if (2 == type) {

            List<UserTaskStatisticsMapper> userTaskStatisticsMappers = rpcUserTaskStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName(),taskId);
            Map<String, UserTaskStatisticsMapper> userTaskStatisticsMapperMap = new HashMap<>();
            for (UserTaskStatisticsMapper userTaskStatisticsMapper : userTaskStatisticsMappers) {
                userTaskStatisticsMapperMap.put(userTaskStatisticsMapper.getStatisticsMonth().toString(), userTaskStatisticsMapper);
            }
            //获取月
            for (Integer i = 1; i <= month; i++) {
                UserTaskStatisticsMapper userTaskStatisticsMapper = userTaskStatisticsMapperMap.get(i.toString());
                if (userTaskStatisticsMapper == null) {
                    userTaskStatisticsMapper = new UserTaskStatisticsMapper(null, null, null,null, null,i,year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(), taskId,null,0,0, platformName);
                }
                list.add(userTaskStatisticsMapper);
            }
        }
        return list;
    }
    
    
    public List<UserTaskStatisticsMapper> listByTaskDimensionAndApplication(int type, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, TaskDimensionMapper taskDimensionMapper) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<UserTaskStatisticsMapper> userTaskStatisticsMappers = new ArrayList<>();
        userTaskStatisticsMappers.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, applicationDimensionMapper.getName(),taskDimensionMapper.getTaskId()));
        return userTaskStatisticsMappers;
    }
}
