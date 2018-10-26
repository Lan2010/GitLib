package com.tianzhixing.kernel.listener.scylladb;

import com.tianzhixing.kernel.listener.async.SpringAsyncConfig;
import com.tianzhixing.oms.redis.starpoint.DeviceMacCollectionStarPointRecords;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/29.
 */
@Service
@EnableAsync
public class DeviceMacStarPointRecordsService {

    @Autowired
    private DeviceMacStarPointRecordsRepository deviceMacStarPointRecordsRepository;

    @Async(SpringAsyncConfig.ASYNC_EXECUTOR)
    public void insert(DeviceMacCollectionStarPointRecords deviceMacCollectionStarPointRecords) {
        deviceMacStarPointRecordsRepository.insert(deviceMacCollectionStarPointRecords);
    }
}
