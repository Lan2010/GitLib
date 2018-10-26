package com.tianzhixing.kernel.rpc.service.scylladb;

import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/29.
 */
@Service
@EnableAsync
public class ScylladbCollectionRecordsAsyncService {

    @Autowired
    private CollectionStarPointRecordsRepository collectionStarPointRecordsRepository;

    /**
     * 异步执行任务, 持久化用户采集记录
     */
    @Async("asyncExecutor")
    public void add(List<DeviceMacCollectionStarPointRecords> deviceMacCollectionStarPointRecordsInfoModelList) {
        List<CollectionStarPointRecordsInfo> collectionStarPointRecordsInfos = new ArrayList<>();
        for (DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords : deviceMacCollectionStarPointRecordsInfoModelList) {
            collectionStarPointRecordsInfos.add(_transfer(deviceMacCollectionStarPointRecords));
        }
        collectionStarPointRecordsRepository.insert(collectionStarPointRecordsInfos);
    }

    private CollectionStarPointRecordsInfo _transfer(DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords) {
        CollectionStarPointRecordsInfo collectionStarPointRecordsInfo = new CollectionStarPointRecordsInfo();
        collectionStarPointRecordsInfo.setCreateTime(new Date());
        collectionStarPointRecordsInfo.setAccountId(Long.valueOf(deviceMacCollectionStarPointRecords.getAccountId()));
        collectionStarPointRecordsInfo.setOperStarPoint(Double.valueOf(deviceMacCollectionStarPointRecords.getOperStarPoint()));
        collectionStarPointRecordsInfo.setRecordToken(deviceMacCollectionStarPointRecords.getRecordToken());
        collectionStarPointRecordsInfo.setRecordsType(deviceMacCollectionStarPointRecords.getRecordsType());
        collectionStarPointRecordsInfo.setTaskId(deviceMacCollectionStarPointRecords.getTaskId());
        collectionStarPointRecordsInfo.setAdvertisementId(deviceMacCollectionStarPointRecords.getAdvertisementId());
        return collectionStarPointRecordsInfo;
    }
}
