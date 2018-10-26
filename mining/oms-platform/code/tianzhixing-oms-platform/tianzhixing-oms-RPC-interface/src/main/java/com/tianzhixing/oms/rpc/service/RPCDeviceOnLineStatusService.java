package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.DeviceOnlineStatusInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCDeviceOnLineStatusService {

    /**
     * insert
     *
     * @param deviceOnlineStatusInfoMapper
     */
    void insert(DeviceOnlineStatusInfoMapper deviceOnlineStatusInfoMapper);

    /**
     * list
     * @param beginTime
     * @param endTime
     * @return
     */
    List<DeviceOnlineStatusInfoMapper> list(Date beginTime, Date endTime);
}
