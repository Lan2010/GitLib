package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.ApplicationOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationOperationStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class ApplicationOperationStatisticsService {

    @Autowired
    private RPCApplicationOperationStatisticsService rpcApplicationOperationStatisticsService;

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    /**
     * 获取清单
     *
     * @param type
     * @return
     */
    public List<ApplicationOperationStatisticsMapping> list(int type) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<ApplicationOperationStatisticsMapping> applicationOperationStatisticsMappings = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
            if (subList != null && !subList.isEmpty()) {
                for (ApplicationDimensionMapper adm : subList) {
                    ApplicationOperationStatisticsMapping applicationOperationStatisticsMapping = new ApplicationOperationStatisticsMapping();
                    adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
                    List<ApplicationOperationStatisticsMapper> list = new ArrayList<>();
                    list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, adm.getName()));
                    applicationOperationStatisticsMapping.setApplicationDimensionMapper(adm);
                    applicationOperationStatisticsMapping.setApplicationOperationStatisticsMappers(list);
                    applicationOperationStatisticsMappings.add(applicationOperationStatisticsMapping);
                }
            } else {
                //未配置客户端
                ApplicationOperationStatisticsMapping applicationOperationStatisticsMapping = new ApplicationOperationStatisticsMapping();
                List<ApplicationOperationStatisticsMapper> list = new ArrayList<>();
                list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, applicationDimensionMapper.getName()));
                applicationOperationStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
                applicationOperationStatisticsMapping.setApplicationOperationStatisticsMappers(list);
                applicationOperationStatisticsMappings.add(applicationOperationStatisticsMapping);
            }

        }
        return applicationOperationStatisticsMappings;
    }

    private List<ApplicationOperationStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName) {
        List<ApplicationOperationStatisticsMapper> list = new ArrayList<>();
        if (0 == type) {
            List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers = rpcApplicationOperationStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName());
            Map<String, ApplicationOperationStatisticsMapper> applicationOperationStatisticsMapperMap = new HashMap<>();
            for (ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper : applicationOperationStatisticsMappers) {
                applicationOperationStatisticsMapperMap.put(applicationOperationStatisticsMapper.getStatisticsHour().toString(), applicationOperationStatisticsMapper);
            }
            //获取小时 24小时
            for (Integer i = 0; i < hour; i++) {
                ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper = applicationOperationStatisticsMapperMap.get(i.toString());
                if (applicationOperationStatisticsMapper == null) {
                    applicationOperationStatisticsMapper = new ApplicationOperationStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), platformName, applicationDimensionMapper.getValue(), 0, 0, 0, 0);
                }
                list.add(applicationOperationStatisticsMapper);
            }
//            //获取小时 24小时
//            for (int i = 0; i < hour; i++) {
//                list.add(rpcApplicationOperationStatisticsService.get(i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue()));
//            }
        } else if (1 == type) {
            List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers = rpcApplicationOperationStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName());
            Map<String, ApplicationOperationStatisticsMapper> applicationOperationStatisticsMapperMap = new HashMap<>();
            for (ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper : applicationOperationStatisticsMappers) {
                applicationOperationStatisticsMapperMap.put(applicationOperationStatisticsMapper.getStatisticsDay().toString(), applicationOperationStatisticsMapper);
            }
            //获取天
            for (Integer i = 1; i <= day; i++) {
                ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper = applicationOperationStatisticsMapperMap.get(i.toString());
                if (applicationOperationStatisticsMapper == null) {
                    applicationOperationStatisticsMapper = new ApplicationOperationStatisticsMapper(null, null, null, null, i, month, year, adm == null ? null : adm.getValue(), platformName, applicationDimensionMapper.getValue(), 0, 0, 0, 0);
                }
                list.add(applicationOperationStatisticsMapper);
            }

//            //获取天
//            for (int i = 1; i <= day; i++) {
//                list.add(rpcApplicationOperationStatisticsService.get(null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue()));
//            }
        } else if (2 == type) {

            List<ApplicationOperationStatisticsMapper> applicationOperationStatisticsMappers = rpcApplicationOperationStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName());
            Map<String, ApplicationOperationStatisticsMapper> applicationOperationStatisticsMapperMap = new HashMap<>();
            for (ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper : applicationOperationStatisticsMappers) {
                applicationOperationStatisticsMapperMap.put(applicationOperationStatisticsMapper.getStatisticsMonth().toString(), applicationOperationStatisticsMapper);
            }
            //获取月
            for (Integer i = 1; i <= month; i++) {
                ApplicationOperationStatisticsMapper applicationOperationStatisticsMapper = applicationOperationStatisticsMapperMap.get(i.toString());
                if (applicationOperationStatisticsMapper == null) {
                    applicationOperationStatisticsMapper = new ApplicationOperationStatisticsMapper(null, null, null, null, null, i, year, adm == null ? null : adm.getValue(), platformName, applicationDimensionMapper.getValue(), 0, 0, 0, 0);
                }
                list.add(applicationOperationStatisticsMapper);
            }

//            //获取月
//            for (int i = 1; i <= month; i++) {
//                list.add(rpcApplicationOperationStatisticsService.get(null, null, i, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue()));
//            }
        }
        return list;
    }
}
