package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.bussiness.model.system.SystemParamModel;

import java.util.List;

/**
 * Created by routine.k on 2018/6/25.
 */
public interface SystemParamService {

    /**
     * 获取数量
     *
     * @return
     */
    long count();

    /**
     * 获取清单
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<SystemParamModel> list(int from, int pageSize);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    SystemParamModel getById(long id);

    /**
     * 更新
     *
     * @param systemParamModel
     */
    void update(SystemParamModel systemParamModel);

    /**
     * 通过类型获取
     *
     * @param systemParamType
     * @return
     */
    SystemParamModel getType(SystemParamType systemParamType);
}
