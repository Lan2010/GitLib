package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.DeviceOperationInfoModel;
import com.tianzhixing.oms.model.UserBasicInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class DeviceOperationRepository extends AbstractScyllaDBRepository<DeviceOperationInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<DeviceOperationInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from device_operation_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
