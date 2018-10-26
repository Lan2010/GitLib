package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCApplicationOperationService;
import com.tianzhixing.oms.utils.JSONUtil;
import com.tianzhixing.oms.utils.MessageValidateUtil;
import nats.client.Message;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by routine.k on 2018/6/27.
 */
@Component
public class ApplicationOperationMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(ApplicationOperationMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.application.operation.subject}")
    private String queuesGroup;

    @Value("${nats.application.operation.queue.group}")
    private String subject;

    @Autowired
    private RPCApplicationOperationService rpcApplicationOperationService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get application operation message from nats, but message is null....");
            ApplicationOperationInfoMapper applicationOperationInfoMapper = JSONUtil.jsonWithDateToBean(json, ApplicationOperationInfoMapper.class);
            //check
            if (!MessageValidateUtil.check(applicationOperationInfoMapper.getPlatformFrom(), applicationOperationInfoMapper.getAppOperationTime(), applicationOperationInfoMapper.getOperationType())) {
                logger.error(" ... error check message with applicationOperationInfoMapper[" + JSONUtil.beanToJson(applicationOperationInfoMapper, false) + "] ...");
                return;
            }
            rpcApplicationOperationService.insert(applicationOperationInfoMapper);
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
