package com.tianzhixing.oms.bussiness.dao.task;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.task.TaskLocationInfoModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Repository("taskLocationInfoDao")
public class TaskLocationInfoDao extends GenericBaseDao<TaskLocationInfoModel, Long> {

    /**
     * 获取数量
     *
     * @param taskId
     * @return
     */
    public long count(final Long taskId) {
        return super.count(new String[]{"task_id"}, new Object[]{taskId});
    }

    /**
     * 获取数量
     *
     * @return
     */
    public long count() {
        return super.count();
    }

    /**
     * 获取清单
     *
     * @param taskId
     * @param from
     * @param pageSize
     * @return
     */
    public List<TaskLocationInfoModel> list(final Long taskId, final int from, final int pageSize) {
        return super.list(new String[]{"task_id"}, new Object[]{taskId}, from, pageSize);
    }

    /**
     * 获取清单
     *
     * @param from
     * @param pageSize
     * @return
     */
    public List<TaskLocationInfoModel> list(final int from, final int pageSize) {
        return super.list(from, pageSize);
    }

    /**
     * 添加
     *
     * @param taskLocationInfoModel
     */
    public TaskLocationInfoModel add(final TaskLocationInfoModel taskLocationInfoModel) {
        long id = super.insert(taskLocationInfoModel);
        taskLocationInfoModel.setId(id);
        return taskLocationInfoModel;
    }

    /**
     * 获取记录
     *
     * @param taskId
     * @return
     */
    public List<TaskLocationInfoModel> list(final Long taskId) {
        return super.list(new String[]{"task_id"}, new Object[]{taskId});
    }

}
