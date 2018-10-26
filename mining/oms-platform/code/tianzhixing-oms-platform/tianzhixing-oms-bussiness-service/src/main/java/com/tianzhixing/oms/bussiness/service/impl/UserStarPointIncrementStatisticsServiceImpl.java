package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.UserStarPointIncrementStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointIncrementStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.UserStarPointIncrementStatisticsService;
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
@Service("userStarPointIncrementStatisticsService")
public class UserStarPointIncrementStatisticsServiceImpl implements UserStarPointIncrementStatisticsService {

    @Autowired
    private UserStarPointIncrementStatisticsDao userStarPointIncrementStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<UserStarPointIncrementStatisticsModel> userStarPointIncrementStatisticsModels, Long taskAllotId) {
        for (UserStarPointIncrementStatisticsModel userStarPointIncrementStatisticsModel : userStarPointIncrementStatisticsModels) {
            userStarPointIncrementStatisticsDao.insert(userStarPointIncrementStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
    public List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, Integer incrementType) {
        return userStarPointIncrementStatisticsDao.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,incrementType);
    }
    
    @Override
	public List<Map<String, Object>> listSum(int type,Integer hour, Integer day, Integer month,Integer year,String platformName) {
		return userStarPointIncrementStatisticsDao.list(type,hour,day,month,year,platformName);
	}

}
