package com.tianzhixing.oms.bussiness.statistics.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.task.RPCTaskAllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/19.
 */
@Service("taskAllotService")
public class TaskAllotService {

    @Autowired
    private RPCTaskAllotService rpcTaskAllotService;

    /**
     * 添加分配的任务数据
     *
     * @param map
     */
    public void add(Map<String, List<TaskAllotMapper>> map) {
        rpcTaskAllotService.add(map);
    }

    public long count(Integer taskType, Date executeTime) {
        return rpcTaskAllotService.count(taskType, executeTime);
    }
}
