package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.UserTaskStatusInfoModel;
import com.tianzhixing.oms.rpc.mapper.UserTaskStatusInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCUserTaskStatusService;
import com.tianzhixing.oms.scylladb.UserTaskStatusRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCUserTaskStatusService")
public class RPCUserTaskStatusServiceImpl implements RPCUserTaskStatusService {

    @Autowired
    private UserTaskStatusRepository userTaskStatusRepository;

    @Override
    public void insert(UserTaskStatusInfoMapper userTaskStatusInfoMapper) {
        UserTaskStatusInfoModel model = new UserTaskStatusInfoModel();
        BeanUtils.copyProperties(userTaskStatusInfoMapper, model);
        userTaskStatusRepository.insert(model);
    }

    @Override
    public List<UserTaskStatusInfoMapper> list(Date beginTime, Date endTime) {
        List<UserTaskStatusInfoModel> userTaskStatusInfoModels = userTaskStatusRepository.list(beginTime.getTime(), endTime.getTime());
        List<UserTaskStatusInfoMapper> list = new ArrayList<>();
        if (userTaskStatusInfoModels != null) {
            for (UserTaskStatusInfoModel userTaskStatusInfoModel : userTaskStatusInfoModels) {
                list.add(_toMapper(userTaskStatusInfoModel));
            }
        }
        return list;

    }

    private UserTaskStatusInfoMapper _toMapper(UserTaskStatusInfoModel userTaskStatusInfoModel) {
        return new UserTaskStatusInfoMapper(userTaskStatusInfoModel.getId(), userTaskStatusInfoModel.getCreateTime(), userTaskStatusInfoModel.getPlatformFrom(), userTaskStatusInfoModel.getClientPlatformType(), userTaskStatusInfoModel.getMobile(), userTaskStatusInfoModel.getOperationTime(), userTaskStatusInfoModel.getOperationType(), userTaskStatusInfoModel.getTaskId(), userTaskStatusInfoModel.getTaskName(), userTaskStatusInfoModel.getTaskInfo());
    }
}
