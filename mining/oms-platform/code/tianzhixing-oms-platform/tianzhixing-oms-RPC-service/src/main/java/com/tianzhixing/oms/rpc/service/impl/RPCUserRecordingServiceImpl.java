package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserLoginStatusInfoModel;
import com.tianzhixing.oms.model.UserRecordingInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserLoginStatusInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserRecordingInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserRecordingService;
import com.tianzhixing.oms.scylladb.UserRecordingRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserRecordingService")
public class RPCUserRecordingServiceImpl implements RPCUserRecordingService {

    @Autowired
    private UserRecordingRepository userRecordingRepository;

    @Override
    public void insert(UserRecordingInfoMapper userRecordingInfoMapper) {
        UserRecordingInfoModel model = new UserRecordingInfoModel();
        BeanUtils.copyProperties(userRecordingInfoMapper, model);
        userRecordingRepository.insert(model);
    }

    @Override
    public List<UserRecordingInfoMapper> list(Date beginTime, Date endTime) {
        List<UserRecordingInfoModel> userRecordingInfoModels = userRecordingRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserRecordingInfoMapper> list = new ArrayList<>();
        if (userRecordingInfoModels != null) {
            for (UserRecordingInfoModel userRecordingInfoModel : userRecordingInfoModels) {
                list.add(_toMapper(userRecordingInfoModel));
            }
        }
        return list;
    }

    private UserRecordingInfoMapper _toMapper(UserRecordingInfoModel userRecordingInfoModel) {
        return new UserRecordingInfoMapper(userRecordingInfoModel.getId(), userRecordingInfoModel.getCreateTime(), userRecordingInfoModel.getPlatformFrom(), userRecordingInfoModel.getClientPlatformType(), userRecordingInfoModel.getMobile(), userRecordingInfoModel.getVoiceId(), userRecordingInfoModel.getVoiceLink(), userRecordingInfoModel.getNickName(), userRecordingInfoModel.getWxID(), userRecordingInfoModel.getRecordingTime());
    }
}
