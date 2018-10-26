package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserAuthInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserAuthRepository extends AbstractScyllaDBRepository<UserAuthInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserAuthInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_auth_info where auth_time >= " + beginTime + " and auth_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
