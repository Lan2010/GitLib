package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceDimensionModel;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface DeviceDimensionService {

    /**
     * 获取列表
     *
     * @param enable
     * @return
     */
    List<DeviceDimensionModel> list(Boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    DeviceDimensionModel getById(Long id);

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
    List<DeviceDimensionModel> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param DeviceDimensionModel
     * @return
     */
    DeviceDimensionModel add(DeviceDimensionModel deviceDimensionModel);

    /**
     * 更新
     *
     * @param DeviceDimensionModel
     */
    void update(DeviceDimensionModel deviceDimensionModel);
}
