package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.UserRecordingInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class UserRecordingRepository extends AbstractScyllaDBRepository<UserRecordingInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<UserRecordingInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from user_recording_info where recording_time >= " + beginTime + " and recording_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
