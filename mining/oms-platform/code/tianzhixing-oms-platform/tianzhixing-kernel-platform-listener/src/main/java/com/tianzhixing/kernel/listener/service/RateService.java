package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.listener.entity.DeviceEntity;
import com.tianzhixing.kernel.listener.entity.RateEntity;
import com.tianzhixing.kernel.listener.entity.TaskWithLocationEntity;
import com.tianzhixing.kernel.listener.redis.service.RedisSystemParamService;
import com.tianzhixing.kernel.listener.util.GpsUtil;
import com.tianzhixing.kernel.listener.util.LatLngUtil;
import com.tianzhixing.oms.redis.bussiness.SystemParamEntity;
import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.redis.bussiness.TaskLocationEntity;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service
public class RateService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @Autowired
    private TaskCacheService taskCacheService;

    @Autowired
    private RedisSystemParamService redisSystemParamService;

    public RateEntity calRate(Set<String> taskIds, DeviceEntity deviceEntity) {
        SystemParamEntity timeoutHourSystemParamEntity = redisSystemParamService.timeoutHour();
        String[] gpsArray = GpsUtil.convert(deviceEntity.getGps());
        for (String taskId : taskIds) {
            TaskWithLocationEntity taskWithLocationEntity = taskCacheService.taskInfo(taskId);
            if (taskWithLocationEntity == null) {
                logger.debug("....failed found task entity from cache with task id[" + taskId + "]....");
                continue;
            }
            TaskEntity taskEntity = taskWithLocationEntity.getTaskEntity();
            if (!taskEntity.getIsEnable() || CalendarUtil.isAfter(new Date(), taskEntity.getEndTime())) {
                //任务失效or任务已过有效期
                continue;
            }
            //获取rate信息
            List<TaskLocationEntity> taskLocationEntities = taskWithLocationEntity.getTaskLocationEntityList();
            if (taskLocationEntities != null) {
                for (TaskLocationEntity taskLocationEntity : taskLocationEntities) {
                    String[] taskLngArray = taskLocationEntity.getLocation().replace("|", "-").split("-");
                    //Double.valueOf(gpsArray[1], , )
                    double distance = LatLngUtil.getDistance(Double.valueOf(gpsArray[1]), Double.valueOf(gpsArray[0]), Double.valueOf(taskLngArray[0].replace("lat:", "").trim()), Double.valueOf(taskLngArray[1].replace("lng:", "").trim()));

                    logger.debug(".... device[" + deviceEntity.getDevid() + "], task[" + taskEntity.getId() + "] cal lng and lat distance[" + distance + "], between gps[" + gpsArray[0] + "," + gpsArray[1] + "] and location[" + taskLngArray[1].replace("lng:", "").trim() + "," + taskLngArray[0].replace("lat:", "").trim() + "] ....");

                    if (Double.valueOf(distance).intValue() <= taskWithLocationEntity.getTaskEntity().getTaskRadius()) {
                        //范围之内
                        RateEntity rateEntity = new RateEntity();
                        rateEntity.setTaskId(taskId);
                        rateEntity.setRate(taskWithLocationEntity.getTaskEntity().getRate());
                        rateEntity.setTimeoutHours(timeoutHourSystemParamEntity == null ? 24l : Long.valueOf(timeoutHourSystemParamEntity.getSystemValue()));
                        rateEntity.setTaskLocationName(taskLocationEntity.getName());
                        rateEntity.setTaskKeyWord(taskWithLocationEntity.getTaskEntity().getTaskName());
                        rateEntity.setTaskName(taskWithLocationEntity.getTaskEntity().getTaskName());
                        return rateEntity;
                    }
                }
            }
        }
        //没有找到任务坐标
        SystemParamEntity rateSystemParam = redisSystemParamService.rate();
        if (rateSystemParam == null) {
            logger.error("....failed found system param default rate....");
            return null;
        }
        RateEntity rateEntity = new RateEntity();
        rateEntity.setRate(rateSystemParam.getSystemValue());
        rateEntity.setTimeoutHours(timeoutHourSystemParamEntity == null ? 24l : Long.valueOf(timeoutHourSystemParamEntity.getSystemValue()));
        return rateEntity;
    }


}
