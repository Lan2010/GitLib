package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallProductDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
public interface RPCMallProductDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<MallProductDimensionMapper> list(boolean enable);

    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    MallProductDimensionMapper getById(Long id);

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
    List<MallProductDimensionMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param MallProductDimensionMapper
     * @return
     */
    MallProductDimensionMapper add(MallProductDimensionMapper mallProductDimensionMapper);

    /**
     * 更新
     *
     * @param MallProductDimensionMapper
     */
    void update(MallProductDimensionMapper mallProductDimensionMapper);

}
