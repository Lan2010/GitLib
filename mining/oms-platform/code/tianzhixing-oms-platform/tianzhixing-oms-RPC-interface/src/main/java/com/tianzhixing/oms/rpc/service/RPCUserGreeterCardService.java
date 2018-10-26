package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserGreeterCardInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserGreeterCardService {

    /**
     * insert
     *
     * @param userGreeterCardInfoMapper
     */
    void insert(UserGreeterCardInfoMapper userGreeterCardInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserGreeterCardInfoMapper> list(Date beginTime, Date endTime);
}
