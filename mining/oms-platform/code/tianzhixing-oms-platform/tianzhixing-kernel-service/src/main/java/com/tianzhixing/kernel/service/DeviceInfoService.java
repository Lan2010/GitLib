package com.tianzhixing.kernel.service;

import com.tianzhixing.kernel.commons.em.DeviceStatus;
import com.tianzhixing.kernel.model.device.DeviceInfoModel;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface DeviceInfoService {

    /**
     * 根据deviceid获取
     *
     * @param deviceId
     * @param org
     * @return
     */
    List<DeviceInfoModel> listByDeviceId(String deviceId, String org);

    /**
     * 添加
     *
     * @param deviceInfoModel
     */
    DeviceInfoModel add(DeviceInfoModel deviceInfoModel);

    /**
     * 更新设备状态
     *
     * @param id
     * @param status
     * @param operationTime
     * @param updateTime
     * @param version
     */
    void updateDeviceStatus(Long id, DeviceStatus status, Date operationTime, Date updateTime, Integer version);

    /**
     * 根据device id & account id获取
     *
     * @param deviceId
     * @param accountId
     * @return
     */
    DeviceInfoModel getByDeviceIdAndAccount(String deviceId, Long accountId);

    /**
     * 根据accountid & status 检索device id列表
     *
     * @param accountId
     * @param status
     * @return
     */
    List<DeviceInfoModel> listDevicesByAccountIdAndStatus(long accountId, DeviceStatus status);

    /**
     * 获取非重复数量数
     *
     * @return
     */
    long distinctCount();

    /**
     * 分组分页获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<String> listWithGroup(int from, int pageSize);

    /**
     * 获取当前设备的绑定账户清单
     *
     * @param deviceId
     * @return
     */
    List<Long> listBindAccountIdsByDeviceId(String deviceId);
}
