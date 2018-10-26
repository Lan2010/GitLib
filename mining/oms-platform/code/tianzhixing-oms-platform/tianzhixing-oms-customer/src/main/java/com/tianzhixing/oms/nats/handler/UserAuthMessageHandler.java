package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserAuthInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserAuthService;
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
public class UserAuthMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserAuthMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.auth.subject}")
    private String queuesGroup;

    @Value("${nats.user.auth.queue.group}")
    private String subject;

    @Autowired
    private RPCUserAuthService rpcUserAuthService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user auth info message from nats, but message is null....");
            UserAuthInfoMapper userAuthInfoMapper = JSONUtil.jsonWithDateToBean(json, UserAuthInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userAuthInfoMapper.getPlatformFrom(), userAuthInfoMapper.getAuthTime(), userAuthInfoMapper.getAuthStatus(), userAuthInfoMapper.getAuthType())) {
                logger.error(" ... error check message with userAuthInfoMapper[" + JSONUtil.beanToJson(userAuthInfoMapper, false) + "] ...");
                return;
            }

            rpcUserAuthService.insert(userAuthInfoMapper);
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
