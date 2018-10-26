package com.tianzhixing.kernel.listener.cache;

import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.bussiness.TaskLocationEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/26.
 */
public class TaskCache {

    private static Map<String, TaskEntity> TASK_CACHE = new HashMap<>();
    private static Map<String, List<TaskLocationEntity>> TASK_LOCATION_CACHE = new HashMap<>();

    public static TaskEntity getTask(String taskId) {
        return TASK_CACHE.get(taskId);
    }

    public static List<TaskLocationEntity> getLocation(String taskId) {
        return TASK_LOCATION_CACHE.get(taskId);
    }

    public static void put(String taskId, TaskEntity taskEntity) {
        TASK_CACHE.put(taskId, taskEntity);
    }

    public static void put(String taskId, List<TaskLocationEntity> taskLocationEntityList) {
        TASK_LOCATION_CACHE.put(taskId, taskLocationEntityList);
    }
}
