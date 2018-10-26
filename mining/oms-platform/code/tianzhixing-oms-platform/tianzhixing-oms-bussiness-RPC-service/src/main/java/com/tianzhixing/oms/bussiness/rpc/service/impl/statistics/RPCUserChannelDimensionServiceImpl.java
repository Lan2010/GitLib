package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.UserChannelDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.UserChannelDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCUserChannelDimensionService;
import com.tianzhixing.oms.bussiness.service.UserChannelDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/10.
 */
@Service("RPCUserChannelDimensionService")
public class RPCUserChannelDimensionServiceImpl implements RPCUserChannelDimensionService {

    @Autowired
    private UserChannelDimensionService userChannelDimensionService;

    @Override
    public List<UserChannelDimensionMapper> list(boolean enable) {
        List<UserChannelDimensionModel> userChannelDimensionModels = userChannelDimensionService.list(enable);
        List<UserChannelDimensionMapper> userChannelDimensionMappers = new ArrayList<>();
        if (userChannelDimensionModels != null) {
            for (UserChannelDimensionModel userChannelDimensionModel : userChannelDimensionModels) {
                userChannelDimensionMappers.add(_toMapper(userChannelDimensionModel));
            }
        }
        return userChannelDimensionMappers;
    }
    
    @Override
    public UserChannelDimensionMapper getById(Long id) {
    	UserChannelDimensionModel userChannelDimensionModel = userChannelDimensionService.getById(id);
        return userChannelDimensionModel == null ? null : _toMapper(userChannelDimensionModel);
    }

    @Override
    public long count() {
        return userChannelDimensionService.count();
    }

    @Override
    public List<UserChannelDimensionMapper> list(int from, int pageSize) {
        List<UserChannelDimensionModel> userChannelDimensionModels = userChannelDimensionService.list(from, pageSize);
        List<UserChannelDimensionMapper> userChannelDimensionMapper = new ArrayList<>();
        if (userChannelDimensionModels != null) {
            for (UserChannelDimensionModel userChannelDimensionModel : userChannelDimensionModels) {
            	userChannelDimensionMapper.add(_toMapper(userChannelDimensionModel));
            }
        }
        return userChannelDimensionMapper;
    }

    @Override
    public UserChannelDimensionMapper add(UserChannelDimensionMapper userChannelDimensionMapper) {
    	UserChannelDimensionModel userChannelDimensionModel = userChannelDimensionService.add(_toModel(userChannelDimensionMapper));
        return _toMapper(userChannelDimensionModel);
    }

    @Override
    public void update(UserChannelDimensionMapper userChannelDimensionMapper) {
    	UserChannelDimensionModel userChannelDimensionModel = userChannelDimensionService.getById(userChannelDimensionMapper.getId());
        Assert.notNull(userChannelDimensionModel, "dimensionModel.not.found");
        userChannelDimensionModel.setName(userChannelDimensionMapper.getName());
        userChannelDimensionModel.setValue(userChannelDimensionMapper.getValue());
        userChannelDimensionModel.setEnable(userChannelDimensionMapper.getEnable());
        userChannelDimensionService.update(userChannelDimensionModel);
    }

    private UserChannelDimensionModel _toModel(UserChannelDimensionMapper userChannelDimensionMapper) {
    	UserChannelDimensionModel userChannelDimensionModel = new UserChannelDimensionModel();
    	userChannelDimensionModel.setVersion(0);
    	userChannelDimensionModel.setCreateTime(new Date());
    	userChannelDimensionModel.setName(userChannelDimensionMapper.getName());
    	userChannelDimensionModel.setValue(userChannelDimensionMapper.getValue());
        userChannelDimensionModel.setEnable(userChannelDimensionMapper.getEnable());
        return userChannelDimensionModel;
    }

    private UserChannelDimensionMapper _toMapper(UserChannelDimensionModel userChannelDimensionModel) {
        return new UserChannelDimensionMapper(userChannelDimensionModel.getId(), userChannelDimensionModel.getVersion(), userChannelDimensionModel.getCreateTime(), userChannelDimensionModel.getName(), userChannelDimensionModel.getValue(), userChannelDimensionModel.getEnable());
    }
}
