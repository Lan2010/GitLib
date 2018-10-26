package com.tianzhixing.kernel.grpc.util;

import com.tianzhixing.oms.utils.CalculateUtil;

/**
 * Created by routine.k on 2018/7/5.
 */
public class FormatStarPointUtil {

    public final static String format(Double d) {
        return CalculateUtil.format(d, 4);
    }
}
