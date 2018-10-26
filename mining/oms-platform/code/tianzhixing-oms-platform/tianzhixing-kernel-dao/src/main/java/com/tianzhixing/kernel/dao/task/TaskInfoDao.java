package com.tianzhixing.kernel.dao.task;

import com.tianzhixing.kernel.commons.em.TaskStatus;
import com.tianzhixing.kernel.dao.generic.GenericBaseDao;
import com.tianzhixing.kernel.model.task.TaskInfoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
@Repository("taskInfoDao")
public class TaskInfoDao extends GenericBaseDao<TaskInfoModel, Long> {

    /**
     * 根据account & task 获取记录
     *
     * @param accountId
     * @param taskId
     * @return
     */
    public TaskInfoModel getByAccountAndTaskId(final Long accountId, final String taskId) {
        return super.get(new String[]{"account_id", "task_id"}, new Object[]{accountId, taskId});
    }

    /**
     * 添加
     *
     * @param taskInfoModel
     * @return
     */
    public TaskInfoModel add(final TaskInfoModel taskInfoModel) {
        long id = super.insert(taskInfoModel);
        taskInfoModel.setId(id);
        return taskInfoModel;
    }

    /**
     * 更新状态
     *
     * @param id
     * @param status
     * @param updateTime
     * @param operationTime
     * @param version
     */
    public void updateStatus(Long id, TaskStatus status, Date updateTime, Date operationTime, Integer version) {
        super.update(id, new String[]{"task_status", "update_time", "operation_time"}, new Object[]{status.name(), updateTime, operationTime}, version);
    }

    /**
     * 根据account id & task status获取
     *
     * @param accountId
     * @param taskStatus
     * @return
     */
    public List<TaskInfoModel> listTaskIdsByAccountId(final Long accountId, final TaskStatus taskStatus) {
        return super.list(new String[]{"account_id", "task_status"}, new Object[]{accountId, taskStatus.name()});
    }
}
