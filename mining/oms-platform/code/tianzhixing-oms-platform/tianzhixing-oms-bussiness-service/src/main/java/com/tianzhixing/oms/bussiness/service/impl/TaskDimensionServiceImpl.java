package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.TaskDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.TaskDimensionModel;
import com.tianzhixing.oms.bussiness.service.TaskDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
@Service
public class TaskDimensionServiceImpl implements TaskDimensionService {

    @Autowired
    private TaskDimensionDao taskDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<TaskDimensionModel> list(boolean enable) {
        return taskDimensionDao.list(enable);
    }
}
