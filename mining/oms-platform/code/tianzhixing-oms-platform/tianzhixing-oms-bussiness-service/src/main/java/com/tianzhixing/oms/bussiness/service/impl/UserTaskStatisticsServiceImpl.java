package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserTaskStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserTaskStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserTaskStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("userTaskStatisticsService")
public class UserTaskStatisticsServiceImpl implements UserTaskStatisticsService {

    @Autowired
    private UserTaskStatisticsDao userTaskStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserTaskStatisticsModel> userTaskStatisticsModels, Long taskAllotId) {
        for (UserTaskStatisticsModel userTaskStatisticsModel : userTaskStatisticsModels) {
            userTaskStatisticsDao.insert(userTaskStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
    public List<Map<String, Object>> listSum(String taskId) {
        return userTaskStatisticsDao.listSum(taskId);
    }
    
    @Override
    public long count() {
        return userTaskStatisticsDao.count();
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String taskId) {
        return userTaskStatisticsDao.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,taskId);
    }
}
