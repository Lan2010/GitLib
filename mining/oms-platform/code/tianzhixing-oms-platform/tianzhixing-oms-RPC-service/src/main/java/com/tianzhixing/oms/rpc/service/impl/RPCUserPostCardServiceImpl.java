package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserPostCardInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserPostCardInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserPostCardService;
import com.tianzhixing.oms.scylladb.UserPostCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserPostCardService")
public class RPCUserPostCardServiceImpl implements RPCUserPostCardService {

    @Autowired
    private UserPostCardRepository userPostCardRepository;

    @Override
    public void insert(UserPostCardInfoMapper userPostCardInfoMapper) {
        UserPostCardInfoModel model = new UserPostCardInfoModel();
        BeanUtils.copyProperties(userPostCardInfoMapper, model);
        userPostCardRepository.insert(model);
    }

    @Override
    public List<UserPostCardInfoMapper> list(Date beginTime, Date endTime) {
        List<UserPostCardInfoModel> userPostCardInfoModels = userPostCardRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserPostCardInfoMapper> list = new ArrayList<>();
        if (userPostCardInfoModels != null) {
            for (UserPostCardInfoModel userPostCardInfoModel : userPostCardInfoModels) {
                list.add(_toMapper(userPostCardInfoModel));
            }
        }
        return list;
    }

    private UserPostCardInfoMapper _toMapper(UserPostCardInfoModel userPostCardInfoModel) {
        return new UserPostCardInfoMapper(userPostCardInfoModel.getId(), userPostCardInfoModel.getCreateTime(), userPostCardInfoModel.getPlatformFrom(), userPostCardInfoModel.getClientPlatformType(), userPostCardInfoModel.getMobile(), userPostCardInfoModel.getPostCardId(), userPostCardInfoModel.getPostCardInfo(), userPostCardInfoModel.getPostCardLink(), userPostCardInfoModel.getNickName(), userPostCardInfoModel.getWxID(), userPostCardInfoModel.getOperationTime(), userPostCardInfoModel.getOperationType(), userPostCardInfoModel.getShareFromPlatform(), userPostCardInfoModel.getShareToPlatform());
    }
}
