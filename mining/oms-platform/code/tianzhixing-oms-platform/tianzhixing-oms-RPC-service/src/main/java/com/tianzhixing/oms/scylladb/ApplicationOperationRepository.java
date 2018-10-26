package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.ApplicationOperationInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class ApplicationOperationRepository extends AbstractScyllaDBRepository<ApplicationOperationInfoModel> {

    /**
     * 获取记录
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<ApplicationOperationInfoModel> list(final long beginTime, final long endTime) {
        String sql = "select * from application_operation_info where app_operation_time >= " + beginTime + " and app_operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
