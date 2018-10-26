package com.tianzhixing.oms.bussiness.rpc.service.staff;

import com.tianzhixing.oms.bussiness.rpc.mapper.staff.StaffInfoMapper;

/**
 * Created by routine.k on 2018/6/23.
 */
public interface RPCStaffInfoService {

    /**
     * 通过username获取
     *
     * @param account
     * @return
     */
    StaffInfoMapper getByAccount(String account);

    /**
     * 更新密码
     *
     * @param id
     * @param pwd
     */
    void updateUserPassword(Long id, String pwd);
    
    StaffInfoMapper getById(Long id);
}
