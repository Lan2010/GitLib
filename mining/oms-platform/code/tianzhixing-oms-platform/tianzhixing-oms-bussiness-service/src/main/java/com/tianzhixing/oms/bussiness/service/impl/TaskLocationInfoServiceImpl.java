package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.task.TaskLocationInfoDao;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;
import com.tianzhixing.oms.bussiness.service.TaskLocationInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service("taskLocationInfoService")
public class TaskLocationInfoServiceImpl implements TaskLocationInfoService {

    @Autowired
    private TaskLocationInfoDao taskLocationInfoDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return taskLocationInfoDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskLocationInfoModel> list(int from, int pageSize) {
        return taskLocationInfoDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count(Long taskId) {
        return taskLocationInfoDao.count(taskId);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskLocationInfoModel> list(Long taskId, int from, int pageSize) {
        return taskLocationInfoDao.list(taskId, from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskLocationInfoModel> list(Long taskId) {
        return taskLocationInfoDao.list(taskId);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public TaskLocationInfoModel add(TaskLocationInfoModel taskLocationInfoModel) {
        return taskLocationInfoDao.add(taskLocationInfoModel);
    }
}
