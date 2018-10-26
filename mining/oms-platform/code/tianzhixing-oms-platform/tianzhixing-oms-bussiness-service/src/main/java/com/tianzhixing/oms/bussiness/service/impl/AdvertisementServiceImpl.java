package com.tianzhixing.oms.bussiness.service.impl;

import com.tianzhixing.oms.bussiness.dao.advertisement.AdvertisementInfoDao;
import com.tianzhixing.oms.bussiness.dao.statistics.AdvertisementDimensionDao;
import com.tianzhixing.oms.bussiness.model.advertisement.AdvertisementInfoModel;
import com.tianzhixing.oms.bussiness.model.statistics.AdvertisementDimensionModel;
import com.tianzhixing.oms.bussiness.service.AdvertisementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service("advertisementService")
public class AdvertisementServiceImpl implements AdvertisementService {

    @Autowired
    private AdvertisementInfoDao advertisementInfoDao;

    @Autowired
    private AdvertisementDimensionDao advertisementDimensionDao;

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public AdvertisementInfoModel getById(Long id) {
        return advertisementInfoDao.get(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return advertisementInfoDao.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public List<AdvertisementInfoModel> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize) {
        return advertisementInfoDao.list(sBeginTime, sEndTime, aBeginTime, aEndTime, from, pageSize);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public AdvertisementInfoModel add(AdvertisementInfoModel advertisementInfoModel) {
        advertisementInfoModel = advertisementInfoDao.add(advertisementInfoModel);
        //添加统计维度
        AdvertisementDimensionModel advertisementDimensionModel = new AdvertisementDimensionModel();
        advertisementDimensionModel.setVersion(0);
        advertisementDimensionModel.setCreateTime(advertisementInfoModel.getCreateTime());
        advertisementDimensionModel.setEnable(true);
        advertisementDimensionModel.setAdvertisementId(advertisementInfoModel.getId().toString());
        advertisementDimensionModel.setBeginTime(advertisementInfoModel.getBeginTime());
        advertisementDimensionModel.setEndTime(advertisementInfoModel.getEndTime());
        advertisementDimensionModel.setName(advertisementInfoModel.getAdvertName());
        advertisementDimensionDao.insert(advertisementDimensionModel);
        return advertisementInfoModel;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void update(AdvertisementInfoModel advertisementInfoModel) {
        advertisementInfoDao.update(advertisementInfoModel.getId(), advertisementInfoModel.getVersion(), advertisementInfoModel);
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    public void updateIsSend(boolean isSend, AdvertisementInfoModel advertisementInfoModel) {
        advertisementInfoDao.updateIsSend(isSend, advertisementInfoModel.getId(), advertisementInfoModel.getVersion());
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED, readOnly = true)
    public Long maxId() {
        return advertisementInfoDao.maxId();
    }
}
