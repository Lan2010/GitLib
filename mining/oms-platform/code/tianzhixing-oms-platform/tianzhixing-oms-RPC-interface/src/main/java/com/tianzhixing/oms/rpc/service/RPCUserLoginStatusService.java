package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserLoginStatusInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserLoginStatusService {

    /**
     * insert
     *
     * @param userLoginStatusInfoMapper
     */
    void insert(UserLoginStatusInfoMapper userLoginStatusInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserLoginStatusInfoMapper> list(Date beginTime, Date endTime);
}
