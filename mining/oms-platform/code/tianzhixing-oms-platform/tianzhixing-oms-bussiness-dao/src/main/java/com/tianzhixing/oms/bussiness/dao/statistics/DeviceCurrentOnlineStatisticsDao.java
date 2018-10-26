package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.DeviceCurrentOnlineStatisticsModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
@Repository("deviceCurrentOnlineStatisticsDao")
public class DeviceCurrentOnlineStatisticsDao extends GenericBaseDao<DeviceCurrentOnlineStatisticsModel, Long> {

    /**
     * 根据状态获取
     *
     * @param status
     * @return
     */
    public Long count(final Integer status) {
        return super.count(new String[]{"status"}, new Object[]{status});
    }

    /**
     * 根据status分页获取
     *
     * @param status
     * @param from
     * @param pageSize
     * @return
     */
    public List<DeviceCurrentOnlineStatisticsModel> list(final Integer status, final int from, final int pageSize) {
        return super.list(new String[]{"status"}, new Object[]{status}, from, pageSize);
    }

    /**
     * 更新状态
     *
     * @param id
     * @param version
     * @param status
     */
    public void changeStatus(Long id, Integer version, Integer status) {
        super.update(id, new String[]{"status"}, new Object[]{status}, version);
    }

    /**
     * 根据deviceid & platform获取
     *
     * @param deviceId
     * @param platform
     * @return
     */
    public DeviceCurrentOnlineStatisticsModel getByDeviceId(final String deviceId, final String platform) {
        return super.get(new String[]{"device_id", "platform_from"}, new Object[]{deviceId, platform});
    }
}
