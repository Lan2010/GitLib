package com.tianzhixing.kernel.listener.nats;

import nats.client.Message;
import nats.client.MessageHandler;
import nats.client.Subscription;

/**
 * Created by routine.k on 2018/6/27.
 */
public abstract class AbstractNatsMessageHandler {

    abstract void listener(Message message);

    abstract Subscription subscription();

    void messageHander() {
        subscription().addMessageHandler(new MessageHandler() {
            @Override
            public void onMessage(Message message) {
                listener(message);
            }
        });
    }
}
