package com.tianzhixing.oms.cache;

import com.tianzhixing.oms.utils.ResourceBundleUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/6.
 */
public class APPClientCache {

    private static Map<String, String> CACHE_MAP = new HashMap<String, String>();

    private static String FILE = "security-config";

    public final static String get(String _key) {
        if (null == _key || "".equals(_key)) {
            return null;
        }
        String msg = CACHE_MAP.get(_key);
        if (msg == null || "".equals(msg)) {
            msg = ResourceBundleUtil.getStringValue(_key, FILE);
            if (null != msg && !"".equals(msg)) {
                CACHE_MAP.put(_key, msg);
            }
        }
        return msg;
    }
}
