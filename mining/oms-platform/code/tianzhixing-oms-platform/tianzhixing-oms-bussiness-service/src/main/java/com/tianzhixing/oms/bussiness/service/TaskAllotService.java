package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/19.
 */
public interface TaskAllotService {

    /**
     * 添加
     *
     * @param list
     */
    void add(List<TaskAllotModel> list);

    /**
     * 获取清单
     *
     * @param date
     * @param taskType
     * @param taskStatus
     * @param currentMachine
     * @return
     */
    List<TaskAllotModel> list(Date date, Integer taskType, Integer taskStatus, String currentMachine);

    /**
     * 获取任务数量
     *
     * @param taskType
     * @param executeTime
     * @return
     */
    long count(Integer taskType, Date executeTime);
}
