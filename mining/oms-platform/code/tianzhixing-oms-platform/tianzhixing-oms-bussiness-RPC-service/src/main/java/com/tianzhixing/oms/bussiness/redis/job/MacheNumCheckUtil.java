package com.tianzhixing.oms.bussiness.redis.job;

import com.tianzhixing.oms.utils.ResourceBundleUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by routine.k on 2018/7/2.
 */
public class MacheNumCheckUtil {

    private static Logger LOGGER = LoggerFactory.getLogger(MacheNumCheckUtil.class);

    public final static boolean check() {
        //获取当前机器号
        String machineArg = ResourceBundleUtil.getStringValue("machine.num.arg", "system-config");
        String currentMachine = System.getProperty(machineArg);
        if (StringUtils.isEmpty(currentMachine)) {
            LOGGER.debug("... cancel account sync task, current machine number not found");
            return false;
        }
        String mastNum = ResourceBundleUtil.getStringValue("job.handout.master.num", "system-config");
        if (!currentMachine.equals(mastNum)) {
            LOGGER.debug("... cancel account sync task, current machine number[" + currentMachine + "] not eq mast machine number[" + mastNum + "]");
            return false;
        }
        return true;
    }
}
