package com.tianzhixing.oms.bussiness.redis.service;

import com.tianzhixing.bussiness.commons.em.TaskStatus;
import com.tianzhixing.oms.bussiness.model.task.TaskInfoModel;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;
import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.bussiness.TaskLocationEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service
public class RedisTaskService {

    @Autowired
    private RedisTemplate<String, Serializable> redisTemplate;
    @Autowired
    private RedisTemplate<String, String> longRedisTemplate;

    public void cache(TaskInfoModel taskInfoModel, List<TaskLocationInfoModel> taskLocationInfoModelList) {
        TaskEntity taskEntity = new TaskEntity();
        taskEntity.setId(taskInfoModel.getId());
        taskEntity.setVersion(taskInfoModel.getVersion());
        taskEntity.setTaskName(taskInfoModel.getTaskName());
        taskEntity.setRate(new BigDecimal(taskInfoModel.getRate()).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
        taskEntity.setIsEnable(TaskStatus.ENABLE.equals(taskInfoModel.getTaskStatus()));
        taskEntity.setBeginTime(taskInfoModel.getBeginTime());
        taskEntity.setEndTime(taskInfoModel.getEndTime());
        taskEntity.setTaskRadius(taskInfoModel.getTaskRadius());
        Set<Long> set = new HashSet<>();
        String taskLocationSetKey = RedisKeyGenerate.generate(taskInfoModel.getId().toString(), RedisKeyConfig.TASK_LOCATION_ID_SET_KEY);
        if (longRedisTemplate.hasKey(taskLocationSetKey)) {
            longRedisTemplate.delete(taskLocationSetKey);
        }
        for (TaskLocationInfoModel taskLocationInfoModel : taskLocationInfoModelList) {
            set.add(taskLocationInfoModel.getId());
            _cacheLocation(taskLocationSetKey, taskLocationInfoModel);
        }
//        taskEntity.setLocationIdSet(set);
        String taskKey = RedisKeyGenerate.generate(taskInfoModel.getId().toString(), RedisKeyConfig.TASK_KEY);
        if (redisTemplate.hasKey(taskKey)) {
            redisTemplate.delete(taskKey);
        }
        redisTemplate.opsForValue().set(taskKey, taskEntity);
    }

    private void _cacheLocation(String taskLocationSetKey, TaskLocationInfoModel taskLocationInfoModel) {
        TaskLocationEntity taskLocationEntity = new TaskLocationEntity();
        taskLocationEntity.setEnable(taskLocationInfoModel.getEnable());
        taskLocationEntity.setVersion(taskLocationInfoModel.getVersion());
        taskLocationEntity.setId(taskLocationInfoModel.getId());
        taskLocationEntity.setLocation(taskLocationInfoModel.getLocation());
        taskLocationEntity.setName(taskLocationInfoModel.getName());
        taskLocationEntity.setTaskId(taskLocationInfoModel.getTaskId());
        String locationKey = RedisKeyGenerate.generate(taskLocationInfoModel.getId().toString(), RedisKeyConfig.TASKLOCATION_KEY);
        if (redisTemplate.hasKey(locationKey)) {
            redisTemplate.delete(locationKey);
        }
        redisTemplate.opsForValue().set(locationKey, taskLocationEntity);
        longRedisTemplate.opsForSet().add(taskLocationSetKey, taskLocationInfoModel.getId().toString());
    }

    public TaskEntity get(Long id) {
        String taskKey = RedisKeyGenerate.generate(id.toString(), RedisKeyConfig.TASK_KEY);
        return (TaskEntity) redisTemplate.opsForValue().get(taskKey);
    }

    public void remove(Long id) {
        String taskKey = RedisKeyGenerate.generate(id.toString(), RedisKeyConfig.TASK_KEY);
        if (redisTemplate.hasKey(taskKey)) {
            redisTemplate.delete(taskKey);
        }
        String taskLocationSetKey = RedisKeyGenerate.generate(id.toString(), RedisKeyConfig.TASK_LOCATION_ID_SET_KEY);
        Set<String> longSet = longRedisTemplate.opsForSet().members(taskLocationSetKey);
        if (longSet != null && !longSet.isEmpty()) {
            for (String key : longSet) {
                redisTemplate.delete(key);
            }
        }
        longRedisTemplate.delete(taskLocationSetKey);
    }

    public void cacheLocation(TaskLocationInfoModel taskLocationInfoModel) {
        String taskLocationSetKey = RedisKeyGenerate.generate(taskLocationInfoModel.getTaskId().toString(), RedisKeyConfig.TASK_LOCATION_ID_SET_KEY);
        _cacheLocation(taskLocationSetKey, taskLocationInfoModel);
    }
}
