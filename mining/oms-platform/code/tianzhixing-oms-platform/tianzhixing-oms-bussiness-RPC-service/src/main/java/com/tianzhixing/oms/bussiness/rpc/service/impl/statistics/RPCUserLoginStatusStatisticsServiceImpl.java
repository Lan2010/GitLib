package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserLoginStatusStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserLoginStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserLoginStatusStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserLoginStatusStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("RPCUserLoginStatusStatisticsService")
public class RPCUserLoginStatusStatisticsServiceImpl implements RPCUserLoginStatusStatisticsService {

    @Autowired
    private UserLoginStatusStatisticsService userLoginStatusStatisticsService;

    @Override
    public void insert(List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userLoginStatusStatisticsMappers != null) {
            List<UserLoginStatusStatisticsModel> userLoginStatusStatisticsModels = new ArrayList<>();
            for (UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper : userLoginStatusStatisticsMappers) {
                UserLoginStatusStatisticsModel userLoginStatusStatisticsModel = new UserLoginStatusStatisticsModel();
                BeanUtils.copyProperties(userLoginStatusStatisticsMapper, userLoginStatusStatisticsModel);
                userLoginStatusStatisticsModels.add(userLoginStatusStatisticsModel);
            }
            userLoginStatusStatisticsService.insert(userLoginStatusStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<UserLoginStatusStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName) {
        List<Map<String, Object>> listMap = userLoginStatusStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom);
        List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userLoginStatusStatisticsMappers.add(new UserLoginStatusStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformFrom, map.get("userLoginCount") == null ? 0 : Integer.valueOf(map.get("userLoginCount").toString()), map.get("userLoginDiffAccountCount") == null ? 0 : Integer.valueOf(map.get("userLoginDiffAccountCount").toString()),null,null, map.get("userOnlineCount") == null ? 0 : Integer.valueOf(map.get("userOnlineCount").toString()),platformName));
        }
        return userLoginStatusStatisticsMappers;
    }
}
