package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserAdvertisementStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAdvertisementStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAdvertisementStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserAdvertisementStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCUserAdvertisementStatisticsService")
public class RPCUserAdvertisementStatisticsServiceImpl implements RPCUserAdvertisementStatisticsService {

    @Autowired
    private UserAdvertisementStatisticsService userAdvertisementStatisticsService;

    @Override
    public void insert(List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userAdvertisementStatisticsMappers != null) {
            List<UserAdvertisementStatisticsModel> userAdvertisementStatisticsModels = new ArrayList<>();
            for(UserAdvertisementStatisticsMapper userAdvertisementStatisticsMapper : userAdvertisementStatisticsMappers){
                UserAdvertisementStatisticsModel userAdvertisementStatisticsModel = new UserAdvertisementStatisticsModel();
                BeanUtils.copyProperties(userAdvertisementStatisticsMapper, userAdvertisementStatisticsModel);
                userAdvertisementStatisticsModels.add(userAdvertisementStatisticsModel);
            }
            userAdvertisementStatisticsService.insert(userAdvertisementStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public List<UserAdvertisementStatisticsMapper> list(String advId) {
        List<Map<String, Object>> listMap = userAdvertisementStatisticsService.listSum(advId);
        List<UserAdvertisementStatisticsMapper> userLoginStatusStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            userLoginStatusStatisticsMappers.add(new UserAdvertisementStatisticsMapper(null, null, null,null,null,null,null,null,null,null,null,  map.get("accessCount") == null ? 0 : Integer.valueOf(map.get("accessCount").toString()), map.get("accessDiffIPCount") == null ? 0 : Integer.valueOf(map.get("accessDiffIPCount").toString()),map.get("clickCount") == null ? 0 : Integer.valueOf(map.get("clickCount").toString()),map.get("clickDiffIPCount") == null ? 0 : Integer.valueOf(map.get("clickDiffIPCount").toString()),null));
        }
        return userLoginStatusStatisticsMappers;
    }
    
    @Override
    public long count() {
        return userAdvertisementStatisticsService.count();
    }

}
