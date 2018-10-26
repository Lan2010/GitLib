package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointConsumeStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointConsumeStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserStarPointConsumeStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserStarPointConsumeStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("RPCUserStarPointConsumeStatisticsService")
public class RPCUserStarPointConsumeStatisticsServiceImpl implements RPCUserStarPointConsumeStatisticsService {

    @Autowired
    private UserStarPointConsumeStatisticsService userStarPointConsumeStatisticsService;

    @Override
    public void insert(List<UserStarPointConsumeStatisticsMapper> userStarPointConsumeStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userStarPointConsumeStatisticsMappers != null) {
            List<UserStarPointConsumeStatisticsModel> userStarPointConsumeStatisticsModels = new ArrayList<>();
            for (UserStarPointConsumeStatisticsMapper userStarPointConsumeStatisticsMapper : userStarPointConsumeStatisticsMappers) {
                UserStarPointConsumeStatisticsModel userStarPointConsumeStatisticsModel = new UserStarPointConsumeStatisticsModel();
                BeanUtils.copyProperties(userStarPointConsumeStatisticsMapper, userStarPointConsumeStatisticsModel);
                userStarPointConsumeStatisticsModels.add(userStarPointConsumeStatisticsModel);
            }
            userStarPointConsumeStatisticsService.insert(userStarPointConsumeStatisticsModels, taskAllotMapper.getId());
        }
    }
}
