package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.DeviceCheckinInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class DeviceCheckinRepository extends AbstractScyllaDBRepository<DeviceCheckinInfoModel> {

    /**
     * list
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<DeviceCheckinInfoModel> list(final long beginTime, final long endTime) {
        String sql = "select * from device_checkin_info where checkin_time >= " + beginTime + " and checkin_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
