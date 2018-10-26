package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.DeviceOnlineStatusStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceOnlineStatusStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.DeviceOnlineStatusStatisticsService;
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
@Service("deviceOnlineStatusStatisticsService")
public class DeviceOnlineStatusStatisticsServiceImpl implements DeviceOnlineStatusStatisticsService {

	@Autowired
	private TaskAllotDao taskAllotDao;

	@Autowired
	private DeviceOnlineStatusStatisticsDao deviceOnlineStatusStatisticsDao;

	@Override
	@Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void insert(List<DeviceOnlineStatusStatisticsModel> deviceOnlineStatusStatisticsModels, Long taskAllotId) {
		for (DeviceOnlineStatusStatisticsModel deviceOnlineStatusStatisticsModel : deviceOnlineStatusStatisticsModels) {
			deviceOnlineStatusStatisticsDao.insert(deviceOnlineStatusStatisticsModel);
		}
		TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
		taskAllotModel.setUpdateTime(new Date());
		taskAllotModel.setTaskStatus(1);
		taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
	}

	@Override
	public List<Map<String, Object>> listSum(int type,Integer hour, Integer day, Integer month,Integer year,String value) {
		return deviceOnlineStatusStatisticsDao.listSum(type,hour,day,month,year,value);
	}

	@Override
	public long count() {
		return deviceOnlineStatusStatisticsDao.count();
	}
}
