package com.tianzhixing.kernel.listener.nats;

import nats.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * Created by routine.k on 2018/6/27.
 */
@Configuration
@PropertySource("classpath:nats/nats-config.properties")
public class NatsClientConfiguration {

    @Value("${nats.url}")
    private String url;

    @Value("${nats.port}")
    private int port;

    @Value("${nats.subject}")
    private String subject;

    @Value("${nats.queue.group}")
    private String queueGroup;

    @Bean
    public nats.client.Nats nats() {
        return new NatsConnector().addHost(url + ":" + port).connect();
    }

    @Bean
    public Subscription subscription(nats.client.Nats nats) {
        return nats.subscribe(subject, queueGroup);
    }

}
