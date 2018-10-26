package com.tianzhixing.oms.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.Date;

/**
 * Created by routine.k on 2018/7/13.
 */
public class MessageValidateUtil {

    /**
     * 检查数据不可为空
     *
     * @param platform
     * @param time
     * @param objs
     * @return
     */
    public static boolean check(String platform, Long time, Object... objs) {
        if (StringUtils.isEmpty(platform)) return false;
        try {
            Date date = new Date(time);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        for (Object obj : objs) {
            if (obj == null) {
                return false;
            }
            if (StringUtils.isEmpty(obj.toString())) {
                return false;
            }
        }
        return true;
    }
}
