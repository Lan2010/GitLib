package com.tianzhixing.kernel.rpc.service.device;

import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface RPCDeviceOperationService {

    /**
     * 绑定设备
     *
     * @param accountInfoMapper
     * @param deviceId
     * @param deviceMAC
     * @param deviceType
     * @param deviceModel
     * @param bindTime
     */
    void bind(AccountInfoMapper accountInfoMapper, String deviceId, String deviceMAC, String deviceType, String deviceModel, Date bindTime);

    /**
     * 解除绑定
     *
     * @param accountInfoMapper
     * @param deviceId
     * @param unBindTime
     */
    void unBind(AccountInfoMapper accountInfoMapper, String deviceId, Date unBindTime);
}
