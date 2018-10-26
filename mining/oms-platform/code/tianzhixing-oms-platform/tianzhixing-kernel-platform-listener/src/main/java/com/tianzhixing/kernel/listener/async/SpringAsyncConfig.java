package com.tianzhixing.kernel.listener.async;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * Created by routine.k on 2018/7/10.
 */
@Configuration
@PropertySource("classpath:spring/spring-async-config.properties")
public class SpringAsyncConfig {

    public final static String ASYNC_EXECUTOR = "asyncExecutor";

    /**
     * Set the ThreadPoolExecutor's core pool size.
     */
    @Value("${spring.task.poo.size.min}")
    private int corePoolSize;
    /**
     * Set the ThreadPoolExecutor's maximum pool size.
     */
    @Value("${spring.task.poo.size.max}")
    private int maxPoolSize;
    /**
     * Set the capacity for the ThreadPoolExecutor's BlockingQueue.
     */
    @Value("${spring.task.queue-capacity}")
    private int queueCapacity;

    private String ThreadNamePrefix = "DeviceMacExecutor-";

    @Bean(name = ASYNC_EXECUTOR)
    public Executor executor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(corePoolSize);
        executor.setMaxPoolSize(maxPoolSize);
        executor.setQueueCapacity(queueCapacity);
        executor.setThreadNamePrefix(ThreadNamePrefix);
        executor.setRejectedExecutionHandler(new ThreadPoolExecutor.CallerRunsPolicy());
        executor.initialize();
        return executor;
    }
}
