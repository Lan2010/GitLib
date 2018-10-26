package com.tianzhixing.kernel.rpc.service.task;

import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.task.TaskOperationMapper;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface RPCTaskOperationService {

    /**
     * 接受任务
     *
     * @param accountInfoMapper
     * @param taskOperationMapper
     */
    void accept(AccountInfoMapper accountInfoMapper, TaskOperationMapper taskOperationMapper);

    /**
     * 取消任务
     *
     * @param accountInfoMapper
     * @param taskId
     * @param cancelTime
     */
    void cancel(AccountInfoMapper accountInfoMapper, String taskId, Date cancelTime);
}
