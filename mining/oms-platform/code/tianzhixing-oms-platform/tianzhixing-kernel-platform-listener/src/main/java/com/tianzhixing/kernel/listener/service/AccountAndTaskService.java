package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.listener.redis.service.RedisTaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service
public class AccountAndTaskService {

    @Autowired
    private RedisTaskService redisTaskService;

    public Set<String> listTaskId(String accountId) {
        return redisTaskService.taskIds(accountId);
    }
}
