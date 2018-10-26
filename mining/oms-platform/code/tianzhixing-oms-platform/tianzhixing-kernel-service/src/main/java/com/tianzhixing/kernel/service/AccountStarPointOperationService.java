package com.tianzhixing.kernel.service;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointInfoModel;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;

import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface AccountStarPointOperationService {

    /**
     * 采集星点
     *
     * @param accountId
     * @param accountStarPointOperationRecordsInfoModels
     * @param advertId
     * @param advertisementStarPoint
     * @return
     */
    AccountStarPointInfoModel collection(Long accountId, List<AccountStarPointOperationRecordsInfoModel> accountStarPointOperationRecordsInfoModels, Double advertisementStarPoint, String advertId);

    /**
     * 增加星点
     *
     * @param accountId
     * @param starPoint
     * @param starPointRecordsType
     * @param taskId
     * @param advertisementId
     * @param remark
     * @return
     */
    AccountStarPointInfoModel increment(Long accountId, Double starPoint, StarPointRecordsType starPointRecordsType, String taskId, String advertisementId, String remark);

    /**
     * 实例资金对象
     *
     * @param accountId
     * @param accountStarPointInfoModel
     * @param starPointRecordsType
     * @param advertisementId
     * @param taskId
     * @param remark
     * @param opertaionStarPoint
     * @return
     */
    AccountStarPointOperationRecordsInfoModel instanceOf(Long accountId, AccountStarPointInfoModel accountStarPointInfoModel, StarPointRecordsType starPointRecordsType, String advertisementId, String taskId, String remark, Double opertaionStarPoint);

    /**
     * 奖励
     *
     * @param accountId
     * @param starPoint
     * @param systemParamType
     */
    AccountStarPointInfoModel award(Long accountId, Double starPoint, String systemParamType);
}
