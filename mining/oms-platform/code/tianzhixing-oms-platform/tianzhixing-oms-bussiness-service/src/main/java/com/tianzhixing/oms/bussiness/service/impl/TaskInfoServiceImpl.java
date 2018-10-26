package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.TaskDimensionDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskInfoDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskLocationInfoDao;
import com.tianzhixing.oms.bussiness.model.statistics.TaskDimensionModel;
import com.tianzhixing.oms.bussiness.model.task.TaskInfoModel;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;
import com.tianzhixing.oms.bussiness.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service("taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    private TaskInfoDao taskInfoDao;

    @Autowired
    private TaskLocationInfoDao taskLocationInfoDao;

    @Autowired
    private TaskDimensionDao taskDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TaskInfoModel getById(Long taskId) {
        return taskInfoDao.getById(taskId);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return taskInfoDao.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskInfoModel> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize) {
        return taskInfoDao.list(sBeginTime, sEndTime, aBeginTime, aEndTime, from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskInfoModel> list() {
        return taskInfoDao.list();
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TaskInfoModel add(TaskInfoModel taskInfoModel, List<TaskLocationInfoModel> taskLocationInfoModels) {
        taskInfoModel = taskInfoDao.add(taskInfoModel);
        for (TaskLocationInfoModel taskLocationInfoModel : taskLocationInfoModels) {
            taskLocationInfoModel.setTaskId(taskInfoModel.getId());
            taskLocationInfoDao.add(taskLocationInfoModel);
        }
        //添加统计维度
        TaskDimensionModel taskDimensionModel = new TaskDimensionModel();
        taskDimensionModel.setVersion(0);
        taskDimensionModel.setCreateTime(taskInfoModel.getCreateTime());
        taskDimensionModel.setEnable(true);
        taskDimensionModel.setTaskId(taskInfoModel.getId().toString());
        taskDimensionModel.setBeginTime(taskInfoModel.getBeginTime());
        taskDimensionModel.setEndTime(taskInfoModel.getEndTime());
        taskDimensionModel.setName(taskInfoModel.getTaskName());
        taskDimensionDao.insert(taskDimensionModel);
        return taskInfoModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateIsSend(boolean isSend, TaskInfoModel taskInfoModel) {
        taskInfoDao.updateIsSend(isSend, taskInfoModel.getId(), taskInfoModel.getVersion());
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(TaskInfoModel taskInfoModel) {
        taskInfoDao.update(taskInfoModel.getId(), taskInfoModel.getVersion(), taskInfoModel);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Long maxId() {
        return taskInfoDao.maxId();
    }
}
