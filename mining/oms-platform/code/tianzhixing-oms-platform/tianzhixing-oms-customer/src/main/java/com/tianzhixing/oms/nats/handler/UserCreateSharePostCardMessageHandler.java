package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserPostCardInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserPostCardService;
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
public class UserCreateSharePostCardMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserCreateSharePostCardMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.create.share.postcard.subject}")
    private String queuesGroup;

    @Value("${nats.user.create.share.postcard.queue.group}")
    private String subject;

    @Autowired
    private RPCUserPostCardService rpcUserPostCardService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user create share post card info message from nats, but message is null....");
            UserPostCardInfoMapper userPostCardInfoMapper = JSONUtil.jsonWithDateToBean(json, UserPostCardInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userPostCardInfoMapper.getPlatformFrom(), userPostCardInfoMapper.getOperationTime(), userPostCardInfoMapper.getOperationType())) {
                logger.error(" ... error check message with userPostCardInfoMapper[" + JSONUtil.beanToJson(userPostCardInfoMapper, false) + "] ...");
                return;
            }

            rpcUserPostCardService.insert(userPostCardInfoMapper);
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
