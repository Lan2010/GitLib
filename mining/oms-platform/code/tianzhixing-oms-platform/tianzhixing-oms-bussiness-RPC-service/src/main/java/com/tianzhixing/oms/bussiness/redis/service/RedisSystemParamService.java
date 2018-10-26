package com.tianzhixing.oms.bussiness.redis.service;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.bussiness.model.system.SystemParamModel;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/25.
 */
@Service
public class RedisSystemParamService {

    @Autowired
    private RedisTemplate<String, SystemParamEntity> redisTemplate;

    public void cache(SystemParamModel systemParamModel){
        SystemParamEntity systemParamEntity = new SystemParamEntity();
        systemParamEntity.setId(systemParamModel.getId());
        systemParamEntity.setSystemParamType(systemParamModel.getSystemParamType().name());
        systemParamEntity.setSystemValue(systemParamModel.getSystemValue());
        systemParamEntity.setVersion(systemParamModel.getVersion());
        systemParamEntity.setEnable(true);
        String key = RedisKeyGenerate.generate(systemParamModel.getSystemParamType().name(), RedisKeyConfig.SYSTEMPARAM_KEY);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, systemParamEntity);
    }

    public SystemParamEntity get(SystemParamType systemParamType){
        String key = RedisKeyGenerate.generate(systemParamType.name(), RedisKeyConfig.SYSTEMPARAM_KEY);
        return redisTemplate.opsForValue().get(key);
    }
}
