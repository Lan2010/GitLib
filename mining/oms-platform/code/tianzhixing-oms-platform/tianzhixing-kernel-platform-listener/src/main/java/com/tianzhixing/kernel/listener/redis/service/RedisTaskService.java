package com.tianzhixing.kernel.listener.redis.service;

import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.bussiness.TaskLocationEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service("redisTaskService")
public class RedisTaskService {

    @Autowired
    private RedisTemplate<String, TaskEntity> redisTemplate;

    @Autowired
    private RedisTemplate<String, TaskLocationEntity> taskLocationEntityRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> longRedisTemplate;

    public TaskEntity getByTaskId(String taskId) {
        String taskKey = RedisKeyGenerate.generate(taskId, RedisKeyConfig.TASK_KEY);
        return redisTemplate.opsForValue().get(taskKey);
    }

    public Set<String> taskIds(String accountId) {
        String relationKey = RedisKeyGenerate.generate(accountId, RedisKeyConfig.ACCOUNTANDTASKRELATION);
        return longRedisTemplate.opsForSet().members(relationKey);
    }

    public Set<String> taskLocationIds(String taskId) {
        String taskLocationSetKey = RedisKeyGenerate.generate(taskId, RedisKeyConfig.TASK_LOCATION_ID_SET_KEY);
        return longRedisTemplate.opsForSet().members(taskLocationSetKey);
    }

    public List<TaskLocationEntity> taskLocations(Set<String> taskLocationIds) {
        Set<String> keys = new HashSet<>();
        for(String id : taskLocationIds){
            keys.add(RedisKeyGenerate.generate(id, RedisKeyConfig.TASKLOCATION_KEY));
        }
        return taskLocationEntityRedisTemplate.opsForValue().multiGet(keys);
    }
}
