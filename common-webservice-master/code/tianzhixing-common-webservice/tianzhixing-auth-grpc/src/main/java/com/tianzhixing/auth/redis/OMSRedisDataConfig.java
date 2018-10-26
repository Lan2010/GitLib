package com.tianzhixing.auth.redis;

import com.tianzhixing.common.auth.utils.ResourceBundleUtil;

/**
 * Created by routine.k on 2018/6/15.
 */
public class OMSRedisDataConfig {

    public static long IDCARD_AUTH_RECORDS_TIMEOUT = ResourceBundleUtil.getIntegerValue("redis.idcard.auth.records.timeout", "redis-config", false);
    public static long MOBILE_LASTTIME_TIMEOUT = ResourceBundleUtil.getIntegerValue("redis.mobile.card.lasttime.timeout", "redis-config", false);
    public static long MOBILE_VALIDATE_CODE_TIMEOUT = ResourceBundleUtil.getIntegerValue("redis.mobile.card.validate.code.timeout", "redis-config", false);
    public static long DEFAULT_TIMEOUT = ResourceBundleUtil.getIntegerValue("redis.data.default.timeout", "redis-config", false);
    public static String IDCARD_AUTH_RECORDS_KEY = ResourceBundleUtil.getStringValue("redis.idcard.auth.records.key", "redis-config");
    public static String MOBILE_LASTTIME_KEY = ResourceBundleUtil.getStringValue("redis.mobile.card.lasttime.key", "redis-config");
    public static String MOBILE_VALIDATE_CODE_KEY = ResourceBundleUtil.getStringValue("redis.mobile.card.validate.code.key", "redis-config");

}
