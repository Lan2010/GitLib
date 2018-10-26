package com.tianzhixing.kernel.redis.job;

import com.tianzhixing.kernel.model.task.TaskInfoModel;
import com.tianzhixing.kernel.redis.service.RedisDeviceService;
import com.tianzhixing.kernel.redis.service.RedisTaskService;
import com.tianzhixing.kernel.redis.service.RedisUnCollectionStarPointRecordsService;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.DeviceInfoService;
import com.tianzhixing.kernel.service.TaskInfoService;
import com.tianzhixing.oms.utils.ResourceBundleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 账户同步
 * Created by routine.k on 2018/7/1.
 */
@Service
public class AccountRelationSyncService {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountRelationSyncService.class);

    @Autowired
    private DeviceInfoService deviceInfoService;

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private RedisDeviceService redisDeviceService;

    @Autowired
    private RedisTaskService redisTaskService;

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private RedisUnCollectionStarPointRecordsService redisUnCollectionStarPointRecordsService;

    public void sync() {
        //获取当前机器号
        String machineArg = ResourceBundleUtil.getStringValue("machine.num.arg", "system-config");
        String currentMachine = System.getProperty(machineArg);
        if (StringUtils.isEmpty(currentMachine)) {
            LOGGER.debug("... cancel account sync task, current machine number not found");
            return;
        }
        String mastNum = ResourceBundleUtil.getStringValue("job.handout.master.num", "system-config");
        if (!currentMachine.equals(mastNum)) {
            LOGGER.debug("... cancel account sync task, current machine number[" + currentMachine + "] not eq mast machine number[" + mastNum + "]");
            return;
        }
        LOGGER.info(".... begin check device, task, uncollection records for account relation with redis ....");
        //check device
        _syncDevice();
        Long maxId = accountInfoService.maxId();
        for (long i = 1; i <= maxId; i++) {
            //check task
            List<TaskInfoModel> taskIds = taskInfoService.listTaskByAccountId(i, com.tianzhixing.kernel.commons.em.TaskStatus.ACCEPT);
            Set<String> taskIdList = new HashSet<>();
            Set<String> set = redisTaskService.getByAccountId(Long.valueOf(i));
            if (set == null) set = new HashSet<>();
            if (taskIds != null && !taskIds.isEmpty()) {
                for (TaskInfoModel taskInfoModel : taskIds) {
                    taskIdList.add(taskInfoModel.getTaskId());
                    //如果无效  - 暂时不清除用户任务信息
//                    TaskEntity taskEntity = redisTaskService.getByTaskId(taskInfoModel.getTaskId());
//                    if (taskEntity != null) {
//                        if (taskEntity.getIsEnable() && CalendarUtil.isAfter(taskEntity.getEndTime(), new Date())) {
//                            taskIdList.add(taskInfoModel.getTaskId());
//                        }
//                    } else {
//                        taskIdList.add(taskInfoModel.getTaskId());
//                    }
                    if (!set.contains(taskInfoModel.getTaskId())) {
                        redisTaskService.cacheAccountAndTaskRelation(Long.valueOf(i), taskInfoModel.getTaskId());
                        set.add(taskInfoModel.getTaskId());
                    }
                }
                //移除无效的任务
                for (String redisTaskId : set) {
                    if (taskIdList.contains(redisTaskId)) {
                        //有效，continue
                        continue;
                    } else {
                        //无效
                        redisTaskService.removeAccountAndTaskRelation(Long.valueOf(i), redisTaskId);
                    }
                }
            }

            //check uncollection records
            Set<String> unCollectionKeys = redisUnCollectionStarPointRecordsService.keys(Long.valueOf(i));
            if (unCollectionKeys != null && !unCollectionKeys.isEmpty()) {
                //check
                for (String key : unCollectionKeys) {
                    if (!redisUnCollectionStarPointRecordsService.checkExist(key)) {
                        //remove
                        redisUnCollectionStarPointRecordsService.remove(Long.valueOf(i), key);
                    }
                }

            }

        }
        LOGGER.info(".... finished check device, task, uncollection records for account relation with redis ....");
    }

    private void _syncDevice() {
        //同步设备信息
        //获取非重复设备记录数
        int pageSize = 50;
        long deviceCount = deviceInfoService.distinctCount();
        //分页处理
        int sum = Long.valueOf(deviceCount % pageSize == 0 ? deviceCount / pageSize : deviceCount / pageSize + 1).intValue();
        for (int i = 0; i < sum; i++) {
            int index = (i - 1) * pageSize;
            int from = (index < 0 ? 0 : index);
            List<String> deviceIds = deviceInfoService.listWithGroup(from, pageSize);
            for (String deviceId : deviceIds) {
                //获取当前设备绑定账户
                List<Long> accountIds = deviceInfoService.listBindAccountIdsByDeviceId(deviceId);
                //check redis
                Set<String> redisSet = redisDeviceService.get(deviceId);
                if (redisSet == null) redisSet = new HashSet<>();
                for (Long accountId : accountIds) {
                    if (redisSet.contains(accountId.toString())) {
                        //已经包含，continue
                        continue;
                    } else {
                        //cache
                        redisDeviceService.cacheAccountAndDeviceRelation(accountId, deviceId);
                    }
                }
                //移除不同步的数据
                for (String redisAccountId : redisSet) {
                    if (accountIds.contains(Long.valueOf(redisAccountId))) {
                        //已经包含 continue
                        continue;
                    } else {
                        //remove
                        redisDeviceService.removeAccountAndDeviceRelation(Long.valueOf(redisAccountId), deviceId);
                    }
                }
            }

        }
    }

}
