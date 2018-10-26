package com.tianzhixing.oms.utils;

import java.math.BigDecimal;

/**
 * Created by routine.k on 2018/6/21.
 */
public class CalculateUtil {

    /**
     * 加法运算，四舍五入
     *
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static String plus(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 乘法运算，四舍五入
     * @param v1
     * @param v2
     * @param scale
     * @return
     */
    public static String mul(double v1, double v2, int scale) {
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }

    /**
     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指
     * 定精度，以后的数字四舍五入。
     *
     * @param v1    被除数
     * @param v2    除数
     * @param scale 表示表示需要精确到小数点以后几位。
     * @return 两个参数的商
     */
    public static double div(double v1, double v2, int scale) {
        if (scale < 0) {
            throw new IllegalArgumentException(
                    "The scale must be a positive integer or zero");
        }
        BigDecimal b1 = new BigDecimal(Double.toString(v1));
        BigDecimal b2 = new BigDecimal(Double.toString(v2));
        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 格式化
     *
     * @param v1
     * @return
     */
    public static String format(double v1, int scale) {
        return new BigDecimal(v1).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();
    }
}
