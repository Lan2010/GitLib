package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserBasicInfoModel;
import com.tianzhixing.oms.model.UserStarPointIncrementInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserStarPointIncrementRepository extends AbstractScyllaDBRepository<UserStarPointIncrementInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserStarPointIncrementInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_star_point_increment_info where increment_time >= " + beginTime + " and increment_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
