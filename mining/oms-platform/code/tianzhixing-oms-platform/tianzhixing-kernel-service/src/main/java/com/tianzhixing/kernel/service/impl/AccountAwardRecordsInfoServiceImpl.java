package com.tianzhixing.kernel.service.impl;

import com.tianzhixing.kernel.dao.account.AccountAwardRecordsInfoDao;
import com.tianzhixing.kernel.model.account.AccountAwardRecordsInfoModel;
import com.tianzhixing.kernel.service.AccountAwardRecordsInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/30.
 */
@Service("accountAwardRecordsInfoService")
public class AccountAwardRecordsInfoServiceImpl implements AccountAwardRecordsInfoService {

    @Autowired
    private AccountAwardRecordsInfoDao accountAwardRecordsInfoDao;

    @Override
    public AccountAwardRecordsInfoModel getByAccountAndType(Long accountId, String systemParamType) {
        return accountAwardRecordsInfoDao.getByAccountAndType(accountId, systemParamType);
    }
}
