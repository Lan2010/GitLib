package com.tianzhixing.kernel.commons.utils;

/**
 * Created by routine.k on 2018/6/30.
 */
public class Assert {

    public static void notNull(Object object, String message) {
        if (object == null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void notNull(Object object, RuntimeException ex) {
        if (object == null) {
            throw ex;
        }
    }

    public static void isNull(Object object, String message) {
        if (object != null) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isNull(Object object, RuntimeException ex) {
        if (object != null) {
            throw ex;
        }
    }

    public static void isTrue(boolean status, String message) {
        if (!status) {
            throw new IllegalArgumentException(message);
        }
    }

    public static void isTrue(boolean status, RuntimeException ex) {
        if (!status) {
            throw ex;
        }
    }
}
