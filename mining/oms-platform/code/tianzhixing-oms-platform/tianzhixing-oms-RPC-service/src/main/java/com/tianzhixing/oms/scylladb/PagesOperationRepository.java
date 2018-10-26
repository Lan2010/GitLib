package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.PagesOperationInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class PagesOperationRepository extends AbstractScyllaDBRepository<PagesOperationInfoModel> {

    public List<PagesOperationInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from pages_operation_info where access_time >= " + beginTime + " and access_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
