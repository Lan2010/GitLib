package com.tianzhixing.kernel.service.impl;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.dao.account.AccountStarPointOperationRecordsInfoDao;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;
import com.tianzhixing.kernel.service.AccountStarPointOperationRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/29.
 */
@Service("accountStarPointOperationRecordsService")
public class AccountStarPointOperationRecordsServiceImpl implements AccountStarPointOperationRecordsService {

    @Autowired
    private AccountStarPointOperationRecordsInfoDao accountStarPointOperationRecordsInfoDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count(Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType) {
        return accountStarPointOperationRecordsInfoDao.count(accountId, beginTime, endTime, taskId, advertisementId, starPointRecordsType);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AccountStarPointOperationRecordsInfoModel> list(Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType, int from, int size) {
        return accountStarPointOperationRecordsInfoDao.list(accountId, beginTime, endTime, taskId, advertisementId, starPointRecordsType, from, size);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AccountStarPointOperationRecordsInfoModel> list(Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType) {
        return accountStarPointOperationRecordsInfoDao.list(accountId, beginTime, endTime, taskId, advertisementId, starPointRecordsType);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Double starPointWithTask(Long accountId, String taskId) {
        return accountStarPointOperationRecordsInfoDao.starPointWithTask(accountId, taskId);
    }
}
