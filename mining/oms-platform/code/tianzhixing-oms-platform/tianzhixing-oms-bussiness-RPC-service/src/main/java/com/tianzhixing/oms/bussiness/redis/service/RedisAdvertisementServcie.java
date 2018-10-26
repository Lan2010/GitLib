package com.tianzhixing.oms.bussiness.redis.service;

import com.tianzhixing.bussiness.commons.em.AdvertisementStatus;
import com.tianzhixing.oms.bussiness.model.advertisement.AdvertisementInfoModel;
import com.tianzhixing.oms.redis.bussiness.AdvertisementEntity;
import com.tianzhixing.oms.redis.config.RedisKeyConfig;
import com.tianzhixing.oms.redis.config.RedisKeyGenerate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * Created by routine.k on 2018/6/25.
 */
@Service
public class RedisAdvertisementServcie {

    @Autowired
    private RedisTemplate<String, AdvertisementEntity> redisTemplate;


    public void cache(AdvertisementInfoModel advertisementInfoModel) {
        AdvertisementEntity advertisementEntity = new AdvertisementEntity();
        advertisementEntity.setBeginTime(advertisementInfoModel.getBeginTime());
        advertisementEntity.setEnable(AdvertisementStatus.ENABLE.equals(advertisementInfoModel.getAdvertStatus()));
        advertisementEntity.setEndTime(advertisementInfoModel.getEndTime());
        advertisementEntity.setId(advertisementInfoModel.getId());
        advertisementEntity.setOnceAccessStarPoint(new BigDecimal(advertisementInfoModel.getOnceAccessStarPoint()).setScale(4, BigDecimal.ROUND_HALF_UP).toString());
        advertisementEntity.setOnceClickStarPoint(new BigDecimal(advertisementInfoModel.getOnceClickStarPoint()).setScale(4, BigDecimal.ROUND_HALF_UP).toString());
        advertisementEntity.setVersion(advertisementInfoModel.getVersion());
        String key = RedisKeyGenerate.generate(advertisementInfoModel.getId().toString(), RedisKeyConfig.ADVERTISEMENT_KEY);
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
        redisTemplate.opsForValue().set(key, advertisementEntity);
    }


    public AdvertisementEntity get(Long id) {
        String key = RedisKeyGenerate.generate(id.toString(), RedisKeyConfig.ADVERTISEMENT_KEY);
        return redisTemplate.opsForValue().get(key);
    }

    public void remove(Long advertisementInfoModelId) {
        String key = RedisKeyGenerate.generate(advertisementInfoModelId.toString(), RedisKeyConfig.ADVERTISEMENT_KEY);
        redisTemplate.delete(key);
    }
}
