package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserTaskStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserTaskStatusService;
import com.tianzhixing.oms.utils.JSONUtil;
import com.tianzhixing.oms.utils.MessageValidateUtil;
import nats.client.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by routine.k on 2018/6/27.
 */
@Component
public class UserTaskAcceptFinishedMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserTaskAcceptFinishedMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.task.accept.finished.subject}")
    private String queuesGroup;

    @Value("${nats.user.task.accept.finished.queue.group}")
    private String subject;

    @Autowired
    private RPCUserTaskStatusService rpcUserTaskStatusService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user task accept-finished info message from nats, but message is null....");
            UserTaskStatusInfoMapper userTaskStatusInfoMapper = JSONUtil.jsonWithDateToBean(json, UserTaskStatusInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userTaskStatusInfoMapper.getPlatformFrom(), userTaskStatusInfoMapper.getOperationTime(), userTaskStatusInfoMapper.getTaskId(), userTaskStatusInfoMapper.getOperationType())) {
                logger.error(" ... error check message with userTaskStatusInfoMapper[" + JSONUtil.beanToJson(userTaskStatusInfoMapper, false) + "] ...");
                return;
            }

            rpcUserTaskStatusService.insert(userTaskStatusInfoMapper);
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Override
    public nats.client.Nats nats() {
        return nats;
    }

    @Override
    public String subject() {
        return subject;
    }

    @Override
    public String queuesGroup() {
        return queuesGroup;
    }
}
