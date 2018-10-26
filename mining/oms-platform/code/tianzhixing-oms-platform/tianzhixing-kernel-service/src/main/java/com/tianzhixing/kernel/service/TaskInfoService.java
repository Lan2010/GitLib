package com.tianzhixing.kernel.service;

import com.tianzhixing.kernel.commons.em.TaskStatus;
import com.tianzhixing.kernel.model.task.TaskInfoModel;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface TaskInfoService {

    /**
     * 根据account & task 获取记录
     *
     * @param accountId
     * @param taskId
     * @return
     */
    TaskInfoModel getByAccountAndTaskId(Long accountId, String taskId);

    /**
     * 添加一条记录
     *
     * @param taskInfoModel
     */
    TaskInfoModel add(TaskInfoModel taskInfoModel);

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @param updateTime
     * @param operationTime
     * @param version
     */
    void updateStatus(Long id, TaskStatus status, Date updateTime, Date operationTime, Integer version);

    /**
     * 根据accountid&status 获取taskid清单
     *
     * @param accountId
     * @param taskStatus
     * @return
     */
    List<TaskInfoModel> listTaskByAccountId(long accountId, TaskStatus taskStatus);
}
