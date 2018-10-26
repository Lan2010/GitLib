package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserGreeterCardStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserGreeterCardStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserGreeterCardStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserGreeterCardStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("RPCUserGreeterCardStatisticsService")
public class RPCUserGreeterCardStatisticsServiceImpl implements RPCUserGreeterCardStatisticsService {

    @Autowired
    private UserGreeterCardStatisticsService userGreeterCardStatisticsService;

    @Override
    public void insert(List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userGreeterCardStatisticsMappers != null) {
            List<UserGreeterCardStatisticsModel> userGreeterCardStatisticsModels = new ArrayList<>();
            for (UserGreeterCardStatisticsMapper userGreeterCardStatisticsMapper : userGreeterCardStatisticsMappers) {
                UserGreeterCardStatisticsModel userGreeterCardStatisticsModel = new UserGreeterCardStatisticsModel();
                BeanUtils.copyProperties(userGreeterCardStatisticsMapper, userGreeterCardStatisticsModel);
                userGreeterCardStatisticsModels.add(userGreeterCardStatisticsModel);
            }
            userGreeterCardStatisticsService.insert(userGreeterCardStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<UserGreeterCardStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear) {
        List<Map<String, Object>> listMap = userGreeterCardStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear);
        List<UserGreeterCardStatisticsMapper> userGreeterCardStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userGreeterCardStatisticsMappers.add(new UserGreeterCardStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), null, null, map.get("createCount") == null ? 0 : Integer.valueOf(map.get("createCount").toString()), map.get("shareCount") == null ? 0 : Integer.valueOf(map.get("shareCount").toString()),null));
        }
        return userGreeterCardStatisticsMappers;
    }
}
