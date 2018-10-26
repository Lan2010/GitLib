package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserGreeterCardInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserGreeterCardRepository extends AbstractScyllaDBRepository<UserGreeterCardInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserGreeterCardInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_greeter_card_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
