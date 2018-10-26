package com.tianzhixing.common.auth.commons.code;


import com.tianzhixing.common.auth.utils.ResourceBundleUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/4/27.
 */
public class CodeProperties {

    private static Map<String, String> CACHE_MAP = new HashMap<String, String>();

    private static String FILE = "code_message";

    public static String get(String _msg) {
        String msg = CACHE_MAP.get(_msg);
        if (msg == null || "".equals(msg)) {
            msg = ResourceBundleUtil.getStringValue(_msg, FILE);
            if (msg != null && !"".equals(msg)) {
                CACHE_MAP.put(_msg, msg);
            }else{
                msg = _msg;
            }
        }
        return msg;
    }
}
