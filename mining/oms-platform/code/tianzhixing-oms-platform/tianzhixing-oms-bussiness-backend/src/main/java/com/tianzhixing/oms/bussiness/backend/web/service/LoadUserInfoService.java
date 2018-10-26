package com.tianzhixing.oms.bussiness.backend.web.service;

import com.tianzhixing.oms.bussiness.rpc.mapper.staff.StaffInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.staff.RPCStaffInfoService;
import com.tianzhixing.oms.web.security.service.UserInfoService;
import com.tianzhixing.oms.web.security.service.entity.UserInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service
public class LoadUserInfoService implements UserInfoService {

    @Autowired
    private RPCStaffInfoService rpcStaffInfoService;

    @Override
    public UserInfo loadByUserName(String username) {
        UserInfo userInfo = null;
        StaffInfoMapper staffInfo = rpcStaffInfoService.getByAccount(username);
        if (staffInfo != null) {
            userInfo = new UserInfo();
            userInfo.setId(staffInfo.getId());
            userInfo.setAccount(staffInfo.getAccount());
            userInfo.setName(staffInfo.getName());
            userInfo.setPassword(staffInfo.getPassword());
            userInfo.setMail(staffInfo.getMail());
            userInfo.setMobile(staffInfo.getMobile());
            userInfo.setCreateTime(staffInfo.getCreateTime());
            userInfo.setUpdateTime(staffInfo.getUpdateTime());
            userInfo.setEnable(staffInfo.getEnable());
            userInfo.setLastLoginTime(staffInfo.getLastLoginTime());
        }
        return userInfo;
    }

    @Override
    public void updateUserInfo(UserInfo userInfo) {

    }
}
