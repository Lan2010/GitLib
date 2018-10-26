package com.tianzhixing.kernel.rpc.service.account;

import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface RPCAccountStarPointOperationService {

    /**
     * 采集星点
     *
     * @param accountInfoMapper
     * @param recordsSet
     * @param advertId
     * @param adverOperationType
     * @return
     */
    AccountStarPointOperationInfoMapper collection(AccountInfoMapper accountInfoMapper, Set<String> recordsSet, String advertId, Integer adverOperationType);

    /**
     * 奖励
     *
     * @param systemParamType
     * @param accountInfoMapper
     */
    AccountStarPointOperationInfoMapper award(String systemParamType, AccountInfoMapper accountInfoMapper);

    /**
     * 绑定通讯录奖励
     *
     * @param contactCount
     * @param accountInfoMapper
     * @return
     */
    AccountStarPointOperationInfoMapper bindAddressListAward(int contactCount, AccountInfoMapper accountInfoMapper);
}
