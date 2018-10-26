package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.PagesOperationStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCPagesOperationStatisticsService;
import com.tianzhixing.oms.bussiness.service.PagesOperationStatisticsService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCPagesOperationStatisticsService")
public class RPCPagesOperationStatisticsServiceImpl implements RPCPagesOperationStatisticsService {

    @Autowired
    private PagesOperationStatisticsService pagesOperationStatisticsService;

    @Override
    public void insert(List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers, TaskAllotMapper taskAllotMapper) {
        if (pagesOperationStatisticsMappers != null) {
            List<PagesOperationStatisticsModel> pagesOperationStatisticsModels = new ArrayList<>();
            for(PagesOperationStatisticsMapper pagesOperationStatisticsMapper : pagesOperationStatisticsMappers){
                PagesOperationStatisticsModel pagesOperationStatisticsModel = new PagesOperationStatisticsModel();
                BeanUtils.copyProperties(pagesOperationStatisticsMapper, pagesOperationStatisticsModel);
                pagesOperationStatisticsModels.add(pagesOperationStatisticsModel);
            }
            pagesOperationStatisticsService.insert(pagesOperationStatisticsModels, taskAllotMapper.getId());
        }
    }
    
    @Override
    public PagesOperationStatisticsMapper get(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom,String url) {
        Map<String, Object> map = pagesOperationStatisticsService.getSum(statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,url);
        return new PagesOperationStatisticsMapper(null, null, null, statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType,platformFrom,null, map.get("pagesOperationCount") == null ? 0 : Integer.valueOf(map.get("pagesOperationCount").toString()),map.get("pagesOperationDiffIPCount") == null ? 0 : Integer.valueOf(map.get("pagesOperationDiffIPCount").toString()),null,null);
    }
    
//    @Override
//    public PagesOperationStatisticsMapper get(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String url) {
//        Map<String, Object> map = pagesOperationStatisticsService.getSum(statisticsHour, statisticsDay, statisticsMonth, statisticsYear, url);
//        return new PagesOperationStatisticsMapper(null, null, null, statisticsHour, statisticsDay, statisticsMonth, statisticsYear, null,null,url, map.get("pagesOperationCount") == null ? 0 : Integer.valueOf(map.get("pagesOperationCount").toString()),map.get("pagesOperationDiffIPCount") == null ? 0 : Integer.valueOf(map.get("pagesOperationDiffIPCount").toString()),null,null);
//    }
    
    @Override
    public List<PagesOperationStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName,String url) {
        List<Map<String, Object>> listMap = pagesOperationStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom,url);
        List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            pagesOperationStatisticsMappers.add(new PagesOperationStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType,platformFrom, url, map.get("pagesOperationCount") == null ? 0 : Integer.valueOf(map.get("pagesOperationCount").toString()), map.get("pagesOperationDiffIPCount") == null ? 0 : Integer.valueOf(map.get("pagesOperationDiffIPCount").toString()),null ,platformName));
        }
        return pagesOperationStatisticsMappers;
    }
}
