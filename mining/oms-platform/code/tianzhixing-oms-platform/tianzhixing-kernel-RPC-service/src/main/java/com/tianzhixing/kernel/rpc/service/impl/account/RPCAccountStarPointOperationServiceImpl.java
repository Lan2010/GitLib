package com.tianzhixing.kernel.rpc.service.impl.account;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.kernel.commons.em.StarPointOperationType;
import com.tianzhixing.kernel.commons.ex.AccountStarPointOperationException;
import com.tianzhixing.kernel.model.account.AccountAwardRecordsInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;
import com.tianzhixing.kernel.redis.service.RedisAdvertisementService;
import com.tianzhixing.kernel.redis.service.RedisSystemParamService;
import com.tianzhixing.kernel.redis.service.RedisUnCollectionStarPointRecordsService;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointInfoMapper;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountStarPointOperationService;
import com.tianzhixing.kernel.rpc.service.scylladb.ScylladbCollectionRecordsAsyncService;
import com.tianzhixing.kernel.service.AccountAwardRecordsInfoService;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.AccountStarPointOperationService;
import com.tianzhixing.oms.redis.bussiness.AdvertisementEntity;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.FormatUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("RPCAccountStarPointOperationService")
public class RPCAccountStarPointOperationServiceImpl implements RPCAccountStarPointOperationService {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private AccountStarPointOperationService accountStarPointOperationService;

    @Autowired
    private ScylladbCollectionRecordsAsyncService scylladbCollectionRecordsAsyncService;

    @Autowired
    private RedisUnCollectionStarPointRecordsService redisUnCollectionStarPointRecordsService;

    @Autowired
    private RedisSystemParamService redisSystemParamService;

    @Autowired
    private AccountAwardRecordsInfoService accountAwardRecordsInfoService;

    @Autowired
    private RedisAdvertisementService redisAdvertisementService;

    @Override
    public AccountStarPointOperationInfoMapper collection(AccountInfoMapper accountInfoMapper, Set<String> recordsSet, String advertId, Integer adverOperationType) {
        //check
        List<DeviceMacCollectionStarPointRecords> deviceMacCollectionStarPointRecordsInfoModelList = redisUnCollectionStarPointRecordsService.list(recordsSet);
        Assert.isTrue(deviceMacCollectionStarPointRecordsInfoModelList != null && deviceMacCollectionStarPointRecordsInfoModelList.size() == recordsSet.size(), "...  failed check uncollection record, portion records not found");
        List<AccountStarPointOperationRecordsInfoModel> accountStarPointOperationRecordsInfoModels = new ArrayList<>();
        Double advertisementStarPoint = null;
        if (StringUtils.isNotEmpty(advertId)) {
            AdvertisementEntity advertisementEntity = redisAdvertisementService.getByAdvertisementId(advertId);
            if (advertisementEntity != null) {
                advertisementStarPoint = (null == adverOperationType ? null : (0 == adverOperationType ? Double.valueOf(advertisementEntity.getOnceAccessStarPoint()) : (1 == adverOperationType ? Double.valueOf(advertisementEntity.getOnceClickStarPoint()) : null)));
            }
        }
        Double starPoint = 0d;
        for (DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords : deviceMacCollectionStarPointRecordsInfoModelList) {
            Assert.isTrue(deviceMacCollectionStarPointRecords.getAccountId().equals(accountInfoMapper.getId().toString()), ".... check uncollection records failed, records account id[" + deviceMacCollectionStarPointRecords.getAccountId() + "] not eq account id[" + accountInfoMapper.getId() + "]");
            accountStarPointOperationRecordsInfoModels.add(_toModel(deviceMacCollectionStarPointRecords));
            starPoint = Double.valueOf(CalculateUtil.plus(starPoint, Double.valueOf(deviceMacCollectionStarPointRecords.getOperStarPoint()), 4));
        }
        //remove from redis
        redisUnCollectionStarPointRecordsService.remove(accountInfoMapper.getId(), deviceMacCollectionStarPointRecordsInfoModelList);
        //添加星点
        AccountStarPointInfoModel accountStarPointInfoModel = accountStarPointOperationService.collection(accountInfoMapper.getId(), accountStarPointOperationRecordsInfoModels, advertisementStarPoint, advertId);
        //异步添加采集记录至scylladb
        scylladbCollectionRecordsAsyncService.add(deviceMacCollectionStarPointRecordsInfoModelList);
        AccountStarPointInfoMapper accountStarPointInfoMapper = new AccountStarPointInfoMapper(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getAccountId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getFrozenStarPoint());
        return new AccountStarPointOperationInfoMapper(starPoint, accountStarPointInfoMapper);
    }

    @Override
    public AccountStarPointOperationInfoMapper award(String systemParamType, AccountInfoMapper accountInfoMapper) {
        SystemParamEntity systemParamEntity = redisSystemParamService.award(systemParamType);
        Assert.notNull(systemParamEntity, "... award failed, not found award information ...");
        AccountAwardRecordsInfoModel accountAwardRecordsInfoModel = accountAwardRecordsInfoService.getByAccountAndType(accountInfoMapper.getId(), systemParamType);
        com.tianzhixing.kernel.commons.utils.Assert.isNull(accountAwardRecordsInfoModel, new AccountStarPointOperationException("already award with type[" + systemParamType + "]"));
        AccountStarPointInfoModel accountStarPointInfoModel = accountStarPointOperationService.award(accountInfoMapper.getId(), Double.valueOf(systemParamEntity.getSystemValue()), systemParamEntity.getSystemParamType());
        return new AccountStarPointOperationInfoMapper(Double.valueOf(systemParamEntity.getSystemValue()), new AccountStarPointInfoMapper(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getAccountId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getFrozenStarPoint()));
    }

    @Override
    public AccountStarPointOperationInfoMapper bindAddressListAward(int contactCount, AccountInfoMapper accountInfoMapper) {
        SystemParamEntity systemParamEntity = redisSystemParamService.award(SystemParamType.BINDADDRESSLIST.name());
        Assert.notNull(systemParamEntity, "... award failed, not found bind address list award information ...");
        SystemParamEntity maxSystemParamEntity = redisSystemParamService.award(SystemParamType.MAXBINDADDRESSLIST.name());
        AccountAwardRecordsInfoModel accountAwardRecordsInfoModel = accountAwardRecordsInfoService.getByAccountAndType(accountInfoMapper.getId(), SystemParamType.BINDADDRESSLIST.name());
        com.tianzhixing.kernel.commons.utils.Assert.isNull(accountAwardRecordsInfoModel, new AccountStarPointOperationException("already award with type[" + SystemParamType.BINDADDRESSLIST.name() + "]"));
        //计算增加星点数
        String starPoint = CalculateUtil.mul(Double.valueOf(systemParamEntity.getSystemValue()), Double.valueOf(String.valueOf(contactCount)), 4);
        Double maxStarPoint = maxSystemParamEntity == null ? Double.valueOf(starPoint) : Double.valueOf(maxSystemParamEntity.getSystemValue());
        if (Double.valueOf(starPoint) > maxStarPoint)
            starPoint = CalculateUtil.format(maxStarPoint, 4);
        AccountStarPointInfoModel accountStarPointInfoModel = accountStarPointOperationService.award(accountInfoMapper.getId(), Double.valueOf(starPoint), SystemParamType.BINDADDRESSLIST.name());
        return new AccountStarPointOperationInfoMapper(Double.valueOf(starPoint), new AccountStarPointInfoMapper(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getAccountId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getFrozenStarPoint()));
    }

    private AccountStarPointOperationRecordsInfoModel _toModel(DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords) {
        AccountStarPointOperationRecordsInfoModel collectionStarPointRecordsInfo = new AccountStarPointOperationRecordsInfoModel();
        collectionStarPointRecordsInfo.setVersion(0);
        collectionStarPointRecordsInfo.setCreateTime(deviceMacCollectionStarPointRecords.getCreateTime());
        collectionStarPointRecordsInfo.setUpdateTime(new Date());
        collectionStarPointRecordsInfo.setAccountId(Long.valueOf(deviceMacCollectionStarPointRecords.getAccountId()));
        collectionStarPointRecordsInfo.setAvailableStarPoint(0d);
        collectionStarPointRecordsInfo.setFrozenStarPoint(0d);
        collectionStarPointRecordsInfo.setOperStarPoint(Double.valueOf(deviceMacCollectionStarPointRecords.getOperStarPoint()));
        collectionStarPointRecordsInfo.setOperationType(StarPointOperationType.INCREMENT.getCode());
        collectionStarPointRecordsInfo.setRecordsType(deviceMacCollectionStarPointRecords.getRecordsType());
        collectionStarPointRecordsInfo.setTaskId(deviceMacCollectionStarPointRecords.getTaskId());
        collectionStarPointRecordsInfo.setAdvertisementId(deviceMacCollectionStarPointRecords.getAdvertisementId());
        return collectionStarPointRecordsInfo;
    }
}
