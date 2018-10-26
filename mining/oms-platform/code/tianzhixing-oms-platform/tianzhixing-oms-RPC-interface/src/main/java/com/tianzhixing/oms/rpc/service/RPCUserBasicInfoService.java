package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserBasicInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserBasicInfoService {

    /**
     * insert
     *
     * @param userBasicInfoMapper
     */
    void insert(UserBasicInfoMapper userBasicInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserBasicInfoMapper> list(Date beginTime, Date endTime);
}
