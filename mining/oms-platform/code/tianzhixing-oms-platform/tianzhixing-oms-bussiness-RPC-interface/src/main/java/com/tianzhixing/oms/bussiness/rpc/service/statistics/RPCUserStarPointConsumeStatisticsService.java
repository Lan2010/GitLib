package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointConsumeStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface RPCUserStarPointConsumeStatisticsService {

    /**
     * insert
     *
     * @param userStarPointConsumeStatisticsMappers
     * @param taskAllotMapper
     */
    void insert(List<UserStarPointConsumeStatisticsMapper> userStarPointConsumeStatisticsMappers, TaskAllotMapper taskAllotMapper);
}
