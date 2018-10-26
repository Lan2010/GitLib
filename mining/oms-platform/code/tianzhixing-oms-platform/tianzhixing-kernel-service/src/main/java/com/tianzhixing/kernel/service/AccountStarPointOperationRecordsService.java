package com.tianzhixing.kernel.service;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/29.
 */
public interface AccountStarPointOperationRecordsService {

    /**
     * 查询数量
     *
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    long count(Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType);

    /**
     * 检索数据
     *
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @param from
     * @param size
     * @return
     */
    List<AccountStarPointOperationRecordsInfoModel> list(Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType, int from, int size);

    /**
     * 检索数据
     *
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    List<AccountStarPointOperationRecordsInfoModel> list(Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType);

    /**
     * 根据任务获取星点
     *
     * @param taskId
     * @param accountId
     * @return
     */
    Double starPointWithTask(Long accountId, String taskId);
}
