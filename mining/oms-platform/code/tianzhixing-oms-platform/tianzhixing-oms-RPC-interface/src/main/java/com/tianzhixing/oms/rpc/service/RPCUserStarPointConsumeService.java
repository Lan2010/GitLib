package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.UserStarPointConsumeInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCUserStarPointConsumeService {

    /**
     * insert
     *
     * @param userStarPointConsumeInfoMapper
     */
    void insert(UserStarPointConsumeInfoMapper userStarPointConsumeInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<UserStarPointConsumeInfoMapper> list(Date beginTime, Date endTime);
}
