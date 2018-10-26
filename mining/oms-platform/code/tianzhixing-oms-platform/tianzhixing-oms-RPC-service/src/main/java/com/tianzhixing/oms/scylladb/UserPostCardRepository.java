package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserPostCardInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserPostCardRepository extends AbstractScyllaDBRepository<UserPostCardInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserPostCardInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_post_card_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
