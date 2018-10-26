package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserStarPointConsumeStatisticsModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface UserStarPointConsumeStatisticsService {

    /**
     * insert
     *
     * @param userStarPointConsumeStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserStarPointConsumeStatisticsModel> userStarPointConsumeStatisticsModels, Long taskAllotId);
}
