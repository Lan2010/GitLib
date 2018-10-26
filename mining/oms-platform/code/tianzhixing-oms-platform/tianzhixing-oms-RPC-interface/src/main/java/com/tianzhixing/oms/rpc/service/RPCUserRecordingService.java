package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserRecordingInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserRecordingService {

    /**
     * insert
     *
     * @param userRecordingInfoMapper
     */
    void insert(UserRecordingInfoMapper userRecordingInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserRecordingInfoMapper> list(Date beginTime, Date endTime);
}
