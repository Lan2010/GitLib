package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.StarPointConsumeDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.StarPointConsumeDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCStarPointConsumeDimensionService;
import com.tianzhixing.oms.bussiness.service.StarPointDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("RPCStarPointConsumeDimensionService")
public class RPCStarPointConsumeDimensionServiceImpl implements RPCStarPointConsumeDimensionService {

    @Autowired
    private StarPointDimensionService starPointDimensionService;

    @Override
    public List<StarPointConsumeDimensionMapper> list(boolean enable) {
        List<StarPointConsumeDimensionModel> starPointConsumeDimensionModels = starPointDimensionService.list(enable);
        List<StarPointConsumeDimensionMapper> starPointConsumeDimensionMappers = new ArrayList<>();
        if (starPointConsumeDimensionModels != null) {
            for (StarPointConsumeDimensionModel starPointConsumeDimensionModel : starPointConsumeDimensionModels) {
                starPointConsumeDimensionMappers.add(_toMapper(starPointConsumeDimensionModel));
            }
        }
        return starPointConsumeDimensionMappers;
    }

    @Override
    public StarPointConsumeDimensionMapper getById(Long id) {
    	StarPointConsumeDimensionModel starPointConsumeDimensionModel = starPointDimensionService.getById(id);
        return starPointConsumeDimensionModel == null ? null : _toMapper(starPointConsumeDimensionModel);
    }

    @Override
    public long count() {
        return starPointDimensionService.count();
    }

    @Override
    public List<StarPointConsumeDimensionMapper> list(int from, int pageSize) {
        List<StarPointConsumeDimensionModel> starPointConsumeDimensionModels = starPointDimensionService.list(from, pageSize);
        List<StarPointConsumeDimensionMapper> starPointConsumeDimensionMapper = new ArrayList<>();
        if (starPointConsumeDimensionModels != null) {
            for (StarPointConsumeDimensionModel starPointConsumeDimensionModel : starPointConsumeDimensionModels) {
            	starPointConsumeDimensionMapper.add(_toMapper(starPointConsumeDimensionModel));
            }
        }
        return starPointConsumeDimensionMapper;
    }

    @Override
    public StarPointConsumeDimensionMapper add(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper) {
    	StarPointConsumeDimensionModel starPointConsumeDimensionModel = starPointDimensionService.add(_toModel(starPointConsumeDimensionMapper));
        return _toMapper(starPointConsumeDimensionModel);
    }

    @Override
    public void update(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper) {
    	StarPointConsumeDimensionModel starPointConsumeDimensionModel = starPointDimensionService.getById(starPointConsumeDimensionMapper.getId());
        Assert.notNull(starPointConsumeDimensionModel, "dimensionModel.not.found");
        starPointConsumeDimensionModel.setName(starPointConsumeDimensionMapper.getName());
        starPointConsumeDimensionModel.setValue(starPointConsumeDimensionMapper.getValue());
        starPointConsumeDimensionModel.setEnable(starPointConsumeDimensionMapper.getEnable());
        starPointDimensionService.update(starPointConsumeDimensionModel);
    }

    private StarPointConsumeDimensionModel _toModel(StarPointConsumeDimensionMapper starPointConsumeDimensionMapper) {
    	StarPointConsumeDimensionModel starPointConsumeDimensionModel = new StarPointConsumeDimensionModel();
    	starPointConsumeDimensionModel.setVersion(0);
    	starPointConsumeDimensionModel.setCreateTime(new Date());
    	starPointConsumeDimensionModel.setName(starPointConsumeDimensionMapper.getName());
    	starPointConsumeDimensionModel.setValue(starPointConsumeDimensionMapper.getValue());
        starPointConsumeDimensionModel.setEnable(starPointConsumeDimensionMapper.getEnable());
        return starPointConsumeDimensionModel;
    }
    
    private StarPointConsumeDimensionMapper _toMapper(StarPointConsumeDimensionModel starPointConsumeDimensionModel) {
        return new StarPointConsumeDimensionMapper(starPointConsumeDimensionModel.getId(), starPointConsumeDimensionModel.getVersion(), starPointConsumeDimensionModel.getCreateTime(), starPointConsumeDimensionModel.getName(), starPointConsumeDimensionModel.getValue(), starPointConsumeDimensionModel.getEnable());
    }
}
