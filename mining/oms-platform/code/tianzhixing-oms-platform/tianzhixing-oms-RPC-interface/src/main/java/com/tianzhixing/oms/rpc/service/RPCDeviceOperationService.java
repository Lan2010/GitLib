package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.DeviceOperationInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCDeviceOperationService {

    /**
     * insert
     *
     * @param deviceOperationInfoMapper
     */
    void insert(DeviceOperationInfoMapper deviceOperationInfoMapper);

    /**
     * list
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DeviceOperationInfoMapper> list(Date beginTime, Date endTime);
}
