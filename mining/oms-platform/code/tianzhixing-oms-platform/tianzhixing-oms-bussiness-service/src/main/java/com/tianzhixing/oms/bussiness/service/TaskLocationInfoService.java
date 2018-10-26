package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
public interface TaskLocationInfoService {

    /**
     * 获取数量
     *
     * @return
     */
    long count();

    /**
     * 获取清单
     *
     * @param from
     * @param pageSize
     * @return
     */
    List<TaskLocationInfoModel> list(int from, int pageSize);

    /**
     * 获取数量
     *
     * @param taskId
     * @return
     */
    long count(Long taskId);

    /**
     * 获取清单
     *
     * @param taskId
     * @param from
     * @param pageSize
     * @return
     */
    List<TaskLocationInfoModel> list(Long taskId, int from, int pageSize);

    /**
     * 获取记录
     *
     * @param taskInfoId
     * @return
     */
    List<TaskLocationInfoModel> list(Long taskInfoId);

    /**
     * 添加
     *
     * @param taskLocationInfoModel
     * @return
     */
    TaskLocationInfoModel add(TaskLocationInfoModel taskLocationInfoModel);
}
