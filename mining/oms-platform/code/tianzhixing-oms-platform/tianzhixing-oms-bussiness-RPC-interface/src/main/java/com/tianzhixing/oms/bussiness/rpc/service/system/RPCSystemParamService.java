package com.tianzhixing.oms.bussiness.rpc.service.system;

import com.tianzhixing.oms.bussiness.rpc.mapper.system.SystemParamMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/6/25.
 */
public interface RPCSystemParamService {

    /**
     * 获取数量
     *
     * @return
     */
    long count();

    /**
     * 获取数据
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<SystemParamMapper> list(int from, int pageSize);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    SystemParamMapper getById(long id);

    /**
     * 更新值
     *
     * @param id
     * @param value
     */
    void updateValue(long id, String value);
}
