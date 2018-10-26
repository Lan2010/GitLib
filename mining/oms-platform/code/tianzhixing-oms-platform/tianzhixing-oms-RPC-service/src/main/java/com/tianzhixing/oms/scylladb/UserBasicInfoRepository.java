package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserBasicInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserBasicInfoRepository extends AbstractScyllaDBRepository<UserBasicInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserBasicInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_basic_info where reg_time >= " + beginTime + " and reg_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
