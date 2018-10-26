package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserStarPointConsumeInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserStarPointIncrementInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointConsumeService;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointIncrementService;
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
public class UserStarPointIncrementMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserStarPointIncrementMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.starpoint.increment.subject}")
    private String queuesGroup;

    @Value("${nats.user.starpoint.increment.queue.group}")
    private String subject;

    @Autowired
    private RPCUserStarPointIncrementService rpcUserStarPointIncrementService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user starpoint increment info message from nats, but message is null....");
            UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper = JSONUtil.jsonWithDateToBean(json, UserStarPointIncrementInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userStarPointIncrementInfoMapper.getPlatformFrom(), userStarPointIncrementInfoMapper.getIncrementTime(), userStarPointIncrementInfoMapper.getIncrementCount(), userStarPointIncrementInfoMapper.getIncrementType())) {
                logger.error(" ... error check message with userStarPointIncrementInfoMapper[" + JSONUtil.beanToJson(userStarPointIncrementInfoMapper, false) + "] ...");
                return;
            }

            rpcUserStarPointIncrementService.insert(userStarPointIncrementInfoMapper);
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
