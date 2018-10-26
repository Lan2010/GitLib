package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.MallProductDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.MallProductDimensionModel;
import com.tianzhixing.oms.bussiness.service.MallProductDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("mallProductDimensionService")
public class MallProductDimensionServiceImpl implements MallProductDimensionService {

    @Autowired
    private MallProductDimensionDao mallProductDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MallProductDimensionModel> list(boolean enable) {
        return mallProductDimensionDao.list(enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public MallProductDimensionModel getById(Long id) {
        return mallProductDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return mallProductDimensionDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<MallProductDimensionModel> list(int from, int pageSize) {
        return mallProductDimensionDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public MallProductDimensionModel add(MallProductDimensionModel mallProductDimensionModel) {
    	mallProductDimensionModel = mallProductDimensionDao.add(mallProductDimensionModel);
        return mallProductDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(MallProductDimensionModel mallProductDimensionModel) {
        mallProductDimensionDao.update(mallProductDimensionModel.getId(), mallProductDimensionModel.getVersion(), mallProductDimensionModel);
    }
}
