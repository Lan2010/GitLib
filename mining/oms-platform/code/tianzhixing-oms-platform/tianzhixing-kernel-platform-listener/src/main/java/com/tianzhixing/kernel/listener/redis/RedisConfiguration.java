package com.tianzhixing.kernel.listener.redis;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.PropertyAccessor;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * Created by routine.k on 2018/6/15.
 */
@Configuration
@EnableCaching
@PropertySource("classpath:redis/oms-redis-config.properties")
public class RedisConfiguration {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Value("${redis.node.host}")
    private String host;

    @Value("${redis.node.port}")
    private int port;

    @Value("${redis.connection.timeout}")
    private int timeout;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.database}")
    private int database;

    @Value("${jedis.maxIdle}")
    private int maxIdle;

    @Value("${jedis.minIdle}")
    private int minIdle;

    @Value("${jedis.maxWaitMillis}")
    private int maxWait;

    @Value("${jedis.maxWaitActive}")
    private int maxActive;

    @Value("${redis.usepool}")
    private boolean userPool;

    @Value("${jedis.testOnBorrow}")
    private boolean testOnBorrow;

    /**
     * redis模板，存储关键字是字符串，值是Jdk序列化
     *
     * @param factory
     * @return
     * @Description:
     */
    @Bean
    public RedisTemplate<?, ?> redisTemplate(RedisConnectionFactory factory) {
        RedisTemplate<?, ?> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(factory);
        //key序列化方式;但是如果方法上有Long等非String类型的话，会报类型转换错误；
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();//Long类型不可以会出现异常信息;
        redisTemplate.setHashKeySerializer(redisSerializer);
        redisTemplate.setKeySerializer(redisSerializer);
        GenericJackson2JsonRedisSerializer jackson2JsonRedisSerializer = new GenericJackson2JsonRedisSerializer();
        redisTemplate.setValueSerializer(jackson2JsonRedisSerializer);
        redisTemplate.setHashValueSerializer(jackson2JsonRedisSerializer);
        //JdkSerializationRedisSerializer序列化方式;
        //JdkSerializationRedisSerializer jdkRedisSerializer = new JdkSerializationRedisSerializer();
        redisTemplate.afterPropertiesSet();
        return redisTemplate;
    }


    /**
     * redis连接的基础设置
     *
     * @return
     * @Description:
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();
        factory.setHostName(host);
        factory.setPort(port);
        factory.setPassword(password);
        //存储的库
        factory.setDatabase(database);
        //设置连接超时时间
        factory.setTimeout(timeout);
        factory.setUsePool(userPool);
        factory.setPoolConfig(jedisPoolConfig());
        return factory;
    }

    /**
     * 连接池配置
     *
     * @return
     * @Description:
     */
    @Bean
    public JedisPoolConfig jedisPoolConfig() {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setMinIdle(minIdle);
        jedisPoolConfig.setMaxWaitMillis(maxWait);
        jedisPoolConfig.setMaxTotal(maxActive);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        return jedisPoolConfig;
    }

}
