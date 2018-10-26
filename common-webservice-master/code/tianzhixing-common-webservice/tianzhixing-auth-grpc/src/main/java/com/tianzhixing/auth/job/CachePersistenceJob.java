package com.tianzhixing.auth.job;

import com.tianzhixing.auth.cache.LinkedQueueCache;
import com.tianzhixing.common.auth.model.AuthIDCardInfo;
import com.tianzhixing.common.auth.model.MobileValidationCode;
import com.tianzhixing.common.auth.service.idcard.AuthIDCardInfoService;
import com.tianzhixing.common.auth.service.idcard.MobileValidationCodeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by routine.k on 2018/6/14.
 */
@Component
@EnableScheduling
public class CachePersistenceJob {

    @Resource(name = "mobileValidationCodeService")
    private MobileValidationCodeService mobileValidationCodeService;

    @Resource(name = "authIDCardInfoService")
    private AuthIDCardInfoService authIDCardInfoService;

    private static final Logger logger = LoggerFactory.getLogger(CachePersistenceJob.class);

    @Scheduled(cron = "0/5 * * * * *")
    public void catche() {
        List<MobileValidationCode> list = LinkedQueueCache.mobileCodeQueue();
        if (list != null) {
            for (MobileValidationCode mobileValidationCode : list) {
                mobileValidationCodeService.insert(mobileValidationCode);
            }
        }
        List<AuthIDCardInfo> authIDCardInfoList = LinkedQueueCache.idCardQueue();
        if(authIDCardInfoList != null){
            for(AuthIDCardInfo authIDCardInfo : authIDCardInfoList){
                authIDCardInfoService.add(authIDCardInfo);
            }
        }
    }
}
