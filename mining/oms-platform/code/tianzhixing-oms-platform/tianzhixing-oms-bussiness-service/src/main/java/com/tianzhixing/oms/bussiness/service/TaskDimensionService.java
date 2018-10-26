package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.TaskDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
public interface TaskDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<TaskDimensionModel> list(boolean enable);
}
