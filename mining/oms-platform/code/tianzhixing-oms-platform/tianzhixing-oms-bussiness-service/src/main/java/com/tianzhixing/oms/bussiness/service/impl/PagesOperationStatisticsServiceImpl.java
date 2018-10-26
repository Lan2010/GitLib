package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.PagesOperationStatisticsDao;
import com.tianzhixing.oms.bussiness.dao.task.TaskAllotDao;
import com.tianzhixing.oms.bussiness.model.statistics.PagesOperationStatisticsModel;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.service.PagesOperationStatisticsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("pagesOperationStatisticsService")
public class PagesOperationStatisticsServiceImpl implements PagesOperationStatisticsService {

    @Autowired
    private TaskAllotDao taskAllotDao;

    @Autowired
    private PagesOperationStatisticsDao pagesOperationStatisticsDao;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void insert(List<PagesOperationStatisticsModel> pagesOperationStatisticsModels, Long taskAllotId) {
        for (PagesOperationStatisticsModel pagesOperationStatisticsModel : pagesOperationStatisticsModels) {
            pagesOperationStatisticsDao.insert(pagesOperationStatisticsModel);
        }
        TaskAllotModel taskAllotModel = taskAllotDao.get(taskAllotId);
        taskAllotModel.setUpdateTime(new Date());
        taskAllotModel.setTaskStatus(1);
        taskAllotDao.update(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel);
    }
    
    @Override
    public Map<String, Object> getSum(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String url) {
        return pagesOperationStatisticsDao.getSum(statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,url);
    }
    
//    @Override
//    public Map<String, Object> getSum(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String url) {
//        return pagesOperationStatisticsDao.getSum(statisticsHour, statisticsDay, statisticsMonth, statisticsYear, url);
//    }
    
    @Override
    public List<Map<String, Object>> listSum(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String url) {
        return pagesOperationStatisticsDao.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,url);
    }
}
