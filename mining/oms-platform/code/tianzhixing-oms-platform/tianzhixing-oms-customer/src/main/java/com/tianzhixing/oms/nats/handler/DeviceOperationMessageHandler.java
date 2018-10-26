package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.DeviceOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceOperationService;
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
public class DeviceOperationMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(DeviceOperationMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.device.operation.subject}")
    private String queuesGroup;

    @Value("${nats.device.operation.queue.group}")
    private String subject;

    @Autowired
    private RPCDeviceOperationService rpcDeviceOperationService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get device operation message from nats, but message is null....");
            DeviceOperationInfoMapper deviceOperationInfoMapper = JSONUtil.jsonWithDateToBean(json, DeviceOperationInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(deviceOperationInfoMapper.getPlatformFrom(), deviceOperationInfoMapper.getOperationTime(), deviceOperationInfoMapper.getDeviceId(), deviceOperationInfoMapper.getDeviceType(), deviceOperationInfoMapper.getOperationTime(), deviceOperationInfoMapper.getOperationType())) {
                logger.error(" ... error check message with deviceOperationInfoMapper[" + JSONUtil.beanToJson(deviceOperationInfoMapper, false) + "] ...");
                return;
            }

            rpcDeviceOperationService.insert(deviceOperationInfoMapper);
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
