package com.tianzhixing.oms.bussiness.rpc.service.statistics;

import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
public interface RPCUserChannelDimensionService {

    /**
     * list
     *
     * @param enable
     * @return
     */
    List<UserChannelDimensionMapper> list(boolean enable);
    
    /**
     * 根据id获取
     *
     * @param id
     * @return
     */
    UserChannelDimensionMapper getById(Long id);

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
    List<UserChannelDimensionMapper> list(int from, int pageSize);

    /**
     * 添加
     *
     * @param userChannelDimensionMapper
     * @return
     */
    UserChannelDimensionMapper add(UserChannelDimensionMapper userChannelDimensionMapper);

    /**
     * 更新
     *
     * @param userChannelDimensionMapper
     */
    void update(UserChannelDimensionMapper userChannelDimensionMapper);
}
