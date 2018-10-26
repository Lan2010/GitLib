package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface RPCUserAuthDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<UserAuthDimensionMapper> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    UserAuthDimensionMapper getById(Long id);

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
    List<UserAuthDimensionMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param UserAuthDimensionMapper
     * @return
     */
    UserAuthDimensionMapper add(UserAuthDimensionMapper userAuthDimensionMapper);

    /**
     * 更新
     *
     * @param UserAuthDimensionMapper
     */
    void update(UserAuthDimensionMapper userAuthDimensionMapper);

}
