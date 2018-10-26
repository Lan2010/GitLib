package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface RPCPagesDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<PagesDimensionMapper> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    PagesDimensionMapper getById(Long id);

    /**
     * 计算数量
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime);

    /**
     * 获取记录数
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    List<PagesDimensionMapper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize);

    /**
     * 添加
     *
     * @param pagesDimensionMapper
     * @return
     */
    PagesDimensionMapper add(PagesDimensionMapper pagesDimensionMapper);

    /**
     * 更新
     *
     * @param pagesDimensionMapper
     */
    void update(PagesDimensionMapper pagesDimensionMapper);
}
