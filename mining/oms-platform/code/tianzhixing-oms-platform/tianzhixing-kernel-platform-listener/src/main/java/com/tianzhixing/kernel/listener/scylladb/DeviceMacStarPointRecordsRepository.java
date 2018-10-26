package com.tianzhixing.kernel.listener.scylladb;

import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

/**
 * Created by routine.k on 2018/6/27.
 */
@Repository
public class DeviceMacStarPointRecordsRepository extends AbstractScyllaDBRepository<DeviceMacCollectionStarPointRecords> {

    public final static String TABLE = "device_mac_starpoint_records";

    /**
     * 插入数据
     *
     * @param deviceMacCollectionStarPointRecordsInfoModel
     */
    public void insert(DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecordsInfoModel) {
        session().execute(
                "INSERT INTO " + table() + "(version, timeout_hour, create_time, update_time, account_id, oper_starpoint, records_type, task_id, advertisement_id, longitude_and_latitude, record_token, remark, status, task_name, task_keyword, task_location_name, dev_id, dev_wifi, dev_bt, dev_time, dev_gps, devmac_count) " +
                        "VALUES(" +
                        deviceMacCollectionStarPointRecordsInfoModel.getVersion() + "," +
                        deviceMacCollectionStarPointRecordsInfoModel.getTimeoutHour() + ",'" +
                        CalendarUtil.dateTime2String(deviceMacCollectionStarPointRecordsInfoModel.getCreateTime()) + "','" +
                        CalendarUtil.dateTime2String(deviceMacCollectionStarPointRecordsInfoModel.getUpdateTime()) + "'," +
                        deviceMacCollectionStarPointRecordsInfoModel.getAccountId() + ",'" +
                        new BigDecimal(deviceMacCollectionStarPointRecordsInfoModel.getOperStarPoint()).setScale(6, BigDecimal.ROUND_HALF_UP) + "'," +
                        deviceMacCollectionStarPointRecordsInfoModel.getRecordsType() + ",'" +
                        (deviceMacCollectionStarPointRecordsInfoModel.getTaskId() == null ? "0" : deviceMacCollectionStarPointRecordsInfoModel.getTaskId()) + "','" +
                        (deviceMacCollectionStarPointRecordsInfoModel.getAdvertisementId() == null ? "0" : deviceMacCollectionStarPointRecordsInfoModel.getAdvertisementId()) + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getLongitudeAndLatitude() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getRecordToken() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getRemark() + "', " +
                        deviceMacCollectionStarPointRecordsInfoModel.getStatus() + ",'" +
                        deviceMacCollectionStarPointRecordsInfoModel.getTaskName() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getTaskKeyWord() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getTaskLocationName() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getDevid() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getDevwifi() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getDevbt() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getTime() + "','" +
                        deviceMacCollectionStarPointRecordsInfoModel.getGps() + "'," +
                        deviceMacCollectionStarPointRecordsInfoModel.getCount() +
                        ")"
        );
    }

    @Override
    String table() {
        return TABLE;
    }
}
