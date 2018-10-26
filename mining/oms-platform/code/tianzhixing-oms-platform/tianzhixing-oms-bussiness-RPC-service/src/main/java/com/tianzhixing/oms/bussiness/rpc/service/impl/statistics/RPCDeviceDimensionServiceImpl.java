package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.DeviceDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.DeviceDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCDeviceDimensionService;
import com.tianzhixing.oms.bussiness.service.DeviceDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/7.
 */
@Service("RPCDeviceDimensionService")
public class RPCDeviceDimensionServiceImpl implements RPCDeviceDimensionService {

    @Autowired
    private DeviceDimensionService deviceDimensionService;

    @Override
    public List<DeviceDimensionMapper> list(boolean enable) {
        List<DeviceDimensionModel> deviceDimensionModels = deviceDimensionService.list(enable);
        List<DeviceDimensionMapper> deviceDimensionMappers = new ArrayList<>();
        if (deviceDimensionModels != null) {
            for (DeviceDimensionModel deviceDimensionModel : deviceDimensionModels) {
                deviceDimensionMappers.add(_toMapper(deviceDimensionModel));
            }
        }
        return deviceDimensionMappers;
    }

    @Override
    public DeviceDimensionMapper getById(Long id) {
    	DeviceDimensionModel DeviceDimensionModel = deviceDimensionService.getById(id);
        return DeviceDimensionModel == null ? null : _toMapper(DeviceDimensionModel);
    }

    @Override
    public long count() {
        return deviceDimensionService.count();
    }

    @Override
    public List<DeviceDimensionMapper> list(int from, int pageSize) {
        List<DeviceDimensionModel> DeviceDimensionModels = deviceDimensionService.list(from, pageSize);
        List<DeviceDimensionMapper> deviceDimensionMapper = new ArrayList<>();
        if (DeviceDimensionModels != null) {
            for (DeviceDimensionModel DeviceDimensionModel : DeviceDimensionModels) {
            	deviceDimensionMapper.add(_toMapper(DeviceDimensionModel));
            }
        }
        return deviceDimensionMapper;
    }

    @Override
    public DeviceDimensionMapper add(DeviceDimensionMapper deviceDimensionMapper) {
    	DeviceDimensionModel DeviceDimensionModel = deviceDimensionService.add(_toModel(deviceDimensionMapper));
        return _toMapper(DeviceDimensionModel);
    }

    @Override
    public void update(DeviceDimensionMapper deviceDimensionMapper) {
    	DeviceDimensionModel DeviceDimensionModel = deviceDimensionService.getById(deviceDimensionMapper.getId());
        Assert.notNull(DeviceDimensionModel, "dimensionModel.not.found");
        DeviceDimensionModel.setName(deviceDimensionMapper.getName());
        DeviceDimensionModel.setValue(deviceDimensionMapper.getValue());
        DeviceDimensionModel.setEnable(deviceDimensionMapper.getEnable());
        deviceDimensionService.update(DeviceDimensionModel);
    }

    private DeviceDimensionModel _toModel(DeviceDimensionMapper deviceDimensionMapper) {
    	DeviceDimensionModel DeviceDimensionModel = new DeviceDimensionModel();
    	DeviceDimensionModel.setVersion(0);
    	DeviceDimensionModel.setCreateTime(new Date());
    	DeviceDimensionModel.setName(deviceDimensionMapper.getName());
    	DeviceDimensionModel.setValue(deviceDimensionMapper.getValue());
        DeviceDimensionModel.setEnable(deviceDimensionMapper.getEnable());
        return DeviceDimensionModel;
    }
    
    private DeviceDimensionMapper _toMapper(DeviceDimensionModel deviceDimensionModel) {
        return new DeviceDimensionMapper(deviceDimensionModel.getId(), deviceDimensionModel.getVersion(), deviceDimensionModel.getCreateTime(), deviceDimensionModel.getName(), deviceDimensionModel.getValue(), deviceDimensionModel.getEnable());
    }
}
