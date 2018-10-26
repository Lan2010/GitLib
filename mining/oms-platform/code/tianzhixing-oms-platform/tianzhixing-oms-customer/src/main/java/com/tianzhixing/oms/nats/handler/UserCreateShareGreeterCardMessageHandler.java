package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserGreeterCardInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserGreeterCardService;
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
public class UserCreateShareGreeterCardMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserCreateShareGreeterCardMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.create.share.greetercard.subject}")
    private String queuesGroup;

    @Value("${nats.user.create.share.greetercard.queue.group}")
    private String subject;

    @Autowired
    private RPCUserGreeterCardService rpcUserGreeterCardService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user create share greeter card info message from nats, but message is null....");
            UserGreeterCardInfoMapper userGreeterCardInfoMapper = JSONUtil.jsonWithDateToBean(json, UserGreeterCardInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userGreeterCardInfoMapper.getPlatformFrom(), userGreeterCardInfoMapper.getOperationTime(), userGreeterCardInfoMapper.getOperationType())) {
                logger.error(" ... error check message with userGreeterCardInfoMapper[" + JSONUtil.beanToJson(userGreeterCardInfoMapper, false) + "] ...");
                return;
            }

            rpcUserGreeterCardService.insert(userGreeterCardInfoMapper);
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