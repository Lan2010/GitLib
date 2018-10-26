package com.tianzhixing.kernel.redis.service;

import com.tianzhixing.oms.redis.bussiness.AdvertisementEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/28.
 */
@Service("redisAdvertisementService")
public class RedisAdvertisementService {

    @Autowired
    private RedisTemplate<String, AdvertisementEntity> redisTemplate;

    public AdvertisementEntity getByAdvertisementId(String advertId) {
        String key = RedisKeyGenerate.generate(advertId, RedisKeyConfig.ADVERTISEMENT_KEY);
        return redisTemplate.opsForValue().get(key);
    }
}
