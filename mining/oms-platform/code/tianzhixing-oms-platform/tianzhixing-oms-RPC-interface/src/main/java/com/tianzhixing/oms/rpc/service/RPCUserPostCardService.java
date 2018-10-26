package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserPostCardInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserPostCardService {

    /**
     * insert
     *
     * @param userPostCardInfoMapper
     */
    void insert(UserPostCardInfoMapper userPostCardInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserPostCardInfoMapper> list(Date beginTime, Date endTime);
}
