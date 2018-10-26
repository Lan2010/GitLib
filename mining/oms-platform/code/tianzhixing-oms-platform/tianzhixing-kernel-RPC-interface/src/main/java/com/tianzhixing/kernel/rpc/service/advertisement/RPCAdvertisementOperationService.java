package com.tianzhixing.kernel.rpc.service.advertisement;

import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.advertisement.AdvertisementOperationMapper;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface RPCAdvertisementOperationService {

    /**
     * 点击广告
     *
     * @param accountInfoMapper
     * @param advertisementOperationMapper
     * @param operationType
     * @return
     */
    AccountStarPointOperationInfoMapper clickOrAccess(AccountInfoMapper accountInfoMapper, AdvertisementOperationMapper advertisementOperationMapper, Integer operationType);

}
