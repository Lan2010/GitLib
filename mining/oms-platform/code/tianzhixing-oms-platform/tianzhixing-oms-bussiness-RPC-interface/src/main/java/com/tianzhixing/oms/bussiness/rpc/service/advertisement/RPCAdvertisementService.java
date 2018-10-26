package com.tianzhixing.oms.bussiness.rpc.service.advertisement;

import com.tianzhixing.oms.bussiness.rpc.mapper.advertisement.AdvertisementMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface RPCAdvertisementService {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    AdvertisementMapper getById(Long id);

    /**
     * 计算数量
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime);

    /**
     * 获取记录数
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    List<AdvertisementMapper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize);

    /**
     * 添加
     *
     * @param advertisementMapper
     * @return
     */
    AdvertisementMapper add(AdvertisementMapper advertisementMapper);

    /**
     * 更新
     *
     * @param advertisementMapper
     */
    void update(AdvertisementMapper advertisementMapper);

    /**
     * 更新发送
     *
     * @param isSend
     */
    void updateIsSend(boolean isSend, Long id);
}
