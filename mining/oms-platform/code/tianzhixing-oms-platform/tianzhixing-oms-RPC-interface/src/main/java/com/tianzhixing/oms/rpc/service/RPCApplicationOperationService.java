package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCApplicationOperationService {

    /**
     * insert
     *
     * @param applicationOperationInfoMapper
     */
    void insert(ApplicationOperationInfoMapper applicationOperationInfoMapper);

    /**
     * 根据开始时间结束时间获取数据
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<ApplicationOperationInfoMapper> list(Date beginTime, Date endTime);
}
