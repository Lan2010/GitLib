package com.tianzhixing.kernel.redis.service;

import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service("redisDeviceService")
public class RedisDeviceService {

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    public void cacheAccountAndDeviceRelation(Long accountId, String deviceId) {
        String relationKey = RedisKeyGenerate.generate(deviceId, RedisKeyConfig.ACCOUNTANDDEVICERELATION);
        if (!stringRedisTemplate.opsForSet().isMember(relationKey, accountId.toString())) {
            stringRedisTemplate.opsForSet().add(relationKey, accountId.toString());
        }
    }

    public void removeAccountAndDeviceRelation(Long accountId, String deviceId) {
        String relationKey = RedisKeyGenerate.generate(deviceId, RedisKeyConfig.ACCOUNTANDDEVICERELATION);
        if (stringRedisTemplate.opsForSet().isMember(relationKey, accountId.toString())) {
            stringRedisTemplate.opsForSet().remove(relationKey, accountId.toString());
        }
    }

    public Set<String> get(String deviceId) {
        String relationKey = RedisKeyGenerate.generate(deviceId, RedisKeyConfig.ACCOUNTANDDEVICERELATION);
        return stringRedisTemplate.opsForSet().members(relationKey);
    }
}
