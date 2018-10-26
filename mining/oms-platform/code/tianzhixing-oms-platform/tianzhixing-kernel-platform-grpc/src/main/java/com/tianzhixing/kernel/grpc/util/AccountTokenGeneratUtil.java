package com.tianzhixing.kernel.grpc.util;

import com.tianzhixing.oms.utils.GenerateUtil;
import org.springframework.util.Assert;

import java.util.Date;

/**
 * 账户token生成器
 * Created by routine.k on 2018/6/21.
 */
public class AccountTokenGeneratUtil {

    /**
     * 生成账户编号
     *
     * @param org
     * @return
     */
    public static String generat(String org) {
        Assert.notNull(org, "org.null");
        long time = new Date().getTime();
        return org + "-" + time + "-" + GenerateUtil.generateNumber(16);
    }
}
