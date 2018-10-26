package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.PagesDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.PagesDimensionModel;
import com.tianzhixing.oms.bussiness.service.PagesDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("pagesDimensionService")
public class PagesDimensionServiceImpl implements PagesDimensionService {

    @Autowired
    private PagesDimensionDao pagesDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PagesDimensionModel> list(Boolean enable) {
        return pagesDimensionDao.list(enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public PagesDimensionModel getById(Long id) {
        return pagesDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return pagesDimensionDao.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<PagesDimensionModel> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize) {
        return pagesDimensionDao.list(sBeginTime, sEndTime, aBeginTime, aEndTime, from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public PagesDimensionModel add(PagesDimensionModel pagesDimensionModel) {
        pagesDimensionModel = pagesDimensionDao.add(pagesDimensionModel);
        return pagesDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(PagesDimensionModel pagesDimensionModel) {
        pagesDimensionDao.update(pagesDimensionModel.getId(), pagesDimensionModel.getVersion(), pagesDimensionModel);
    }
}
