package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.listener.redis.service.RedisDeviceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service
public class AcountAndDeviceService {

    @Autowired
    private RedisDeviceService redisDeviceService;



    /**
     * 根据deviceid获取账户信息
     *
     * @param devid
     * @return
     */
    public Set<String> getAccountIdByDevice(String devid) {
        return redisDeviceService.accounts(devid);
    }
}
