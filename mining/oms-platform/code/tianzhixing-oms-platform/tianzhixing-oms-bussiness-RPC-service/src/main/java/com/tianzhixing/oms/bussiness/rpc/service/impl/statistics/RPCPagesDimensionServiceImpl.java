package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.PagesDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.PagesDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCPagesDimensionService;
import com.tianzhixing.oms.bussiness.service.PagesDimensionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCPagesDimensionService")
public class RPCPagesDimensionServiceImpl implements RPCPagesDimensionService {

    @Autowired
    private PagesDimensionService pagesDimensionService;

    @Override
    public List<PagesDimensionMapper> list(boolean enable) {
        List<PagesDimensionModel> pagesDimensionModels = pagesDimensionService.list(enable);
        List<PagesDimensionMapper> pagesDimensionMappers = new ArrayList<>();
        if (pagesDimensionModels != null) {
            for (PagesDimensionModel pagesDimensionModel : pagesDimensionModels) {
                pagesDimensionMappers.add(_toMapper(pagesDimensionModel));
            }
        }
        return pagesDimensionMappers;
    }
    
    @Override
    public PagesDimensionMapper getById(Long id) {
        PagesDimensionModel pagesDimensionModel = pagesDimensionService.getById(id);
        return pagesDimensionModel == null ? null : _toMapper(pagesDimensionModel);
    }

    @Override
    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return pagesDimensionService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    @Override
    public List<PagesDimensionMapper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize) {
        List<PagesDimensionModel> pagesDimensionModels = pagesDimensionService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, from, pageSize);
        List<PagesDimensionMapper> pagesDimensionMappers = new ArrayList<>();
        if (pagesDimensionModels != null) {
            for (PagesDimensionModel pagesDimensionModel : pagesDimensionModels) {
                pagesDimensionMappers.add(_toMapper(pagesDimensionModel));
            }
        }
        return pagesDimensionMappers;
    }

    @Override
    public PagesDimensionMapper add(PagesDimensionMapper pagesDimensionMapper) {
        PagesDimensionModel pagesDimensionModel = pagesDimensionService.add(_toModel(pagesDimensionMapper));
        return _toMapper(pagesDimensionModel);
    }

    @Override
    public void update(PagesDimensionMapper pagesDimensionMapper) {
        PagesDimensionModel pagesDimensionModel = pagesDimensionService.getById(pagesDimensionMapper.getId());
        Assert.notNull(pagesDimensionModel, "dimension.not.found");
        pagesDimensionModel.setName(pagesDimensionMapper.getName());
        pagesDimensionModel.setUrl(pagesDimensionMapper.getUrl());
        pagesDimensionModel.setEnable(pagesDimensionMapper.getEnable());
        pagesDimensionModel.setEndTime(pagesDimensionMapper.getEndTime());
        pagesDimensionModel.setBeginTime(pagesDimensionMapper.getBeginTime());
        pagesDimensionService.update(pagesDimensionModel);
    }

    private PagesDimensionModel _toModel(PagesDimensionMapper pagesDimensionMapper) {
        PagesDimensionModel pagesDimensionModel = new PagesDimensionModel();
        pagesDimensionModel.setVersion(0);
        pagesDimensionModel.setEnable(true);
        pagesDimensionModel.setCreateTime(new Date());
        pagesDimensionModel.setName(pagesDimensionMapper.getName());
        pagesDimensionModel.setUrl(pagesDimensionMapper.getUrl());
        pagesDimensionModel.setBeginTime(pagesDimensionMapper.getBeginTime());
        pagesDimensionModel.setEndTime(pagesDimensionMapper.getEndTime());
        return pagesDimensionModel;
    }

    private PagesDimensionMapper _toMapper(PagesDimensionModel pagesDimensionModel) {
        return new PagesDimensionMapper(pagesDimensionModel.getId(), pagesDimensionModel.getVersion(), pagesDimensionModel.getCreateTime(), pagesDimensionModel.getName(), pagesDimensionModel.getUrl(), pagesDimensionModel.getEnable(), pagesDimensionModel.getBeginTime(), pagesDimensionModel.getEndTime());
    }
}
