package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.DeviceCheckinStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceCheckinStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.DeviceCheckinStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("deviceCheckinStatisticsService")
public class DeviceCheckinStatisticsServiceImpl implements DeviceCheckinStatisticsService {

    @Autowired
    private DeviceCheckinStatisticsDao deviceCheckinStatisticsDao;

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<DeviceCheckinStatisticsModel> deviceCheckinStatisticsModels, Long taskAllotId) {
        for (DeviceCheckinStatisticsModel deviceCheckinStatisticsModel : deviceCheckinStatisticsModels) {
            deviceCheckinStatisticsDao.insert(deviceCheckinStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
}
