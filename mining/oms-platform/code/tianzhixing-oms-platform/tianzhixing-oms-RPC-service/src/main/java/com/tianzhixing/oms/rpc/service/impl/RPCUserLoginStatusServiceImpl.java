package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserLoginStatusInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserLoginStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserLoginStatusService;
import com.tianzhixing.oms.scylladb.UserLoginStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserLoginStatusService")
public class RPCUserLoginStatusServiceImpl implements RPCUserLoginStatusService {

    @Autowired
    private UserLoginStatusRepository userLoginStatusRepository;

    @Override
    public void insert(UserLoginStatusInfoMapper userLoginStatusInfoMapper) {
        UserLoginStatusInfoModel model = new UserLoginStatusInfoModel();
        BeanUtils.copyProperties(userLoginStatusInfoMapper, model);
        userLoginStatusRepository.insert(model);
    }

    @Override
    public List<UserLoginStatusInfoMapper> list(Date beginTime, Date endTime) {
        List<UserLoginStatusInfoModel> userLoginStatusInfoModels = userLoginStatusRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserLoginStatusInfoMapper> list = new ArrayList<>();
        if (userLoginStatusInfoModels != null) {
            for (UserLoginStatusInfoModel userLoginStatusInfoModel : userLoginStatusInfoModels) {
                list.add(_toMapper(userLoginStatusInfoModel));
            }
        }
        return list;
    }

    private UserLoginStatusInfoMapper _toMapper(UserLoginStatusInfoModel userLoginStatusInfoModel) {
        return new UserLoginStatusInfoMapper(userLoginStatusInfoModel.getId(), userLoginStatusInfoModel.getCreateTime(), userLoginStatusInfoModel.getPlatformFrom(), userLoginStatusInfoModel.getClientPlatformType(), userLoginStatusInfoModel.getMobile(), userLoginStatusInfoModel.getOperationTime(), userLoginStatusInfoModel.getOperationType(), userLoginStatusInfoModel.getWxID(), userLoginStatusInfoModel.getQqID(), userLoginStatusInfoModel.getSinaWeiBoID(), userLoginStatusInfoModel.getIp());
    }
}
