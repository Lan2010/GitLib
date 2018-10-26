package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserBasicStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserBasicStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserBasicStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserBasicStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("RPCUserBasicStatisticsService")
public class RPCUserBasicStatisticsServiceImpl implements RPCUserBasicStatisticsService {

    @Autowired
    private UserBasicStatisticsService userBasicStatisticsService;

    @Override
    public void insert(List<UserBasicStatisticsMapper> userBasicStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userBasicStatisticsMappers != null) {
            List<UserBasicStatisticsModel> userBasicStatisticsModels = new ArrayList<>();
            for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMappers) {
                UserBasicStatisticsModel userBasicStatisticsModel = new UserBasicStatisticsModel();
                BeanUtils.copyProperties(userBasicStatisticsMapper, userBasicStatisticsModel);
                userBasicStatisticsModels.add(userBasicStatisticsModel);
            }
            userBasicStatisticsService.insert(userBasicStatisticsModels, taskAllotMapper.getId());
        }
    }

    @Override
    public UserBasicStatisticsMapper get(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType) {
        Map<String, Object> map = userBasicStatisticsService.getSum(statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, userFromType);
        return new UserBasicStatisticsMapper(null, null, null, statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, map.get("userCount") == null ? 0 : Integer.valueOf(map.get("userCount").toString()), map.get("userFromType") != null ? map.get("userFromType").toString() : null, map.get("userFromTypeName") != null ? map.get("userFromTypeName").toString() : null, null);
    }

    @Override
    public List<UserBasicStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String userFromType, String userFromTypeName) {
        List<Map<String, Object>> listMap = userBasicStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, userFromType);
        List<UserBasicStatisticsMapper> userBasicStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userBasicStatisticsMappers.add(new UserBasicStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformFrom, map.get("userCount") == null ? 0 : Integer.valueOf(map.get("userCount").toString()), userFromType, userFromTypeName, null));
        }
        return userBasicStatisticsMappers;
    }
}
