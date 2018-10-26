package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.UserAdvertisementInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserAdvertisementService;
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
public class UserAccessAndClickAdvertmentMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(UserAccessAndClickAdvertmentMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.user.access.click.advertisement.subject}")
    private String queuesGroup;

    @Value("${nats.user.access.click.advertisement.queue.group}")
    private String subject;

    @Autowired
    private RPCUserAdvertisementService rpcUserAdvertisementService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get user access/click advertisement message from nats, but message is null....");
            UserAdvertisementInfoMapper userAdvertisementInfoMapper = JSONUtil.jsonWithDateToBean(json, UserAdvertisementInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(userAdvertisementInfoMapper.getPlatformFrom(), userAdvertisementInfoMapper.getOperationTime(), userAdvertisementInfoMapper.getAdvertId(), userAdvertisementInfoMapper.getOperationType())) {
                logger.error(" ... error check message with userAdvertisementInfoMapper[" + JSONUtil.beanToJson(userAdvertisementInfoMapper, false) + "] ...");
                return;
            }

            rpcUserAdvertisementService.insert(userAdvertisementInfoMapper);
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
