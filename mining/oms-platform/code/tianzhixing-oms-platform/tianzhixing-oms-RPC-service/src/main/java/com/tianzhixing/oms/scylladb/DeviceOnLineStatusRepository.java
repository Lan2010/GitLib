package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.DeviceOnlineStatusInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class DeviceOnLineStatusRepository extends AbstractScyllaDBRepository<DeviceOnlineStatusInfoModel> {

    /**
     * 查询记录
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<DeviceOnlineStatusInfoModel> list(final long beginTime, final long endTime) {
        String sql = "select * from device_online_status_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
