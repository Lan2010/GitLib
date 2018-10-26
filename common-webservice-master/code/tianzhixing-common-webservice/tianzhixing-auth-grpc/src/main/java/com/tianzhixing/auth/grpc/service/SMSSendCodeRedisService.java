package com.tianzhixing.auth.grpc.service;

import com.tianzhixing.auth.cache.LinkedQueueCache;
import com.tianzhixing.auth.redis.OMSRedisDataConfig;
import com.tianzhixing.common.auth.model.MobileValidationCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Created by routine.k on 2018/6/15.
 */
@Service("smsSendCodeRedisService")
public class SMSSendCodeRedisService {

    @Autowired
    private RedisTemplate redisTemplate;


    public Date getMobileLastSendTime(String mobile) {
        Object obj = redisTemplate.boundValueOps(OMSRedisDataConfig.MOBILE_LASTTIME_KEY + mobile).get();
        return obj == null ? null : new Date(Long.valueOf(obj.toString()));
    }

    public MobileValidationCode insert(MobileValidationCode mobileValidationCode) {
        redisTemplate.boundValueOps(_mobileValidateCodeKey(mobileValidationCode.getToken(), mobileValidationCode.getMobile())).set(mobileValidationCode, OMSRedisDataConfig.MOBILE_VALIDATE_CODE_TIMEOUT, TimeUnit.MINUTES);
        return mobileValidationCode;

    }

    public void updateSendResult(MobileValidationCode mobileValidationCode) {
        if (mobileValidationCode.getStatus()) {
            redisTemplate.boundValueOps(OMSRedisDataConfig.MOBILE_LASTTIME_KEY + mobileValidationCode.getMobile()).set(mobileValidationCode.getCreateTime().getTime(), OMSRedisDataConfig.MOBILE_LASTTIME_TIMEOUT, TimeUnit.SECONDS);
        }
        insert(mobileValidationCode);
        LinkedQueueCache.toQueue(mobileValidationCode);
    }

    public MobileValidationCode get(String codeToken, String mobile) {
        Object obj = redisTemplate.boundValueOps(_mobileValidateCodeKey(codeToken, mobile)).get();
        return obj == null ? null : (MobileValidationCode) obj;
    }

    public void updateValidateResult(MobileValidationCode mobileValidationCode) {
        String key = _mobileValidateCodeKey(mobileValidationCode.getToken(), mobileValidationCode.getMobile());
        if (redisTemplate.hasKey(key)) {
            redisTemplate.delete(key);
        }
    }

    public List<MobileValidationCode> searchWithMobile(String mobile) {
        Set<String> keys = redisTemplate.keys("*" + _mobileKey(mobile));
        List<MobileValidationCode> list = new ArrayList<MobileValidationCode>();
        if (keys != null) {
            List results = redisTemplate.opsForValue().multiGet(keys);
            if (results != null) {
                for (Object obj : results) {
                    list.add((MobileValidationCode) obj);
                }
            }
        }
        return list;
    }

    private String _mobileValidateCodeKey(String token, String mobile) {
        return token + "-" + _mobileKey(mobile);
    }

    private String _mobileKey(String mobile) {
        return OMSRedisDataConfig.MOBILE_VALIDATE_CODE_KEY + mobile;
    }
}
