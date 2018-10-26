package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.listener.cache.TaskCache;
import com.tianzhixing.kernel.listener.entity.TaskWithLocationEntity;
import com.tianzhixing.kernel.listener.redis.service.RedisTaskService;
import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.bussiness.TaskLocationEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service
public class TaskCacheService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RedisTaskService redisTaskService;

    public TaskWithLocationEntity taskInfo(String taskId) {
        TaskEntity taskEntity = _taskEntity(taskId);
//        return taskEntity == null ? null : new TaskWithLocationEntity(taskEntity, TaskCache.getLocation(taskId));
        return taskEntity == null ? null : new TaskWithLocationEntity(taskEntity, _taskLocation(taskId));
    }

    private TaskEntity _taskEntity(String taskId) {
        TaskEntity redisTaskEntity = redisTaskService.getByTaskId(taskId);
        if (redisTaskEntity == null) {
            logger.error("...can't found task entity from redis with id[" + taskId + "]...");
            return null;
        }
//        TaskEntity cacheTaskEntity = TaskCache.getTask(taskId);
//        if (cacheTaskEntity == null) {
//            //同步redis至缓存
//            TaskCache.put(taskId, redisTaskEntity);
//            //同步位置信息
//            _syncTaskLocation(taskId);
//        } else {
//            //比对版本号
//            if (cacheTaskEntity.getVersion() != cacheTaskEntity.getVersion()) {
//                //同步redis至缓存
//                TaskCache.put(taskId, redisTaskEntity);
//                //同步位置信息
//                _syncTaskLocation(taskId);
//            }
//        }
        return redisTaskEntity;
    }

    private List<TaskLocationEntity> _taskLocation(String taskId) {
        Set<String> set = redisTaskService.taskLocationIds(taskId);
        List<TaskLocationEntity> taskLocationEntityList = redisTaskService.taskLocations(set);
        return taskLocationEntityList == null ? new ArrayList<TaskLocationEntity>() : taskLocationEntityList;
    }

//    private void _syncTaskLocation(String taskId) {
//        //获取task location ids
//        Set<String> set = redisTaskService.taskLocationIds(taskId);
//        if (set != null) {
//            //获取集合
//            List<TaskLocationEntity> taskLocationEntityList = redisTaskService.taskLocations(set);
//            if (taskLocationEntityList != null && !taskLocationEntityList.isEmpty()) {
//                TaskCache.put(taskId, taskLocationEntityList);
//            }
//        }
//    }


}
