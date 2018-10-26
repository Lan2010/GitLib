package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.StarPointConsumeDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserStarPointConsumeStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCStarPointConsumeDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserStarPointConsumeStatisticsService;
import com.tianzhixing.oms.rpc.mapper.UserStarPointConsumeInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointConsumeService;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service
public class UserStarPointConsumeStatisticsService {

    @Autowired
    private RPCApplicationDimensionService rpcApplicationDimensionService;

    @Autowired
    private RPCStarPointConsumeDimensionService rpcStarPointConsumeDimensionService;

    @Autowired
    private RPCUserStarPointConsumeService rpcUserStarPointConsumeService;

    @Autowired
    private RPCUserStarPointConsumeStatisticsService rpcUserStarPointConsumeStatisticsService;

    public void statistics(TaskAllotMapper taskAllotMapper, Date beginTime, Date endTime) {
        int year = Integer.valueOf(CalendarUtil.year(beginTime));
        int month = Integer.valueOf(CalendarUtil.month(beginTime));
        int day = Integer.valueOf(CalendarUtil.day(beginTime));
        int hour = Integer.valueOf(CalendarUtil.hour(beginTime));
        //获取统计平台
        List<UserStarPointConsumeStatisticsMapper> userStarPointConsumeStatisticsMappers = new ArrayList<>();
        List<ApplicationDimensionMapper> applicationDimensionMapperList = rpcApplicationDimensionService.list(StatisticsDimension.PLATFORM, true);
        List<StarPointConsumeDimensionMapper> starPointConsumeDimensionMappers = rpcStarPointConsumeDimensionService.list(true);
        if (applicationDimensionMapperList != null && !applicationDimensionMapperList.isEmpty()) {
            List<UserStarPointConsumeInfoMapper> userStarPointConsumeInfoMappers = rpcUserStarPointConsumeService.list(beginTime, endTime);
            for (ApplicationDimensionMapper applicationDimensionMapper : applicationDimensionMapperList) {
                //获取client清单数据
                List<ApplicationDimensionMapper> subList = rpcApplicationDimensionService.listByParentId(applicationDimensionMapper.getId(), StatisticsDimension.CLIENT, true);
                if (subList != null && !subList.isEmpty()) {
                    for (ApplicationDimensionMapper adm : subList) {
                        userStarPointConsumeStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, adm, userStarPointConsumeInfoMappers, starPointConsumeDimensionMappers));
                    }
                } else {
                    //未配置客户端
                    userStarPointConsumeStatisticsMappers.addAll(_statistics(hour, day, month, year, applicationDimensionMapper, null, userStarPointConsumeInfoMappers, starPointConsumeDimensionMappers));
                }

            }
        }
        rpcUserStarPointConsumeStatisticsService.insert(userStarPointConsumeStatisticsMappers, taskAllotMapper);
    }

    private List<UserStarPointConsumeStatisticsMapper> _statistics(int hour, int day, int month, int year, ApplicationDimensionMapper applicationDimensionMapper, ApplicationDimensionMapper adm, List<UserStarPointConsumeInfoMapper> userStarPointConsumeInfoMappers, List<StarPointConsumeDimensionMapper> starPointConsumeDimensionMappers) {
        List<UserStarPointConsumeStatisticsMapper> list = new ArrayList<>();
        //设备类型
        for (StarPointConsumeDimensionMapper starPointConsumeDimensionMapper : starPointConsumeDimensionMappers) {
            UserStarPointConsumeStatisticsMapper userStarPointConsumeStatisticsMapper = new UserStarPointConsumeStatisticsMapper();
            userStarPointConsumeStatisticsMapper.setId(null);
            userStarPointConsumeStatisticsMapper.setVersion(0);
            userStarPointConsumeStatisticsMapper.setStatisticsHour(hour);
            userStarPointConsumeStatisticsMapper.setStatisticsDay(day);
            userStarPointConsumeStatisticsMapper.setStatisticsMonth(month);
            userStarPointConsumeStatisticsMapper.setStatisticsYear(year);
            userStarPointConsumeStatisticsMapper.setClientPlatformType(adm == null ? null : adm.getValue());
            userStarPointConsumeStatisticsMapper.setPlatformFrom(applicationDimensionMapper.getValue());
            userStarPointConsumeStatisticsMapper.setPlatformName(applicationDimensionMapper.getName() + (adm == null ? "" : ("-" + adm.getName())));
            userStarPointConsumeStatisticsMapper.setConsumeCause(starPointConsumeDimensionMapper.getValue());
            userStarPointConsumeStatisticsMapper.setConsumeCauseName(starPointConsumeDimensionMapper.getName());
            //计算
            double consumeCount = 0;//用户消费数量
            for (UserStarPointConsumeInfoMapper userStarPointConsumeInfoMapper : userStarPointConsumeInfoMappers) {
                if (!applicationDimensionMapper.getValue().equals(userStarPointConsumeInfoMapper.getPlatformFrom())) {
                    continue;
                }
                if (adm != null && !adm.getValue().equals(userStarPointConsumeInfoMapper.getClientPlatformType())) {
                    continue;
                }
                if (!starPointConsumeDimensionMapper.getValue().equals(userStarPointConsumeInfoMapper.getConsumeCause())) {
                    continue;
                }
                consumeCount = Double.valueOf(CalculateUtil.plus(consumeCount, StringUtils.isEmpty(userStarPointConsumeInfoMapper.getConsumeCount()) ? 0d : Double.valueOf(userStarPointConsumeInfoMapper.getConsumeCount()), 4));
            }
            userStarPointConsumeStatisticsMapper.setConsumeCount(consumeCount);
            list.add(userStarPointConsumeStatisticsMapper);
        }
        return list;
    }

}
