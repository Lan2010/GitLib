package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserBasicInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserBasicInfoService;
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
public class UserBasicInfoMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserBasicInfoMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.basic.info.subject}")
    private String queuesGroup;

    @Value("${nats.user.basic.info.queue.group}")
    private String subject;

    @Autowired
    private RPCUserBasicInfoService rpcUserBasicInfoService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user basic info message from nats, but message is null....");
            UserBasicInfoMapper userBasicInfoMapper  = JSONUtil.jsonWithDateToBean(json, UserBasicInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userBasicInfoMapper.getPlatformFrom(), userBasicInfoMapper.getRegTime(), userBasicInfoMapper.getUserFromType())) {
                logger.error(" ... error check message with userBasicInfoMapper[" + JSONUtil.beanToJson(userBasicInfoMapper, false) + "] ...");
                return;
            }

            rpcUserBasicInfoService.insert(userBasicInfoMapper);
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
