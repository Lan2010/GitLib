package com.tianzhixing.oms.bussiness.statistics.utils;

import com.tianzhixing.oms.utils.ResourceBundleUtil;

/**
 * Created by routine.k on 2018/7/6.
 */
public class MachineNumUtils {

    public final static String currentMachine() {
        //获取当前机器号
        String machineArg = ResourceBundleUtil.getStringValue("machine.num.arg", "system-config");
        String currentMachine = System.getProperty(machineArg);
        return currentMachine;
    }
}
