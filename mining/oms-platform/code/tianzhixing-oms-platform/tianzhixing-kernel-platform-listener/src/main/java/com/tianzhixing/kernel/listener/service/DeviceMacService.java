package com.tianzhixing.kernel.listener.service;

import com.tianzhixing.kernel.listener.entity.DeviceEntity;
import com.tianzhixing.kernel.listener.entity.RateEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.Set;

/**
 * Created by routine.k on 2018/6/26.
 */
@Service("deviceMacService")
public class DeviceMacService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private AcountAndDeviceService acountAndDeviceService;

    @Autowired
    private AccountAndTaskService accountAndTaskService;

    @Autowired
    private RateService rateService;

    @Autowired
    private StarPointService starPointService;

    @Autowired
    private AccountKernelPlatformService accountKernelPlatformService;

    /**
     * 分析mac信息
     */
    public void analyseMac(DeviceEntity deviceEntity) {
        //查找当前设备所属账户
        Set<String> accountIds = acountAndDeviceService.getAccountIdByDevice(deviceEntity.getDevid());
        if (accountIds == null || accountIds.size() == 0) {
            logger.error("....device[" + deviceEntity.getDevid() + "] failed analyse, can't found account....");
            return;
        }
        for (String accountId : accountIds) {
            //当前账户接受清单
            Set<String> taskIds = accountAndTaskService.listTaskId(accountId);
            //获取汇率信息
            RateEntity rateEntity = rateService.calRate(taskIds, deviceEntity);
            Assert.notNull(rateEntity, "... failed analyse device mac , not found rate value from redis ...");
            //计算星点
            double starPoint = starPointService.calStarPoint(rateEntity, deviceEntity);
            //持久化
            accountKernelPlatformService.persistence(accountId, starPoint, deviceEntity, rateEntity);
        }
    }
}
