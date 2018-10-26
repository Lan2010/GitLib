package com.tianzhixing.oms.bussiness.redis.job;

import com.tianzhixing.bussiness.commons.em.AdvertisementStatus;
import com.tianzhixing.oms.bussiness.model.advertisement.AdvertisementInfoModel;
import com.tianzhixing.oms.bussiness.redis.service.RedisAdvertisementServcie;
import com.tianzhixing.oms.bussiness.service.AdvertisementService;
import com.tianzhixing.oms.redis.bussiness.AdvertisementEntity;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 广告同步服务
 * Created by routine.k on 2018/7/1.
 */
@Service
public class AdvertisementSyncService {

    private static Logger LOGGER = LoggerFactory.getLogger(AdvertisementSyncService.class);

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private RedisAdvertisementServcie redisAdvertisementServcie;

    public void sync() {

        if (!MacheNumCheckUtil.check()) {
            return;
        }

        LOGGER.info(".... begin check advertisement with redis ....");
        Long maxId = advertisementService.maxId();
        for (long i = 1; i <= maxId; i++) {
            AdvertisementInfoModel advertisementInfoModel = advertisementService.getById(i);
            if (advertisementInfoModel == null) {
                continue;
            }
            if (!AdvertisementStatus.ENABLE.equals(advertisementInfoModel.getAdvertStatus()) || CalendarUtil.isAfter(new Date(), advertisementInfoModel.getEndTime())) {
                redisAdvertisementServcie.remove(advertisementInfoModel.getId());
                continue;
            }
            AdvertisementEntity advertisementEntity = redisAdvertisementServcie.get(i);
            if (advertisementEntity == null || advertisementEntity.getVersion() != advertisementInfoModel.getVersion())
                redisAdvertisementServcie.cache(advertisementInfoModel);

        }
        LOGGER.info(".... finished check advertisement with redis ....");
    }
}
