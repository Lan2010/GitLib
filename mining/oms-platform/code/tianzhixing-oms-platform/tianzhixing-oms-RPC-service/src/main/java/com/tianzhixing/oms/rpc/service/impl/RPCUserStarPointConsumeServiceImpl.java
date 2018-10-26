package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserStarPointConsumeInfoModel;
import com.tianzhixing.oms.model.UserStarPointConsumeInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserStarPointConsumeInfoMapper;
import com.tianzhixing.oms.rpc.mapper.UserStarPointConsumeInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserStarPointConsumeService;
import com.tianzhixing.oms.scylladb.UserStarPointConsumeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserStarPointConsumeService")
public class RPCUserStarPointConsumeServiceImpl implements RPCUserStarPointConsumeService {

    @Autowired
    private UserStarPointConsumeRepository userStarPointConsumeRepository;

    @Override
    public void insert(UserStarPointConsumeInfoMapper userStarPointConsumeInfoMapper) {
        UserStarPointConsumeInfoModel model = new UserStarPointConsumeInfoModel();
        BeanUtils.copyProperties(userStarPointConsumeInfoMapper, model);
        userStarPointConsumeRepository.insert(model);
    }

    @Override
    public List<UserStarPointConsumeInfoMapper> list(Date beginTime, Date endTime) {
        List<UserStarPointConsumeInfoModel> userStarPointConsumeInfoModels = userStarPointConsumeRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserStarPointConsumeInfoMapper> list = new ArrayList<>();
        if (userStarPointConsumeInfoModels != null) {
            for (UserStarPointConsumeInfoModel userStarPointConsumeInfoModel : userStarPointConsumeInfoModels) {
                list.add(_toMapper(userStarPointConsumeInfoModel));
            }
        }
        return list;

    }

    private UserStarPointConsumeInfoMapper _toMapper(UserStarPointConsumeInfoModel userStarPointConsumeInfoModel) {
        return new UserStarPointConsumeInfoMapper(userStarPointConsumeInfoModel.getId(), userStarPointConsumeInfoModel.getCreateTime(), userStarPointConsumeInfoModel.getPlatformFrom(), userStarPointConsumeInfoModel.getClientPlatformType(), userStarPointConsumeInfoModel.getMobile(), userStarPointConsumeInfoModel.getConsumeTime(), userStarPointConsumeInfoModel.getConsumeCount(), userStarPointConsumeInfoModel.getConsumeCause());
    }
}
