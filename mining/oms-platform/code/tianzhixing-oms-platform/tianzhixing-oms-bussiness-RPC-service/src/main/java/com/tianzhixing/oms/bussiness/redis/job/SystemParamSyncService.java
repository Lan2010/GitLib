package com.tianzhixing.oms.bussiness.redis.job;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.bussiness.model.system.SystemParamModel;
import com.tianzhixing.oms.bussiness.redis.service.RedisSystemParamService;
import com.tianzhixing.oms.bussiness.service.SystemParamService;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * 系统参数同步服务
 * Created by routine.k on 2018/7/1.
 */
@Service
public class SystemParamSyncService {

    private static Logger LOGGER = LoggerFactory.getLogger(SystemParamSyncService.class);

    @Autowired
    private RedisSystemParamService redisSystemParamService;

    @Autowired
    private SystemParamService systemParamService;

    public void sync() {

        if (!MacheNumCheckUtil.check()) {
            return;
        }

        LOGGER.info(".... begin check system param setting with redis ....");
        //检测系统参数是否存在redis
        for (SystemParamType systemParamType : SystemParamType.values()) {
            SystemParamEntity systemParamEntity = redisSystemParamService.get(systemParamType);
            SystemParamModel systemParamModel = systemParamService.getType(systemParamType);
            Assert.notNull(systemParamModel, ".... sync system setting[" + systemParamType.name() + "] failed, can't found model from db ....");
            if (systemParamEntity == null || systemParamEntity.getVersion() != systemParamModel.getVersion()) {
                redisSystemParamService.cache(systemParamModel);
            }
            LOGGER.info(".... check system param setting[" + systemParamType.name() + "] with redis & cache ....");
        }
        LOGGER.info(".... finished check system param setting with redis ....");
    }
}
