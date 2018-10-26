package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserRecordingStatisticsModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/11.
 */
public interface UserRecordingStatisticsService {

    /**
     * insert
     *
     * @param userRecordingStatisticsModels
     * @param taskAllotId
     */
    void insert(List<UserRecordingStatisticsModel> userRecordingStatisticsModels, Long taskAllotId);
}
