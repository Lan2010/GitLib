package com.tianzhixing.oms.bussiness.dao.staff;

import com.tianzhixing.oms.bussiness.dao.generic.GenericBaseDao;
import com.tianzhixing.oms.bussiness.model.staff.StaffInfoModel;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/23.
 */
@Repository("staffInfoDao")
public class StaffInfoDao extends GenericBaseDao<StaffInfoModel, Long> {

    /**
     * 根据account获取
     *
     * @param account
     * @return
     */
    public StaffInfoModel getByAccount(final String account) {
        return super.get(new String[]{"account"}, new Object[]{account});
    }

    /**
     * 更新密码
     *
     * @param id
     * @param pwd
     * @param version
     */
    public void updatePassword(final Long id, final String pwd, final Integer version) {
        super.update(id, new String[]{"password", "update_time"}, new Object[]{pwd, new Date()}, version);
    }
}
