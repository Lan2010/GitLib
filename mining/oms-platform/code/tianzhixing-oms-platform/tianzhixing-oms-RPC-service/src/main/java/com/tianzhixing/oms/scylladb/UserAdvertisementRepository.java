package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserAdvertisementInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserAdvertisementRepository extends AbstractScyllaDBRepository<UserAdvertisementInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserAdvertisementInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_advertisement_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
