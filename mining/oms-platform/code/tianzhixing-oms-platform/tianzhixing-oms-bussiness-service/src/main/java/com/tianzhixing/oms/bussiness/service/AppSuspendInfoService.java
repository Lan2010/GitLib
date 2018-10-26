package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.app.AppSuspendInfoModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface AppSuspendInfoService {

    /**
     * 获取数量
     *
     * @return
     */
    long count();

    /**
     * 分页获取
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<AppSuspendInfoModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param appSuspendInfoModel
     */
    void add(AppSuspendInfoModel appSuspendInfoModel);


    /**
     * 更新
     *
     * @param isSend
     * @param appSuspendInfoModel
     */
    void updateIsSend(boolean isSend, AppSuspendInfoModel appSuspendInfoModel);

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    AppSuspendInfoModel getById(long id);
}
