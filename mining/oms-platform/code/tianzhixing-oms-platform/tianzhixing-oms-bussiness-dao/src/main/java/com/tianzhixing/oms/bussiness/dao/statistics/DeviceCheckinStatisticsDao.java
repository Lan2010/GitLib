package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceCheckinStatisticsModel;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/7.
 */
@Repository("deviceCheckinStatisticsDao")
public class DeviceCheckinStatisticsDao extends GenericBaseDao<DeviceCheckinStatisticsModel, Long> {
}
