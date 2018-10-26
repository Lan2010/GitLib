package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserAdvertisementInfoModel;
import com.tianzhixing.oms.model.UserBasicInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserAdvertisementInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserBasicInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserBasicInfoService;
import com.tianzhixing.oms.scylladb.UserBasicInfoRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserBasicInfoService")
public class RPCUserBasicInfoServiceImpl implements RPCUserBasicInfoService {

    @Autowired
    private UserBasicInfoRepository userBasicInfoRepository;

    @Override
    public void insert(UserBasicInfoMapper userBasicInfoMapper) {
        UserBasicInfoModel model = new UserBasicInfoModel();
        BeanUtils.copyProperties(userBasicInfoMapper, model);
        userBasicInfoRepository.insert(model);
    }

    @Override
    public List<UserBasicInfoMapper> list(Date beginTime, Date endTime) {
        List<UserBasicInfoModel> userBasicInfoModels = userBasicInfoRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserBasicInfoMapper> list = new ArrayList<>();
        if (userBasicInfoModels != null) {
            for (UserBasicInfoModel userBasicInfoModel : userBasicInfoModels) {
                list.add(_toMapper(userBasicInfoModel));
            }
        }
        return list;
    }

    private UserBasicInfoMapper _toMapper(UserBasicInfoModel userBasicInfoModel) {
        return new UserBasicInfoMapper(userBasicInfoModel.getId(), userBasicInfoModel.getCreateTime(), userBasicInfoModel.getPlatformFrom(), userBasicInfoModel.getClientPlatformType(), userBasicInfoModel.getMobile(), userBasicInfoModel.getEmail(), userBasicInfoModel.getRealName(), userBasicInfoModel.getIdCard(), userBasicInfoModel.getNickName(), userBasicInfoModel.getUserFromType(), userBasicInfoModel.getAvatar(), userBasicInfoModel.getUserOperType(), userBasicInfoModel.getWxID(), userBasicInfoModel.getQqID(), userBasicInfoModel.getSinaWeiBoID(), userBasicInfoModel.getRegTime(), userBasicInfoModel.getIp());
    }
}
