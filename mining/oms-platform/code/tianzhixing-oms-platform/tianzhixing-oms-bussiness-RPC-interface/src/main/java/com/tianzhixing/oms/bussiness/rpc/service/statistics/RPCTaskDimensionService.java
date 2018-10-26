package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.TaskDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface RPCTaskDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<TaskDimensionMapper> list(boolean enable);

}
