package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.advertisement.AdvertisementInfoModel;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
public interface AdvertisementService {

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    AdvertisementInfoModel getById(Long id);

    /**
     * 获取记录数
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime);

    /**
     * 获取记录
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    List<AdvertisementInfoModel> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize);

    /**
     * 添加
     *
     * @param advertisementInfoModel
     * @return
     */
    AdvertisementInfoModel add(AdvertisementInfoModel advertisementInfoModel);

    /**
     * 更新
     *
     * @param advertisementInfoModel
     */
    void update(AdvertisementInfoModel advertisementInfoModel);

    /**
     * 更新发送
     *
     * @param isSend
     * @param advertisementInfoModel
     */
    void updateIsSend(boolean isSend, AdvertisementInfoModel advertisementInfoModel);

    /**
     * 获取最大ID
     *
     * @return
     */
    Long maxId();
}
