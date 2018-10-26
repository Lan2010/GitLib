package com.tianzhixing.kernel.service;

import com.tianzhixing.kernel.model.account.AccountAwardRecordsInfoModel;

/**
 * Created by routine.k on 2018/6/30.
 */
public interface AccountAwardRecordsInfoService {

    /**
     * 根据账户ID获取
     *
     * @param accountId
     * @param systemParamType
     * @return
     */
    AccountAwardRecordsInfoModel getByAccountAndType(Long accountId, String systemParamType);

}
