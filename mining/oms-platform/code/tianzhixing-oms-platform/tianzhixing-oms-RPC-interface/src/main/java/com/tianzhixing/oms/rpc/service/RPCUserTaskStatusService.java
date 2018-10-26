package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserTaskStatusInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserTaskStatusService {

    /**
     * insert
     *
     * @param userTaskStatusInfoMapper
     */
    void insert(UserTaskStatusInfoMapper userTaskStatusInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserTaskStatusInfoMapper> list(Date beginTime, Date endTime);
}
