package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.bussiness.commons.em.StatisticsDimension;
import com.tianzhixing.oms.bussiness.model.statistics.ApplicationDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.ApplicationDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCApplicationDimensionService;
import com.tianzhixing.oms.bussiness.service.ApplicationDimensionService;

import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

/**
 * Created by routine.k on 2018/7/6.
 */
@Service("RPCApplicationDimensionService")
public class RPCApplicationDimensionServiceImpl implements RPCApplicationDimensionService {

    @Resource
    private ApplicationDimensionService applicationDimensionService;

    @Override
    public List<ApplicationDimensionMapper> list(StatisticsDimension statisticsDimension, Boolean enable) {
        List<ApplicationDimensionModel> applicationDimensionModels = applicationDimensionService.list(null, statisticsDimension, enable);
        List<ApplicationDimensionMapper> applicationDimensionMapperList = new ArrayList<>();
        if (applicationDimensionModels != null) {
            for (ApplicationDimensionModel applicationDimensionModel : applicationDimensionModels) {
                applicationDimensionMapperList.add(_toMapper(applicationDimensionModel));
            }
        }
        return applicationDimensionMapperList;
    }

    @Override
    public List<ApplicationDimensionMapper> listByParentId(Long parentId, StatisticsDimension statisticsDimension, Boolean enable) {
        List<ApplicationDimensionModel> applicationDimensionModels = applicationDimensionService.list(parentId, statisticsDimension, enable);
        List<ApplicationDimensionMapper> applicationDimensionMapperList = new ArrayList<>();
        if (applicationDimensionModels != null) {
            for (ApplicationDimensionModel applicationDimensionModel : applicationDimensionModels) {
                applicationDimensionMapperList.add(_toMapper(applicationDimensionModel));
            }
        }
        return applicationDimensionMapperList;
    }
    
    @Override
    public ApplicationDimensionMapper getById(Long id) {
        ApplicationDimensionModel applicationDimensionModel = applicationDimensionService.getById(id);
        return applicationDimensionModel == null ? null : _toMapper(applicationDimensionModel);
    }

    @Override
    public long count() {
        return applicationDimensionService.count();
    }

    @Override
    public List<ApplicationDimensionMapper> list(int from, int pageSize) {
        List<ApplicationDimensionModel> applicationDimensionModels = applicationDimensionService.list(from, pageSize);
        List<ApplicationDimensionMapper> applicationDimensionMapper = new ArrayList<>();
        if (applicationDimensionModels != null) {
            for (ApplicationDimensionModel applicationDimensionModel : applicationDimensionModels) {
            	applicationDimensionMapper.add(_toMapper(applicationDimensionModel));
            }
        }
        return applicationDimensionMapper;
    }

    @Override
    public ApplicationDimensionMapper add(ApplicationDimensionMapper applicationDimensionMapper) {
    	ApplicationDimensionModel applicationDimensionModel = applicationDimensionService.add(_toModel(applicationDimensionMapper));
        return _toMapper(applicationDimensionModel);
    }

    @Override
    public void update(ApplicationDimensionMapper applicationDimensionMapper) {
    	ApplicationDimensionModel applicationDimensionModel = applicationDimensionService.getById(applicationDimensionMapper.getId());
        Assert.notNull(applicationDimensionModel, "dimensionModel.not.found");
        applicationDimensionModel.setName(applicationDimensionMapper.getName());
        applicationDimensionModel.setValue(applicationDimensionMapper.getValue());
        applicationDimensionModel.setEnable(applicationDimensionMapper.getEnable());
        applicationDimensionModel.setStatisticsDimension(applicationDimensionMapper.getStatisticsDimension());
        applicationDimensionService.update(applicationDimensionModel);
    }

    private ApplicationDimensionModel _toModel(ApplicationDimensionMapper applicationDimensionMapper) {
    	ApplicationDimensionModel applicationDimensionModel = new ApplicationDimensionModel();
    	applicationDimensionModel.setVersion(0);
    	applicationDimensionModel.setCreateTime(new Date());
    	applicationDimensionModel.setName(applicationDimensionMapper.getName());
    	applicationDimensionModel.setValue(applicationDimensionMapper.getValue());
        applicationDimensionModel.setEnable(applicationDimensionMapper.getEnable());
        applicationDimensionModel.setStatisticsDimension(applicationDimensionMapper.getStatisticsDimension());
        applicationDimensionModel.setParentId(applicationDimensionMapper.getParentId());
        return applicationDimensionModel;
    }

    
    private ApplicationDimensionMapper _toMapper(ApplicationDimensionModel applicationDimensionModel) {
        return new ApplicationDimensionMapper(applicationDimensionModel.getId(), applicationDimensionModel.getVersion(), applicationDimensionModel.getCreateTime(), applicationDimensionModel.getStatisticsDimension(), applicationDimensionModel.getParentId(), applicationDimensionModel.getEnable(), applicationDimensionModel.getName(), applicationDimensionModel.getValue());
    }
}
