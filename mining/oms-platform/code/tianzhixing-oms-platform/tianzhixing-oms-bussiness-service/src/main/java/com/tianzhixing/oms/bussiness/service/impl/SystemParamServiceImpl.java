package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.oms.bussiness.dao.system.SystemParamDao;
import com.tianzhixing.oms.bussiness.model.system.SystemParamModel;
import com.tianzhixing.oms.bussiness.service.SystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/6/25.
 */
@Service("systemParamService")
public class SystemParamServiceImpl implements SystemParamService {

    @Autowired
    private SystemParamDao systemParamDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return systemParamDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<SystemParamModel> list(int from, int pageSize) {
        return systemParamDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public SystemParamModel getById(long id) {
        return systemParamDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(SystemParamModel systemParamModel) {
        systemParamDao.update(systemParamModel.getId(), systemParamModel.getVersion(), systemParamModel);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public SystemParamModel getType(SystemParamType systemParamType) {
        return systemParamDao.getType(systemParamType);
    }
}
