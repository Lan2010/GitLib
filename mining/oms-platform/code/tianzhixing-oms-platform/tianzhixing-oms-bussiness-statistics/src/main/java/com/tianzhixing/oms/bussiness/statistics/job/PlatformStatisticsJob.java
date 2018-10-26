package com.tianzhixing.oms.bussiness.statistics.job;

import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.task.RPCTaskAllotService;
import com.tianzhixing.oms.bussiness.statistics.em.TaskListEnum;
import com.tianzhixing.oms.bussiness.statistics.service.*;
import com.tianzhixing.oms.bussiness.statistics.utils.MachineNumUtils;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/6.
 */
public class PlatformStatisticsJob {

    private static Logger LOGGER = LoggerFactory.getLogger(PlatformStatisticsJob.class);

    @Autowired
    private RPCTaskAllotService rpcTaskAllotService;

    @Autowired
    private ApplicationOperationStatisticsService applicationOperationStatisticsService;

    @Autowired
    private DeviceCheckinStatisticsService deviceCheckinStatisticsService;

    @Autowired
    private DeviceOnlineStatusStatisticsService deviceOnlineStatusStatisticsService;

    @Autowired
    private DeviceOperationStatisticsService deviceOperationStatisticsService;

    @Autowired
    private PagesOperationStatisticsService pagesOperationStatisticsService;

    @Autowired
    private UserAdvertisementStatisticsService userAdvertisementStatisticsService;

    @Autowired
    private UserBasicStatisticsService userBasicStatisticsService;

    @Autowired
    private UserAuthStatisticsService userAuthStatisticsService;

    @Autowired
    private UserLoginStatusStatisticsService userLoginStatusStatisticsService;

    @Autowired
    private UserGreeterCardStatisticsService userGreeterCardStatisticsService;

    @Autowired
    private UserPostCardStatisticsService userPostCardStatisticsService;

    @Autowired
    private UserRecordingStatisticsService userRecordingStatisticsService;

    @Autowired
    private UserTaskStatisticsService userTaskStatisticsService;

    @Autowired
    private UserStarPointIncrementStatisticsService userStarPointIncrementStatisticsService;

    @Autowired
    private UserStarPointConsumeStatisticsService userStarPointConsumeStatisticsService;

    public void execute() {
        //获取当前机器号
        String currentMachine = MachineNumUtils.currentMachine();
        if (StringUtils.isEmpty(currentMachine)) {
            LOGGER.debug("... cancel application operation statistics task, current machine number not found");
            return;
        }
        //获取当前需要执行的任务清单
        Date date = new Date();
        List<TaskAllotMapper> taskAllotMapperList = rpcTaskAllotService.list(date, 1, 0, currentMachine);
        if (taskAllotMapperList == null || taskAllotMapperList.isEmpty()) {
            return;
        }
        for (TaskAllotMapper taskAllotMapper : taskAllotMapperList) {
            Date previousTime = CalendarUtil.calDateForHour(taskAllotMapper.getExecuteTime(), -1);
            //设置计算时间区间
            Date beginTime = CalendarUtil.calBeginHourTimeForThisTime(previousTime);
            Date endTime = CalendarUtil.calEndHourTimeForThisTime(previousTime);
            if (TaskListEnum.APPOPERATION.name().equals(taskAllotMapper.getTaskName())) {
                applicationOperationStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.DEVICECHECKIN.name().equals(taskAllotMapper.getTaskName())) {
                deviceCheckinStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.DEVICEREGANDONLINEANDOUTLINE.name().equals(taskAllotMapper.getTaskName())) {
                deviceOnlineStatusStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.DEVICEBINDANDUNBIND.name().equals(taskAllotMapper.getTaskName())) {
                deviceOperationStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.PAGESACCESS.name().equals(taskAllotMapper.getTaskName())) {
                pagesOperationStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERCLICKADVERTMENT.name().equals(taskAllotMapper.getTaskName())) {
                userAdvertisementStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERREG.name().equals(taskAllotMapper.getTaskName())) {
                userBasicStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERAUTH.name().equals(taskAllotMapper.getTaskName())) {
                userAuthStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERLOGINSTATUS.name().equals(taskAllotMapper.getTaskName())) {
                userLoginStatusStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERCREATEGREETERCARDANDSHARED.name().equals(taskAllotMapper.getTaskName())) {
                userGreeterCardStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERCREATEPOSTCARDANDSHARED.name().equals(taskAllotMapper.getTaskName())) {
                userPostCardStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERRECORDING.name().equals(taskAllotMapper.getTaskName())) {
                userRecordingStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERTASK.name().equals(taskAllotMapper.getTaskName())) {
                userTaskStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERSTARPOINTINCREMENT.name().equals(taskAllotMapper.getTaskName())) {
                userStarPointIncrementStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }
            if (TaskListEnum.USERSTARPOINTCONSUME.name().equals(taskAllotMapper.getTaskName())) {
                userStarPointConsumeStatisticsService.statistics(taskAllotMapper, beginTime, endTime);
            }

        }
    }
}
