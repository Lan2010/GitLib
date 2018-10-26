package com.tianzhixing.kernel.listener.nats;

import com.tianzhixing.kernel.listener.entity.DeviceEntity;
import com.tianzhixing.kernel.listener.service.DeviceMacService;
import com.tianzhixing.kernel.listener.util.GpsUtil;
import com.tianzhixing.oms.utils.JSONUtil;
import nats.client.Message;
import nats.client.Subscription;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.PostConstruct;

/**
 * Created by routine.k on 2018/6/27.
 */
@Component
public class MacNatsMessageHandler extends AbstractNatsMessageHandler {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private Subscription subscription;

    @Autowired
    private DeviceMacService deviceMacService;

    @PostConstruct
    public void init() {
        super.messageHander();
        logger.info(".... init nats listener ....");
    }

    @Override
    public void listener(Message message) {
        try {
            String deviceJson = message.getBody();
            Assert.isTrue(StringUtils.isNotEmpty(deviceJson), "....get message from nats, but message is null....");
            logger.debug(".... get message from nats: " + deviceJson + " ....");
            DeviceEntity deviceEntity = JSONUtil.jsonToBean(deviceJson, DeviceEntity.class);
            Assert.notNull(deviceEntity, ".... get message from nats, but device entity is null....");
            Assert.isTrue(StringUtils.isNotEmpty(deviceEntity.getDevid()), ".... get message from nats, but device id is null....");
            Assert.isTrue(StringUtils.isNotEmpty(deviceEntity.getGps()), ".... get message from nats, but device gps is null....");
            String[] gps = GpsUtil.convert(deviceEntity.getGps());
            Assert.isTrue(gps.length == 2, ".... get message from nats, but device gps format error....");
            Double.parseDouble(gps[0]);
            Double.parseDouble(gps[1]);
            deviceMacService.analyseMac(deviceEntity);
        } catch (NumberFormatException ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        } catch (Exception ex) {
            logger.error(ex.getMessage());
            ex.printStackTrace();
        }

    }

    @Override
    Subscription subscription() {
        return subscription;
    }
}
