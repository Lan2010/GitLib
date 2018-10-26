package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.staff.StaffInfoDao;
import com.tianzhixing.oms.bussiness.model.staff.StaffInfoModel;
import com.tianzhixing.oms.bussiness.service.StaffInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by routine.k on 2018/6/23.
 */
@Service("staffInfoService")
public class StaffInfoServiceImpl implements StaffInfoService {

    @Autowired
    private StaffInfoDao staffInfoDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public StaffInfoModel getByAccount(String account) {
        return staffInfoDao.getByAccount(account);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public StaffInfoModel getById(Long id) {
        return staffInfoDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updatePassword(StaffInfoModel staffInfoModel, String pwd) {
        staffInfoDao.updatePassword(staffInfoModel.getId(), pwd, staffInfoModel.getVersion());
    }
}
