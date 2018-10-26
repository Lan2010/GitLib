package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.PagesOperationStatisticsModel;

import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface PagesOperationStatisticsService {

    /**
     * insert
     *
     * @param pagesOperationStatisticsModels
     * @param taskAllotId
     */
    void insert(List<PagesOperationStatisticsModel> pagesOperationStatisticsModels, Long taskAllotId);
    
    /**
     * 获取统计值（sum）
     *
     * @param statisticsHour
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @return
     */
    Map<String, Object> getSum(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String url);
   
//    /**
//     * 获取统计值（sum）
//     *
//     * @param statisticsHour
//     * @param statisticsDay
//     * @param statisticsMonth
//     * @param statisticsYear
//     * @param url
//     * @return
//     */
//    Map<String, Object> getSum(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType);
//    
    /**
     * 获取统计值（sum）
     *
     * @param statisticsDay
     * @param statisticsMonth
     * @param statisticsYear
     * @param clientPlatformType
     * @param platformFrom
     * @return
     */
    List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String url);
}
