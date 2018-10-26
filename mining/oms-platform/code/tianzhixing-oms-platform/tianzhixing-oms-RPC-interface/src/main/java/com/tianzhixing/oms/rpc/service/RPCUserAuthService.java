package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserAuthInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserAuthService {

    /**
     * insert
     *
     * @param userAuthInfoMapper
     */
    void insert(UserAuthInfoMapper userAuthInfoMapper);

    /**
     * insert
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserAuthInfoMapper> list(Date beginTime, Date endTime);
}
