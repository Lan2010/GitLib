package com.tianzhixing.kernel.redis.service;

import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/29.
 */
@Service("redisUnCollectionStarPointRecordsService")
public class RedisUnCollectionStarPointRecordsService {

    @Autowired
    private RedisTemplate<String, DeviceMacCollectionStarPointRecords> recordsRedisTemplate;

    @Autowired
    private RedisTemplate<String, String> stringRedisTemplate;

    /**
     * 获取列表
     *
     * @param accountId
     * @return
     */
    public List<DeviceMacCollectionStarPointRecords> list(Long accountId) {
        String setKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTUNCOLLECTIONRECORDS);
        Set<String> keys = stringRedisTemplate.opsForSet().members(setKey);
        List<DeviceMacCollectionStarPointRecords> deviceMacCollectionStarPointRecords = new ArrayList<>();
        if (keys != null) {
            List<DeviceMacCollectionStarPointRecords> list = recordsRedisTemplate.opsForValue().multiGet(keys);
            if (list != null) {
                for (DeviceMacCollectionStarPointRecords dmc : list) {
                    if (dmc != null) {
                        deviceMacCollectionStarPointRecords.add(dmc);
                    }
                }
            }
        }
        return deviceMacCollectionStarPointRecords;
    }

    public List<DeviceMacCollectionStarPointRecords> list(Set<String> recordsToken) {
        List<DeviceMacCollectionStarPointRecords> deviceMacCollectionStarPointRecords = new ArrayList<>();
        List<DeviceMacCollectionStarPointRecords> list = recordsRedisTemplate.opsForValue().multiGet(recordsToken);
        if (list != null) {
            for (DeviceMacCollectionStarPointRecords dmc : list) {
                if (dmc != null) {
                    deviceMacCollectionStarPointRecords.add(dmc);
                }
            }
        }
        return deviceMacCollectionStarPointRecords;
    }

    public void remove(Long accountId, List<DeviceMacCollectionStarPointRecords> deviceMacCollectionStarPointRecordsInfoModelList) {
        //账户缓存
        String setKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTUNCOLLECTIONRECORDS);
        Set<String> records = new HashSet<>();
        for (DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords : deviceMacCollectionStarPointRecordsInfoModelList) {
            stringRedisTemplate.opsForSet().remove(setKey, deviceMacCollectionStarPointRecords.getRecordToken());
            //recordsRedisTemplate.delete(deviceMacCollectionStarPointRecords.getRecordToken());
            records.add(deviceMacCollectionStarPointRecords.getRecordToken());

        }
        recordsRedisTemplate.delete(records);
    }

    public void remove(Long accountId, String key) {
        String setKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTUNCOLLECTIONRECORDS);
        if (stringRedisTemplate.opsForSet().isMember(setKey, key)) {
            stringRedisTemplate.opsForSet().remove(setKey, key);
        }
    }

    public Set<String> keys(Long accountId) {
        String setKey = RedisKeyGenerate.generate(accountId.toString(), RedisKeyConfig.ACCOUNTUNCOLLECTIONRECORDS);
        return stringRedisTemplate.opsForSet().members(setKey);
    }

    public boolean checkExist(String key) {
        return recordsRedisTemplate.hasKey(key);
    }
}
