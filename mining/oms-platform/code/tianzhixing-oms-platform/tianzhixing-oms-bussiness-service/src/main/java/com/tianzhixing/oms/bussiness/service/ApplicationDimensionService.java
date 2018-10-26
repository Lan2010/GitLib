package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.model.statistics.ApplicationDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/6.
 */
public interface ApplicationDimensionService {

    /**
     * 查询记录
     *
     * @param parentId
     * @param statisticsDimension
     * @param enable
     * @return
     */
    List<ApplicationDimensionModel> list(Long parentId, StatisticsDimension statisticsDimension, Boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    ApplicationDimensionModel getById(Long id);

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
    List<ApplicationDimensionModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param ApplicationDimensionModel
     * @return
     */
    ApplicationDimensionModel add(ApplicationDimensionModel applicationDimensionModel);

    /**
     * 更新
     *
     * @param ApplicationDimensionModel
     */
    void update(ApplicationDimensionModel applicationDimensionModel);

}
