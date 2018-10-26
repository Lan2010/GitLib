package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserRecordingStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserRecordingStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserRecordingStatisticsService;
import com.tianzhixing.oms.bussiness.service.UserRecordingStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("RPCUserRecordingStatisticsService")
public class RPCUserRecordingStatisticsServiceImpl implements RPCUserRecordingStatisticsService {

    @Autowired
    private UserRecordingStatisticsService userRecordingStatisticsService;

    @Override
    public void insert(List<UserRecordingStatisticsMapper> userRecordingStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (userRecordingStatisticsMappers != null) {
            List<UserRecordingStatisticsModel> userRecordingStatisticsModels = new ArrayList<>();
            for (UserRecordingStatisticsMapper userRecordingStatisticsMapper : userRecordingStatisticsMappers) {
                UserRecordingStatisticsModel userRecordingStatisticsModel = new UserRecordingStatisticsModel();
                BeanUtils.copyProperties(userRecordingStatisticsMapper, userRecordingStatisticsModel);
                userRecordingStatisticsModels.add(userRecordingStatisticsModel);
            }
            userRecordingStatisticsService.insert(userRecordingStatisticsModels, taskAllotMapper.getId());
        }
    }
}
