package com.tianzhixing.oms.bussiness.backend.web.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jinmy
 */
public class ListUtils {

    public static List<List<String>> pagingString(List<String> list, int pageSize) {
        int totalCount = list.size();
        int pageCount;
        int m = totalCount % pageSize;

        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }

        List<List<String>> totalList = new ArrayList<List<String>>();
        for (int i = 1; i <= pageCount; i++) {
            if (m == 0) {
                List<String> subList = list.subList((i - 1) * pageSize, pageSize * (i));
                totalList.add(subList);
            } else {
                if (i == pageCount) {
                    List<String> subList = list.subList((i - 1) * pageSize, totalCount);
                    totalList.add(subList);
                } else {
                    List<String> subList = list.subList((i - 1) * pageSize, pageSize * i);
                    totalList.add(subList);
                }
            }
        }

        return totalList;
    }
    
    public static List<List<Integer>> pagingInt(List<Integer> list, int pageSize) {
        int totalCount = list.size();
        int pageCount;
        int m = totalCount % pageSize;

        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }

        List<List<Integer>> totalList = new ArrayList<List<Integer>>();
        for (int i = 1; i <= pageCount; i++) {
            if (m == 0) {
                List<Integer> subList = list.subList((i - 1) * pageSize, pageSize * (i));
                totalList.add(subList);
            } else {
                if (i == pageCount) {
                    List<Integer> subList = list.subList((i - 1) * pageSize, totalCount);
                    totalList.add(subList);
                } else {
                    List<Integer> subList = list.subList((i - 1) * pageSize, pageSize * i);
                    totalList.add(subList);
                }
            }
        }

        return totalList;
    }
    
    public static List<List<Double>> pagingDouble(List<Double> list, int pageSize) {
        int totalCount = list.size();
        int pageCount;
        int m = totalCount % pageSize;

        if (m > 0) {
            pageCount = totalCount / pageSize + 1;
        } else {
            pageCount = totalCount / pageSize;
        }

        List<List<Double>> totalList = new ArrayList<List<Double>>();
        for (int i = 1; i <= pageCount; i++) {
            if (m == 0) {
                List<Double> subList = list.subList((i - 1) * pageSize, pageSize * (i));
                totalList.add(subList);
            } else {
                if (i == pageCount) {
                    List<Double> subList = list.subList((i - 1) * pageSize, totalCount);
                    totalList.add(subList);
                } else {
                    List<Double> subList = list.subList((i - 1) * pageSize, pageSize * i);
                    totalList.add(subList);
                }
            }
        }

        return totalList;
    }
}
