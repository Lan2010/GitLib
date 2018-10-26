package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.MallUserOrderPayStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.MallUserOrderPayStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.MallUserOrderPayStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("mallUserOrderPayStatisticsService")
public class MallUserOrderPayStatisticsServiceImpl implements MallUserOrderPayStatisticsService {

    @Autowired
    private MallUserOrderPayStatisticsDao mallUserOrderPayStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<MallUserOrderPayStatisticsModel> mallUserOrderPayStatisticsModels, Long taskAllotId) {
        for (MallUserOrderPayStatisticsModel mallUserOrderPayStatisticsModel : mallUserOrderPayStatisticsModels) {
            mallUserOrderPayStatisticsDao.insert(mallUserOrderPayStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
   	public List<Map<String, Object>> listSum(int type,Integer hour, Integer day, Integer month,Integer year,String platformName) {
   		return mallUserOrderPayStatisticsDao.list(type,hour,day,month,year,platformName);
   	}
    
    @Override
	public List<Map<String, Object>> listSumByType(int type,Integer hour, Integer day, Integer month,Integer year,String value) {
		return mallUserOrderPayStatisticsDao.listSumByType(type,hour,day,month,year,value);
	}

	@Override
	public long count() {
		return mallUserOrderPayStatisticsDao.count();
	}
	
	@Override
	public List<Map<String, Object>> listByAppAndType(int type,Integer hour, Integer day, Integer month,Integer year,String platformName,String value) {
		return mallUserOrderPayStatisticsDao.listByAppAndType(type,hour,day,month,year,platformName,value);
	}

}
