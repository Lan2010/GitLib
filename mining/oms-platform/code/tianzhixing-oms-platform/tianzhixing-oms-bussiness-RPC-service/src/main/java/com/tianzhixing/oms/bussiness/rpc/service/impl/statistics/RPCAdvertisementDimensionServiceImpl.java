package com.tianzhixing.oms.bussiness.rpc.service.impl.statistics;

import com.tianzhixing.oms.bussiness.model.statistics.AdvertisementDimensionModel;
import com.tianzhixing.oms.bussiness.rpc.mapper.statistics.AdvertisementDimensionMapper;
import com.tianzhixing.oms.bussiness.rpc.service.statistics.RPCAdvertisementDimensionService;
import com.tianzhixing.oms.bussiness.service.AdvertisementDimensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by routine.k on 2018/7/9.
 */
@Service("RPCAdvertisementDimensionService")
public class RPCAdvertisementDimensionServiceImpl implements RPCAdvertisementDimensionService {

    @Autowired
    private AdvertisementDimensionService advertisementDimensionService;

    @Override
    public List<AdvertisementDimensionMapper> list(boolean enable) {
        List<AdvertisementDimensionModel> advertisementDimensionModels = advertisementDimensionService.list(enable);
        List<AdvertisementDimensionMapper> advertisementDimensionMappers = new ArrayList<>();
        if (advertisementDimensionModels != null) {
            for (AdvertisementDimensionModel advertisementDimensionModel : advertisementDimensionModels) {
                advertisementDimensionMappers.add(_toMapper(advertisementDimensionModel));
            }
        }
        return advertisementDimensionMappers;
    }

    private AdvertisementDimensionMapper _toMapper(AdvertisementDimensionModel advertisementDimensionModel) {
        return new AdvertisementDimensionMapper(advertisementDimensionModel.getId(), advertisementDimensionModel.getVersion(), advertisementDimensionModel.getCreateTime(), advertisementDimensionModel.getName(), advertisementDimensionModel.getAdvertisementId(), advertisementDimensionModel.getBeginTime(), advertisementDimensionModel.getEndTime(), advertisementDimensionModel.getEnable());
    }
}
