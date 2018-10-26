package com.tianzhixing.oms.bussiness.rpc.service.impl.task;

import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskAllotMapper;
import com.tianzhixing.oms.bussiness.rpc.service.task.RPCTaskAllotService;
import com.tianzhixing.oms.bussiness.service.TaskAllotService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/19.
 */
@Service("RPCTaskAllotService")
public class RPCTaskAllotServiceImpl implements RPCTaskAllotService {

    @Autowired
    private TaskAllotService taskAllotService;

    @Override
    public void add(Map<String, List<TaskAllotMapper>> map) {
        List<TaskAllotModel> list = new ArrayList<>();
        for (String key : map.keySet()) {
            List<TaskAllotMapper> taskAllotMappers = map.get(key);
            for (TaskAllotMapper taskAllotMapper : taskAllotMappers) {
                list.add(_mapperToModel(taskAllotMapper));
            }
        }
        taskAllotService.add(list);
    }

    @Override
    public List<TaskAllotMapper> list(Date date, Integer taskType, Integer taskStatus, String currentMachine) {
        List<TaskAllotModel> taskAllotModels = taskAllotService.list(date, taskType, taskStatus, currentMachine);
        List<TaskAllotMapper> list = new ArrayList<>();
        if (taskAllotModels != null) {
            for (TaskAllotModel taskAllotModel : taskAllotModels) {
                list.add(_toMapper(taskAllotModel));
            }
        }
        return list;
    }

    @Override
    public long count(Integer taskType, Date executeTime) {
        return taskAllotService.count(taskType, executeTime);
    }

    private TaskAllotMapper _toMapper(TaskAllotModel taskAllotModel) {
        return new TaskAllotMapper(taskAllotModel.getId(), taskAllotModel.getVersion(), taskAllotModel.getCreateTime(), taskAllotModel.getUpdateTime(), taskAllotModel.getExecuteTime(), taskAllotModel.getExecuteDay(), taskAllotModel.getTaskType(), taskAllotModel.getTaskStatus(), taskAllotModel.getMachineNum(), taskAllotModel.getTaskName());
    }

    private TaskAllotModel _mapperToModel(TaskAllotMapper taskAllotMapper) {
        TaskAllotModel taskAllotModel = new TaskAllotModel();
        taskAllotModel.setExecuteDay(taskAllotMapper.getExecuteDay());
        taskAllotModel.setExecuteTime(taskAllotMapper.getExecuteTime());
        taskAllotModel.setCreateTime(taskAllotMapper.getCreateTime());
        taskAllotModel.setMachineNum(taskAllotMapper.getMachineNum());
        taskAllotModel.setTaskStatus(taskAllotMapper.getTaskStatus());
        taskAllotModel.setTaskType(taskAllotMapper.getTaskType());
        taskAllotModel.setUpdateTime(taskAllotMapper.getUpdateTime());
        taskAllotModel.setTaskName(taskAllotMapper.getTaskName());
        taskAllotModel.setVersion(taskAllotMapper.getVersion());
        return taskAllotModel;
    }
}
