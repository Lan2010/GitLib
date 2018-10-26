package com.tianzhixing.common.auth.utils;

import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by routine.k on 16/8/15.
 */
public class StringUtil {

    public static boolean check(String str) {
        boolean notEmpty = StringUtils.isNotEmpty(str);
        return notEmpty;
    }

    public static boolean checkIsMobile(String recevier) {

        if (check(recevier)) {
            String regex = "^(13[0-9]|14[0-9]|15[0-9]|17[0-9]|18[0-9])\\d{8}$";
            return matText(regex, recevier);
        }
        return false;

    }

    public static boolean checkIsMail(String recevier) {
        if (check(recevier)) {
            String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
            return matText(regex, recevier);
        }
        return false;
    }

    private static boolean matText(String expression, String text) {
        Pattern p = Pattern.compile(expression); // 正则表达式
        Matcher m = p.matcher(text); // 操作的字符串
        boolean b = m.matches();
        return b;
    }
}
