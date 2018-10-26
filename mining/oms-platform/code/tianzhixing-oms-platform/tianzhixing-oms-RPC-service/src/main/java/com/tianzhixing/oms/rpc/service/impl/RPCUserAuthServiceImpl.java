package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserAuthInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserAuthInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserAuthService;
import com.tianzhixing.oms.scylladb.UserAuthRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserAuthService")
public class RPCUserAuthServiceImpl implements RPCUserAuthService {

    @Autowired
    private UserAuthRepository userAuthRepository;

    @Override
    public void insert(UserAuthInfoMapper userAuthInfoMapper) {
        UserAuthInfoModel model = new UserAuthInfoModel();
        BeanUtils.copyProperties(userAuthInfoMapper, model);
        userAuthRepository.insert(model);
    }

    @Override
    public List<UserAuthInfoMapper> list(Date beginTime, Date endTime) {
        List<UserAuthInfoModel> userAuthInfoModels = userAuthRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserAuthInfoMapper> list = new ArrayList<>();
        if (userAuthInfoModels != null) {
            for (UserAuthInfoModel userAuthInfoModel : userAuthInfoModels) {
                list.add(_toMapper(userAuthInfoModel));
            }
        }
        return list;
    }

    private UserAuthInfoMapper _toMapper(UserAuthInfoModel userAuthInfoModel) {
        return new UserAuthInfoMapper(userAuthInfoModel.getId(), userAuthInfoModel.getCreateTime(), userAuthInfoModel.getPlatformFrom(), userAuthInfoModel.getClientPlatformType(), userAuthInfoModel.getAuthType(), userAuthInfoModel.getAuthStatus(), userAuthInfoModel.getAuthTime());
    }
}
