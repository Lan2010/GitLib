package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserRecordingStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface RPCUserRecordingStatisticsService {

    /**
     * insert
     *
     * @param userRecordingStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserRecordingStatisticsMapper> userRecordingStatisticsMappers, TaskAllotMapper taskAllotMapper);
}
