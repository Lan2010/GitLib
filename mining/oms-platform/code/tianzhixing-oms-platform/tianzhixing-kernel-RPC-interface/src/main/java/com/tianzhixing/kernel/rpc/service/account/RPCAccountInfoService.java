package com.tianzhixing.kernel.rpc.service.account;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.rpc.mapper.account.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 * Created by routine.k on 2018/6/21.
 */
public interface RPCAccountInfoService {

    /**
     * 通过account token获取
     *
     * @param accountToken
     * @return
     */
    AccountInfoMapper getByAccountToken(String accountToken);

    /**
     * 通过手机号获取
     *
     * @param mobile
     * @param org
     * @return
     */
    AccountInfoMapper getByMobile(String mobile, String org);

    /**
     * 通过第三方TOKEN获取
     *
     * @param thirdToken
     * @param org
     * @return
     */
    AccountInfoMapper getByThirdToken(String thirdToken, String org);

    /**
     * 添加
     *
     * @param accountInfoMapper
     * @param referrerAccountId
     */
    AccountInfoMapper add(AccountInfoMapper accountInfoMapper, Long referrerAccountId);

    /**
     * 通过account token获取星点账户
     *
     * @param accountId
     * @return
     */
    AccountStarPointInfoMapper getAccountStarPointByAccountId(Long accountId);

    /**
     * 获取排行数据
     *
     * @param org
     * @param from
     * @param size
     * @return
     */
    AccountWithStarPointInfoWithPagerMapper listWithRanking(String org, int from, int size);

    /**
     * 获取流水记录
     *
     * @param accountInfoMapper
     * @param beginTime
     * @param endTime
     * @param from
     * @param size
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    AccountStarPointRecordsInfoWithPagerMapper records(AccountInfoMapper accountInfoMapper, Date beginTime, Date endTime, int from, int size, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType);

    /**
     * 获取未采集星点记录
     *
     * @param accountInfoMapper
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UnCollectionStarPointRecordsInfoMapper> unCollectionRecords(AccountInfoMapper accountInfoMapper, Date beginTime, Date endTime);

    /**
     * 记录信息，以天为单位
     *
     * @param accountInfoMapper
     * @param endTime
     * @param days
     * @return
     */
    List<AccountStarPointWithDayRecordsMapper> recordsWithDay(AccountInfoMapper accountInfoMapper, Date endTime, int days);

    /**
     * 根据任务获取星点
     *
     * @param accountInfoMapper
     * @param taskIds
     * @return
     */
    List<AccountStarPointWithTaskMapper> starPointWithTask(AccountInfoMapper accountInfoMapper, List<String> taskIds);
}
