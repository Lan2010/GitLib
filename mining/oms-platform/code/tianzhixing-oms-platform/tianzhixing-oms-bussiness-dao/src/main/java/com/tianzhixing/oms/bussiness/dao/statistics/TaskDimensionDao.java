package com.tianzhixing.oms.bussiness.dao.statistics;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.statistics.TaskDimensionModel;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by routine.k on 2018/7/12.
 */
@Repository("taskDimensionDao")
public class TaskDimensionDao extends GenericBaseDao<TaskDimensionModel, Long> {

    /**
     * list
     *
     * @param enable
     * @return
     */
    public List<TaskDimensionModel> list(boolean enable) {
        return super.list(new String[]{"enable"}, new Object[]{enable});
    }
}
