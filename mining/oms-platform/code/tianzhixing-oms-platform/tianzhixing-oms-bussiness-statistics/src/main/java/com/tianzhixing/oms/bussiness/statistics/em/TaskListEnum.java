package com.tianzhixing.oms.bussiness.statistics.em;

/**
 * 任务清单
 * Created by routine.k on 2018/6/19.
 */
public enum TaskListEnum {

    APPOPERATION, //程序启动/关闭
    DEVICEBINDANDUNBIND, //设备绑定/解除绑定
    DEVICECHECKIN, //设备登记
    DEVICEREGANDONLINEANDOUTLINE, //设备上线/下线
    PAGESACCESS, //页面访问
    USERSTARPOINTCONSUME, //用户星点消费
    USERCLICKADVERTMENT,//用户点击/访问广告
    USERCREATEGREETERCARDANDSHARED, //用户创建/分享贺卡
    USERCREATEPOSTCARDANDSHARED, //用户创建/分享明信片
    USERAUTH, //用户认证
    USERREG, //用户注册
    USERLOGINSTATUS, //用户登录，登出
    USERRECORDING, //用户录音
    USERSTARPOINTINCREMENT, //用户星点增加
    USERTASK //用户任务接受/取消

}
