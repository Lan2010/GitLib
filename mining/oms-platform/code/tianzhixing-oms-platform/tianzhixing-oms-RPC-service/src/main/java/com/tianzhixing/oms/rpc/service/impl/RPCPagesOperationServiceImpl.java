package com.tianzhixing.oms.rpc.service.impl;

import com.tianzhixing.oms.model.DeviceOperationInfoModel;
import com.tianzhixing.oms.model.PagesOperationInfoModel;
import com.tianzhixing.oms.rpc.mapper.DeviceOperationInfoMapper;
import com.tianzhixing.oms.rpc.mapper.PagesOperationInfoMapper;
import com.tianzhixing.oms.rpc.service.RPCPagesOperationService;
import com.tianzhixing.oms.scylladb.PagesOperationRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by routine.k on 2018/7/3.
 */
@Service("RPCPagesOperationService")
public class RPCPagesOperationServiceImpl implements RPCPagesOperationService {

    @Autowired
    private PagesOperationRepository pagesOperationRepository;

    @Override
    public void insert(PagesOperationInfoMapper pagesOperationInfoMapper) {
        PagesOperationInfoModel model = new PagesOperationInfoModel();
        BeanUtils.copyProperties(pagesOperationInfoMapper, model);
        pagesOperationRepository.insert(model);
    }

    @Override
    public List<PagesOperationInfoMapper> list(Date beginTime, Date endTime) {
        List<PagesOperationInfoModel> pagesOperationInfoModels = pagesOperationRepository.list(beginTime.getTime(), endTime.getTime());
        List<PagesOperationInfoMapper> list = new ArrayList<>();
        if (pagesOperationInfoModels != null) {
            for (PagesOperationInfoModel pagesOperationInfoModel : pagesOperationInfoModels) {
                list.add(_toMapper(pagesOperationInfoModel));
            }
        }
        return list;
    }

    private PagesOperationInfoMapper _toMapper(PagesOperationInfoModel pagesOperationInfoModel) {
        return new PagesOperationInfoMapper(pagesOperationInfoModel.getId(), pagesOperationInfoModel.getCreateTime(), pagesOperationInfoModel.getPlatformFrom(), pagesOperationInfoModel.getClientPlatformType(), pagesOperationInfoModel.getMobile(), pagesOperationInfoModel.getWxID(), pagesOperationInfoModel.getAccessTime(), pagesOperationInfoModel.getPagesURL(), pagesOperationInfoModel.getPagesInfo(), pagesOperationInfoModel.getPagesName(), pagesOperationInfoModel.getLoginStatus(), pagesOperationInfoModel.getIp());
    }
}
