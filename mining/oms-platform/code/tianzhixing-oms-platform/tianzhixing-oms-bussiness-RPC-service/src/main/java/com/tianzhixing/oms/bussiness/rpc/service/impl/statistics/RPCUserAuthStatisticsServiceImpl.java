package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserAuthStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAuthStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserAuthStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("RPCUserAuthStatisticsService")
public class RPCUserAuthStatisticsServiceImpl implements RPCUserAuthStatisticsService {

    @Autowired
    private UserAuthStatisticsService userAuthStatisticsService;

    @Override
    public void insert(List<UserAuthStatisticsMapper> userAuthStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userAuthStatisticsMappers != null) {
            List<UserAuthStatisticsModel> userAuthStatisticsModels = new ArrayList<>();
            for (UserAuthStatisticsMapper userAuthStatisticsMapper : userAuthStatisticsMappers) {
                UserAuthStatisticsModel userAuthStatisticsModel = new UserAuthStatisticsModel();
                BeanUtils.copyProperties(userAuthStatisticsMapper, userAuthStatisticsModel);
                userAuthStatisticsModels.add(userAuthStatisticsModel);
            }
            userAuthStatisticsService.insert(userAuthStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<UserAuthStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName , String authType) {
        List<Map<String, Object>> listMap = userAuthStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, authType);
        List<UserAuthStatisticsMapper> userAuthStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userAuthStatisticsMappers.add(new UserAuthStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformFrom, null, map.get("sucAuthCount") == null ? 0 : Integer.valueOf(map.get("sucAuthCount").toString()), map.get("authCount") == null ? 0 : Integer.valueOf(map.get("authCount").toString()), null,authType, platformName));
        }
        return userAuthStatisticsMappers;
    }
}
