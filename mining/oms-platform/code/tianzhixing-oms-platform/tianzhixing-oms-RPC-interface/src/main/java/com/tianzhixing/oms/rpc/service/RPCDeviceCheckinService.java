package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.DeviceCheckinInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCDeviceCheckinService {

    /**
     * insert
     *
     * @param deviceCheckinInfoMapper
     */
    void insert(DeviceCheckinInfoMapper deviceCheckinInfoMapper);

    /**
     * list
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DeviceCheckinInfoMapper> list(Date beginTime, Date endTime);
}
