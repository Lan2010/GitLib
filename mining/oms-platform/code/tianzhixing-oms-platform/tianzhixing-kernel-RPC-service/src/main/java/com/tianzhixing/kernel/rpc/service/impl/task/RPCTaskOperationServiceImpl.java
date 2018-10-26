package com.tianzhixing.kernel.rpc.service.impl.task;

import com.tianzhixing.kernel.commons.em.TaskStatus;
import com.tianzhixing.kernel.model.account.AccountInfoModel;
import com.tianzhixing.kernel.model.task.TaskInfoModel;
import com.tianzhixing.kernel.redis.service.RedisTaskService;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.task.TaskOperationMapper;
import com.tianzhixing.kernel.rpc.service.task.RPCTaskOperationService;
import com.tianzhixing.kernel.service.AccountInfoService;
import com.tianzhixing.kernel.service.TaskInfoService;
import com.tianzhixing.oms.redis.bussiness.TaskEntity;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/22.
 */
@Service("RPCTaskOperationService")
public class RPCTaskOperationServiceImpl implements RPCTaskOperationService {

    @Autowired
    private AccountInfoService accountInfoService;

    @Autowired
    private TaskInfoService taskInfoService;

    @Autowired
    private RedisTaskService redisTaskService;

    @Override
    public void accept(AccountInfoMapper accountInfoMapper, TaskOperationMapper taskOperationMapper) {
//        TaskEntity taskEntity = redisTaskService.getByTaskId(Long.valueOf(taskOperationMapper.getTaskId()));
//        Assert.notNull(taskEntity, "task.not.found");
//        Assert.isTrue(CalendarUtil.isAfter(taskOperationMapper.getAcceptTime(), taskEntity.getBeginTime()), "task.not.begin");
//        Assert.isTrue(CalendarUtil.isBefore(taskOperationMapper.getAcceptTime(), taskEntity.getEndTime()), "task.ending.time");
//        Assert.isTrue(taskEntity.getIsEnable(), "task.status.disable");
        //查询是否已经存在
        TaskInfoModel taskInfoModel = taskInfoService.getByAccountAndTaskId(accountInfoMapper.getId(), taskOperationMapper.getTaskId());
        if (taskInfoModel == null) {
            taskInfoModel = new TaskInfoModel();
            taskInfoModel.setAccountId(accountInfoMapper.getId());
            taskInfoModel.setCreateTime(new Date());
            taskInfoModel.setTaskId(taskOperationMapper.getTaskId());
            taskInfoModel.setTaskStatus(TaskStatus.ACCEPT);
            taskInfoModel.setUpdateTime(new Date());
            taskInfoModel.setVersion(0);
            taskInfoModel = taskInfoService.add(taskInfoModel);
        } else {
            if (!TaskStatus.ACCEPT.equals(taskInfoModel.getTaskStatus())) {
                taskInfoService.updateStatus(taskInfoModel.getId(), TaskStatus.ACCEPT, new Date(), taskOperationMapper.getAcceptTime(), taskInfoModel.getVersion());
            }
        }
        //to cache
        redisTaskService.cacheAccountAndTaskRelation(accountInfoMapper.getId(), taskInfoModel.getTaskId());
    }

    @Override
    public void cancel(AccountInfoMapper accountInfoMapper, String taskId, Date cancelTime) {
        //查询是否已经存在
        TaskInfoModel taskInfoModel = taskInfoService.getByAccountAndTaskId(accountInfoMapper.getId(), taskId);
        Assert.notNull(taskInfoModel, "... cancel task[" + taskId + "] failed, task not found for account ...");
        if (!TaskStatus.CANCEL.equals(taskInfoModel.getTaskStatus())) {
            taskInfoService.updateStatus(taskInfoModel.getId(), TaskStatus.CANCEL, new Date(), cancelTime, taskInfoModel.getVersion());
        }
        //remove from cache
        redisTaskService.removeAccountAndTaskRelation(accountInfoMapper.getId(), taskInfoModel.getTaskId());
    }
}
