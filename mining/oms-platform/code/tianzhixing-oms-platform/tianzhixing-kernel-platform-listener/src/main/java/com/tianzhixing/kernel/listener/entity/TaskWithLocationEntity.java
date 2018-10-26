package com.tianzhixing.kernel.listener.entity;

import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.bussiness.TaskLocationEntity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by routine.k on 2018/6/26.
 */
public class TaskWithLocationEntity implements Serializable {

    private TaskEntity taskEntity;
    private List<TaskLocationEntity> taskLocationEntityList;

    public TaskWithLocationEntity(TaskEntity taskEntity, List<TaskLocationEntity> taskLocationEntityList) {
        this.taskEntity = taskEntity;
        this.taskLocationEntityList = taskLocationEntityList;
    }

    public TaskEntity getTaskEntity() {
        return taskEntity;
    }

    public void setTaskEntity(TaskEntity taskEntity) {
        this.taskEntity = taskEntity;
    }

    public List<TaskLocationEntity> getTaskLocationEntityList() {
        return taskLocationEntityList;
    }

    public void setTaskLocationEntityList(List<TaskLocationEntity> taskLocationEntityList) {
        this.taskLocationEntityList = taskLocationEntityList;
    }
}
