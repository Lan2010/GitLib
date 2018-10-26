package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserAdvertisementStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserAdvertisementStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserAdvertisementStatisticsService;
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
@Service("userAdvertisementStatisticsService")
public class UserAdvertisementStatisticsServiceImpl implements UserAdvertisementStatisticsService {

    @Autowired
    private UserAdvertisementStatisticsDao userAdvertisementStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserAdvertisementStatisticsModel> userAdvertisementStatisticsModels, Long taskAllotId) {
        for (UserAdvertisementStatisticsModel userAdvertisementStatisticsModel : userAdvertisementStatisticsModels) {
            userAdvertisementStatisticsDao.insert(userAdvertisementStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
    public List<Map<String, Object>> listSum(String advId) {
        return userAdvertisementStatisticsDao.listSum(advId);
    }
    
    @Override
    public long count() {
        return userAdvertisementStatisticsDao.count();
    }
}
