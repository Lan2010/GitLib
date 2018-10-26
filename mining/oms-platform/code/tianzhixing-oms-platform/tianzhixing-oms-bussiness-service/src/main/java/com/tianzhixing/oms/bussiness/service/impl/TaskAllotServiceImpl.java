package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.TaskAllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/19.
 */
@Service("taskAllotService")
public class TaskAllotServiceImpl implements TaskAllotService {

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void add(List<TaskAllotModel> list) {
        for(TaskAllotModel taskAllotModel : list){
            taskAllotDao.add(taskAllotModel);
        }
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskAllotModel> list(Date date, Integer taskType, Integer taskStatus, String currentMachine) {
        return taskAllotDao.list(date, taskType, taskStatus, currentMachine);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count(Integer taskType, Date executeTime) {
        return taskAllotDao.count(taskType, executeTime);
    }
}
