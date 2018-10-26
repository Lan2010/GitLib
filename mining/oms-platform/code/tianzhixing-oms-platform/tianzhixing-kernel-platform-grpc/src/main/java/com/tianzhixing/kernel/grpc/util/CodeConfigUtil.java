package com.tianzhixing.kernel.grpc.util;

/**
 * Created by routine.k on 2018/7/6.
 */
public class CodeConfigUtil {

    /**
     * 请求成功
     */
    public final static int SUCCESS = 200;

    /**
     * 账户已经存在
     */
    public final static int STARPOINTALREADYEXISTS = 501;

    /**
     * 无法找到账户
     */
    public final static int STARPOINTNOTFOUND = 502;

    /**
     * 注册异常
     */
    public final static int REGEXCEPTION = 503;

    /**
     * 星点操作失败
     */
    public final static int STARPOINTOPERATIONEXCEPTION = 503;

    /**
     * 星点奖励失败
     */
    public final static int STARPOINTAWARDEXCEPTION = 504;

    /**
     * 系统错误
     */
    public final static int SYSTEMERROR = 500;

    /**
     * 请求参数错误
     */
    public final static int PARAMERROR = 300;

}
