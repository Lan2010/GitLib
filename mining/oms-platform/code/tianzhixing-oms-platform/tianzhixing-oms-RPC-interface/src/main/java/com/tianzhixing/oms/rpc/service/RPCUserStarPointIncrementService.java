package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserStarPointIncrementInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserStarPointIncrementService {

    /**
     * insert
     *
     * @param userStarPointIncrementInfoMapper
     */
    void insert(UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserStarPointIncrementInfoMapper> list(Date beginTime, Date endTime);
}
