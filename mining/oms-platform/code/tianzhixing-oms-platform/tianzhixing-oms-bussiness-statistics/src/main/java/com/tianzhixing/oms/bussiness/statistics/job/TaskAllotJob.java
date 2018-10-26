package com.tianzhixing.oms.bussiness.statistics.job;

import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.statistics.em.TaskListEnum;
import com.tianzhixing.oms.bussiness.statistics.service.TaskAllotService;
import com.tianzhixing.oms.bussiness.statistics.utils.MachineNumUtils;
import com.tianzhixing.oms.utils.CalendarUtil;
import com.tianzhixing.oms.utils.ResourceBundleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 任务分配
 * Created by routine.k on 2018/6/19.
 */
public class TaskAllotJob {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskAllotJob.class);

    @Autowired
    private TaskAllotService taskAllotService;

    public void execute() {
        LOGGER.info(".... begin allot task ....");
        //获取当前机器号
        String currentMachine = MachineNumUtils.currentMachine();
        if (StringUtils.isEmpty(currentMachine)) {
            LOGGER.info("... cancel allot task, current machine number not found");
            return;
        }
        String mastNum = ResourceBundleUtil.getStringValue("job.handout.master.num", "system-config");
        if (!currentMachine.equals(mastNum)) {
            LOGGER.info("... cancel allot task, current machine number[" + currentMachine + "] not eq mast machine number[" + mastNum + "]");
            return;
        }
        //获取机器列表
        Map<String, List<TaskAllotMapper>> map = new HashMap<>();
        String[] machineList = ResourceBundleUtil.getStringValue("machine.num.list", "system-config").split(",");
        for (String machine : machineList) {
            List<TaskAllotMapper> list = new ArrayList<>();
            map.put(machine, list);
        }
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date currentTime = new Date();
        Date date = CalendarUtil.calDateForMin(CalendarUtil.calBeginTimeForThisTime(currentTime), 1);
        for (TaskListEnum item : TaskListEnum.values()) {
            String machine = null;
            //寻找最小任务数的机器
            int count = Integer.MAX_VALUE;
            for (String key : map.keySet()) {
                if (count > map.get(key).size()) {
                    count = map.get(key).size();
                    machine = key;
                }
            }
            for (int i = 1; i < 25; i++) {
                TaskAllotMapper taskAllotMapper = new TaskAllotMapper();
                taskAllotMapper.setVersion(0);
                taskAllotMapper.setCreateTime(currentTime);
                taskAllotMapper.setUpdateTime(currentTime);
                taskAllotMapper.setExecuteTime(CalendarUtil.calDateForHour(date, i));
                taskAllotMapper.setExecuteDay(simpleDateFormat.format(taskAllotMapper.getExecuteTime()));
                taskAllotMapper.setTaskType(1);
                taskAllotMapper.setTaskStatus(0);
                taskAllotMapper.setMachineNum(machine);
                taskAllotMapper.setTaskName(item.name());
                //如果已经存在，则取消添加
                long taskCount = taskAllotService.count(taskAllotMapper.getTaskType(), taskAllotMapper.getExecuteTime());
                if (taskCount != 0) continue;
                map.get(machine).add(taskAllotMapper);
            }
        }

        taskAllotService.add(map);
    }
}
