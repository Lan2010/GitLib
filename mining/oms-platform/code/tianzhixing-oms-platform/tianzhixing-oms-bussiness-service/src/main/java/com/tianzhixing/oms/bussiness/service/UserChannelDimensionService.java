package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.UserChannelDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface UserChannelDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<UserChannelDimensionModel> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    UserChannelDimensionModel getById(Long id);

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
    List<UserChannelDimensionModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param UserChannelDimensionModel
     * @return
     */
    UserChannelDimensionModel add(UserChannelDimensionModel userChannelDimensionModel);

    /**
     * 更新
     *
     * @param UserChannelDimensionModel
     */
    void update(UserChannelDimensionModel userChannelDimensionModel);
}
