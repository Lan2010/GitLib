package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.StarPointConsumeDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface RPCStarPointConsumeDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<StarPointConsumeDimensionMapper> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    StarPointConsumeDimensionMapper getById(Long id);

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
    List<StarPointConsumeDimensionMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param StarPointConsumeDimensionMapper
     * @return
     */
    StarPointConsumeDimensionMapper add(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper);

    /**
     * 更新
     *
     * @param StarPointConsumeDimensionMapper
     */
    void update(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper);
}
