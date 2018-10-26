package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.MallUserOrderPayInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCMallUserOrderPayService {

    /**
     * insert
     *
     * @param mallUserOrderPayInfoMapper
     */
    void insert(MallUserOrderPayInfoMapper mallUserOrderPayInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<MallUserOrderPayInfoMapper> list(Date beginTime, Date endTime);
}
