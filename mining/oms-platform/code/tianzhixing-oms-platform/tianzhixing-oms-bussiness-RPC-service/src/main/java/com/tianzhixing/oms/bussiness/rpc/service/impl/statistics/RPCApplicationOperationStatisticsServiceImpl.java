package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.ApplicationOperationStatisticsModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationOperationStatisticsService;
import com.tianzhixing.oms.bussiness.service.ApplicationOperationStatisticsService;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/7/6.
 */
@Service("RPCApplicationOperationStatisticsService")
public class RPCApplicationOperationStatisticsServiceImpl implements RPCApplicationOperationStatisticsService {

    @Autowired
    private ApplicationOperationStatisticsService applicationOperationStatisticsService;

    @Override
    public void insert(List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMapperList, TaskAllotMapper taskAllotMapper) {
        if (applicationOperationStatisticsMapperList != null) {
            List<ApplicationOperationStatisticsModel> applicationOperationStatisticsModelList = new ArrayList<>();
            for (ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper : applicationOperationStatisticsMapperList) {
                ApplicationOperationStatisticsModel applicationOperationStatisticsModel = new ApplicationOperationStatisticsModel();
                BeanUtils.copyProperties(applicationOperationStatisticsMapper, applicationOperationStatisticsModel);
                applicationOperationStatisticsModelList.add(applicationOperationStatisticsModel);
            }
            applicationOperationStatisticsService.insert(applicationOperationStatisticsModelList, taskAllotMapper.getId());
        }
    }

    @Override
    public ApplicationOperationStatisticsMapper get(Integer statisticsHour, Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName) {
        Map<String, Object> map = applicationOperationStatisticsService.getSum(statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom);
        return new ApplicationOperationStatisticsMapper(null, null, null, statisticsHour, statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformName, platformFrom, map.get("appStartTotalCount") == null ? 0 : Integer.valueOf(map.get("appStartTotalCount").toString()), map.get("appDownTotalCount") == null ? 0 : Integer.valueOf(map.get("appDownTotalCount").toString()), map.get("appStartDiffIPTotalCount") == null ? 0 : Integer.valueOf(map.get("appStartDiffIPTotalCount").toString()), map.get("appDownDiffIPTotalCount") == null ? 0 : Integer.valueOf(map.get("appDownDiffIPTotalCount").toString()));
    }

    @Override
    public List<ApplicationOperationStatisticsMapper> list(Integer statisticsDay, Integer statisticsMonth, Integer statisticsYear, String clientPlatformType, String platformFrom, String platformName) {
        List<Map<String, Object>> listMap = applicationOperationStatisticsService.listSum(statisticsDay, statisticsMonth, statisticsYear, clientPlatformType, platformFrom);
        List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers = new ArrayList<>();
        for (Map<String, Object> map : listMap) {
            applicationOperationStatisticsMappers.add(new ApplicationOperationStatisticsMapper(null, null, null, map.get("statisticsHour") == null ? null : Integer.valueOf(map.get("statisticsHour").toString()), map.get("statisticsDay") == null ? null : Integer.valueOf(map.get("statisticsDay").toString()), map.get("statisticsMonth") == null ? null : Integer.valueOf(map.get("statisticsMonth").toString()), map.get("statisticsYear") == null ? null : Integer.valueOf(map.get("statisticsYear").toString()), clientPlatformType, platformName, platformFrom, map.get("appStartTotalCount") == null ? 0 : Integer.valueOf(map.get("appStartTotalCount").toString()), map.get("appDownTotalCount") == null ? 0 : Integer.valueOf(map.get("appDownTotalCount").toString()), map.get("appStartDiffIPTotalCount") == null ? 0 : Integer.valueOf(map.get("appStartDiffIPTotalCount").toString()), map.get("appDownDiffIPTotalCount") == null ? 0 : Integer.valueOf(map.get("appDownDiffIPTotalCount").toString())));
        }
        return applicationOperationStatisticsMappers;
    }
}
