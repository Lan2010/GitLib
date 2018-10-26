package com.tianzhixing.kernel.commons.utils;

import java.util.UUID;

/**
 * Created by routine.k on 2018/6/28.
 */
public class StarPointRecordsTokenGeneratUtil {

    public final static String generate(String accountId) {
        return UUID.randomUUID() + "-" + accountId;
    }
}
