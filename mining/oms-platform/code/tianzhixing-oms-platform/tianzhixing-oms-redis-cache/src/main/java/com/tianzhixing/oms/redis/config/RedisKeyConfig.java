package com.tianzhixing.oms.redis.config;

/**
 * Created by routine.k on 2018/6/25.
 */
public class RedisKeyConfig {

    /**
     * 任务key
     */
    public final static String TASK_KEY = "BussinessTask";

    /**
     * 任务坐标ID集合key
     */
    public final static String TASK_LOCATION_ID_SET_KEY = "BussinessTaskLocationIdSet";

    /**
     * 任务坐标key
     */
    public final static String TASKLOCATION_KEY = "BussinessTaskLocation";

    /**
     * 广告key
     */
    public final static String ADVERTISEMENT_KEY = "BussinessAdvertisement";

    /**
     * 系统参数
     */
    public final static String SYSTEMPARAM_KEY = "BussinessSystemParam";

    /**
     * 账户和任务关系
     */
    public final static String ACCOUNTANDTASKRELATION = "KernelAccountTaskRelation";

    /**
     * 账户和设备关系
     */
    public final static String ACCOUNTANDDEVICERELATION = "KernelAccountDeviceRelation";

    /**
     * 账户未采集星星key
     */
    public final static String ACCOUNTUNCOLLECTIONRECORDS = "KernelAccountUnCollectionRecords";

}
