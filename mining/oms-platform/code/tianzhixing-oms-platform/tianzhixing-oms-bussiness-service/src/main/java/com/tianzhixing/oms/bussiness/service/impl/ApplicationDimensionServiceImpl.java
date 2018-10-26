package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.dao.statistics.ApplicationDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.ApplicationDimensionModel;
import com.tianzhixing.oms.bussiness.service.ApplicationDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/6.
 */
@Service("applicationDimensionService")
public class ApplicationDimensionServiceImpl implements ApplicationDimensionService {

    @Autowired
    private ApplicationDimensionDao applicationDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ApplicationDimensionModel> list(Long parentId, StatisticsDimension statisticsDimension, Boolean enable) {
        return applicationDimensionDao.list(parentId, statisticsDimension, enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public ApplicationDimensionModel getById(Long id) {
        return applicationDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return applicationDimensionDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<ApplicationDimensionModel> list(int from, int pageSize) {
        return applicationDimensionDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public ApplicationDimensionModel add(ApplicationDimensionModel applicationDimensionModel) {
    	applicationDimensionModel = applicationDimensionDao.add(applicationDimensionModel);
        return applicationDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(ApplicationDimensionModel applicationDimensionModel) {
        applicationDimensionDao.update(applicationDimensionModel.getId(), applicationDimensionModel.getVersion(), applicationDimensionModel);
    }
}
