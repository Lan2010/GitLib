package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.backend.web.mapping.ApplicationOperationStatisticsMapping;
import com.tianzhixing.oms.bussiness.backend.web.mapping.UserBasicStatisticsMapping;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationOperationStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserBasicStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationOperationStatisticsService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserBasicStatisticsService;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/14.
 */
@Service
public class UserBasicInfoStatisticsService {

    @Autowired
    private RPCUserBasicStatisticsService rpcUserBasicStatisticsService;

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    /**
     * 获取清单
     *
     * @param type
     * @return
     */
    public List<UserBasicStatisticsMapping> list(int type) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<UserBasicStatisticsMapping> userBasicStatisticsMappings = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMappers = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMappers) {
            List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
            if (subList != null && !subList.isEmpty()) {
                for (ApplicationDimensionMapper adm : subList) {
                    UserBasicStatisticsMapping userBasicStatisticsMapping = new UserBasicStatisticsMapping();
                    adm.setName(applicationDimensionMapper.getName() + "-" + adm.getName());
                    List<UserBasicStatisticsMapper> list = new ArrayList<>();
                    list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, null, null, null));
                    userBasicStatisticsMapping.setApplicationDimensionMapper(adm);
                    userBasicStatisticsMapping.setUserBasicStatisticsMappers(list);
                    userBasicStatisticsMappings.add(userBasicStatisticsMapping);
                }
            } else {
                //未配置客户端
                UserBasicStatisticsMapping userBasicStatisticsMapping = new UserBasicStatisticsMapping();
                List<UserBasicStatisticsMapper> list = new ArrayList<>();
                list.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, null, null, null, null));
                userBasicStatisticsMapping.setApplicationDimensionMapper(applicationDimensionMapper);
                userBasicStatisticsMapping.setUserBasicStatisticsMappers(list);
                userBasicStatisticsMappings.add(userBasicStatisticsMapping);
            }

        }
        return userBasicStatisticsMappings;
    }

    private List<UserBasicStatisticsMapper> _get(int type, Integer day, Integer hour, Integer month, Integer year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, String userFromType, String userFromTypeName, String platformName) {
        List<UserBasicStatisticsMapper> list = new ArrayList<>();
        if (0 == type) {
            List<UserBasicStatisticsMapper> userBasicStatisticsMappers = rpcUserBasicStatisticsService.list(day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), userFromType, userFromTypeName);
            Map<String, UserBasicStatisticsMapper> userBasicStatisticsMapperMap = new HashMap<>();
            for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMappers) {
                userBasicStatisticsMapperMap.put(userBasicStatisticsMapper.getStatisticsHour().toString(), userBasicStatisticsMapper);
            }
            //获取小时 24小时
            for (Integer i = 0; i < hour; i++) {
                UserBasicStatisticsMapper userBasicStatisticsMapper = userBasicStatisticsMapperMap.get(i.toString());
                if (userBasicStatisticsMapper == null) {
                    userBasicStatisticsMapper = new UserBasicStatisticsMapper(null, null, null, i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, userFromType, userFromTypeName, platformName);
                }
                list.add(userBasicStatisticsMapper);
            }
//            for (int i = 0; i < hour; i++) {
//                //list.add(rpcUserBasicStatisticsService.get(i, day, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), userFromType));
//            }
        } else if (1 == type) {
            List<UserBasicStatisticsMapper> userBasicStatisticsMappers = rpcUserBasicStatisticsService.list(null, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), userFromType, userFromTypeName);
            Map<String, UserBasicStatisticsMapper> userBasicStatisticsMapperMap = new HashMap<>();
            for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMappers) {
                userBasicStatisticsMapperMap.put(userBasicStatisticsMapper.getStatisticsDay().toString(), userBasicStatisticsMapper);
            }
            //获取天
            for (Integer i = 1; i <= day; i++) {
                UserBasicStatisticsMapper userBasicStatisticsMapper = userBasicStatisticsMapperMap.get(i.toString());
                if (userBasicStatisticsMapper == null) {
                    userBasicStatisticsMapper = new UserBasicStatisticsMapper(null, null, null, null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, userFromType, userFromTypeName, platformName);
                }
                list.add(userBasicStatisticsMapper);
            }
//            //获取天
//            for (int i = 1; i <= day; i++) {
//                list.add(rpcUserBasicStatisticsService.get(null, i, month, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), userFromType));
//            }
        } else if (2 == type) {
            List<UserBasicStatisticsMapper> userBasicStatisticsMappers = rpcUserBasicStatisticsService.list(null, null, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), userFromType, userFromTypeName);
            Map<String, UserBasicStatisticsMapper> userBasicStatisticsMapperMap = new HashMap<>();
            for (UserBasicStatisticsMapper userBasicStatisticsMapper : userBasicStatisticsMappers) {
                userBasicStatisticsMapperMap.put(userBasicStatisticsMapper.getStatisticsMonth().toString(), userBasicStatisticsMapper);
            }
            //获取月
            for (Integer i = 1; i <= month; i++) {
                UserBasicStatisticsMapper userBasicStatisticsMapper = userBasicStatisticsMapperMap.get(i.toString());
                if (userBasicStatisticsMapper == null) {
                    userBasicStatisticsMapper = new UserBasicStatisticsMapper(null, null, null, null, null, i, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), 0, userFromType, userFromTypeName, platformName);
                }
                list.add(userBasicStatisticsMapper);
            }
//            //获取月
//            for (int i = 1; i <= month; i++) {
//                list.add(rpcUserBasicStatisticsService.get(null, null, i, year, adm == null ? null : adm.getValue(), applicationDimensionMapper == null ? null : applicationDimensionMapper.getValue(), userFromType));
//            }
        }
        return list;
    }

    public List<UserBasicStatisticsMapper> listByUserChannel(int type, String userFromType, String userFromTypeName) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<UserBasicStatisticsMapper> userBasicStatisticsMappers = new ArrayList<>();
        userBasicStatisticsMappers.addAll(_get(type, day, hour, month, year, null, null, userFromType, userFromTypeName, null));
        return userBasicStatisticsMappers;
    }

    public List<UserBasicStatisticsMapper> listByUserChannelAndApplication(int type, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, UserChannelDimensionMapper channelDimensionMapper) {
        Date now = new Date();
        Integer day = Integer.valueOf(CalendarUtil.day(now));
        Integer hour = Integer.valueOf(CalendarUtil.hour(now));
        Integer month = Integer.valueOf(CalendarUtil.month(now));
        Integer year = Integer.valueOf(CalendarUtil.year(now));
        List<UserBasicStatisticsMapper> userBasicStatisticsMappers = new ArrayList<>();
        userBasicStatisticsMappers.addAll(_get(type, day, hour, month, year, applicationDimensionMapper, adm, channelDimensionMapper.getValue(), channelDimensionMapper.getName(), applicationDimensionMapper.getName()));
        return userBasicStatisticsMappers;
    }
}
