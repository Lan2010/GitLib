package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.ApplicationOperationInfoModel;
import com.tianzhixing.oms.rpc.mapper.ApplicationOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCApplicationOperationService;
import com.tianzhixing.oms.scylladb.ApplicationOperationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCApplicationOperationService")
public class RPCApplicationOperationServiceImpl implements RPCApplicationOperationService {

    @Autowired
    private ApplicationOperationRepository applicationOperationRepository;

    @Override
    public void insert(ApplicationOperationInfoMapper applicationOperationInfoMapper) {
        ApplicationOperationInfoModel applicationOperationInfoModel = new ApplicationOperationInfoModel();
        BeanUtils.copyProperties(applicationOperationInfoMapper, applicationOperationInfoModel);
        applicationOperationRepository.insert(applicationOperationInfoModel);
    }

    @Override
    public List<ApplicationOperationInfoMapper> list(Date beginTime, Date endTime) {
        List<ApplicationOperationInfoModel> applicationOperationInfoModels = applicationOperationRepository.list(beginTime.getTime(), endTime.getTime());
        List<ApplicationOperationInfoMapper> list = new ArrayList<>();
        if (applicationOperationInfoModels != null) {
            for (ApplicationOperationInfoModel applicationOperationInfoModel : applicationOperationInfoModels) {
                list.add(_toMapper(applicationOperationInfoModel));
            }
        }
        return list;
    }

    private ApplicationOperationInfoMapper _toMapper(ApplicationOperationInfoModel applicationOperationInfoModel) {
        return new ApplicationOperationInfoMapper(applicationOperationInfoModel.getId(), applicationOperationInfoModel.getCreateTime(), applicationOperationInfoModel.getPlatformFrom(), applicationOperationInfoModel.getClientPlatformType(), applicationOperationInfoModel.getAppOperationTime(), applicationOperationInfoModel.getMobile(), applicationOperationInfoModel.getWxID(), applicationOperationInfoModel.getIp(), applicationOperationInfoModel.getOperationType());
    }
}
