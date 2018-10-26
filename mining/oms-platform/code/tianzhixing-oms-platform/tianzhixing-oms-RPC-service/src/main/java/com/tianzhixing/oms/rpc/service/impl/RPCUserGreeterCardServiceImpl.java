package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserGreeterCardInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserGreeterCardInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserGreeterCardService;
import com.tianzhixing.oms.scylladb.UserGreeterCardRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserGreeterCardService")
public class RPCUserGreeterCardServiceImpl implements RPCUserGreeterCardService {

    @Autowired
    private UserGreeterCardRepository userGreeterCardRepository;

    @Override
    public void insert(UserGreeterCardInfoMapper userGreeterCardInfoMapper) {
        UserGreeterCardInfoModel model = new UserGreeterCardInfoModel();
        BeanUtils.copyProperties(userGreeterCardInfoMapper, model);
        userGreeterCardRepository.insert(model);
    }

    @Override
    public List<UserGreeterCardInfoMapper> list(Date beginTime, Date endTime) {
        List<UserGreeterCardInfoModel> userGreeterCardInfoModels = userGreeterCardRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserGreeterCardInfoMapper> list = new ArrayList<>();
        if (userGreeterCardInfoModels != null) {
            for (UserGreeterCardInfoModel userGreeterCardInfoModel : userGreeterCardInfoModels) {
                list.add(_toMapper(userGreeterCardInfoModel));
            }
        }
        return list;
    }

    private UserGreeterCardInfoMapper _toMapper(UserGreeterCardInfoModel userGreeterCardInfoModel) {
        return new UserGreeterCardInfoMapper(userGreeterCardInfoModel.getId(), userGreeterCardInfoModel.getCreateTime(), userGreeterCardInfoModel.getPlatformFrom(), userGreeterCardInfoModel.getClientPlatformType(), userGreeterCardInfoModel.getMobile(), userGreeterCardInfoModel.getGreeterCardId(), userGreeterCardInfoModel.getGreeterCardInfo(), userGreeterCardInfoModel.getGreeterCardLink(), userGreeterCardInfoModel.getNickName(), userGreeterCardInfoModel.getWxID(), userGreeterCardInfoModel.getOperationTime(), userGreeterCardInfoModel.getOperationType(), userGreeterCardInfoModel.getShareFromPlatform(), userGreeterCardInfoModel.getShareToPlatform());
    }
}
