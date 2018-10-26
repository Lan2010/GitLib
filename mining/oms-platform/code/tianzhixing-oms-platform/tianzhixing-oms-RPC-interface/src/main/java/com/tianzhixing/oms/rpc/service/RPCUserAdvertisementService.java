package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserAdvertisementInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserAdvertisementService {

    /**
     * insert
     *
     * @param userAdvertisementInfoMapper
     */
    void insert(UserAdvertisementInfoMapper userAdvertisementInfoMapper);

    /**
     * list
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserAdvertisementInfoMapper> list(Date beginTime, Date endTime);
}
