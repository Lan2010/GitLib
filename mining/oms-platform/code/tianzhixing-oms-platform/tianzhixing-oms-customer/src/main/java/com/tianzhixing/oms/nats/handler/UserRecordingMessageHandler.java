package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserRecordingInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserRecordingService;
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
public class UserRecordingMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserRecordingMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.recording.subject}")
    private String queuesGroup;

    @Value("${nats.user.recording.queue.group}")
    private String subject;

    @Autowired
    private RPCUserRecordingService rpcUserRecordingService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user recording info message from nats, but message is null....");
            UserRecordingInfoMapper userRecordingInfoMapper = JSONUtil.jsonWithDateToBean(json, UserRecordingInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userRecordingInfoMapper.getPlatformFrom(), userRecordingInfoMapper.getRecordingTime())) {
                logger.error(" ... error check message with userRecordingInfoMapper[" + JSONUtil.beanToJson(userRecordingInfoMapper, false) + "] ...");
                return;
            }

            rpcUserRecordingService.insert(userRecordingInfoMapper);
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
