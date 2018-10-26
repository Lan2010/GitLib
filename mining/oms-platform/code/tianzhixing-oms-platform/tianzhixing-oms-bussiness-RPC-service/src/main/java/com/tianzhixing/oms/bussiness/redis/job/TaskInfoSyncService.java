package com.tianzhixing.oms.bussiness.redis.job;

import com.tianzhixing.bussiness.commons.em.TaskStatus;
import com.tianzhixing.oms.bussiness.model.advertisement.AdvertisementInfoModel;
import com.tianzhixing.oms.bussiness.model.task.TaskInfoModel;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;
import com.tianzhixing.oms.bussiness.redis.service.RedisTaskService;
import com.tianzhixing.oms.bussiness.service.TaskInfoService;
import com.tianzhixing.oms.bussiness.service.TaskLocationInfoService;
import com.tianzhixing.oms.redis.bussiness.AdvertisementEntity;
import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 任务同步
 * Created by routine.k on 2018/7/1.
 */
@Service
public class TaskInfoSyncService {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskInfoSyncService.class);

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private TaskLocationInfoService taskLocationInfoService;

    @Autowired
    private RedisTaskService redisTaskService;

    public void sync() {

        if (!MacheNumCheckUtil.check()) {
            return;
        }

        LOGGER.info(".... begin check task info with redis ....");
        Long maxId = taskInfoService.maxId();
        for (long i = 1; i <= maxId; i++) {
            TaskInfoModel taskInfoModel = taskInfoService.getById(i);
            if (taskInfoModel == null) continue;
            if (!TaskStatus.ENABLE.equals(taskInfoModel.getTaskStatus()) || CalendarUtil.isAfter(new Date(), taskInfoModel.getEndTime())) {
                redisTaskService.remove(taskInfoModel.getId());
                continue;
            }
            TaskEntity taskEntity = redisTaskService.get(i);
            if (taskEntity == null || taskEntity.getVersion() != taskInfoModel.getVersion()) {
                List<TaskLocationInfoModel> list = taskLocationInfoService.list(i);
                redisTaskService.cache(taskInfoModel, list);
            }
        }
        LOGGER.info(".... finished check task info with redis ....");
    }
}
