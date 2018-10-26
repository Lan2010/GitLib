package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserLoginStatusStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserLoginStatusStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserLoginStatusStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserLoginStatusStatisticsService {

    @Autowired
    private RPCUserLoginStatusStatisticsService rpcUserLoginStatusStatisticsService;

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    /**
     * 获取清单
     *
     * @param type
     * @return
     */
    public List<UserLoginStatusStatisticsMapping> list(int type) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<UserLoginStatusStatisticsMapping> userLoginStatusStatisticsMappings = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
            if (subList != null && !subList.isEmpty()) {
                for (ApplicationDimensionMapper adm : subList) {
                    UserLoginStatusStatisticsMapping userLoginStatusStatisticsMapping = new UserLoginStatusStatisticsMapping();
                    adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
                    List<UserLoginStatusStatisticsMapper> list = new ArrayList<>();
                    list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, adm.getName()));
                    userLoginStatusStatisticsMapping.setApplicationDimensionMapper(adm);
                    userLoginStatusStatisticsMapping.setUserLoginStatusStatisticsMappers(list);
                    userLoginStatusStatisticsMappings.add(userLoginStatusStatisticsMapping);
                }
            } else {
                //未配置客户端
                UserLoginStatusStatisticsMapping userLoginStatusStatisticsMapping = new UserLoginStatusStatisticsMapping();
                List<UserLoginStatusStatisticsMapper> list = new ArrayList<>();
                list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, applicationDimensionMapper.getName()));
                userLoginStatusStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
                userLoginStatusStatisticsMapping.setUserLoginStatusStatisticsMappers(list);
                userLoginStatusStatisticsMappings.add(userLoginStatusStatisticsMapping);
            }

        }
        return userLoginStatusStatisticsMappings;
    }

    private List<UserLoginStatusStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String platformName) {
        List<UserLoginStatusStatisticsMapper> list = new ArrayList<>();
        if (0 == type) {
            List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers = rpcUserLoginStatusStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName());
            Map<String, UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMapperMap = new HashMap<>();
            for (UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper : userLoginStatusStatisticsMappers) {
                userLoginStatusStatisticsMapperMap.put(userLoginStatusStatisticsMapper.getStatisticsHour().toString(), userLoginStatusStatisticsMapper);
            }
            //获取小时 24小时
            for (Integer i = 0; i < hour; i++) {
                UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper = userLoginStatusStatisticsMapperMap.get(i.toString());
                if (userLoginStatusStatisticsMapper == null) {
                    userLoginStatusStatisticsMapper = new UserLoginStatusStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(),0,0,0,0,0, platformName);
                }
                list.add(userLoginStatusStatisticsMapper);
            }
        } else if (1 == type) {
            List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers = rpcUserLoginStatusStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName());
            Map<String, UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMapperMap = new HashMap<>();
            for (UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper : userLoginStatusStatisticsMappers) {
                userLoginStatusStatisticsMapperMap.put(userLoginStatusStatisticsMapper.getStatisticsDay().toString(), userLoginStatusStatisticsMapper);
            }
            //获取天
            for (Integer i = 1; i <= day; i++) {
                UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper = userLoginStatusStatisticsMapperMap.get(i.toString());
                if (userLoginStatusStatisticsMapper == null) {
                    userLoginStatusStatisticsMapper = new UserLoginStatusStatisticsMapper(null, null, null, null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(),0,0,0,0,0, platformName);
                }
                list.add(userLoginStatusStatisticsMapper);
            }

        } else if (2 == type) {

            List<UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMappers = rpcUserLoginStatusStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), applicationDimensionMapper.getName());
            Map<String, UserLoginStatusStatisticsMapper> userLoginStatusStatisticsMapperMap = new HashMap<>();
            for (UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper : userLoginStatusStatisticsMappers) {
                userLoginStatusStatisticsMapperMap.put(userLoginStatusStatisticsMapper.getStatisticsMonth().toString(), userLoginStatusStatisticsMapper);
            }
            //获取月
            for (Integer i = 1; i <= month; i++) {
                UserLoginStatusStatisticsMapper userLoginStatusStatisticsMapper = userLoginStatusStatisticsMapperMap.get(i.toString());
                if (userLoginStatusStatisticsMapper == null) {
                    userLoginStatusStatisticsMapper = new UserLoginStatusStatisticsMapper(null, null, null, null, null, i, year,adm == null ? null : adm.getValue(), applicationDimensionMapper.getValue(),0,0,0,0,0, platformName);
                }
                list.add(userLoginStatusStatisticsMapper);
            }

        }
        return list;
    }
}
