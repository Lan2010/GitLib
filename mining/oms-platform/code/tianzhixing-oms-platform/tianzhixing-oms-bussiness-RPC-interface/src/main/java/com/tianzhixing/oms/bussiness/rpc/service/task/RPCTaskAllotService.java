package com.tianzhixing.oms.bussiness.rpc.service.task;

import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/19.
 */
public interface RPCTaskAllotService {

    /**
     * 添加任务清单 key=machine number， value=task list
     *
     * @param map
     */
    void add(Map<String, List<TaskAllotMapper>> map);

    /**
     * 获取任务清单
     *
     * @param date
     * @param taskType
     * @param taskStatus
     * @param currentMachine
     * @return
     */
    List<TaskAllotMapper> list(Date date, Integer taskType, Integer taskStatus, String currentMachine);

    /**
     * 获取任务数量
     *
     * @param taskType
     * @param executeTime
     * @return
     */
    long count(Integer taskType, Date executeTime);
}
