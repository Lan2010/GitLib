package com.tianzhixing.kernel.service.impl;

import com.tianzhixing.kernel.commons.em.TaskStatus;
import com.tianzhixing.kernel.dao.task.TaskInfoDao;
import com.tianzhixing.kernel.model.task.TaskInfoModel;
import com.tianzhixing.kernel.service.TaskInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("taskInfoService")
public class TaskInfoServiceImpl implements TaskInfoService {

    @Autowired
    private TaskInfoDao taskInfoDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public TaskInfoModel getByAccountAndTaskId(Long accountId, String taskId) {
        return taskInfoDao.getByAccountAndTaskId(accountId, taskId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TaskInfoModel add(TaskInfoModel taskInfoModel) {
        return taskInfoDao.add(taskInfoModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateStatus(Long id, TaskStatus status, Date updateTime, Date operationTime, Integer version) {
        taskInfoDao.updateStatus(id, status, updateTime, operationTime, version);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskInfoModel> listTaskByAccountId(long accountId, TaskStatus taskStatus) {
        return taskInfoDao.listTaskIdsByAccountId(accountId, taskStatus);
    }
}
