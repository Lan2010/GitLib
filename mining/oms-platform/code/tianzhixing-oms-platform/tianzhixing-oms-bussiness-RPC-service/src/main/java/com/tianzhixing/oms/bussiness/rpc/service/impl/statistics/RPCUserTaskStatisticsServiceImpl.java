package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserTaskStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserTaskStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserTaskStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserTaskStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCUserTaskStatisticsService")
public class RPCUserTaskStatisticsServiceImpl implements RPCUserTaskStatisticsService {

    @Autowired
    private UserTaskStatisticsService userTaskStatisticsService;

    @Override
    public void insert(List<UserTaskStatisticsMapper> userTaskStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userTaskStatisticsMappers != null) {
            List<UserTaskStatisticsModel> userTaskStatisticsModels = new ArrayList<>();
            for(UserTaskStatisticsMapper userTaskStatisticsMapper : userTaskStatisticsMappers){
                UserTaskStatisticsModel userTaskStatisticsModel = new UserTaskStatisticsModel();
                BeanUtils.copyProperties(userTaskStatisticsMapper, userTaskStatisticsModel);
                userTaskStatisticsModels.add(userTaskStatisticsModel);
            }
            userTaskStatisticsService.insert(userTaskStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<UserTaskStatisticsMapper> list(String taskId) {
        List<Map<String, Object>> listMap = userTaskStatisticsService.listSum(taskId);
        List<UserTaskStatisticsMapper> userLoginStatusStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userLoginStatusStatisticsMappers.add(new UserTaskStatisticsMapper(null, null, null,null,null,null,null,null,null,null,null,  map.get("acceptCount") == null ? 0 : Integer.valueOf(map.get("acceptCount").toString()), map.get("cancelCount") == null ? 0 : Integer.valueOf(map.get("cancelCount").toString()),null));
        }
        return userLoginStatusStatisticsMappers;
    }
    
    @Override
    public long count() {
        return userTaskStatisticsService.count();
    }
    
    @Override
    public List<UserTaskStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName,String taskId) {
        List<Map<String, Object>> listMap = userTaskStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,taskId);
        List<UserTaskStatisticsMapper> userTaskStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userTaskStatisticsMappers.add(new UserTaskStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformFrom,taskId,null, map.get("acceptCount") == null ? 0 : Integer.valueOf(map.get("acceptCount").toString()), map.get("cancelCount") == null ? 0 : Integer.valueOf(map.get("cancelCount").toString()), null));
        }
        return userTaskStatisticsMappers;
    }
}
