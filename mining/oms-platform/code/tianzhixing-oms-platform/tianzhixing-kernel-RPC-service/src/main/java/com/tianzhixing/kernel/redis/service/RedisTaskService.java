package com.tianzhixing.kernel.redis.service;

import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service("redisTaskService")
public class RedisTaskService {

    @Autowired
    private RedisTemplate<String, TaskEntity> redisTemplate;

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    public TaskEntity getByTaskId(String taskId) {
        String taskKey = RedisKeyGenerate.generate(taskId, RedisKeyConfig.TASK_KEY);
        return redisTemplate.opsForValue().get(taskKey);
    }

    public void cacheAccountAndTaskRelation(Long accountId, String taskId) {
        String relationKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTANDTASKRELATION);
        if (!stringRedisTemplate.opsForSet().isMember(relationKey, taskId)) {
            stringRedisTemplate.opsForSet().add(relationKey, taskId);
        }
    }

    public void removeAccountAndTaskRelation(Long accountId, String taskId) {
        String relationKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTANDTASKRELATION);
        if (stringRedisTemplate.opsForSet().isMember(relationKey, taskId)) {
            stringRedisTemplate.opsForSet().remove(relationKey, taskId);
        }
    }

    public Set<String> getByAccountId(Long accountId) {
        String relationKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTANDTASKRELATION);
        return stringRedisTemplate.opsForSet().members(relationKey);
    }
}
