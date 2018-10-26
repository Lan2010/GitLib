package com.tianzhixing.oms.bussiness.backend.web.mapping;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.AdvertisementDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAdvertisementStatisticsMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/16.
 */
public class UserAdvertisementStatisticsMapping {

    private AdvertisementDimensionMapper advertisementDimensionMapper;

    private List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers;

    public UserAdvertisementStatisticsMapping() {
    }

    public UserAdvertisementStatisticsMapping(AdvertisementDimensionMapper advertisementDimensionMapper, List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers) {
        this.advertisementDimensionMapper = advertisementDimensionMapper;
        this.userAdvertisementStatisticsMappers = userAdvertisementStatisticsMappers;
    }

    public AdvertisementDimensionMapper getAdvertisementDimensionMapper() {
        return advertisementDimensionMapper;
    }

    public void setAdvertisementDimensionMapper(AdvertisementDimensionMapper advertisementDimensionMapper) {
        this.advertisementDimensionMapper = advertisementDimensionMapper;
    }

    public List<UserAdvertisementStatisticsMapper> getUserAdvertisementStatisticsMappers() {
        return userAdvertisementStatisticsMappers;
    }

    public void setUserAdvertisementStatisticsMappers(List<UserAdvertisementStatisticsMapper> userAdvertisementStatisticsMappers) {
        this.userAdvertisementStatisticsMappers = userAdvertisementStatisticsMappers;
    }
}
