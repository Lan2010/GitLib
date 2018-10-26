package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesOperationStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class PagesOperationStatisticsMapping {

    private PagesDimensionMapper pagesDimensionMapper;
    
    private ApplicationDimensionMapper applicationDimensionMapper;

    private List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers;

    public PagesOperationStatisticsMapping() {
    }

    public PagesOperationStatisticsMapping(PagesDimensionMapper pagesDimensionMapper, List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers) {
        this.pagesDimensionMapper = pagesDimensionMapper;
        this.pagesOperationStatisticsMappers = pagesOperationStatisticsMappers;
    }

    public PagesDimensionMapper getPagesDimensionMapper() {
        return pagesDimensionMapper;
    }

    public void setPagesDimensionMapper(PagesDimensionMapper pagesDimensionMapper) {
        this.pagesDimensionMapper = pagesDimensionMapper;
    }

    public List<PagesOperationStatisticsMapper> getPagesOperationStatisticsMappers() {
        return pagesOperationStatisticsMappers;
    }

    public void setPagesOperationStatisticsMappers(List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers) {
        this.pagesOperationStatisticsMappers = pagesOperationStatisticsMappers;
    }
    
    public PagesOperationStatisticsMapping(ApplicationDimensionMapper applicationDimensionMapper, List<PagesOperationStatisticsMapper> pagesOperationStatisticsMappers) {
        this.applicationDimensionMapper = applicationDimensionMapper;
        this.pagesOperationStatisticsMappers = pagesOperationStatisticsMappers;
    }

    public ApplicationDimensionMapper getApplicationDimensionMapper() {
        return applicationDimensionMapper;
    }

    public void setApplicationDimensionMapper(ApplicationDimensionMapper applicationDimensionMapper) {
        this.applicationDimensionMapper = applicationDimensionMapper;
    }
}
