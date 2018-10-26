package com.tianzhixing.kernel.grpc.handler;

import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.utils.Assert;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.task.TaskOperationMapper;
import com.tianzhixing.kernel.rpc.service.account.RPCAccountInfoService;
import com.tianzhixing.kernel.rpc.service.task.RPCTaskOperationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service
public class TaskOperationHandler {

    @Autowired
    private RPCTaskOperationService rpcTaskOperationService;

    @Autowired
    private RPCAccountInfoService rpcAccountInfoService;

    /**
     * 接受任务
     *
     * @param accountToken
     * @param taskId
     * @param acceptTime
     */
    public void accept(String accountToken, String taskId, Date acceptTime) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        rpcTaskOperationService.accept(accountInfoMapper, new TaskOperationMapper(taskId, acceptTime));
    }

    /**
     * 取消任务
     *
     * @param accountToken
     * @param taskId
     * @param cancelTime
     */
    public void cancel(String accountToken, String taskId, Date cancelTime) {
        AccountInfoMapper accountInfoMapper = rpcAccountInfoService.getByAccountToken(accountToken);
        Assert.notNull(accountInfoMapper, new AccountStarPointNotFoundException("... get account info by account token[" + accountToken + "] failed, account not found ..."));
        rpcTaskOperationService.cancel(accountInfoMapper, taskId, cancelTime);
    }
}
