package com.tianzhixing.oms.nats;

import nats.client.Message;
import nats.client.MessageHandler;
import nats.client.Subscription;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by routine.k on 2018/6/27.
 */
public abstract class AbstractNatsMessageHandler {

    private static Logger logger = LoggerFactory.getLogger(AbstractNatsMessageHandler.class);

    public abstract void listener(Message message);

    public abstract nats.client.Nats nats();

    public abstract String subject();

    public abstract String queuesGroup();

    public void messageHander() {
        logger.info(".... init application operation nats listener subject[" + subject() + "] with queues group[" + queuesGroup() + "] ....");
        nats().subscribe(subject(), queuesGroup()).addMessageHandler(new MessageHandler() {
            @Override
            public void onMessage(Message message) {
                listener(message);
            }
        });
    }
}
