package com.tianzhixing.kernel.rpc.mapper.account;

import java.io.Serializable;

/**
 * Created by routine.k on 2018/7/5.
 */
public class AccountStarPointOperationInfoMapper implements Serializable {

    /**
     * 采集的星点数
     */
    private Double starPoint;

    /**
     * 账户星点信息
     */
    private AccountStarPointInfoMapper accountStarPointInfoMapper;

    public AccountStarPointOperationInfoMapper() {
    }

    public AccountStarPointOperationInfoMapper(Double starPoint, AccountStarPointInfoMapper accountStarPointInfoMapper) {
        this.starPoint = starPoint;
        this.accountStarPointInfoMapper = accountStarPointInfoMapper;
    }

    public Double getStarPoint() {
        return starPoint;
    }

    public void setStarPoint(Double starPoint) {
        this.starPoint = starPoint;
    }

    public AccountStarPointInfoMapper getAccountStarPointInfoMapper() {
        return accountStarPointInfoMapper;
    }

    public void setAccountStarPointInfoMapper(AccountStarPointInfoMapper accountStarPointInfoMapper) {
        this.accountStarPointInfoMapper = accountStarPointInfoMapper;
    }
}
