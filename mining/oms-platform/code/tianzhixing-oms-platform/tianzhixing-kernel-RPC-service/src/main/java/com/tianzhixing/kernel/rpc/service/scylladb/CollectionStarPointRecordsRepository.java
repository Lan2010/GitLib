package com.tianzhixing.kernel.rpc.service.scylladb;

import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by routine.k on 2018/6/27.
 */
@Repository
public class CollectionStarPointRecordsRepository extends AbstractScyllaDBRepository<CollectionStarPointRecordsInfo> {

    public final static String TABLE = "collection_starpoint_records";

    /**
     * 插入数据
     *
     * @param
     */
    public void insert(List<CollectionStarPointRecordsInfo> collectionStarPointRecordsInfos) {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("BEGIN BATCH ");
        //循环更新
        for (CollectionStarPointRecordsInfo collectionStarPointRecordsInfo : collectionStarPointRecordsInfos) {
            stringBuffer.append(" INSERT INTO " + table() + "(create_time, account_id, oper_starpoint, records_type, task_id, advertisement_id, record_token) " +
                    "VALUES('" +
                    CalendarUtil.dateTime2String(collectionStarPointRecordsInfo.getCreateTime()) + "'," +
                    collectionStarPointRecordsInfo.getAccountId() + ",'" +
                    new BigDecimal(collectionStarPointRecordsInfo.getOperStarPoint()).setScale(6, BigDecimal.ROUND_HALF_UP) + "'," +
                    collectionStarPointRecordsInfo.getRecordsType() + ",'" +
                    collectionStarPointRecordsInfo.getTaskId() + "','" +
                    collectionStarPointRecordsInfo.getAdvertisementId() + "','" +
                    collectionStarPointRecordsInfo.getRecordToken() + "'); ");
        }
        stringBuffer.append(" APPLY BATCH");
        session().execute(stringBuffer.toString());
    }

    @Override
    String table() {
        return TABLE;
    }
}
