package com.tianzhixing.oms.bussiness.service;

import com.tianzhixing.oms.bussiness.model.staff.StaffInfoModel;

/**
 * Created by routine.k on 2018/6/23.
 */
public interface StaffInfoService {

    /**
     * 通过账户获取
     *
     * @param account
     * @return
     */
    StaffInfoModel getByAccount(String account);

    /**
     * 通过id获取
     *
     * @param id
     * @return
     */
    StaffInfoModel getById(Long id);

    /**
     * 更新密码
     *
     * @param staffInfoModel
     * @param pwd
     */
    void updatePassword(StaffInfoModel staffInfoModel, String pwd);
}
