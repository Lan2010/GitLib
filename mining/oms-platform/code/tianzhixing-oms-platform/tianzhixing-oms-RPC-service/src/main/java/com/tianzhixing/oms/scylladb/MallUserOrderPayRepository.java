package com.tianzhixing.oms.scylladb;

import com.tianzhixing.oms.model.MallUserOrderPayInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Repository
public class MallUserOrderPayRepository extends AbstractScyllaDBRepository<MallUserOrderPayInfoModel> {

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    public List<MallUserOrderPayInfoModel> list(long beginTime, long endTime) {
        String sql = "select * from mall_user_order_pay_info where operation_time >= " + beginTime + " and operation_time <= " + endTime + " ALLOW FILTERING";
        return super.list(sql);
    }
}
