package com.tianzhixing.oms.bussiness.dao.app;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.app.AppSuspendInfoModel;
import org.springframework.stereotype.Repository;

/**
 * Created by routine.k on 2018/7/7.
 */
@Repository("appSuspendInfoDao")
public class AppSuspendInfoDao extends GenericBaseDao<AppSuspendInfoModel, Long> {

    /**
     * 更新发送
     *
     * @param isSend
     * @param id
     * @param version
     */
    public void updateIsSend(final boolean isSend, final Long id, final Integer version) {
        super.update(id, new String[]{"is_send"}, new Object[]{isSend}, version);
    }
}
