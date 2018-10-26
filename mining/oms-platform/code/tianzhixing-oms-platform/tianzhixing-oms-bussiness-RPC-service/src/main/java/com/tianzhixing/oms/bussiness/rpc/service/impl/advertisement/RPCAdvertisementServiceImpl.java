package com.tianzhixing.oms.bussiness.rpc.service.impl.advertisement;

import com.tianzhixing.bussiness.commons.em.AdvertisementStatus;
import com.tianzhixing.oms.bussiness.model.advertisement.AdvertisementInfoModel;
import com.tianzhixing.oms.bussiness.redis.service.RedisAdvertisementServcie;
import com.tianzhixing.oms.bussiness.rpc.mapper.advertisement.AdvertisementMapper;
import com.tianzhixing.oms.bussiness.rpc.service.advertisement.RPCAdvertisementService;
import com.tianzhixing.oms.bussiness.service.AdvertisementService;
import com.tianzhixing.oms.utils.CalculateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/6/24.
 */
@Service("RPCAdvertisementService")
public class RPCAdvertisementServiceImpl implements RPCAdvertisementService {

    @Autowired
    private AdvertisementService advertisementService;

    @Autowired
    private RedisAdvertisementServcie redisAdvertisementServcie;

    @Override
    public AdvertisementMapper getById(Long id) {
        AdvertisementInfoModel advertisementInfoModel = advertisementService.getById(id);
        return advertisementInfoModel == null ? null : _toMapper(advertisementInfoModel);
    }

    @Override
    public long count(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime) {
        return advertisementService.count(sBeginTime, sEndTime, aBeginTime, aEndTime);
    }

    @Override
    public List<AdvertisementMapper> list(Date sBeginTime, Date sEndTime, Date aBeginTime, Date aEndTime, int from, int pageSize) {
        List<AdvertisementInfoModel> advertisementInfoModels = advertisementService.list(sBeginTime, sEndTime, aBeginTime, aEndTime, from, pageSize);
        List<AdvertisementMapper> advertisementMappers = new ArrayList<>();
        if (advertisementInfoModels != null) {
            for (AdvertisementInfoModel advertisementInfoModel : advertisementInfoModels) {
                advertisementMappers.add(_toMapper(advertisementInfoModel));
            }
        }
        return advertisementMappers;
    }

    @Override
    public AdvertisementMapper add(AdvertisementMapper advertisementMapper) {
        AdvertisementInfoModel advertisementInfoModel = advertisementService.add(_toModel(advertisementMapper));
        //将广告信息缓存至数据库
        _cacheToRedis(advertisementInfoModel.getId());
        return _toMapper(advertisementInfoModel);
    }

    @Override
    public void update(AdvertisementMapper advertisementMapper) {
        AdvertisementInfoModel advertisementInfoModel = advertisementService.getById(advertisementMapper.getId());
        Assert.notNull(advertisementInfoModel, "advertisement.not.found");
        advertisementInfoModel.setUpdateTime(new Date());
        advertisementInfoModel.setUpdateUser(advertisementMapper.getUpdateUser());
        advertisementInfoModel.setAdvertName(advertisementMapper.getAdvertName());
        advertisementInfoModel.setCity(advertisementMapper.getCity());
        advertisementInfoModel.setArea(advertisementMapper.getArea());
        advertisementInfoModel.setAdvertPic(advertisementMapper.getAdvertPic());
        advertisementInfoModel.setAdvertRemark(advertisementMapper.getAdvertRemark());
        advertisementInfoModel.setAdvertIcon(advertisementMapper.getAdvertIcon());
        advertisementInfoModel.setBeginTime(advertisementMapper.getBeginTime());
        advertisementInfoModel.setEndTime(advertisementMapper.getEndTime());
        advertisementInfoModel.setAdvertisementType(advertisementMapper.getAdvertisementType());
        advertisementInfoModel.setOnceAccessStarPoint(CalculateUtil.div(advertisementMapper.getTotalAccessStarPoint(), advertisementMapper.getTotalCount(), 4));
        advertisementInfoModel.setOnceClickStarPoint(CalculateUtil.div(advertisementMapper.getTotalClickStarPoint(), advertisementMapper.getTotalCount(), 4));
        advertisementInfoModel.setTotalAccessStarPoint(advertisementMapper.getTotalAccessStarPoint());
        advertisementInfoModel.setTotalClickStarPoint(advertisementMapper.getTotalClickStarPoint());
        advertisementInfoModel.setTotalCount(advertisementMapper.getTotalCount());
        advertisementInfoModel.setAdvertisementLink(advertisementMapper.getAdvertisementLink());
        advertisementInfoModel.setAdvertisementDescribe(advertisementMapper.getAdvertisementDescribe());
        advertisementInfoModel.setAdvertisementAttribute(advertisementMapper.getAdvertisementAttribute());
        advertisementService.update(advertisementInfoModel);
        //将广告信息缓存至数据库
        _cacheToRedis(advertisementInfoModel.getId());
    }

    @Override
    public void updateIsSend(boolean isSend, Long id) {
        AdvertisementInfoModel advertisementInfoModel = advertisementService.getById(id);
        Assert.notNull(advertisementInfoModel, "advertisement.not.found");
        advertisementService.updateIsSend(isSend, advertisementInfoModel);
    }

    private AdvertisementInfoModel _toModel(AdvertisementMapper advertisementMapper) {
        AdvertisementInfoModel advertisementInfoModel = new AdvertisementInfoModel();
        advertisementInfoModel.setVersion(0);
        advertisementInfoModel.setCreateTime(new Date());
        advertisementInfoModel.setUpdateTime(new Date());
        advertisementInfoModel.setCreateUser(advertisementMapper.getCreateUser());
        advertisementInfoModel.setUpdateUser(advertisementMapper.getUpdateUser());
        advertisementInfoModel.setAdvertName(advertisementMapper.getAdvertName());
        advertisementInfoModel.setCity(advertisementMapper.getCity());
        advertisementInfoModel.setArea(advertisementMapper.getArea());
        advertisementInfoModel.setAdvertRemark(advertisementMapper.getAdvertRemark());
        advertisementInfoModel.setAdvertIcon(advertisementMapper.getAdvertIcon());
        advertisementInfoModel.setAdvertPic(advertisementMapper.getAdvertPic());
        advertisementInfoModel.setBeginTime(advertisementMapper.getBeginTime());
        advertisementInfoModel.setEndTime(advertisementMapper.getEndTime());
        advertisementInfoModel.setAdvertStatus(AdvertisementStatus.ENABLE);
        advertisementInfoModel.setAdvertisementType(advertisementMapper.getAdvertisementType());
        advertisementInfoModel.setTotalCount(advertisementMapper.getTotalCount());
        advertisementInfoModel.setOnceAccessStarPoint(CalculateUtil.div(advertisementMapper.getTotalAccessStarPoint(), advertisementMapper.getTotalCount(), 4));
        advertisementInfoModel.setOnceClickStarPoint(CalculateUtil.div(advertisementMapper.getTotalClickStarPoint(), advertisementMapper.getTotalCount(), 4));
        advertisementInfoModel.setTotalAccessStarPoint(advertisementMapper.getTotalAccessStarPoint());
        advertisementInfoModel.setTotalClickStarPoint(advertisementMapper.getTotalClickStarPoint());
        advertisementInfoModel.setIsSend(false);
        advertisementInfoModel.setAdvertisementDescribe(advertisementMapper.getAdvertisementDescribe());
        advertisementInfoModel.setAdvertisementLink(advertisementMapper.getAdvertisementLink());
        advertisementInfoModel.setAdvertisementAttribute(advertisementMapper.getAdvertisementAttribute());
        return advertisementInfoModel;
    }

    private void _cacheToRedis(long id) {
        //更新缓存
        redisAdvertisementServcie.cache(advertisementService.getById(id));
    }


    private AdvertisementMapper _toMapper(AdvertisementInfoModel advertisementInfoModel) {
        return new AdvertisementMapper(advertisementInfoModel.getId(), advertisementInfoModel.getCreateTime(), advertisementInfoModel.getUpdateTime(), advertisementInfoModel.getCreateUser(), advertisementInfoModel.getUpdateUser(), advertisementInfoModel.getAdvertName(), advertisementInfoModel.getCity(), advertisementInfoModel.getArea(), advertisementInfoModel.getAdvertRemark(), advertisementInfoModel.getAdvertIcon(), advertisementInfoModel.getAdvertPic(), advertisementInfoModel.getBeginTime(), advertisementInfoModel.getEndTime(), advertisementInfoModel.getAdvertStatus(), advertisementInfoModel.getAdvertisementType(), advertisementInfoModel.getTotalCount(), advertisementInfoModel.getTotalAccessStarPoint(), advertisementInfoModel.getOnceAccessStarPoint(), advertisementInfoModel.getIsSend(), advertisementInfoModel.getAdvertisementLink(), advertisementInfoModel.getAdvertisementDescribe(), advertisementInfoModel.getAdvertisementAttribute(), advertisementInfoModel.getOnceClickStarPoint(), advertisementInfoModel.getTotalClickStarPoint());
    }
}
