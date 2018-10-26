package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.StarPointConsumeDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface StarPointDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<StarPointConsumeDimensionModel> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    StarPointConsumeDimensionModel getById(Long id);

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
    List<StarPointConsumeDimensionModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param StarPointConsumeDimensionModel
     * @return
     */
    StarPointConsumeDimensionModel add(StarPointConsumeDimensionModel starPointConsumeDimensionModel);

    /**
     * 更新
     *
     * @param StarPointConsumeDimensionModel
     */
    void update(StarPointConsumeDimensionModel starPointConsumeDimensionModel);
}
