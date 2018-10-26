package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface RPCDeviceDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<DeviceDimensionMapper> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    DeviceDimensionMapper getById(Long id);

    /**
     * 计算数量
     *
     * @return
     */
    long count();

    /**
     * 获取记录数
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<DeviceDimensionMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param DeviceDimensionMapper
     * @return
     */
    DeviceDimensionMapper add(DeviceDimensionMapper deviceDimensionMapper);

    /**
     * 更新
     *
     * @param DeviceDimensionMapper
     */
    void update(DeviceDimensionMapper deviceDimensionMapper);

}
