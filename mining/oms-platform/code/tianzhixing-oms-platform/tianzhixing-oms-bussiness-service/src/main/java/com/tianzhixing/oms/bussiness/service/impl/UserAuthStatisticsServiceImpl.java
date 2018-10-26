package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserAuthStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserAuthStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserAuthStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("userAuthStatisticsService")
public class UserAuthStatisticsServiceImpl implements UserAuthStatisticsService {

    @Autowired
    private UserAuthStatisticsDao userAuthStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserAuthStatisticsModel> userAuthStatisticsModels, Long taskAllotId) {
        for (UserAuthStatisticsModel userAuthStatisticsModel : userAuthStatisticsModels) {
            userAuthStatisticsDao.insert(userAuthStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
    public List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String authType) {
        return userAuthStatisticsDao.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom, authType);
    }
}
