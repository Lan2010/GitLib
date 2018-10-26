package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserTaskStatusInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserTaskStatusRepository extends AbstractScyllaDBRepository<UserTaskStatusInfoModel> {
    public List<UserTaskStatusInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_task_status_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
