package com.tianzhixing.kernel.service.impl;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.dao.account.AccountInfoDao;
import com.tianzhixing.kernel.dao.account.AccountStarPointInfoDao;
import com.tianzhixing.kernel.dao.account.AccountStarPointOperationRecordsInfoDao;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.AccountStarPointOperationService;
import com.tianzhixing.oms.utils.CalculateUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/19.
 */
@Service("accountInfoService")
public class AccountInfoServiceImpl implements AccountInfoService {

    @Autowired
    private AccountInfoDao accountInfoDao;

    @Autowired
    private AccountStarPointInfoDao accountStarPointInfoDao;

    @Autowired
    private AccountStarPointOperationService accountStarPointOperationService;

    @Autowired
    private AccountStarPointOperationRecordsInfoDao accountStarPointOperationRecordsInfoDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AccountInfoModel add(AccountInfoModel accountInfoModel, Double startPoint, String awardType, Long referrerAccountId, String referrerType, Double referrerStarPoint) {
        accountInfoModel = accountInfoDao.add(accountInfoModel);
        AccountStarPointInfoModel accountStarPointInfoModel = new AccountStarPointInfoModel();
        accountStarPointInfoModel.setAccountId(accountInfoModel.getId());
        accountStarPointInfoModel.setCreateTime(accountInfoModel.getCreateTime());
        accountStarPointInfoModel.setAvailableStarPoint(0D);
        accountStarPointInfoModel.setFrozenStarPoint(0D);
        //注册奖励
        if (startPoint != null && startPoint.doubleValue() != 0) {
            accountStarPointInfoModel.setAvailableStarPoint(startPoint);
            AccountStarPointOperationRecordsInfoModel accountStarPointOperationRecordsInfoModel = accountStarPointOperationService.instanceOf(accountInfoModel.getId(), accountStarPointInfoModel, StarPointRecordsType.AWARD, null, null, awardType, startPoint);
            accountStarPointOperationRecordsInfoDao.insert(accountStarPointOperationRecordsInfoModel);
        }
        if (referrerAccountId != null && referrerAccountId != 0 && referrerStarPoint != null && referrerStarPoint != 0) {
            //为推荐人增加奖励
            AccountStarPointInfoModel referrerAccountStarPoint = accountStarPointInfoDao.getByAccountId(referrerAccountId);
            referrerAccountStarPoint.setAvailableStarPoint(Double.valueOf(CalculateUtil.plus(referrerAccountStarPoint.getAvailableStarPoint(), referrerStarPoint, 4)));
            accountStarPointInfoDao.updateAvailableStarPoint(referrerAccountStarPoint.getId(), referrerAccountStarPoint.getAvailableStarPoint(), new Date(), referrerAccountStarPoint.getVersion());
            accountStarPointOperationRecordsInfoDao.insert(accountStarPointOperationService.instanceOf(referrerAccountId, referrerAccountStarPoint, StarPointRecordsType.AWARD, null, null, referrerType, referrerStarPoint));
            //被推荐人增加奖励
            accountStarPointInfoModel.setAvailableStarPoint(Double.valueOf(CalculateUtil.plus(accountStarPointInfoModel.getAvailableStarPoint(), referrerStarPoint, 4)));
            accountStarPointOperationRecordsInfoDao.insert(accountStarPointOperationService.instanceOf(accountInfoModel.getId(), accountStarPointInfoModel, StarPointRecordsType.AWARD, null, null, referrerType, referrerStarPoint));
        }
        accountStarPointInfoModel.setUpdateTime(accountInfoModel.getUpdateTime());
        accountStarPointInfoModel.setVersion(0);
        accountStarPointInfoDao.add(accountStarPointInfoModel);
        return accountInfoModel;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AccountInfoModel getByMobile(String mobile, String org) {
        return accountInfoDao.getByMobile(mobile, org);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AccountInfoModel getByThirdToken(String thirdToken, String org) {
        return accountInfoDao.getByThirdToken(thirdToken, org);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AccountStarPointInfoModel getAccountStarPointByAccountId(Long accountId) {
        return accountStarPointInfoDao.getByAccountId(accountId);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AccountStarPointInfoModel> listRankingAccount(String org, int from, int size) {
        return accountStarPointInfoDao.listRankingAccount(org, from, size);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AccountInfoModel getByAccountToken(String accountToken) {
        return accountInfoDao.getByAccountToken(accountToken);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AccountInfoModel getById(Long id) {
        return accountInfoDao.getById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long countAccount(String org) {
        return accountInfoDao.countAccount(org);
    }

    @Override
    public Long maxId() {
        return accountInfoDao.maxId();
    }
}
