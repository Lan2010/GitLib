package com.tianzhixing.kernel.service.impl;

import com.tianzhixing.kernel.commons.em.StarPointOperationType;
import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.dao.account.AccountAwardRecordsInfoDao;
import com.tianzhixing.kernel.dao.account.AccountStarPointInfoDao;
import com.tianzhixing.kernel.dao.account.AccountStarPointOperationRecordsInfoDao;
import com.tianzhixing.kernel.model.account.AccountAwardRecordsInfoModel;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;
import com.tianzhixing.kernel.service.AccountStarPointOperationService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("accountStarPointOperationService")
public class AccountStarPointOperationServiceImpl implements AccountStarPointOperationService {

    @Autowired
    private AccountStarPointInfoDao accountStarPointInfoDao;

    @Autowired
    private AccountStarPointOperationRecordsInfoDao accountStarPointOperationRecordsInfoDao;

    @Autowired
    private AccountAwardRecordsInfoDao accountAwardRecordsInfoDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AccountStarPointInfoModel collection(Long accountId, List<AccountStarPointOperationRecordsInfoModel> accountStarPointOperationRecordsInfoModels, Double advertisementStarPoint, String advertId) {
        AccountStarPointInfoModel accountStarPointInfoModel = accountStarPointInfoDao.getByAccountId(accountId);
        Assert.notNull(accountStarPointInfoModel, "... get account starpoint info by account id[" + accountId + "] failed, account starpoint not found");
        Map<String, AccountStarPointOperationRecordsInfoModel> modelMap = new HashMap<>();
        for (AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel : accountStarPointOperationRecordsInfoModels) {
            accountStarPointInfoModel.setAvailableStarPoint(Double.valueOf(_plus(accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointOperationRecordsInfoModel.getOperStarPoint())));
            //星点分类
            if (StringUtils.isEmpty(accountStarPointOperationRecordsInfoModel.getTaskId())) {
                accountStarPointOperationRecordsInfoModel.setTaskId("0");
            }
            AccountStarPointOperationRecordsInfoModel newAccountStarPointOperationRecordsInfoModel = modelMap.get(accountStarPointOperationRecordsInfoModel.getTaskId());
            if (newAccountStarPointOperationRecordsInfoModel == null) {
                String taskId = "0".equals(accountStarPointOperationRecordsInfoModel.getTaskId()) ? null : accountStarPointOperationRecordsInfoModel.getTaskId();
                newAccountStarPointOperationRecordsInfoModel = instanceOf(accountId, accountStarPointInfoModel, "0".equals(accountStarPointOperationRecordsInfoModel.getTaskId()) ? StarPointRecordsType.RANDOM : StarPointRecordsType.TASK, null, taskId, taskId, 0d);
                modelMap.put(accountStarPointOperationRecordsInfoModel.getTaskId(), newAccountStarPointOperationRecordsInfoModel);
            }
            newAccountStarPointOperationRecordsInfoModel.setAvailableStarPoint(accountStarPointInfoModel.getAvailableStarPoint());
            newAccountStarPointOperationRecordsInfoModel.setOperStarPoint(Double.valueOf(_plus(Double.valueOf(accountStarPointOperationRecordsInfoModel.getOperStarPoint()), newAccountStarPointOperationRecordsInfoModel.getOperStarPoint())));
        }
        for (String key : modelMap.keySet()) {
            accountStarPointOperationRecordsInfoDao.insert(modelMap.get(key));
        }
        //计算广告奖励
        if (advertisementStarPoint != null && advertisementStarPoint != 0) {
            accountStarPointInfoModel.setAvailableStarPoint(Double.valueOf(_plus(accountStarPointInfoModel.getAvailableStarPoint(), advertisementStarPoint)));
            AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel = instanceOf(accountId, accountStarPointInfoModel, StarPointRecordsType.ADVERTISEMENT, advertId, null, advertId, advertisementStarPoint);
            accountStarPointOperationRecordsInfoDao.insert(accountStarPointOperationRecordsInfoModel);
        }
        accountStarPointInfoDao.updateAvailableStarPoint(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getVersion());
        return accountStarPointInfoModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AccountStarPointInfoModel increment(Long accountId, Double starPoint, StarPointRecordsType starPointRecordsType, String taskId, String advertisementId, String remark) {
        AccountStarPointInfoModel accountStarPointInfoModel = accountStarPointInfoDao.getByAccountId(accountId);
        Assert.notNull(accountStarPointInfoModel, "... get account starpoint info by account id[" + accountId + "] failed, account starpoint not found");
        accountStarPointInfoModel.setAvailableStarPoint(Double.valueOf(_plus(accountStarPointInfoModel.getAvailableStarPoint(), starPoint)));
        accountStarPointInfoDao.updateAvailableStarPoint(accountStarPointInfoModel.getId(), accountStarPointInfoModel.getAvailableStarPoint(), accountStarPointInfoModel.getUpdateTime(), accountStarPointInfoModel.getVersion());
        AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel = instanceOf(accountId, accountStarPointInfoModel, starPointRecordsType, advertisementId, taskId, remark, starPoint);
        accountStarPointOperationRecordsInfoDao.insert(accountStarPointOperationRecordsInfoModel);
        return accountStarPointInfoModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AccountStarPointInfoModel award(Long accountId, Double starPoint, String systemParamType) {
        AccountAwardRecordsInfoModel accountAwardRecordsInfoModel = new AccountAwardRecordsInfoModel();
        accountAwardRecordsInfoModel.setAccountId(accountId);
        accountAwardRecordsInfoModel.setCreateTime(new Date());
        accountAwardRecordsInfoModel.setUpdateTime(new Date());
        accountAwardRecordsInfoModel.setVersion(0);
        accountAwardRecordsInfoModel.setAwardType(systemParamType);
        accountAwardRecordsInfoDao.insert(accountAwardRecordsInfoModel);
        return increment(accountId, starPoint, StarPointRecordsType.AWARD, null, null, systemParamType);
    }


    @Override
    public AccountStarPointOperationRecordsInfoModel instanceOf(Long accountId, AccountStarPointInfoModel accountStarPointInfoModel, StarPointRecordsType starPointRecordsType, String advertisementId, String taskId, String remark, Double opertaionStarPoint) {
        AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel;
        accountStarPointOperationRecordsInfoModel = new AccountStarPointOperationRecordsInfoModel();
        accountStarPointOperationRecordsInfoModel.setVersion(0);
        accountStarPointOperationRecordsInfoModel.setCreateTime(new Date());
        accountStarPointOperationRecordsInfoModel.setUpdateTime(new Date());
        accountStarPointOperationRecordsInfoModel.setAccountId(accountId);
        accountStarPointOperationRecordsInfoModel.setFrozenStarPoint(accountStarPointInfoModel.getFrozenStarPoint());
        accountStarPointOperationRecordsInfoModel.setOperationType(StarPointOperationType.INCREMENT.getCode());
        accountStarPointOperationRecordsInfoModel.setRecordsType(starPointRecordsType.getCode());
        accountStarPointOperationRecordsInfoModel.setTaskId(taskId);
        accountStarPointOperationRecordsInfoModel.setAdvertisementId(advertisementId);
        accountStarPointOperationRecordsInfoModel.setAvailableStarPoint(accountStarPointInfoModel.getAvailableStarPoint());
        accountStarPointOperationRecordsInfoModel.setOperStarPoint(opertaionStarPoint);
        accountStarPointOperationRecordsInfoModel.setRemark(remark);
        return accountStarPointOperationRecordsInfoModel;
    }

    /*
     * 加法运算，四舍五入
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    private String _plus(double v1, double v2) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(4, BigDecimal.ROUND_HALF_UP).toString();
    }
}
