package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserRecordingInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserStarPointConsumeInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserRecordingService;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointConsumeService;
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
public class UserStarPointConsumeMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserStarPointConsumeMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.starpoint.consume.subject}")
    private String queuesGroup;

    @Value("${nats.user.starpoint.consume.queue.group}")
    private String subject;

    @Autowired
    private RPCUserStarPointConsumeService rpcUserStarPointConsumeService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user starpoint consume info message from nats, but message is null....");
            UserStarPointConsumeInfoMapper userStarPointConsumeInfoMapper = JSONUtil.jsonWithDateToBean(json, UserStarPointConsumeInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userStarPointConsumeInfoMapper.getPlatformFrom(), userStarPointConsumeInfoMapper.getConsumeTime(), userStarPointConsumeInfoMapper.getConsumeCount(), userStarPointConsumeInfoMapper.getConsumeCause())) {
                logger.error(" ... error check message with userStarPointConsumeInfoMapper[" + JSONUtil.beanToJson(userStarPointConsumeInfoMapper, false) + "] ...");
                return;
            }

            rpcUserStarPointConsumeService.insert(userStarPointConsumeInfoMapper);
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
