package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;
import java.util.List;

/**
 * Created by routine.k on 2018/6/21.
 */
public class AccountStarPointRecordsInfoWithPagerMapper implements Serializable {

    private long total;

    private List<AccountStarPointRecordsInfoMapper> accountStarPointRecordsInfoMapperList;

    public AccountStarPointRecordsInfoWithPagerMapper() {
    }

    public AccountStarPointRecordsInfoWithPagerMapper(long total, List<AccountStarPointRecordsInfoMapper> accountStarPointRecordsInfoMapperList) {
        this.total = total;
        this.accountStarPointRecordsInfoMapperList = accountStarPointRecordsInfoMapperList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<AccountStarPointRecordsInfoMapper> getAccountStarPointRecordsInfoMapperList() {
        return accountStarPointRecordsInfoMapperList;
    }

    public void setAccountStarPointRecordsInfoMapperList(List<AccountStarPointRecordsInfoMapper> accountStarPointRecordsInfoMapperList) {
        this.accountStarPointRecordsInfoMapperList = accountStarPointRecordsInfoMapperList;
    }
}
