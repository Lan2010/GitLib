package com.tianzhixing.oms.bussiness.dao.task;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.task.TaskInfoModel;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/23.
 */
@Repository("taskInfoDao")
public class TaskInfoDao extends GenericBaseDao<TaskInfoModel, Long> {

    /**
     * 获取数量
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    public long count(final Date sBeginTime, final Date sEndTime, final Date aBeginTime, final Date aEndTime) {
        StringBuffer sql = new StringBuffer("select count(*) from task_info where 1 = 1 ");
        return super.count(_condition(sql, sBeginTime, sEndTime, aBeginTime, aEndTime));
    }

    /**
     * 获取数据
     *
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @param from
     * @param pageSize
     * @return
     */
    public List<TaskInfoModel> list(final Date sBeginTime, final Date sEndTime, final Date aBeginTime, final Date aEndTime, final int from, final int pageSize) {
        StringBuffer sql = new StringBuffer("select * from task_info where 1 = 1 ");
        return super.list(new StringBuffer(_condition(sql, sBeginTime, sEndTime, aBeginTime, aEndTime)).append(" limit ").append(from).append(",").append(pageSize).toString());
    }

    /**
     * 根据id获取
     *
     * @param taskId
     * @return
     */
    public TaskInfoModel getById(final Long taskId) {
        return super.get(taskId);
    }

    /**
     * 组装条件
     *
     * @param sql
     * @param sBeginTime
     * @param sEndTime
     * @param aBeginTime
     * @param aEndTime
     * @return
     */
    private String _condition(StringBuffer sql, Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        if (sBeginTime != null) {
            sql.append(" and begin_time >= '").append(CalendarUtil.dateTime2String(sBeginTime)).append("'");
        }
        if (sEndTime != null) {
            sql.append(" and begin_time <= '").append(CalendarUtil.dateTime2String(sEndTime)).append("'");
        }
        if (aBeginTime != null) {
            sql.append(" and end_time >= '").append(CalendarUtil.dateTime2String(aBeginTime)).append("'");
        }
        if (aEndTime != null) {
            sql.append(" and end_time <= '").append(CalendarUtil.dateTime2String(aEndTime)).append("'");
        }
        return sql.toString();
    }

    /**
     * 添加
     *
     * @param taskInfoModel
     * @return
     */
    public TaskInfoModel add(final TaskInfoModel taskInfoModel) {
        long id = super.insert(taskInfoModel);
        taskInfoModel.setId(id);
        return taskInfoModel;
    }

    /**
     * 更新
     *
     * @param isSend
     * @param id
     * @param version
     */
    public void updateIsSend(final boolean isSend, final Long id, final Integer version) {
        super.update(id, new String[]{"is_send"}, new Object[]{isSend}, version);
    }
}
