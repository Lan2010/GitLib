package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.statistics.PagesDimensionModel;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
public interface PagesDimensionService {

    /**
     * 获取列表
     *
     * @param enable
     * @return
     */
    List<PagesDimensionModel> list(Boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    PagesDimensionModel getById(Long id);

    /**
     * 获取记录数
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime);

    /**
     * 获取记录
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    List<PagesDimensionModel> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize);

    /**
     * 添加
     *
     * @param pagesDimensionModel
     * @return
     */
    PagesDimensionModel add(PagesDimensionModel pagesDimensionModel);

    /**
     * 更新
     *
     * @param pagesDimensionModel
     */
    void update(PagesDimensionModel pagesDimensionModel);
}
