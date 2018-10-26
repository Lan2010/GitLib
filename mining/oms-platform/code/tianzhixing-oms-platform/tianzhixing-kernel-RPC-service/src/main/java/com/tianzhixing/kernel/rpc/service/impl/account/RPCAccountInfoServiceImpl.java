package com.tianzhixing.kernel.rpc.service.impl.account;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.kernel.commons.em.StarPointOperationType;
import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;
import com.tianzhixing.kernel.redis.service.RedisSystemParamService;
import com.tianzhixing.kernel.redis.service.RedisUnCollectionStarPointRecordsService;
import com.tianzhixing.kernel.rpc.mapper.account.*;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.AccountStarPointOperationRecordsService;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/6/19.
 */
@Service("RPCAccountInfoService")
public class RPCAccountInfoServiceImpl implements RPCAccountInfoService {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private RedisSystemParamService systemParamService;

    @Autowired
    private AccountStarPointOperationRecordsService accountStarPointOperationRecordsService;

    @Autowired
    private RedisUnCollectionStarPointRecordsService redisUnCollectionStarPointRecordsService;

    @Override
    public AccountInfoMapper getByAccountToken(String accountToken) {
        AccountInfoModel accountInfoModel = accountInfoService.getByAccountToken(accountToken);
        return accountInfoModel == null ? null : _modelToMapper(accountInfoModel);
    }

    @Override
    public AccountInfoMapper getByMobile(String mobile, String org) {
        AccountInfoModel accountInfoModel = accountInfoService.getByMobile(mobile, org);
        return _modelToMapper(accountInfoModel);
    }

    @Override
    public AccountInfoMapper getByThirdToken(String thirdToken, String org) {
        AccountInfoModel accountInfoModel = accountInfoService.getByThirdToken(thirdToken, org);
        return _modelToMapper(accountInfoModel);
    }

    @Override
    public AccountInfoMapper add(AccountInfoMapper accountInfoMapper, Long referrerAccountId) {
        //获取默认系统奖励
        SystemParamEntity systemParamEntity = systemParamService.award(SystemParamType.REGAWARD.name());
        SystemParamEntity invitationSystemParamEntity = systemParamService.award(SystemParamType.INVITATION.name());
        AccountInfoModel accountInfoModel = accountInfoService.add(_mapperToModel(accountInfoMapper), systemParamEntity == null ? null : Double.valueOf(systemParamEntity.getSystemValue()), SystemParamType.REGAWARD.name(), referrerAccountId, SystemParamType.INVITATION.name(), Double.valueOf(invitationSystemParamEntity == null ? null : invitationSystemParamEntity.getSystemValue()));
        accountInfoMapper.setId(accountInfoModel.getId());
        accountInfoMapper.setAccountToken(accountInfoModel.getAccountToken());
        return accountInfoMapper;
    }

    @Override
    public AccountStarPointInfoMapper getAccountStarPointByAccountId(Long accountId) {
        AccountStarPointInfoModel accountStarPointInfoModel = accountInfoService.getAccountStarPointByAccountId(accountId);
        return new AccountStarPointInfoMapper(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getAccountId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getFrozenStarPoint());
    }

    @Override
    public AccountWithStarPointInfoWithPagerMapper listWithRanking(String org, int from, int size) {
        AccountWithStarPointInfoWithPagerMapper accountWithStarPointInfoWithPagerMapper = new AccountWithStarPointInfoWithPagerMapper();
        List<AccountWithStarPointInfoMapper> accountWithStarPointInfoMapperList = new ArrayList<>();
        long count = accountInfoService.countAccount(org);
        if (count > 0) {
            List<AccountStarPointInfoModel> accountStarPointInfoModelList = accountInfoService.listRankingAccount(org, from, size);
            if (accountStarPointInfoModelList != null) {
                for (AccountStarPointInfoModel accountStarPointInfoModel : accountStarPointInfoModelList) {
                    AccountInfoModel accountInfoModel = accountInfoService.getById(accountStarPointInfoModel.getAccountId());
                    accountWithStarPointInfoMapperList.add(new AccountWithStarPointInfoMapper(_modelToMapper(accountInfoModel), _modelToMapper(accountStarPointInfoModel)));
                }
            }
        }
        accountWithStarPointInfoWithPagerMapper.setTotal(count);
        accountWithStarPointInfoWithPagerMapper.setAccountWithStarPointInfoMapperList(accountWithStarPointInfoMapperList);
        return accountWithStarPointInfoWithPagerMapper;
    }

    @Override
    public AccountStarPointRecordsInfoWithPagerMapper records(AccountInfoMapper accountInfoMapper, Date beginTime, Date endTime, int from, int size, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType) {
        AccountStarPointRecordsInfoWithPagerMapper accountStarPointRecordsInfoWithPagerMapper = new AccountStarPointRecordsInfoWithPagerMapper();
        long total = accountStarPointOperationRecordsService.count(accountInfoMapper.getId(), beginTime, endTime, taskId, advertisementId, starPointRecordsType);
        List<AccountStarPointRecordsInfoMapper> list = new ArrayList<>();
        if (total > 0) {
            List<AccountStarPointOperationRecordsInfoModel> accountStarPointOperationRecordsInfoModels = accountStarPointOperationRecordsService.list(accountInfoMapper.getId(), beginTime, endTime, taskId, advertisementId, starPointRecordsType, from, size);
            if (accountStarPointOperationRecordsInfoModels != null) {
                for (AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel : accountStarPointOperationRecordsInfoModels) {
                    list.add(new AccountStarPointRecordsInfoMapper(accountStarPointOperationRecordsInfoModel.getId(), accountStarPointOperationRecordsInfoModel.getCreateTime(), accountStarPointOperationRecordsInfoModel.getUpdateTime(), accountStarPointOperationRecordsInfoModel.getAccountId(), accountStarPointOperationRecordsInfoModel.getAvailableStarPoint(), accountStarPointOperationRecordsInfoModel.getFrozenStarPoint(), accountStarPointOperationRecordsInfoModel.getOperStarPoint(), StarPointOperationType.byCode(accountStarPointOperationRecordsInfoModel.getOperationType()), StarPointRecordsType.byCode(accountStarPointOperationRecordsInfoModel.getRecordsType()), accountStarPointOperationRecordsInfoModel.getTaskId(), accountStarPointOperationRecordsInfoModel.getAdvertisementId(), accountStarPointOperationRecordsInfoModel.getRemark()));
                }
            }
        }
        accountStarPointRecordsInfoWithPagerMapper.setTotal(total);
        accountStarPointRecordsInfoWithPagerMapper.setAccountStarPointRecordsInfoMapperList(list);
        return accountStarPointRecordsInfoWithPagerMapper;
    }

    @Override
    public List<UnCollectionStarPointRecordsInfoMapper> unCollectionRecords(AccountInfoMapper accountInfoMapper, Date beginTime, Date endTime) {
        List<UnCollectionStarPointRecordsInfoMapper> list = new ArrayList<>();
        List<DeviceMacCollectionStarPointRecords> deviceMacCollectionStarPointRecordses = redisUnCollectionStarPointRecordsService.list(accountInfoMapper.getId());
        if (deviceMacCollectionStarPointRecordses != null) {
            for (DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords : deviceMacCollectionStarPointRecordses) {
                list.add(new UnCollectionStarPointRecordsInfoMapper(deviceMacCollectionStarPointRecords.getCreateTime(), deviceMacCollectionStarPointRecords.getUpdateTime(), Long.valueOf(deviceMacCollectionStarPointRecords.getAccountId()), Double.valueOf(deviceMacCollectionStarPointRecords.getOperStarPoint()), StarPointRecordsType.byCode(deviceMacCollectionStarPointRecords.getRecordsType()), deviceMacCollectionStarPointRecords.getTaskId(), deviceMacCollectionStarPointRecords.getAdvertisementId(), deviceMacCollectionStarPointRecords.getLongitudeAndLatitude(), deviceMacCollectionStarPointRecords.getRecordToken(), deviceMacCollectionStarPointRecords.getRemark(), deviceMacCollectionStarPointRecords.getTimeoutHour()));
            }
        }
        return list;
    }

    @Override
    public List<AccountStarPointWithDayRecordsMapper> recordsWithDay(AccountInfoMapper accountInfoMapper, Date endTime, int days) {
        List<AccountStarPointWithDayRecordsMapper> list = new ArrayList<>();
        //获取日期内记录
        Date beginTime = CalendarUtil.calDateForDay(endTime, 0 - days);
        List<AccountStarPointOperationRecordsInfoModel> accountStarPointOperationRecordsInfoModels = accountStarPointOperationRecordsService.list(accountInfoMapper.getId(), beginTime, endTime, null, null, null);
        Map<String, Double> map = new HashMap<>();
        if (accountStarPointOperationRecordsInfoModels != null) {
            for (AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel : accountStarPointOperationRecordsInfoModels) {
                //当前日期的day值
                String day = CalendarUtil.dayWithYearAndMonth(accountStarPointOperationRecordsInfoModel.getCreateTime());
                Double value = map.get(day);
                Double currentValue = accountStarPointOperationRecordsInfoModel.getAvailableStarPoint();
                if (value == null) {
                    value = currentValue;
                    map.put(day, value);
                }
                //比对
                if (value < currentValue) {
                    map.put(day, currentValue);
                }
            }
        }
        //循环map
        for (String key : map.keySet()) {
            list.add(new AccountStarPointWithDayRecordsMapper(key, map.get(key)));
        }
        return list;
    }

    @Override
    public List<AccountStarPointWithTaskMapper> starPointWithTask(AccountInfoMapper accountInfoMapper, List<String> taskIds) {
        List<AccountStarPointWithTaskMapper> list = new ArrayList<>();
        for(String taskId : taskIds){
            Double starPoint = accountStarPointOperationRecordsService.starPointWithTask(accountInfoMapper.getId(), taskId);
            list.add(new AccountStarPointWithTaskMapper(taskId, starPoint));
        }
        return list;
    }

    private AccountInfoMapper _modelToMapper(AccountInfoModel accountInfoModel) {
        if (accountInfoModel == null) return null;
        return new AccountInfoMapper(accountInfoModel.getId(), accountInfoModel.getVersion(), accountInfoModel.getCreateTime(), accountInfoModel.getUpdateTime(), accountInfoModel.getRegTime(), accountInfoModel.getMobile(), accountInfoModel.getAccountToken(), accountInfoModel.getOrg(), accountInfoModel.getThirdToken());
    }

    private AccountStarPointInfoMapper _modelToMapper(AccountStarPointInfoModel accountStarPointInfoModel) {
        if (accountStarPointInfoModel == null) return null;
        return new AccountStarPointInfoMapper(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getAccountId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getFrozenStarPoint());
    }

    private AccountInfoModel _mapperToModel(AccountInfoMapper accountInfoMapper) {
        AccountInfoModel accountInfoModel = new AccountInfoModel();
        accountInfoModel.setAccountToken(accountInfoMapper.getAccountToken());
        accountInfoModel.setMobile(accountInfoMapper.getMobile());
        accountInfoModel.setOrg(accountInfoMapper.getOrg());
        accountInfoModel.setRegTime(accountInfoMapper.getRegTime());
        accountInfoModel.setThirdToken(accountInfoMapper.getThirdToken());
        accountInfoModel.setCreateTime(accountInfoMapper.getCreateTime());
        accountInfoModel.setUpdateTime(accountInfoMapper.getUpdateTime());
        accountInfoModel.setVersion(accountInfoMapper.getVersion());
        return accountInfoModel;
    }
}
