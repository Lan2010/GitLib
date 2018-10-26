package com.tianzhixing.kernel.grpc.handler;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.kernel.commons.ex.AccountStarPointAwardException;
import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.utils.Assert;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointInfoMapper;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountStarPointOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service
public class AccountSarPointOperationHandler {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountSarPointOperationHandler.class);

    @Autowired
    private RPCAccountStarPointOperationService rpcAccountStarPointOperationService;

    @Autowired
    private RPCAccountInfoService rpcAccountInfoService;

    /**
     * 采集星点
     *
     * @param accountToken
     * @param recordsSet
     * @param advertId
     * @return
     */
    public AccountStarPointOperationInfoMapper collection(String accountToken, Set<String> recordsSet, String advertId, Integer advertOperationType) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        AccountStarPointInfoMapper accountStarPointInfoMapper = rpcAccountInfoService.getAccountStarPointByAccountId(accountInfoMapper.getId());
        Assert.notNull(accountStarPointInfoMapper, new AccountStarPointNotFoundException("... get account starpoint info by account token[" + accountToken + "] failed, account starpoint not found ..."));
        return rpcAccountStarPointOperationService.collection(accountInfoMapper, recordsSet, advertId, advertOperationType);

    }

    /**
     * 奖励
     *
     * @param systemParamType
     * @param accountToken
     */
    public AccountStarPointOperationInfoMapper award(SystemParamType systemParamType, String accountToken) {
        Assert.isTrue(SystemParamType.AWARDSET.contains(systemParamType), new AccountStarPointAwardException("award.type.error"));
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        AccountStarPointInfoMapper accountStarPointInfoMapper = rpcAccountInfoService.getAccountStarPointByAccountId(accountInfoMapper.getId());
        Assert.notNull(accountStarPointInfoMapper, new AccountStarPointNotFoundException("... get account starpoint info by account token[" + accountToken + "] failed, account starpoint not found ..."));
        return rpcAccountStarPointOperationService.award(systemParamType.name(), accountInfoMapper);
    }

    /**
     * 绑定通讯录奖励
     *
     * @param contactCount
     * @param accountToken
     * @return
     */
    public AccountStarPointOperationInfoMapper bindAddressListAward(int contactCount, String accountToken) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        AccountStarPointInfoMapper accountStarPointInfoMapper = rpcAccountInfoService.getAccountStarPointByAccountId(accountInfoMapper.getId());
        Assert.notNull(accountStarPointInfoMapper, new AccountStarPointNotFoundException("... get account starpoint info by account token[" + accountToken + "] failed, account starpoint not found ..."));
        return rpcAccountStarPointOperationService.bindAddressListAward(contactCount, accountInfoMapper);
    }
}
