package com.tianzhixing.kernel.grpc.handler;

import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.utils.Assert;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.advertisement.AdvertisementOperationMapper;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import com.tianzhixing.kernel.rpc.service.advertisement.RPCAdvertisementOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service
public class AdvertisementOperationHandler {

    @Autowired
    private RPCAdvertisementOperationService rpcAdvertisementOperationService;

    @Autowired
    private RPCAccountInfoService rpcAccountInfoService;

    /**
     * 点击广告
     *
     * @param accountToken
     * @param advertId
     * @param clickTime
     * @return
     */
    public AccountStarPointOperationInfoMapper clickOrAccessAdvertisement(String accountToken, String advertId, Double starPoint, Date clickTime, Integer operationType) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        return rpcAdvertisementOperationService.clickOrAccess(accountInfoMapper, new AdvertisementOperationMapper(advertId, starPoint, clickTime), operationType);
    }
}
