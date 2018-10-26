package com.tianzhixing.oms.bussiness.rpc.service.app;

import com.tianzhixing.oms.bussiness.rpc.mapper.app.AppSuspendInfoMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface RPCAppSuspendService {

    /**
     * 获取数量
     *
     * @return
     */
    long count();

    /**
     * 获取列表
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<AppSuspendInfoMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param appSuspendInfoMapper
     */
    void add(AppSuspendInfoMapper appSuspendInfoMapper);

    /**
     * 更新是否推送
     *
     * @param isSend
     * @param id
     */
    void updateIsSend(boolean isSend, long id);

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    AppSuspendInfoMapper getById(long id);
}
