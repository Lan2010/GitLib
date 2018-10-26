package com.tianzhixing.kernel.dao.account;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.dao.generic.GenericBaseDao;
import com.tianzhixing.kernel.model.account.AccountStarPointOperationRecordsInfoModel;
import com.tianzhixing.oms.utils.CalendarUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/29.
 */
@Repository("accountStarPointOperationRecordsInfoDao")
public class AccountStarPointOperationRecordsInfoDao extends GenericBaseDao<AccountStarPointOperationRecordsInfoModel, Long> {

    /**
     * 查询记录数
     *
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    public long count(final Long accountId, final Date beginTime, final Date endTime, final String taskId, final String advertisementId, final StarPointRecordsType starPointRecordsType) {
        StringBuffer sql = new StringBuffer("select count(*) from account_starpoint_operation_records where 1 = 1 ");
        return super.count(_condition(sql, accountId, beginTime, endTime, taskId, advertisementId, starPointRecordsType));
    }

    /**
     * 查询记录
     *
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @param from
     * @param size
     * @return
     */
    public List<AccountStarPointOperationRecordsInfoModel> list(final Long accountId, final Date beginTime, final Date endTime, final String taskId, final String advertisementId, final StarPointRecordsType starPointRecordsType, final int from, final int size) {
        StringBuffer sql = new StringBuffer("select * from account_starpoint_operation_records where 1 = 1 ");
        return super.list(new StringBuffer(_condition(sql, accountId, beginTime, endTime, taskId, advertisementId, starPointRecordsType)).append(" order by id desc ").append(" limit ").append(from).append(",").append(size).toString());
    }

    /**
     * 组装条件
     *
     * @param sql
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    private String _condition(StringBuffer sql, Long accountId, Date beginTime, Date endTime, String taskId, String advertisementId, StarPointRecordsType starPointRecordsType) {
        if (beginTime != null) {
            sql.append(" and create_time >= '").append(CalendarUtil.dateTime2String(beginTime)).append("'");
        }
        if (endTime != null) {
            sql.append(" and create_time <= '").append(CalendarUtil.dateTime2String(endTime)).append("'");
        }
        if (StringUtils.isNotEmpty(taskId) && !"0".equals(taskId)) {
            sql.append(" and task_id = '").append(taskId).append("'");
        }
        if (StringUtils.isNotEmpty(advertisementId) && !"0".equals(advertisementId)) {
            sql.append(" and advertisement_id = '").append(taskId).append("'");
        }
        if (starPointRecordsType != null) {
            sql.append(" and records_type = ").append(starPointRecordsType.getCode());
        }
        if (accountId != null) {
            sql.append(" and account_id = ").append(accountId);
        }
        return sql.toString();
    }

    /**
     * 查询记录
     *
     * @param accountId
     * @param beginTime
     * @param endTime
     * @param taskId
     * @param advertisementId
     * @param starPointRecordsType
     * @return
     */
    public List<AccountStarPointOperationRecordsInfoModel> list(final Long accountId, final Date beginTime, final Date endTime, final String taskId, final String advertisementId, final StarPointRecordsType starPointRecordsType) {
        StringBuffer sql = new StringBuffer("select * from account_starpoint_operation_records where 1 = 1 ");
        return super.list(new StringBuffer(_condition(sql, accountId, beginTime, endTime, taskId, advertisementId, starPointRecordsType)).toString());
    }

    /**
     * 根据任务获取星点
     *
     * @param accountId
     * @param taskId
     * @return
     */
    public Double starPointWithTask(final Long accountId, final String taskId) {
        String sql = "select sum(oper_starpoint) from account_starpoint_operation_records where account_id = " + accountId + " and task_id = " + taskId;
        Object obj = super.getProperty(sql);
        return obj == null ? 0d : Double.valueOf(obj.toString());
    }
}
