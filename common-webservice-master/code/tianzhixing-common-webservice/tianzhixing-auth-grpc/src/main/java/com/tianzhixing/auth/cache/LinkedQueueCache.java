package com.tianzhixing.auth.cache;

import com.tianzhixing.common.auth.model.AuthIDCardInfo;
import com.tianzhixing.common.auth.model.MobileValidationCode;
import com.tianzhixing.common.auth.utils.CalendarUtil;
import com.tianzhixing.common.auth.utils.ResourceBundleUtil;

import java.util.*;
import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * Created by routine.k on 2018/6/14.
 */
public class LinkedQueueCache {

    private static ConcurrentLinkedQueue<MobileValidationCode> QUEUE = new ConcurrentLinkedQueue();

    private static ConcurrentLinkedQueue<AuthIDCardInfo> IDCARD_QUEUE = new ConcurrentLinkedQueue();

    public static void toQueue(MobileValidationCode mobileValidationCode) {
        QUEUE.add(mobileValidationCode);
    }

    public static void toQueue(AuthIDCardInfo authIDCardInfo) {
        IDCARD_QUEUE.add(authIDCardInfo);
    }

    public static List<MobileValidationCode> mobileCodeQueue() {
        List<MobileValidationCode> list = new ArrayList<>();
        while (!QUEUE.isEmpty()) {
            list.add(QUEUE.poll());
        }
        return list;
    }

    public static List<AuthIDCardInfo> idCardQueue() {
        List<AuthIDCardInfo> list = new ArrayList<>();
        while (!IDCARD_QUEUE.isEmpty()) {
            list.add(IDCARD_QUEUE.poll());
        }
        return list;
    }
}
