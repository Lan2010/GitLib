package com.tianzhixing.kernel.listener.redis.service;

import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

/**
 * Created by routine.k on 2018/6/29.
 */
@Service("redisUnCollectionStarPointRecordsService")
public class RedisUnCollectionStarPointRecordsService {

    @Autowired
    private RedisTemplate<String, DeviceMacCollectionStarPointRecords> recordsRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    public void cache(DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords) {
        //添加账户缓存
        String setKey = RedisKeyGenerate.generate(deviceMacCollectionStarPointRecords.getAccountId(), RedisKeyConfig.ACCOUNTUNCOLLECTIONRECORDS);
        if (!stringRedisTemplate.opsForSet().isMember(setKey, deviceMacCollectionStarPointRecords.getRecordToken())) {
            stringRedisTemplate.opsForSet().add(setKey, deviceMacCollectionStarPointRecords.getRecordToken());
        }
        recordsRedisTemplate.opsForValue().set(deviceMacCollectionStarPointRecords.getRecordToken(), deviceMacCollectionStarPointRecords, deviceMacCollectionStarPointRecords.getTimeoutHour(), TimeUnit.HOURS);
    }

}
