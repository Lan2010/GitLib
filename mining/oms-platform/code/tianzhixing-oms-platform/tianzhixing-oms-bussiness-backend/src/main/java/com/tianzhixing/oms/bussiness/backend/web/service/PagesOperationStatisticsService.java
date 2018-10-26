package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.PagesOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCPagesOperationStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class PagesOperationStatisticsService {

	@Autowired
    private RPCPagesOperationStatisticsService rpcPagesOperationStatisticsService;

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;
    
    /**
     * 获取清单
     *
     * @param type
     * @return
     */
    public List<PagesOperationStatisticsMapping> list(int type) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<PagesOperationStatisticsMapping> pagesOperationStatisticsMappings = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
            if (subList != null && !subList.isEmpty()) {
                for (ApplicationDimensionMapper adm : subList) {
                    PagesOperationStatisticsMapping pagesOperationStatisticsMapping = new PagesOperationStatisticsMapping();
                    adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
                    List<PagesOperationStatisticsMapper> list = new ArrayList<>();
                    list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, adm.getName(),null));
                    pagesOperationStatisticsMapping.setApplicationDimensionMapper(adm);
                    pagesOperationStatisticsMapping.setPagesOperationStatisticsMappers(list);
                    pagesOperationStatisticsMappings.add(pagesOperationStatisticsMapping);
                }
            } else {
                //未配置客户端
                PagesOperationStatisticsMapping pagesOperationStatisticsMapping = new PagesOperationStatisticsMapping();
                List<PagesOperationStatisticsMapper> list = new ArrayList<>();
                list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, applicationDimensionMapper.getName(),null));
                pagesOperationStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
                pagesOperationStatisticsMapping.setPagesOperationStatisticsMappers(list);
                pagesOperationStatisticsMappings.add(pagesOperationStatisticsMapping);
            }

        }
        return pagesOperationStatisticsMappings;
    }

    private List<PagesOperationStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName,String url) {
        List<PagesOperationStatisticsMapper> list = new ArrayList<>();
        if (0 == type) {
            List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = rpcPagesOperationStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName(),url);
            Map<String, PagesOperationStatisticsMapper> pagesOperationStatisticsMapperMap = new HashMap<>();
            for (PagesOperationStatisticsMapper pagesOperationStatisticsMapper : pagesOperationStatisticsMappers) {
                pagesOperationStatisticsMapperMap.put(pagesOperationStatisticsMapper.getStatisticsHour().toString(), pagesOperationStatisticsMapper);
            }
            //获取小时 24小时
            for (Integer i = 0; i < hour; i++) {
                PagesOperationStatisticsMapper pagesOperationStatisticsMapper = pagesOperationStatisticsMapperMap.get(i.toString());
                if (pagesOperationStatisticsMapper == null) {
                    pagesOperationStatisticsMapper = new PagesOperationStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(), url,0,0,null, platformName);
                }
                list.add(pagesOperationStatisticsMapper);
            }
        } else if (1 == type) {
            List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = rpcPagesOperationStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName(),url);
            Map<String, PagesOperationStatisticsMapper> pagesOperationStatisticsMapperMap = new HashMap<>();
            for (PagesOperationStatisticsMapper pagesOperationStatisticsMapper : pagesOperationStatisticsMappers) {
                pagesOperationStatisticsMapperMap.put(pagesOperationStatisticsMapper.getStatisticsDay().toString(), pagesOperationStatisticsMapper);
            }
            //获取天
            for (Integer i = 1; i <= day; i++) {
                PagesOperationStatisticsMapper pagesOperationStatisticsMapper = pagesOperationStatisticsMapperMap.get(i.toString());
                if (pagesOperationStatisticsMapper == null) {
                    pagesOperationStatisticsMapper = new PagesOperationStatisticsMapper(null, null, null,null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(), url,0,0,null, platformName);
                }
                list.add(pagesOperationStatisticsMapper);
            }
        } else if (2 == type) {

            List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = rpcPagesOperationStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName(),url);
            Map<String, PagesOperationStatisticsMapper> pagesOperationStatisticsMapperMap = new HashMap<>();
            for (PagesOperationStatisticsMapper pagesOperationStatisticsMapper : pagesOperationStatisticsMappers) {
                pagesOperationStatisticsMapperMap.put(pagesOperationStatisticsMapper.getStatisticsMonth().toString(), pagesOperationStatisticsMapper);
            }
            //获取月
            for (Integer i = 1; i <= month; i++) {
                PagesOperationStatisticsMapper pagesOperationStatisticsMapper = pagesOperationStatisticsMapperMap.get(i.toString());
                if (pagesOperationStatisticsMapper == null) {
                    pagesOperationStatisticsMapper = new PagesOperationStatisticsMapper(null, null, null,null, null,i,year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(), url,0,0,null, platformName);
                }
                list.add(pagesOperationStatisticsMapper);
            }
        }
        return list;
    }
    
    
    public List<PagesOperationStatisticsMapper> listByPagesDimensionAndApplication(int type, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, PagesDimensionMapper pagesDimensionMapper) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers = new ArrayList<>();
        pagesOperationStatisticsMappers.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, applicationDimensionMapper.getName(),pagesDimensionMapper.getUrl()));
        return pagesOperationStatisticsMappers;
    }
}
