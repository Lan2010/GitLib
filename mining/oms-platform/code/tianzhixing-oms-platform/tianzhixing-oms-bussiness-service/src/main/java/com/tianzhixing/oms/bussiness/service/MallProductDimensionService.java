package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.MallProductDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface MallProductDimensionService {

    /**
     * list
     * @param enable
     * @return
     */
    List<MallProductDimensionModel> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    MallProductDimensionModel getById(Long id);

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
    List<MallProductDimensionModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param MallProductDimensionModel
     * @return
     */
    MallProductDimensionModel add(MallProductDimensionModel mallProductDimensionModel);

    /**
     * 更新
     *
     * @param MallProductDimensionModel
     */
    void update(MallProductDimensionModel mallProductDimensionModel);
}
