package com.tianzhixing.oms.bussiness.rpc.service.impl.staff;

import com.tianzhixing.oms.bussiness.model.staff.StaffInfoModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.staff.StaffInfoMapper;
import com.tianzhixing.oms.bussiness.rpc.service.staff.RPCStaffInfoService;
import com.tianzhixing.oms.bussiness.service.StaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service("RPCStaffInfoService")
public class RPCStaffInfoServiceImpl implements RPCStaffInfoService {

    @Autowired
    private StaffInfoService staffInfoService;

    @Override
    public StaffInfoMapper getByAccount(String account) {
        StaffInfoModel staffInfoModel = staffInfoService.getByAccount(account);
        return staffInfoModel == null ? null : new StaffInfoMapper(staffInfoModel.getId(), staffInfoModel.getAccount(), staffInfoModel.getName(), staffInfoModel.getPassword(), staffInfoModel.getMail(), staffInfoModel.getMobile(), staffInfoModel.getCreateTime(), staffInfoModel.getUpdateTime(), staffInfoModel.getLastLoginTime(), staffInfoModel.getEnable());
    }

    @Override
    public void updateUserPassword(Long id, String pwd) {
        StaffInfoModel staffInfoModel = staffInfoService.getById(id);
        staffInfoService.updatePassword(staffInfoModel, pwd);
    }

	@Override
	public StaffInfoMapper getById(Long id) {
		StaffInfoModel staffInfoModel = staffInfoService.getById(id);
		return staffInfoModel == null ? null : new StaffInfoMapper(staffInfoModel.getId(), staffInfoModel.getAccount(), staffInfoModel.getName(), staffInfoModel.getPassword(), staffInfoModel.getMail(), staffInfoModel.getMobile(), staffInfoModel.getCreateTime(), staffInfoModel.getUpdateTime(), staffInfoModel.getLastLoginTime(), staffInfoModel.getEnable());
	}

}
