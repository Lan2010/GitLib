package com.tianzhixing.kernel.listener.redis.service;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service("redisSystemParamService")
public class RedisSystemParamService {

    @Autowired
    private RedisTemplate<String, SystemParamEntity> redisTemplate;

    @Autowired
    private RedisTemplate<String ,Object> redisTemplate1;

    public SystemParamEntity rate() {
        String key = RedisKeyGenerate.generate(SystemParamType.DEFAULTRATE.name(), RedisKeyConfig.SYSTEMPARAM_KEY);
        return redisTemplate.opsForValue().get(key);
    }

    public SystemParamEntity timeoutHour() {
        String key = RedisKeyGenerate.generate(SystemParamType.STARPOINTLOST.name(), RedisKeyConfig.SYSTEMPARAM_KEY);
        return redisTemplate.boundValueOps(key).get();
    }
}
