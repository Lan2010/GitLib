package com.tianzhixing.oms.bussiness.rpc.service.task;

import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskInfoMappper;
import com.tianzhixing.oms.bussiness.rpc.mapper.task.TaskLocationInfoMapper;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/22.
 */
public interface RPCTaskInfoService {

    /**
     * 根据ID获取
     *
     * @param taskId
     * @return
     */
    TaskInfoMappper getByTaskId(Long taskId);

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
     * 分页获取数据
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    List<TaskInfoMappper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize);

    /**
     * 获取位置数量
     *
     * @param taskId
     * @return
     */
    long countTaskLocationByTaskId(Long taskId);

    /**
     * 获取位置清单
     *
     * @param taskId
     * @param from
     * @param pageSize
     * @return
     */
    List<TaskLocationInfoMapper> listTaskLocationByTaskId(Long taskId, int from, int pageSize);

    /**
     * 获取所有数据
     *
     * @return
     */
    List<TaskInfoMappper> list();

    /**
     * 添加任务
     *
     * @param taskInfoMappper
     * @param list
     * @return
     */
    TaskInfoMappper add(TaskInfoMappper taskInfoMappper, List<TaskLocationInfoMapper> list);

    /**
     * 更新
     *
     * @param isSend
     * @param id
     */
    void push(boolean isSend, Long id);

    /**
     * 更新
     *
     * @param taskInfoMappper
     */
    void update(TaskInfoMappper taskInfoMappper);

    /**
     * 根据taskid获取
     *
     * @param taskId
     * @return
     */
    List<TaskLocationInfoMapper> listTaskLocationByTaskId(Long taskId);

    /**
     * 添加location
     *
     * @param taskLocationInfoMapper
     */
    void addLocation(TaskLocationInfoMapper taskLocationInfoMapper);
}
