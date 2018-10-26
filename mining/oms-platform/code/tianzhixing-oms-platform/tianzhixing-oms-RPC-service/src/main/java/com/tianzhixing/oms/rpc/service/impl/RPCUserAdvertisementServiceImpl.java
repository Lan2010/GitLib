package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserAdvertisementInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserAdvertisementInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserAdvertisementService;
import com.tianzhixing.oms.scylladb.UserAdvertisementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserAdvertisementService")
public class RPCUserAdvertisementServiceImpl implements RPCUserAdvertisementService {

    @Autowired
    private UserAdvertisementRepository userAdvertisementRepository;

    @Override
    public void insert(UserAdvertisementInfoMapper userAdvertisementInfoMapper) {
        UserAdvertisementInfoModel model = new UserAdvertisementInfoModel();
        BeanUtils.copyProperties(userAdvertisementInfoMapper, model);
        userAdvertisementRepository.insert(model);
    }

    @Override
    public List<UserAdvertisementInfoMapper> list(Date beginTime, Date endTime) {
        List<UserAdvertisementInfoModel> userAdvertisementInfoModels = userAdvertisementRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserAdvertisementInfoMapper> list = new ArrayList<>();
        if (userAdvertisementInfoModels != null) {
            for (UserAdvertisementInfoModel userAdvertisementInfoModel : userAdvertisementInfoModels) {
                list.add(_toMapper(userAdvertisementInfoModel));
            }
        }
        return list;
    }

    private UserAdvertisementInfoMapper _toMapper(UserAdvertisementInfoModel userAdvertisementInfoModel) {
        return new UserAdvertisementInfoMapper(userAdvertisementInfoModel.getId(), userAdvertisementInfoModel.getCreateTime(), userAdvertisementInfoModel.getPlatformFrom(), userAdvertisementInfoModel.getClientPlatformType(), userAdvertisementInfoModel.getMobile(), userAdvertisementInfoModel.getAdvertId(), userAdvertisementInfoModel.getAdvertLink(), userAdvertisementInfoModel.getAdvertType(), userAdvertisementInfoModel.getAdvertName(), userAdvertisementInfoModel.getAdvertInfo(), userAdvertisementInfoModel.getOperationTime(), userAdvertisementInfoModel.getOperationType(), userAdvertisementInfoModel.getIp());
    }
}
