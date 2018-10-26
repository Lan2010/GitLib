package com.tianzhixing.kernel.grpc.handler;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.utils.Assert;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointRecordsInfoWithPagerMapper;
import com.tianzhixing.kernel.rpc.mapper.account.UnCollectionStarPointRecordsInfoMapper;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/21.
 */
@Service
public class AccountStarPointHandler {

    @Autowired
    private RPCAccountInfoService rpcAccountInfoService;


    /**
     * 流水记录
     *
     * @param accountToken
     * @param beginTime
     * @param endTime
     * @param from
     * @param size
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    public AccountStarPointRecordsInfoWithPagerMapper records(String accountToken, Date beginTime, Date endTime, int from, int size, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        return rpcAccountInfoService.records(accountInfoMapper, beginTime, endTime, from, size, taskId, advertisementId, starPointRecordsType);
    }

    /**
     * 未采集记录
     *
     * @param accountToken
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UnCollectionStarPointRecordsInfoMapper> unCollectionRecords(String accountToken, Date beginTime, Date endTime) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        return rpcAccountInfoService.unCollectionRecords(accountInfoMapper, beginTime, endTime);
    }

}
