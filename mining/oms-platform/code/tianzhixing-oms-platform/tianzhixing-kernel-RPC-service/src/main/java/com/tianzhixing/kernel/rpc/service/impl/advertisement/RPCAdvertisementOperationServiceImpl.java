package com.tianzhixing.kernel.rpc.service.impl.advertisement;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import com.tianzhixing.kernel.redis.service.RedisAdvertisementService;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.advertisement.AdvertisementOperationMapper;
import com.tianzhixing.kernel.rpc.service.advertisement.RPCAdvertisementOperationService;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.AccountStarPointOperationService;
import com.tianzhixing.oms.redis.bussiness.AdvertisementEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("RPCAdvertisementOperationService")
public class RPCAdvertisementOperationServiceImpl implements RPCAdvertisementOperationService {

    @Autowired
    private AccountStarPointOperationService accountStarPointOperationService;

    @Autowired
    private RedisAdvertisementService redisAdvertisementService;

    @Override
    public AccountStarPointOperationInfoMapper clickOrAccess(AccountInfoMapper accountInfoMapper, AdvertisementOperationMapper advertisementOperationMapper, Integer operationType) {
        AdvertisementEntity advertisementEntity = redisAdvertisementService.getByAdvertisementId(advertisementOperationMapper.getAdvertisementId());
        Assert.notNull(advertisementEntity, "... get advertisement info by id[" + advertisementOperationMapper.getAdvertisementId() + "] failed, entity not found ...");
        Assert.isTrue(advertisementEntity.getEnable(), "... get advertisement info by id[" + advertisementOperationMapper.getAdvertisementId() + "] , but enable is false ...");
        Assert.isTrue(0 == operationType || 1 == operationType, "...  advertisement operation type by id[" + advertisementOperationMapper.getAdvertisementId() + "] , but operation type unknown ...");
        Double starPoint = 0 == operationType ? Double.valueOf(advertisementEntity.getOnceAccessStarPoint()) : Double.valueOf(advertisementEntity.getOnceClickStarPoint());
        AccountStarPointInfoModel accountStarPointInfoModel = accountStarPointOperationService.increment(accountInfoMapper.getId(), starPoint, StarPointRecordsType.ADVERTISEMENT, null, advertisementOperationMapper.getAdvertisementId(), 0 == operationType ? "ACCESS" : "CLICK");
        return new AccountStarPointOperationInfoMapper(starPoint, new AccountStarPointInfoMapper(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getAccountId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getFrozenStarPoint()));
    }
}
