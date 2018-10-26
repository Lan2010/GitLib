package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/6/21.
 */
public class AccountWithStarPointInfoMapper implements Serializable {

    /**
     * 账户信息
     */
    private AccountInfoMapper accountInfoMapper;

    /**
     * 星点信息
     */
    private AccountStarPointInfoMapper accountStarPointInfoMapper;

    public AccountWithStarPointInfoMapper() {
    }

    public AccountWithStarPointInfoMapper(AccountInfoMapper accountInfoMapper, AccountStarPointInfoMapper accountStarPointInfoMapper) {
        this.accountInfoMapper = accountInfoMapper;
        this.accountStarPointInfoMapper = accountStarPointInfoMapper;
    }

    public AccountInfoMapper getAccountInfoMapper() {
        return accountInfoMapper;
    }

    public void setAccountInfoMapper(AccountInfoMapper accountInfoMapper) {
        this.accountInfoMapper = accountInfoMapper;
    }

    public AccountStarPointInfoMapper getAccountStarPointInfoMapper() {
        return accountStarPointInfoMapper;
    }

    public void setAccountStarPointInfoMapper(AccountStarPointInfoMapper accountStarPointInfoMapper) {
        this.accountStarPointInfoMapper = accountStarPointInfoMapper;
    }
}
