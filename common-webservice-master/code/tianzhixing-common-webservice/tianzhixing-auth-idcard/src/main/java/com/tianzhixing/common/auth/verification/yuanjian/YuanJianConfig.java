package com.tianzhixing.common.auth.verification.yuanjian;

import com.tianzhixing.common.auth.utils.ResourceBundleUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/5/22.
 */
public class YuanJianConfig {

    private static final String PROPERTIES_FILE_CONFIG_NAME = "yuanjian_config";

    private static boolean YUANJIAN_ENV_ENABLE;

    private static String URI;

    private static String privatePFXFile;

    private static String privatePFXPassword;

    private static String memberID;

    private static String terminalID;

    private static String dataType;

    private static int transIDLength;

    private static String tradeDateFormat;

    private static String isPhoto;

    private static String[] ERROR_CODE_LIST = {"S0001", "S1000", "S1001", "S1002", "S1004", "S1005", "S1006", "S1007", "S2000", "S2001", "S2002", "S2003", "S2004", "S2005", "S2006", "E500"};
    private static Map<String, String> ERROR_CODE = new HashMap<String, String>();


    static {
        privatePFXFile = ResourceBundleUtil.getStringValue("yuanjian.pri.pfx.file.name", PROPERTIES_FILE_CONFIG_NAME);
        privatePFXPassword = ResourceBundleUtil.getStringValue("yuanjian.pri.pfx.password", PROPERTIES_FILE_CONFIG_NAME);
        URI = ResourceBundleUtil.getStringValue("yuanjian.request.remove.uri.ssl", PROPERTIES_FILE_CONFIG_NAME);
        memberID = ResourceBundleUtil.getStringValue("yuanjian.member.id", PROPERTIES_FILE_CONFIG_NAME);
        terminalID = ResourceBundleUtil.getStringValue("yuanjian.terminal.id", PROPERTIES_FILE_CONFIG_NAME);
        dataType = ResourceBundleUtil.getStringValue("yuanjian.request.data.type", PROPERTIES_FILE_CONFIG_NAME);
        tradeDateFormat = ResourceBundleUtil.getStringValue("yuanjian.trade.date.format", PROPERTIES_FILE_CONFIG_NAME);
        isPhoto = ResourceBundleUtil.getStringValue("yuanjian.request.is.photo", PROPERTIES_FILE_CONFIG_NAME);
        YUANJIAN_ENV_ENABLE = Boolean.valueOf(ResourceBundleUtil.getStringValue("yuanjian.environment.enable", PROPERTIES_FILE_CONFIG_NAME));
        transIDLength = ResourceBundleUtil.getIntegerValue("yuanjian.trans.id.length", PROPERTIES_FILE_CONFIG_NAME, false);
        for (String key : ERROR_CODE_LIST) {
            String ERROR_MSG = ResourceBundleUtil.getStringValue("error.code." + key, PROPERTIES_FILE_CONFIG_NAME);
            ERROR_CODE.put(key, ERROR_MSG);
        }

    }

    public static boolean isYuanjianEnvEnable() {
        return YUANJIAN_ENV_ENABLE;
    }

    public static String getURI() {
        return URI;
    }

    public static String getPrivatePFXFile() {
        return privatePFXFile;
    }

    public static String getPrivatePFXPassword() {
        return privatePFXPassword;
    }

    public static String getMemberID() {
        return memberID;
    }

    public static String getTerminalID() {
        return terminalID;
    }

    public static String getDataType() {
        return dataType;
    }

    public static int getTransIDLength() {
        return transIDLength;
    }

    public static String getTradeDateFormat() {
        return tradeDateFormat;
    }

    public static String getIsPhoto() {
        return isPhoto;
    }

    public static String getErrorMSG(String code) {
        String msg = ERROR_CODE.get(code);
        return msg != null && !"".equals(msg) ? msg : ERROR_CODE.get("E500");
    }
}
