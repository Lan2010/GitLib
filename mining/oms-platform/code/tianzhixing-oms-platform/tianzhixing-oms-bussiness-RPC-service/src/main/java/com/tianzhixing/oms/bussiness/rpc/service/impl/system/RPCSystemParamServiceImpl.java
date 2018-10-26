package com.tianzhixing.oms.bussiness.rpc.service.impl.system;

import com.tianzhixing.oms.bussiness.model.system.SystemParamModel;
import com.tianzhixing.oms.bussiness.redis.service.RedisSystemParamService;
import com.tianzhixing.oms.bussiness.rpc.mapper.system.SystemParamMapper;
import com.tianzhixing.oms.bussiness.rpc.service.system.RPCSystemParamService;
import com.tianzhixing.oms.bussiness.service.SystemParamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/6/25.
 */
@Service("RPCSystemParamService")
public class RPCSystemParamServiceImpl implements RPCSystemParamService {

    @Autowired
    private RedisSystemParamService redisSystemParamService;

    @Autowired
    private SystemParamService systemParamService;

    @Override
    public long count() {
        return systemParamService.count();
    }

    @Override
    public List<SystemParamMapper> list(int from, int pageSize) {
        List<SystemParamModel> systemParamModels = systemParamService.list(from, pageSize);
        List<SystemParamMapper> systemParamMappers = new ArrayList<>();
        if (systemParamModels == null) return systemParamMappers;
        for (SystemParamModel systemParamModel : systemParamModels) {
            systemParamMappers.add(_toMapper(systemParamModel));
        }
        return systemParamMappers;
    }

    @Override
    public SystemParamMapper getById(long id) {
        SystemParamModel systemParamModel = systemParamService.getById(id);
        return systemParamModel == null ? null : _toMapper(systemParamModel);
    }

    @Override
    public void updateValue(long id, String value) {
        SystemParamModel systemParamModel = systemParamService.getById(id);
        Assert.notNull(systemParamModel, "system.param.not.found");
        systemParamModel.setSystemValue(value);
        systemParamService.update(systemParamModel);
        //缓存至redis
        _catchToRedis(systemParamModel.getId());
    }

    private void _catchToRedis(long id) {
        redisSystemParamService.cache(systemParamService.getById(id));
    }

    private SystemParamMapper _toMapper(SystemParamModel systemParamModel) {
        return new SystemParamMapper(systemParamModel.getId(), systemParamModel.getCreateTime(), systemParamModel.getUpdateTime(), systemParamModel.getCreateUser(), systemParamModel.getUpdateUser(), systemParamModel.getSystemValue(), systemParamModel.getSystemParamType(), systemParamModel.getNeedPush());
    }
}
