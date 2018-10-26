package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;
import com.tianzhixing.oms.rpc.mapper.DeviceCheckinInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCApplicationOperationService;
import com.tianzhixing.oms.rpc.service.RPCDeviceCheckinService;
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
public class DeviceCheckinMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(DeviceCheckinMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.device.checkin.subject}")
    private String queuesGroup;

    @Value("${nats.device.checkin.queue.group}")
    private String subject;

    @Autowired
    private RPCDeviceCheckinService rpcDeviceCheckinService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get device checkin message from nats, but message is null....");
            DeviceCheckinInfoMapper deviceCheckinInfoMapper = JSONUtil.jsonWithDateToBean(json, DeviceCheckinInfoMapper.class);
            //check
            if (!MessageValidateUtil.check(deviceCheckinInfoMapper.getPlatformFrom(), deviceCheckinInfoMapper.getCheckinTime(), deviceCheckinInfoMapper.getDeviceId(), deviceCheckinInfoMapper.getDeviceType(), deviceCheckinInfoMapper.getBindStatus())) {
                logger.error(" ... error check message with deviceCheckinInfoMapper[" + JSONUtil.beanToJson(deviceCheckinInfoMapper, false) + "] ...");
                return;
            }
            rpcDeviceCheckinService.insert(deviceCheckinInfoMapper);
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
