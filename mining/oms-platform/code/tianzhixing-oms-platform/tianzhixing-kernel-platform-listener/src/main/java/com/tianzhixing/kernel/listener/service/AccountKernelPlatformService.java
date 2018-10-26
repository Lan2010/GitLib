package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.commons.utils.StarPointRecordsTokenGeneratUtil;
import com.tianzhixing.kernel.listener.entity.DeviceEntity;
import com.tianzhixing.kernel.listener.entity.RateEntity;
import com.tianzhixing.kernel.listener.redis.service.RedisUnCollectionStarPointRecordsService;
import com.tianzhixing.kernel.listener.scylladb.DeviceMacStarPointRecordsService;
import com.tianzhixing.kernel.listener.util.GpsUtil;
import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/28.
 */
@Service
public class AccountKernelPlatformService {

    @Autowired
    private DeviceMacStarPointRecordsService deviceMacStarPointRecordsService;

    @Autowired
    private RedisUnCollectionStarPointRecordsService redisUnCollectionStarPointRecordsService;

    public void persistence(String accountId, Double starPoint, DeviceEntity deviceEntity, RateEntity rateEntity) {
//        UnCollectionStarPointRecordsInfoMapper unCollectionStarPointRecordsInfoMapper = new UnCollectionStarPointRecordsInfoMapper();
//        unCollectionStarPointRecordsInfoMapper.setId(null);
//        unCollectionStarPointRecordsInfoMapper.setCreateTime(new Date());
//        unCollectionStarPointRecordsInfoMapper.setUpdateTime(new Date());
//        unCollectionStarPointRecordsInfoMapper.setAccountId(Long.valueOf(accountId));
//        unCollectionStarPointRecordsInfoMapper.setOperStarPoint(starPoint);
//        unCollectionStarPointRecordsInfoMapper.setRecordsType((StringUtils.isEmpty(rateEntity.getTaskId())) ? StarPointRecordsType.RANDOM : StarPointRecordsType.TASK);
//        unCollectionStarPointRecordsInfoMapper.setTaskId(StringUtils.isEmpty(rateEntity.getTaskId()) ? null : rateEntity.getTaskId());
//        unCollectionStarPointRecordsInfoMapper.setAdvertisementId(null);
//        String[] gps = GpsUtil.convert(deviceEntity.getGps());
//        unCollectionStarPointRecordsInfoMapper.setLongitudeAndLatitude("lat:" + gps[1] + "|" + "lng:" + gps[0]);
//        String token = StarPointRecordsTokenGeneratUtil.generate(accountId);
//        unCollectionStarPointRecordsInfoMapper.setRecordToken(token);
//        unCollectionStarPointRecordsInfoMapper.setRemark(deviceEntity.getDevid());
//        unCollectionStarPointRecordsInfoMapper.setTimeoutHour(rateEntity.getTimeoutHours().intValue());
//        rpcDeviceMacListenerService.persistence(unCollectionStarPointRecordsInfoMapper);
        DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords = _get(accountId, starPoint, deviceEntity, rateEntity);
        // to redis
        redisUnCollectionStarPointRecordsService.cache(deviceMacCollectionStarPointRecords);
        // to scylladb
        deviceMacStarPointRecordsService.insert(deviceMacCollectionStarPointRecords);
    }

    private DeviceMacCollectionStarPointRecords _get(String accountId, Double starPoint, DeviceEntity deviceEntity, RateEntity rateEntity) {
        DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecordsInfoModel = new DeviceMacCollectionStarPointRecords();
        deviceMacCollectionStarPointRecordsInfoModel.setId(null);
        deviceMacCollectionStarPointRecordsInfoModel.setVersion(0);
        deviceMacCollectionStarPointRecordsInfoModel.setCreateTime(new Date());
        deviceMacCollectionStarPointRecordsInfoModel.setUpdateTime(new Date());
        deviceMacCollectionStarPointRecordsInfoModel.setAccountId(accountId);
        deviceMacCollectionStarPointRecordsInfoModel.setOperStarPoint(new BigDecimal(starPoint).setScale(6, BigDecimal.ROUND_HALF_UP).toString());
        deviceMacCollectionStarPointRecordsInfoModel.setRecordsType(StringUtils.isEmpty(rateEntity.getTaskId()) ? StarPointRecordsType.RANDOM.getCode() : StarPointRecordsType.TASK.getCode());
        deviceMacCollectionStarPointRecordsInfoModel.setTaskId(StringUtils.isEmpty(rateEntity.getTaskId()) ? null : rateEntity.getTaskId());
        deviceMacCollectionStarPointRecordsInfoModel.setAdvertisementId(null);
        String[] gps = GpsUtil.convert(deviceEntity.getGps());
        deviceMacCollectionStarPointRecordsInfoModel.setLongitudeAndLatitude("lat:" + gps[1] + "|" + "lng:" + gps[0]);
        String token = StarPointRecordsTokenGeneratUtil.generate(accountId);
        deviceMacCollectionStarPointRecordsInfoModel.setRecordToken(token);
        deviceMacCollectionStarPointRecordsInfoModel.setTimeoutHour(rateEntity.getTimeoutHours().intValue());
        deviceMacCollectionStarPointRecordsInfoModel.setRemark(deviceEntity.getDevid());
        deviceMacCollectionStarPointRecordsInfoModel.setStatus(0);
        deviceMacCollectionStarPointRecordsInfoModel.setTaskName(rateEntity.getTaskName());
        deviceMacCollectionStarPointRecordsInfoModel.setTaskKeyWord(rateEntity.getTaskKeyWord());
        deviceMacCollectionStarPointRecordsInfoModel.setTaskLocationName(rateEntity.getTaskLocationName());
        deviceMacCollectionStarPointRecordsInfoModel.setDevid(deviceEntity.getDevid());
        deviceMacCollectionStarPointRecordsInfoModel.setDevbt(deviceEntity.getDevbt());
        deviceMacCollectionStarPointRecordsInfoModel.setDevwifi(deviceEntity.getDevwifi());
        deviceMacCollectionStarPointRecordsInfoModel.setTime(deviceEntity.getTime());
        deviceMacCollectionStarPointRecordsInfoModel.setGps(deviceEntity.getGps());
        deviceMacCollectionStarPointRecordsInfoModel.setCount(deviceEntity.getCount());
        return deviceMacCollectionStarPointRecordsInfoModel;
    }
}
