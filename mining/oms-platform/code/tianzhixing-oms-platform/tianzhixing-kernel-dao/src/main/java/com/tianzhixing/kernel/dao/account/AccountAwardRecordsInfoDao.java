package com.tianzhixing.kernel.dao.account;

import com.tianzhixing.kernel.dao.generic.GenericBaseDao;
import com.tianzhixing.kernel.model.account.AccountAwardRecordsInfoModel;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/6/30.
 */
@Repository("accountAwardRecordsInfoDao")
public class AccountAwardRecordsInfoDao extends GenericBaseDao<AccountAwardRecordsInfoModel, Long> {

    /**
     * 根据accountID获取
     *
     * @param accountId
     * @param systemParamType
     * @return
     */
    public AccountAwardRecordsInfoModel getByAccountAndType(final Long accountId, final String systemParamType) {
        return super.get(new String[]{"account_id", "award_type"}, new Object[]{accountId, systemParamType});
    }
}
