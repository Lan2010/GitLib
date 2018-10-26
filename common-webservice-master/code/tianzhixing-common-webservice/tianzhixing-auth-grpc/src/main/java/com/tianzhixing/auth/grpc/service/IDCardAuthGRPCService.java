package com.tianzhixing.auth.grpc.service;

import com.tianzhixing.auth.cache.APPClientCache;
import com.tianzhixing.auth.cache.LinkedQueueCache;
import com.tianzhixing.auth.grpc.proto.idcard.AuthResult;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardAuthSerivceGrpc;
import com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo;
import com.tianzhixing.common.auth.commons.code.CodeConfig;
import com.tianzhixing.common.auth.commons.em.AuthOrgType;
import com.tianzhixing.common.auth.commons.em.RequestFromType;
import com.tianzhixing.common.auth.model.AuthIDCardInfo;
import com.tianzhixing.common.auth.utils.GenerateUtil;
import com.tianzhixing.common.auth.verification.yuanjian.YuanJianAuthResult;
import com.tianzhixing.common.auth.verification.yuanjian.YuanJianIdCardAuthException;
import com.tianzhixing.common.auth.verification.yuanjian.YuanJianIdCardAuthTools;
import com.tianzhixing.common.auth.verification.yuanjian.YuanJianResultDataCode;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.Date;

/**
 * Created by routine.k on 2018/6/9.
 */
@GrpcService(IDCardAuthSerivceGrpc.class)
public class IDCardAuthGRPCService extends IDCardAuthSerivceGrpc.IDCardAuthSerivceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(IDCardAuthGRPCService.class);

    @Override
    public void authIDCard(IDCardInfo request, StreamObserver<AuthResult> responseObserver) {
        String clientInfo = APPClientCache.get(request.getToken());
        if (null == clientInfo || "".equals(clientInfo)) {
            _completed(responseObserver, AuthResult.newBuilder().setCode(CodeConfig.AUTHFAILED.getCode()).setMessage(CodeConfig.AUTHFAILED.getValue()).build());
            return;
        }
        String name = request.getName();
        String idcard = request.getIdcard();
        if (null == name || "".equals(name) || null == idcard || "".equals(idcard)) {
            _completed(responseObserver, AuthResult.newBuilder().setCode(CodeConfig.IDCARDNULL.getCode()).setMessage(CodeConfig.IDCARDNULL.getValue()).build());
            return;
        }
        logger.info(" .... get auth idcard request from " + clientInfo + " , begin auth ....");
        //首先存储
        YuanJianAuthResult validateResult = new YuanJianAuthResult();
        validateResult.setIdCard(idcard);
        validateResult.setName(name);
        validateResult.setRequestStatus(false);
        _persistenceRequest(validateResult, AuthOrgType.DATA, RequestFromType.valueOf(clientInfo));
        //执行验证
        String message = null;
        try {
            YuanJianAuthResult yuanJianAuthResult = YuanJianIdCardAuthTools.auth(idcard, name);
            message = yuanJianAuthResult.getMessage();
            //存储请求数据
            _persistenceRequest(yuanJianAuthResult, AuthOrgType.YUANJIAN, RequestFromType.valueOf(clientInfo));
            if (yuanJianAuthResult.getRequestStatus()) {
                if (!YuanJianResultDataCode.ZERO.equals(yuanJianAuthResult.getYuanJianResultDataCode())) {
                    _completed(responseObserver, AuthResult.newBuilder().setCode(CodeConfig.IDCARDAUTHFAILED.getCode()).setMessage(CodeConfig.IDCARDAUTHFAILED.getValue()).setAuthPlatform(AuthOrgType.YUANJIAN.name()).build());
                    return;
                }
                _completed(responseObserver, AuthResult.newBuilder().setCode(CodeConfig.IDCARDAUTHSUC.getCode()).setMessage(CodeConfig.IDCARDAUTHSUC.getValue()).setPhoto(yuanJianAuthResult.getPhoto()).setAuthPlatform(AuthOrgType.YUANJIAN.name()).build());
                return;
            }
        } catch (YuanJianIdCardAuthException ex) {
            ex.printStackTrace();
        }
        _completed(responseObserver, AuthResult.newBuilder().setCode(CodeConfig.REQUESTERROR.getCode()).setMessage(message == null || "".equals(message) ? CodeConfig.REQUESTERROR.getValue() : message).build());
    }

    private void _completed(StreamObserver<AuthResult> responseObserver, AuthResult authResult) {
        responseObserver.onNext(authResult);
        responseObserver.onCompleted();
    }

    private void _persistenceRequest(YuanJianAuthResult yuanJianAuthResult, AuthOrgType authOrgType, RequestFromType requestFromType) {
        Date date = new Date();
        AuthIDCardInfo authIDCardInfo = new AuthIDCardInfo();
        authIDCardInfo.setIdCard(yuanJianAuthResult.getIdCard());
        authIDCardInfo.setPhoto(yuanJianAuthResult.getPhoto());
        authIDCardInfo.setTransID(yuanJianAuthResult.getTransID());
        authIDCardInfo.setAuthOrgType(authOrgType);
        authIDCardInfo.setAuthToken(requestFromType.name() + "-" + authOrgType.name() + "-" + date.getTime() + "-" + GenerateUtil.generateLowStrRecaptcha(16));
        authIDCardInfo.setCreateTime(date);
        authIDCardInfo.setEnable(true);
        authIDCardInfo.setFeeStatus(yuanJianAuthResult.getYuanJianFeeStatus() == null ? null : yuanJianAuthResult.getYuanJianFeeStatus().name());
        authIDCardInfo.setRequestFromType(requestFromType);
        authIDCardInfo.setRequestStatus(yuanJianAuthResult.getRequestStatus());
        authIDCardInfo.setResultDataCode(yuanJianAuthResult.getYuanJianResultDataCode() == null ? null : yuanJianAuthResult.getYuanJianResultDataCode().name());
        authIDCardInfo.setTradeNum(yuanJianAuthResult.getTradeNum());
        authIDCardInfo.setTransID(yuanJianAuthResult.getTransID());
        authIDCardInfo.setUpdateTime(date);
        authIDCardInfo.setUserName(yuanJianAuthResult.getName());
        authIDCardInfo.setStatusCode(yuanJianAuthResult.getCode());
        authIDCardInfo.setVersion(0);
        LinkedQueueCache.toQueue(authIDCardInfo);
    }
}
