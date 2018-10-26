package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.MallProductDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.MallProductDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCMallProductDimensionService;
import com.tianzhixing.oms.bussiness.service.MallProductDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCMallProductDimensionService")
public class RPCMallProductDimensionServiceImpl implements RPCMallProductDimensionService {

    @Autowired
    private MallProductDimensionService mallProductDimensionService;

    @Override
    public List<MallProductDimensionMapper> list(boolean enable) {
        List<MallProductDimensionModel> mallProductDimensionModels = mallProductDimensionService.list(enable);
        List<MallProductDimensionMapper> mallProductDimensionMappers = new ArrayList<>();
        if (mallProductDimensionModels != null) {
            for (MallProductDimensionModel mallProductDimensionModel : mallProductDimensionModels) {
                mallProductDimensionMappers.add(_toMapper(mallProductDimensionModel));
            }
        }
        return mallProductDimensionMappers;
    }

    @Override
    public MallProductDimensionMapper getById(Long id) {
    	MallProductDimensionModel MallProductDimensionModel = mallProductDimensionService.getById(id);
        return MallProductDimensionModel == null ? null : _toMapper(MallProductDimensionModel);
    }

    @Override
    public long count() {
        return mallProductDimensionService.count();
    }

    @Override
    public List<MallProductDimensionMapper> list(int from, int pageSize) {
        List<MallProductDimensionModel> MallProductDimensionModels = mallProductDimensionService.list(from, pageSize);
        List<MallProductDimensionMapper> mallProductDimensionMapper = new ArrayList<>();
        if (MallProductDimensionModels != null) {
            for (MallProductDimensionModel MallProductDimensionModel : MallProductDimensionModels) {
            	mallProductDimensionMapper.add(_toMapper(MallProductDimensionModel));
            }
        }
        return mallProductDimensionMapper;
    }

    @Override
    public MallProductDimensionMapper add(MallProductDimensionMapper mallProductDimensionMapper) {
    	MallProductDimensionModel MallProductDimensionModel = mallProductDimensionService.add(_toModel(mallProductDimensionMapper));
        return _toMapper(MallProductDimensionModel);
    }

    @Override
    public void update(MallProductDimensionMapper mallProductDimensionMapper) {
    	MallProductDimensionModel MallProductDimensionModel = mallProductDimensionService.getById(mallProductDimensionMapper.getId());
        Assert.notNull(MallProductDimensionModel, "dimensionModel.not.found");
        MallProductDimensionModel.setName(mallProductDimensionMapper.getName());
        MallProductDimensionModel.setValue(mallProductDimensionMapper.getValue());
        MallProductDimensionModel.setEnable(mallProductDimensionMapper.getEnable());
        mallProductDimensionService.update(MallProductDimensionModel);
    }

    private MallProductDimensionModel _toModel(MallProductDimensionMapper mallProductDimensionMapper) {
    	MallProductDimensionModel MallProductDimensionModel = new MallProductDimensionModel();
    	MallProductDimensionModel.setVersion(0);
    	MallProductDimensionModel.setCreateTime(new Date());
    	MallProductDimensionModel.setName(mallProductDimensionMapper.getName());
    	MallProductDimensionModel.setValue(mallProductDimensionMapper.getValue());
        MallProductDimensionModel.setEnable(mallProductDimensionMapper.getEnable());
        return MallProductDimensionModel;
    }
    
    private MallProductDimensionMapper _toMapper(MallProductDimensionModel mallProductDimensionModel) {
        return new MallProductDimensionMapper(mallProductDimensionModel.getId(), mallProductDimensionModel.getVersion(), mallProductDimensionModel.getCreateTime(), mallProductDimensionModel.getName(), mallProductDimensionModel.getValue(), mallProductDimensionModel.getEnable());
    }
}
