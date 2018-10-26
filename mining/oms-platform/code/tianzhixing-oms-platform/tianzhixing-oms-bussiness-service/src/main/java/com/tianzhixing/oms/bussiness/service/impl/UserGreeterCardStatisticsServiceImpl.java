package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserGreeterCardStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserGreeterCardStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserGreeterCardStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/11.
 */
@Service("userGreeterCardStatisticsService")
public class UserGreeterCardStatisticsServiceImpl implements UserGreeterCardStatisticsService {

    @Autowired
    private UserGreeterCardStatisticsDao userGreeterCardStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserGreeterCardStatisticsModel> userGreeterCardStatisticsModels, Long taskAllotId) {
        for (UserGreeterCardStatisticsModel userGreeterCardStatisticsModel : userGreeterCardStatisticsModels) {
            userGreeterCardStatisticsDao.insert(userGreeterCardStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
    public List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear) {
        return userGreeterCardStatisticsDao.listSum(statisticsDay, statisticsMonth, statisticsYear);
    }
}
