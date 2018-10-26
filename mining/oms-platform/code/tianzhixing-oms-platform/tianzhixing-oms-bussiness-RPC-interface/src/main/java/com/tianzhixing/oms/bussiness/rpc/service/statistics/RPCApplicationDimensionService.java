package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/5.
 */
public interface RPCApplicationDimensionService {

    /**
     * 获取清单
     *
     * @param statisticsDimension
     * @param enable
     * @return
     */
    List<ApplicationDimensionMapper> list(StatisticsDimension statisticsDimension, Boolean enable);

    /**
     * 获取子类清单
     *
     * @param parentId
     * @param statisticsDimension
     * @param enable
     * @return
     */
    List<ApplicationDimensionMapper> listByParentId(Long parentId, StatisticsDimension statisticsDimension, Boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    ApplicationDimensionMapper getById(Long id);

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
    List<ApplicationDimensionMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param ApplicationDimensionMapper
     * @return
     */
    ApplicationDimensionMapper add(ApplicationDimensionMapper applicationDimensionMapper);

    /**
     * 更新
     *
     * @param ApplicationDimensionMapper
     */
    void update(ApplicationDimensionMapper applicationDimensionMapper);

}
