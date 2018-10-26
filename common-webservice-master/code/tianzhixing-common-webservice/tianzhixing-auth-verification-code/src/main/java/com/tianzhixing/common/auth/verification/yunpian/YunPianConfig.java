package com.tianzhixing.common.auth.verification.yunpian;

import com.tianzhixing.common.auth.utils.ResourceBundleUtil;
import com.tianzhixing.common.auth.utils.StringUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/14.
 */
public class YunPianConfig {

    private static String API_KEY;

    private static String SECURITY_MOD;

    private static boolean ENABLE;

    private static Map<String, String> MODMAP = new HashMap<>();

    static {
        API_KEY = ResourceBundleUtil.getStringValue("yunpian.apikey", "yunpian_config");
        SECURITY_MOD = ResourceBundleUtil.getStringValue("yunpian.security.mod", "yunpian_config");
        ENABLE = Boolean.valueOf(ResourceBundleUtil.getStringValue("yunpian.send.enable", "yunpian_config"));
    }

    public static String getApiKey() {
        return API_KEY;
    }

    public static String getSecurityMod() {
        return SECURITY_MOD;
    }

    public static String getMod(String key) {
        if (!MODMAP.containsKey(key)) {
            String content = ResourceBundleUtil.getStringValue(key, "yunpian_config");
            if (StringUtils.isNotEmpty(content))
                MODMAP.put(key, content);
        }
        return MODMAP.get(key);
    }

    public static boolean isENABLE() {
        return ENABLE;
    }
}
