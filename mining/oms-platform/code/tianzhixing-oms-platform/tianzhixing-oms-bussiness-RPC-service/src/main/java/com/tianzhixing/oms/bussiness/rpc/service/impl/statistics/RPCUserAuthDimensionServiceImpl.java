package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserAuthDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserAuthDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserAuthDimensionService;
import com.tianzhixing.oms.bussiness.service.UserAuthDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("RPCUserAuthDimensionService")
public class RPCUserAuthDimensionServiceImpl implements RPCUserAuthDimensionService {

    @Autowired
    private UserAuthDimensionService userAuthDimensionService;

    @Override
    public List<UserAuthDimensionMapper> list(boolean enable) {
        List<UserAuthDimensionModel> userAuthDimensionModels = userAuthDimensionService.list(enable);
        List<UserAuthDimensionMapper> userAuthDimensionMappers = new ArrayList<>();
        if (userAuthDimensionModels != null) {
            for (UserAuthDimensionModel userAuthDimensionModel : userAuthDimensionModels) {
                userAuthDimensionMappers.add(_toMapper(userAuthDimensionModel));
            }
        }
        return userAuthDimensionMappers;
    }
    
    @Override
    public UserAuthDimensionMapper getById(Long id) {
    	UserAuthDimensionModel userAuthDimensionModel = userAuthDimensionService.getById(id);
        return userAuthDimensionModel == null ? null : _toMapper(userAuthDimensionModel);
    }

    @Override
    public long count() {
        return userAuthDimensionService.count();
    }

    @Override
    public List<UserAuthDimensionMapper> list(int from, int pageSize) {
        List<UserAuthDimensionModel> userAuthDimensionModels = userAuthDimensionService.list(from, pageSize);
        List<UserAuthDimensionMapper> userAuthDimensionMapper = new ArrayList<>();
        if (userAuthDimensionModels != null) {
            for (UserAuthDimensionModel userAuthDimensionModel : userAuthDimensionModels) {
            	userAuthDimensionMapper.add(_toMapper(userAuthDimensionModel));
            }
        }
        return userAuthDimensionMapper;
    }

    @Override
    public UserAuthDimensionMapper add(UserAuthDimensionMapper userAuthDimensionMapper) {
    	UserAuthDimensionModel userAuthDimensionModel = userAuthDimensionService.add(_toModel(userAuthDimensionMapper));
        return _toMapper(userAuthDimensionModel);
    }

    @Override
    public void update(UserAuthDimensionMapper userAuthDimensionMapper) {
    	UserAuthDimensionModel userAuthDimensionModel = userAuthDimensionService.getById(userAuthDimensionMapper.getId());
        Assert.notNull(userAuthDimensionModel, "dimensionModel.not.found");
        userAuthDimensionModel.setName(userAuthDimensionMapper.getName());
        userAuthDimensionModel.setValue(userAuthDimensionMapper.getValue());
        userAuthDimensionModel.setEnable(userAuthDimensionMapper.getEnable());
        userAuthDimensionService.update(userAuthDimensionModel);
    }

    private UserAuthDimensionModel _toModel(UserAuthDimensionMapper userAuthDimensionMapper) {
    	UserAuthDimensionModel userAuthDimensionModel = new UserAuthDimensionModel();
    	userAuthDimensionModel.setVersion(0);
    	userAuthDimensionModel.setCreateTime(new Date());
    	userAuthDimensionModel.setName(userAuthDimensionMapper.getName());
    	userAuthDimensionModel.setValue(userAuthDimensionMapper.getValue());
        userAuthDimensionModel.setEnable(userAuthDimensionMapper.getEnable());
        return userAuthDimensionModel;
    }
    
    private UserAuthDimensionMapper _toMapper(UserAuthDimensionModel userAuthDimensionModel) {
        return new UserAuthDimensionMapper(userAuthDimensionModel.getId(), userAuthDimensionModel.getVersion(), userAuthDimensionModel.getCreateTime(), userAuthDimensionModel.getName(), userAuthDimensionModel.getValue(), userAuthDimensionModel.getEnable());
    }

}
