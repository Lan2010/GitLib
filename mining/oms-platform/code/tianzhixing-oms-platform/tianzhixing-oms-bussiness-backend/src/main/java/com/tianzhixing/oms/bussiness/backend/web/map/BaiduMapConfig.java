package com.tianzhixing.oms.bussiness.backend.web.map;

import com.tianzhixing.oms.utils.ResourceBundleUtil;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by routine.k on 2018/6/24.
 */
public class BaiduMapConfig {

    public static String BAIDU_MAP_API_URI = ResourceBundleUtil.getStringValue("baidu.map.uri", "map-config");
    public static String BAIDU_MAP_KEY = ResourceBundleUtil.getStringValue("baidu.map.app.key", "map-config");
    public static String BAIDU_MAP_WEB_KEY = ResourceBundleUtil.getStringValue("baidu.map.web.key", "map-config");
    public static String BAIDU_MAP_COORDTYPE = ResourceBundleUtil.getStringValue("baidu.map.coord.type", "map-config");
    public static String BAIDU_MAP_COORDTYPE_NUM = ResourceBundleUtil.getStringValue("baidu.map.coord.type.num", "map-config");
    public static String BAIDU_MAP_DATA_PAGESIZE = ResourceBundleUtil.getStringValue("baidu.map.pagesize", "map-config");

    private static Map<String, String> CITY = new HashMap<>();

    static {
        String filePath = BaiduMapConfig.class.getClassLoader().getResource("/").getPath() + "/map/BaiduMap_cityCode_1102.txt";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
            String str = null;
            while ((str = bufferedReader.readLine()) != null) {
                String[] s = str.split(",");
                CITY.put(s[0], s[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String city(String code) {
        return CITY.get(code);
    }

    public static String code(String city) {
        for (String key : CITY.keySet()) {
            if (CITY.get(key).equals(city)) {
                return key;
            }
        }
        return null;
    }

    public static Map<String, String> cities() {
        return CITY;
    }
}
