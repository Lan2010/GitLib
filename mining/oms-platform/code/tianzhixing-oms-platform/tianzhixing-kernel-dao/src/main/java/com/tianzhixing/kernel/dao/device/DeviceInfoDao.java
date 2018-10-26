package com.tianzhixing.kernel.dao.device;

import com.tianzhixing.kernel.commons.em.DeviceStatus;
import com.tianzhixing.kernel.dao.generic.GenericBaseDao;
import com.tianzhixing.kernel.model.device.DeviceInfoModel;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/22.
 */
@Repository("deviceInfoDao")
public class DeviceInfoDao extends GenericBaseDao<DeviceInfoModel, Long> {

    /**
     * 根据deviceid获取
     *
     * @param deviceId
     * @param org
     * @return
     */
    public List<DeviceInfoModel> listByDeviceId(final String deviceId, final String org) {
        return super.list(new String[]{"device_id", "org"}, new Object[]{deviceId, org});
    }

    /**
     * 添加
     *
     * @param deviceInfoModel
     * @return
     */
    public DeviceInfoModel add(final DeviceInfoModel deviceInfoModel) {
        long id = super.insert(deviceInfoModel);
        deviceInfoModel.setId(id);
        return deviceInfoModel;
    }

    /**
     * 更新设备状态
     *
     * @param id
     * @param status
     * @param operationTime
     * @param updateTime
     * @param version
     */
    public void updateDeviceStatus(final Long id, final DeviceStatus status, final Date operationTime, final Date updateTime, final Integer version) {
        super.update(id, new String[]{"device_status", "operation_time", "update_time"}, new Object[]{status.name(), operationTime, updateTime}, version);
    }

    /**
     * 根据deviceid & account获取
     *
     * @param deviceId
     * @param accountId
     * @return
     */
    public DeviceInfoModel getByDeviceIdAndAccount(final String deviceId, final Long accountId) {
        return super.get(new String[]{"device_id", "account_id"}, new Object[]{deviceId, accountId});
    }

    /**
     * 根据accountid&status获取deviceid清单
     *
     * @param accountId
     * @param status
     * @return
     */
    public List<DeviceInfoModel> listDevicesIdsByAccountIdAndStatus(final Long accountId, final DeviceStatus status) {
        return super.list(new String[]{"account_id", "device_status"}, new Object[]{accountId, status.name()});
    }

    /**
     * 获取非重复记录数
     *
     * @return
     */
    public long distinctCount() {
        String sql = "select count(distinct(device_id)) from device_info ";
        return super.count(sql);
    }

    /**
     * 分组分页获取设备id
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<String> listWithGroup(final int from, final int pageSize) {
        String sql = "select device_id as deviceId from device_info group by device_id limit " + from + ", " + pageSize;
        List<Object> list = super.listProperty(sql, "deviceId");
        List<String> results = new ArrayList<>();
        for (Object obj : list) {
            results.add(obj.toString());
        }
        return results;
    }

    /**
     * 根据设备id获取账户清单
     *
     * @param deviceId
     * @return
     */
    public List<Long> listBindAccountIdsByDeviceId(final String deviceId) {
        String sql = "select account_id as accountId from device_info where device_id = '" + deviceId + "' and device_status = '" + DeviceStatus.BIND.name() + "'";
        List<Object> list = super.listProperty(sql, "accountId");
        List<Long> results = new ArrayList<>();
        for (Object obj : list) {
            results.add(Long.valueOf(obj.toString()));
        }
        return results;
    }
}
