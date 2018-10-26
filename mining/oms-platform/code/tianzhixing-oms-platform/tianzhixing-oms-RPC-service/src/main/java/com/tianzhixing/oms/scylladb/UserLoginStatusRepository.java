package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserLoginStatusInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserLoginStatusRepository extends AbstractScyllaDBRepository<UserLoginStatusInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserLoginStatusInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_login_status_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
