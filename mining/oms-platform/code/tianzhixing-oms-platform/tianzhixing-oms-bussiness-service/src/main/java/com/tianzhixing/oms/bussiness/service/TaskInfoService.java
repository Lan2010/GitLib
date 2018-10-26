package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.task.TaskInfoModel;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/23.
 */
public interface TaskInfoService {

    /**
     * 获取记录
     *
     * @param taskId
     * @return
     */
    TaskInfoModel getById(Long taskId);

    /**
     * 获取数量
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime);

    /**
     * 获取列表
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    List<TaskInfoModel> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize);


    /**
     * 获取所有数据
     *
     * @return
     */
    List<TaskInfoModel> list();

    /**
     * 添加
     *
     * @param taskInfoModel
     * @param taskLocationInfoModels
     * @return
     */
    TaskInfoModel add(TaskInfoModel taskInfoModel, List<TaskLocationInfoModel> taskLocationInfoModels);

    /**
     * 更新
     *
     * @param isSend
     * @param taskInfoModel
     */
    void updateIsSend(boolean isSend, TaskInfoModel taskInfoModel);

    /**
     * 更新
     *
     * @param taskInfoModel
     */
    void update(TaskInfoModel taskInfoModel);

    /**
     * 获取最大id
     *
     * @return
     */
    Long maxId();
}
