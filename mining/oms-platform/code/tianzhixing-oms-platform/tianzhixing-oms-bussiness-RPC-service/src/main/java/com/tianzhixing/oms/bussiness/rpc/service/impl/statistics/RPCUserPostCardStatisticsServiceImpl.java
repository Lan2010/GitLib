package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserPostCardStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserPostCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserPostCardStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserPostCardStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("RPCUserPostCardStatisticsService")
public class RPCUserPostCardStatisticsServiceImpl implements RPCUserPostCardStatisticsService {

    @Autowired
    private UserPostCardStatisticsService userPostCardStatisticsService;

    @Override
    public void insert(List<UserPostCardStatisticsMapper> userPostCardStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userPostCardStatisticsMappers != null) {
            List<UserPostCardStatisticsModel> userPostCardStatisticsModels = new ArrayList<>();
            for (UserPostCardStatisticsMapper userPostCardStatisticsMapper : userPostCardStatisticsMappers) {
                UserPostCardStatisticsModel userPostCardStatisticsModel = new UserPostCardStatisticsModel();
                BeanUtils.copyProperties(userPostCardStatisticsMapper, userPostCardStatisticsModel);
                userPostCardStatisticsModels.add(userPostCardStatisticsModel);
            }
            userPostCardStatisticsService.insert(userPostCardStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<UserPostCardStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear) {
        List<Map<String, Object>> listMap = userPostCardStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear);
        List<UserPostCardStatisticsMapper> userPostCardStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userPostCardStatisticsMappers.add(new UserPostCardStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), null, null, map.get("createCount") == null ? 0 : Integer.valueOf(map.get("createCount").toString()), map.get("shareCount") == null ? 0 : Integer.valueOf(map.get("shareCount").toString()),null));
        }
        return userPostCardStatisticsMappers;
    }
}
