package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserStarPointIncrementInfoModel;
import com.tianzhixing.oms.model.UserStarPointIncrementInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserStarPointIncrementInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserStarPointIncrementInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointIncrementService;
import com.tianzhixing.oms.scylladb.UserStarPointIncrementRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserStarPointIncrementService")
public class RPCUserStarPointIncrementServiceImpl implements RPCUserStarPointIncrementService {

    @Autowired
    private UserStarPointIncrementRepository userStarPointIncrementRepository;

    @Override
    public void insert(UserStarPointIncrementInfoMapper userStarPointIncrementInfoMapper) {
        UserStarPointIncrementInfoModel model = new UserStarPointIncrementInfoModel();
        BeanUtils.copyProperties(userStarPointIncrementInfoMapper, model);
        userStarPointIncrementRepository.insert(model);
    }

    @Override
    public List<UserStarPointIncrementInfoMapper> list(Date beginTime, Date endTime) {
        List<UserStarPointIncrementInfoModel> userStarPointIncrementInfoModels = userStarPointIncrementRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserStarPointIncrementInfoMapper> list = new ArrayList<>();
        if (userStarPointIncrementInfoModels != null) {
            for (UserStarPointIncrementInfoModel userStarPointIncrementInfoModel : userStarPointIncrementInfoModels) {
                list.add(_toMapper(userStarPointIncrementInfoModel));
            }
        }
        return list;

    }

    private UserStarPointIncrementInfoMapper _toMapper(UserStarPointIncrementInfoModel userStarPointIncrementInfoModel) {
        return new UserStarPointIncrementInfoMapper(userStarPointIncrementInfoModel.getId(), userStarPointIncrementInfoModel.getCreateTime(), userStarPointIncrementInfoModel.getPlatformFrom(), userStarPointIncrementInfoModel.getClientPlatformType(), userStarPointIncrementInfoModel.getMobile(), userStarPointIncrementInfoModel.getIncrementTime(), userStarPointIncrementInfoModel.getIncrementCount(), userStarPointIncrementInfoModel.getIncrementType(), userStarPointIncrementInfoModel.getThirdId(), userStarPointIncrementInfoModel.getThirdName());
    }
}
