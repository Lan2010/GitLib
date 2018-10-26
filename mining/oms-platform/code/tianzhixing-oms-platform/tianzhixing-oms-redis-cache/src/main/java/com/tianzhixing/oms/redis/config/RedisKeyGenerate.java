package com.tianzhixing.oms.redis.config;

/**
 * Created by routine.k on 2018/6/25.
 */
public class RedisKeyGenerate {


    public final static String generate(String id, String key) {
        return key.trim() + "-" + id.trim();
    }

    public final static String generate(String accountId, String key, String token) {
        return key.trim() + "-" + accountId.trim() + "-" + token.trim();
    }
}