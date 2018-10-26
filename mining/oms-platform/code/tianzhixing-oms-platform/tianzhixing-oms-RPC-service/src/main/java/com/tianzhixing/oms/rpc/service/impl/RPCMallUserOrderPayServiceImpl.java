package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.MallUserOrderPayInfoModel;
import com.tianzhixing.oms.rpc.mapper.MallUserOrderPayInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCMallUserOrderPayService;
import com.tianzhixing.oms.scylladb.MallUserOrderPayRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCMallUserOrderPayService")
public class RPCMallUserOrderPayServiceImpl implements RPCMallUserOrderPayService {

    @Autowired
    private MallUserOrderPayRepository mallUserOrderPayRepository;

    @Override
    public void insert(MallUserOrderPayInfoMapper mallUserOrderPayInfoMapper) {
        MallUserOrderPayInfoModel model = new MallUserOrderPayInfoModel();
        BeanUtils.copyProperties(mallUserOrderPayInfoMapper, model);
        mallUserOrderPayRepository.insert(model);
    }

    @Override
    public List<MallUserOrderPayInfoMapper> list(Date beginTime, Date endTime) {
        List<MallUserOrderPayInfoModel> mallUserOrderPayInfoModels = mallUserOrderPayRepository.list(beginTime.getTime(), endTime.getTime());
        List<MallUserOrderPayInfoMapper> list = new ArrayList<>();
        if (mallUserOrderPayInfoModels != null) {
            for (MallUserOrderPayInfoModel mallUserOrderPayInfoModel : mallUserOrderPayInfoModels) {
                list.add(_toMapper(mallUserOrderPayInfoModel));
            }
        }
        return list;

    }

    private MallUserOrderPayInfoMapper _toMapper(MallUserOrderPayInfoModel mallUserOrderPayInfoModel) {
        return new MallUserOrderPayInfoMapper(mallUserOrderPayInfoModel.getId(), mallUserOrderPayInfoModel.getCreateTime(), mallUserOrderPayInfoModel.getPlatformFrom(), mallUserOrderPayInfoModel.getClientPlatformType(), mallUserOrderPayInfoModel.getMobile(), mallUserOrderPayInfoModel.getOrderNum(), mallUserOrderPayInfoModel.getOrderStatus(), mallUserOrderPayInfoModel.getOrderAmount(), mallUserOrderPayInfoModel.getProductInfo(), mallUserOrderPayInfoModel.getOperationTime(), mallUserOrderPayInfoModel.getWxID(), mallUserOrderPayInfoModel.getQqID(), mallUserOrderPayInfoModel.getSinaWeiBoID(), mallUserOrderPayInfoModel.getIp());
    }
}
