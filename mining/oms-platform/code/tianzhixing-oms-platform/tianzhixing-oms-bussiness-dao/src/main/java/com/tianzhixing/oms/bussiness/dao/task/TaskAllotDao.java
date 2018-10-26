package com.tianzhixing.oms.bussiness.dao.task;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.task.TaskAllotModel;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/19.
 */
@Repository("taskAllotDao")
public class TaskAllotDao extends GenericBaseDao<TaskAllotModel, Long> {


    /**
     * 添加
     *
     * @param taskAllotModel
     */
    public TaskAllotModel add(final TaskAllotModel taskAllotModel) {
        Long id = super.insert(taskAllotModel);
        taskAllotModel.setId(id);
        return taskAllotModel;
    }

    /**
     * 获取清单
     *
     * @param date
     * @param taskType
     * @param taskStatus
     * @param currentMachine
     * @return
     */
    public List<TaskAllotModel> list(final Date date, final Integer taskType, final Integer taskStatus, final String currentMachine) {
        String sql = "select * from task_allot where execute_time <= '" + CalendarUtil.dateTime2String(date) + "' and task_type = " + taskType + " and task_status = " + taskStatus + " and machine_num = '" + currentMachine + "'";
        return super.list(sql);
    }

    /**
     * 获取任务数量
     *
     * @param taskType
     * @param executeTime
     * @return
     */
    public long count(final Integer taskType, final Date executeTime) {
        return super.count(new String[]{"task_type", "execute_time"}, new Object[]{taskType, executeTime});
    }
}
