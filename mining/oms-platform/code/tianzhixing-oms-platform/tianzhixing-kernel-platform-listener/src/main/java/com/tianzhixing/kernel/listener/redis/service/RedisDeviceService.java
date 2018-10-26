package com.tianzhixing.kernel.listener.redis.service;

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
    private RedisTemplate<String, String> longRedisTemplate;

    public Set<String> accounts(String devid) {
        String relationKey = RedisKeyGenerate.generate(devid, RedisKeyConfig.ACCOUNTANDDEVICERELATION);
        return longRedisTemplate.opsForSet().members(relationKey);
    }
}
