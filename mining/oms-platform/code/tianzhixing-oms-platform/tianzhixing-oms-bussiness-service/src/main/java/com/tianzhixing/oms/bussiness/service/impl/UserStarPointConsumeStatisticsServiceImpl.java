package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserStarPointConsumeStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointConsumeStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserStarPointConsumeStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("userStarPointConsumeStatisticsService")
public class UserStarPointConsumeStatisticsServiceImpl implements UserStarPointConsumeStatisticsService {

    @Autowired
    private UserStarPointConsumeStatisticsDao userStarPointConsumeStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserStarPointConsumeStatisticsModel> userStarPointConsumeStatisticsModels, Long taskAllotId) {
        for (UserStarPointConsumeStatisticsModel userStarPointConsumeStatisticsModel : userStarPointConsumeStatisticsModels) {
            userStarPointConsumeStatisticsDao.insert(userStarPointConsumeStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
}
