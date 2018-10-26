package com.tianzhixing.oms.rpc.service;

import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;
import com.tianzhixing.oms.rpc.mapper.PagesOperationInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
public interface RPCPagesOperationService {

    /**
     * insert
     *
     * @param pagesOperationInfoMapper
     */
    void insert(PagesOperationInfoMapper pagesOperationInfoMapper);

    /**
     * list
     *
     * @param beginTime
     * @param endTime
     * @return
     */
    List<PagesOperationInfoMapper> list(Date beginTime, Date endTime);
}
