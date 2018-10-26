package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserRecordingStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserPostCardStatisticsModel;
import com.tianzhixing.oms.bussiness.model.statistics.UserRecordingStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserRecordingStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("userRecordingStatisticsService")
public class UserRecordingStatisticsServiceImpl implements UserRecordingStatisticsService {

    @Autowired
    private UserRecordingStatisticsDao userRecordingStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserRecordingStatisticsModel> userRecordingStatisticsModels, Long taskAllotId) {
        for (UserRecordingStatisticsModel userRecordingStatisticsModel : userRecordingStatisticsModels) {
            userRecordingStatisticsDao.insert(userRecordingStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
}
