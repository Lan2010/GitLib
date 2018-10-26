package com.tianzhixing.oms.nats.handler;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceCurrentOnlineStatisticsMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceCurrentOnlineStatisticsService;
import com.tianzhixing.oms.nats.AbstractNatsMessageHandler;
import com.tianzhixing.oms.rpc.mapper.DeviceOnlineStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCDeviceOnLineStatusService;
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
import java.util.Date;

/**
 * Created by routine.k on 2018/6/27.
 */
@Component
public class DeviceOnlineStatusMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(DeviceOnlineStatusMessageHandler.class);

    @Autowired
    private nats.client.Nats nats;

    @Value("${nats.device.onoff.line.subject}")
    private String queuesGroup;

    @Value("${nats.device.onoff.line.queue.group}")
    private String subject;

    @Autowired
    private RPCDeviceOnLineStatusService rpcDeviceOnLineStatusService;

    @Autowired
    private RPCDeviceCurrentOnlineStatisticsService rpcDeviceCurrentOnlineStatisticsService;

    @PostConstruct
    public void init() {
        super.messageHander();
    }

    @Override
    public void listener(Message message) {
        try {
            String json = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(json), "....get device on-off line message from nats, but message is null....");
            DeviceOnlineStatusInfoMapper deviceOnlineStatusInfoMapper = JSONUtil.jsonWithDateToBean(json, DeviceOnlineStatusInfoMapper.class);

            //check
            if (!MessageValidateUtil.check(deviceOnlineStatusInfoMapper.getPlatformFrom(), deviceOnlineStatusInfoMapper.getOperationTime(), deviceOnlineStatusInfoMapper.getDeviceId(), deviceOnlineStatusInfoMapper.getDeviceType(), deviceOnlineStatusInfoMapper.getOperationTime(), deviceOnlineStatusInfoMapper.getLat(), deviceOnlineStatusInfoMapper.getLng(), deviceOnlineStatusInfoMapper.getBindStatus())) {
                logger.error(" ... error check message with deviceOnlineStatusInfoMapper[" + JSONUtil.beanToJson(deviceOnlineStatusInfoMapper, false) + "] ...");
                return;
            }

            rpcDeviceOnLineStatusService.insert(deviceOnlineStatusInfoMapper);
            //获取记录
            DeviceCurrentOnlineStatisticsMapper deviceCurrentOnlineStatisticsMapper = rpcDeviceCurrentOnlineStatisticsService.getByDeviceId(deviceOnlineStatusInfoMapper.getDeviceId(), deviceOnlineStatusInfoMapper.getPlatformFrom());
            if (deviceCurrentOnlineStatisticsMapper == null) {
                //添加记录
                deviceCurrentOnlineStatisticsMapper = new DeviceCurrentOnlineStatisticsMapper(null, 0, new Date(), new Date(), deviceOnlineStatusInfoMapper.getClientPlatformType(), deviceOnlineStatusInfoMapper.getPlatformFrom(), deviceOnlineStatusInfoMapper.getDeviceId(), deviceOnlineStatusInfoMapper.getOperationType(), deviceOnlineStatusInfoMapper.getLng(), deviceOnlineStatusInfoMapper.getLat(), deviceOnlineStatusInfoMapper.getDeviceType());
                rpcDeviceCurrentOnlineStatisticsService.insert(deviceCurrentOnlineStatisticsMapper);
            } else {
                //更新状态
                if (deviceCurrentOnlineStatisticsMapper.getStatus() != deviceOnlineStatusInfoMapper.getOperationType()) {
                    rpcDeviceCurrentOnlineStatisticsService.changeStatus(deviceOnlineStatusInfoMapper.getOperationType(), deviceOnlineStatusInfoMapper.getDeviceId(), deviceOnlineStatusInfoMapper.getPlatformFrom());
                }
            }
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
