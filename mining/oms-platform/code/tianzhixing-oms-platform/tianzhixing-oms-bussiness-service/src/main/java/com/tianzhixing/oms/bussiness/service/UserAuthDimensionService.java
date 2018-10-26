package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserAuthDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface UserAuthDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<UserAuthDimensionModel> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    UserAuthDimensionModel getById(Long id);

    /**
     * 获取记录数
     *
     * @return
     */
    long count();

    /**
     * 获取记录
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<UserAuthDimensionModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param UserAuthDimensionModel
     * @return
     */
    UserAuthDimensionModel add(UserAuthDimensionModel userAuthDimensionModel);

    /**
     * 更新
     *
     * @param UserAuthDimensionModel
     */
    void update(UserAuthDimensionModel userAuthDimensionModel);
}
