package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserStarPointConsumeInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserStarPointConsumeRepository extends AbstractScyllaDBRepository<UserStarPointConsumeInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserStarPointConsumeInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_star_point_consume_info where consume_time >= " + beginTime + " and consume_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
