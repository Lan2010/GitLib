package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.TaskDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCTaskDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service
public class TaskDimensionService {

    @Autowired
    private RPCTaskDimensionService rpcTaskDimensionService;

    public List<TaskDimensionMapper> list(boolean enable) {
        return rpcTaskDimensionService.list(enable);
    }
}
