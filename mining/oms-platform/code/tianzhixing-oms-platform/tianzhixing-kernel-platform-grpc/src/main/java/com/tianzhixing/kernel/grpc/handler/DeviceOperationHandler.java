package com.tianzhixing.kernel.grpc.handler;

import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.utils.Assert;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import com.tianzhixing.kernel.rpc.service.device.RPCDeviceOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service
public class DeviceOperationHandler {

    @Autowired
    private RPCDeviceOperationService rpcDeviceOperationService;

    @Autowired
    private RPCAccountInfoService rpcAccountInfoService;

    /**
     * 绑定设备
     *
     * @param accountToken
     * @param deviceId
     * @param deviceType
     * @param deviceMAC
     * @param deviceModel
     * @param bindTime
     */
    public void bind(String accountToken, String deviceId, String deviceType, String deviceMAC, String deviceModel, Date bindTime) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        rpcDeviceOperationService.bind(accountInfoMapper, deviceId, deviceMAC, deviceType, deviceModel, bindTime);
    }

    /**
     * 解除绑定
     *
     * @param accountToken
     * @param deviceId
     * @param unBindTime
     */
    public void unBind(String accountToken, String deviceId, Date unBindTime) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        rpcDeviceOperationService.unBind(accountInfoMapper, deviceId, unBindTime);
    }
}
