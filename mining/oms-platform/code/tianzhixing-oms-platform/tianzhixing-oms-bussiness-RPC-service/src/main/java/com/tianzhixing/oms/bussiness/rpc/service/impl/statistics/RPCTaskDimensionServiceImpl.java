package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.TaskDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.TaskDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCTaskDimensionService;
import com.tianzhixing.oms.bussiness.service.TaskDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCTaskDimensionService")
public class RPCTaskDimensionServiceImpl implements RPCTaskDimensionService {

    @Autowired
    private TaskDimensionService taskDimensionService;

    @Override
    public List<TaskDimensionMapper> list(boolean enable) {
        List<TaskDimensionModel> taskDimensionModels = taskDimensionService.list(enable);
        List<TaskDimensionMapper> taskDimensionMappers = new ArrayList<>();
        if (taskDimensionModels != null) {
            for (TaskDimensionModel taskDimensionModel : taskDimensionModels) {
                taskDimensionMappers.add(_toMapper(taskDimensionModel));
            }
        }
        return taskDimensionMappers;
    }

    private TaskDimensionMapper _toMapper(TaskDimensionModel taskDimensionModel) {
        return new TaskDimensionMapper(taskDimensionModel.getId(), taskDimensionModel.getVersion(), taskDimensionModel.getCreateTime(), taskDimensionModel.getName(), taskDimensionModel.getTaskId(), taskDimensionModel.getBeginTime(), taskDimensionModel.getEndTime(), taskDimensionModel.getEnable());
    }
}
