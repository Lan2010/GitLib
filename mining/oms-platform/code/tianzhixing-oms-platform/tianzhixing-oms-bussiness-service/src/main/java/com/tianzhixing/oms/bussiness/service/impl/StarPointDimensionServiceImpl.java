package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.statistics.StarPointDimensionDao;
import com.tianzhixing.oms.bussiness.model.statistics.StarPointConsumeDimensionModel;
import com.tianzhixing.oms.bussiness.service.StarPointDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("starPointDimensionService")
public class StarPointDimensionServiceImpl implements StarPointDimensionService {

    @Autowired
    private StarPointDimensionDao starPointDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<StarPointConsumeDimensionModel> list(boolean enable) {
        return starPointDimensionDao.list(enable);
    }
    
    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public StarPointConsumeDimensionModel getById(Long id) {
        return starPointDimensionDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count() {
        return starPointDimensionDao.count();
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<StarPointConsumeDimensionModel> list(int from, int pageSize) {
        return starPointDimensionDao.list(from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public StarPointConsumeDimensionModel add(StarPointConsumeDimensionModel starPointConsumeDimensionModel) {
    	starPointConsumeDimensionModel = starPointDimensionDao.add(starPointConsumeDimensionModel);
        return starPointConsumeDimensionModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(StarPointConsumeDimensionModel starPointConsumeDimensionModel) {
        starPointDimensionDao.update(starPointConsumeDimensionModel.getId(), starPointConsumeDimensionModel.getVersion(), starPointConsumeDimensionModel);
    }
}
