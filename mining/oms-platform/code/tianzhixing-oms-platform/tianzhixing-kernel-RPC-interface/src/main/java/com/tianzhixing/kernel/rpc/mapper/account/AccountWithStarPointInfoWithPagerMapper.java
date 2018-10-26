package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;
import java.util.List;

/**
 * Created by routine.k on 2018/6/21.
 */
public class AccountWithStarPointInfoWithPagerMapper implements Serializable{

    private long total;

    private List<AccountWithStarPointInfoMapper> accountWithStarPointInfoMapperList;

    public AccountWithStarPointInfoWithPagerMapper() {
    }

    public AccountWithStarPointInfoWithPagerMapper(long total, List<AccountWithStarPointInfoMapper> accountWithStarPointInfoMapperList) {
        this.total = total;
        this.accountWithStarPointInfoMapperList = accountWithStarPointInfoMapperList;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }

    public List<AccountWithStarPointInfoMapper> getAccountWithStarPointInfoMapperList() {
        return accountWithStarPointInfoMapperList;
    }

    public void setAccountWithStarPointInfoMapperList(List<AccountWithStarPointInfoMapper> accountWithStarPointInfoMapperList) {
        this.accountWithStarPointInfoMapperList = accountWithStarPointInfoMapperList;
    }
}
