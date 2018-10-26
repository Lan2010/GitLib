package com.tianzhixing.auth.grpc.service;

import com.tianzhixing.auth.cache.APPClientCache;
import com.tianzhixing.auth.cache.LinkedQueueCache;
import com.tianzhixing.auth.grpc.proto.mobile.*;
import com.tianzhixing.common.auth.commons.code.CodeConfig;
import com.tianzhixing.common.auth.commons.em.CodeOrgType;
import com.tianzhixing.common.auth.commons.em.RequestFromType;
import com.tianzhixing.common.auth.model.MobileValidationCode;
import com.tianzhixing.common.auth.utils.CalendarUtil;
import com.tianzhixing.common.auth.utils.GenerateUtil;
import com.tianzhixing.common.auth.utils.RequestUtil;
import com.tianzhixing.common.auth.utils.ResourceBundleUtil;
import com.tianzhixing.common.auth.verification.yunpian.YunPianConfig;
import com.tianzhixing.common.auth.verification.yunpian.YunPianSMSClient;
import com.tianzhixing.common.auth.verification.yunpian.YunPianSMSSendResult;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import java.util.*;

/**
 * Created by routine.k on 2018/6/14.
 */
@GrpcService(MobileValidationSerivceGrpc.class)
public class MobileValidationGRPCSerivce extends MobileValidationSerivceGrpc.MobileValidationSerivceImplBase {

    private static final Logger logger = LoggerFactory.getLogger(MobileValidationGRPCSerivce.class);

    @Resource(name = "smsSendCodeRedisService")
    private SMSSendCodeRedisService smsSendCodeRedisService;

    @Override
    public void send(SendCodeInfo request, StreamObserver<SendCodeResult> responseObserver) {
        String clientInfo = APPClientCache.get(request.getToken());
        if (null == clientInfo || "".equals(clientInfo)) {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.AUTHFAILED.getCode()).setMessage(CodeConfig.AUTHFAILED.getValue()).build());
            return;
        }
        String mobile = request.getMobile();
        if (mobile == null || "".equals(mobile)) {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.MOBILENULL.getCode()).setMessage(CodeConfig.MOBILENULL.getValue()).build());
            return;
        }
        logger.info(" .... get send mobile code request from " + clientInfo + " , begin send ....");
        SendCodeInfo.SendCodeType sendCodeType = request.getSendCodeType();
        if (!SendCodeInfo.SendCodeType.SECURITY_CODE.equals(sendCodeType)) {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.ONLYSECURITY.getCode()).setMessage(CodeConfig.ONLYSECURITY.getValue()).build());
            return;
        }
        int length = request.getCodeLength();
        if (length < 4 || length > 8) {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.CODELENGTH_ERROR.getCode()).setMessage(CodeConfig.CODELENGTH_ERROR.getValue()).build());
            return;
        }
        //检查验证码发送频率
        boolean status = _checkMobileSendCode(mobile);
        if (!status) {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.CODEINTERVALERROR.getCode()).setMessage(CodeConfig.CODEINTERVALERROR.getValue()).build());
            return;
        }

        //根据长度生成随机码
        String genCode = GenerateUtil.generateNumber(length);
        String genToken = UUID.randomUUID().toString();
        //发送验证码
        MobileValidationCode mobileValidationCode = _persistence(genToken, genCode, mobile, RequestFromType.valueOf(clientInfo), sendCodeType.name());
        YunPianSMSSendResult yunPianSMSSendResult = new YunPianSMSClient().sendCodeWithValidateCode(genCode, mobile);
        if (yunPianSMSSendResult.getCode() == 0) {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.SUCCESS.getCode()).setMessage(CodeConfig.SUCCESS.getValue()).setCodeToken(genToken).setMobile(mobile).setSendPlatform(clientInfo).build());
        } else {
            _completed(responseObserver, SendCodeResult.newBuilder().setCode(CodeConfig.MOBILESENDFAILED.getCode()).setMessage(CodeConfig.MOBILESENDFAILED.getValue()).setMobile(mobile).setSendPlatform(clientInfo).build());
        }
        _updateSendResult(yunPianSMSSendResult, mobileValidationCode, true);
    }

    @Override
    public void sendWithContent(SendContentInfo request, StreamObserver<SendContentResult> responseObserver) {
        String clientInfo = APPClientCache.get(request.getToken());
        if (null == clientInfo || "".equals(clientInfo)) {
            _completed(responseObserver, SendContentResult.newBuilder().setCode(CodeConfig.AUTHFAILED.getCode()).setMessage(CodeConfig.AUTHFAILED.getValue()).build());
            return;
        }
        String mobile = request.getMobile();
        if (mobile == null || "".equals(mobile)) {
            _completed(responseObserver, SendContentResult.newBuilder().setCode(CodeConfig.MOBILENULL.getCode()).setMessage(CodeConfig.MOBILENULL.getValue()).build());
            return;
        }
        String mod = request.getModKey();
        if (StringUtils.isEmpty(mod) || StringUtils.isEmpty(YunPianConfig.getMod(mod))) {
            _completed(responseObserver, SendContentResult.newBuilder().setCode(CodeConfig.SMSMODERROR.getCode()).setMessage(CodeConfig.SMSMODERROR.getValue()).build());
            return;
        }
        logger.info(" .... get send mobile content request from " + clientInfo + " , begin send content[" + mod + "] ....");
        String genToken = UUID.randomUUID().toString();
        MobileValidationCode mobileValidationCode = _getMobileValidationCode(genToken, "0", mobile, RequestFromType.valueOf(clientInfo), "MULCONTENT");
        YunPianSMSSendResult yunPianSMSSendResult = new YunPianSMSClient().sendCodeWithMod(mod, request.getReplaceMap(), mobile);
        if (yunPianSMSSendResult.getCode() == 0) {
            _completed(responseObserver, SendContentResult.newBuilder().setCode(CodeConfig.SUCCESS.getCode()).setMessage(CodeConfig.SUCCESS.getValue()).setMobile(mobile).setSendPlatform(clientInfo).build());
        } else {
            _completed(responseObserver, SendContentResult.newBuilder().setCode(CodeConfig.MOBILESENDFAILED.getCode()).setMessage(CodeConfig.MOBILESENDFAILED.getValue()).setMobile(mobile).setSendPlatform(clientInfo).build());
        }
        _updateSendResult(yunPianSMSSendResult, mobileValidationCode, false);
        //to queue
        LinkedQueueCache.toQueue(mobileValidationCode);
    }

    private void _completed(StreamObserver<SendContentResult> responseObserver, SendContentResult result) {
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    private boolean _checkMobileSendCode(String mobile) {
        Date date = smsSendCodeRedisService.getMobileLastSendTime(mobile);
        if (date != null) {
            int timeoutSec = ResourceBundleUtil.getIntegerValue("sms.code.send.interval.time", "sms-config", false);
            Date timeoutDate = CalendarUtil.calDateForSec(date, timeoutSec);
            if (CalendarUtil.isBefore(new Date(), timeoutDate)) {
                return false;
            }
        }
        return true;
    }

    private void _updateSendResult(YunPianSMSSendResult yunPianSMSSendResult, MobileValidationCode mobileValidationCode, boolean persistence) {
        mobileValidationCode.setUpdateTime(new Date());
        mobileValidationCode.setFee(String.valueOf(yunPianSMSSendResult.getFee()));
        mobileValidationCode.setMessage(yunPianSMSSendResult.getMsg());
        mobileValidationCode.setResultCode(String.valueOf(yunPianSMSSendResult.getCode()));
        mobileValidationCode.setSid(String.valueOf(yunPianSMSSendResult.getSid()));
        mobileValidationCode.setSenderOrg(CodeOrgType.YUNPIAN.name());
        mobileValidationCode.setStatus(yunPianSMSSendResult.getCode() == 0);
        if (persistence) smsSendCodeRedisService.updateSendResult(mobileValidationCode);
    }

    private MobileValidationCode _persistence(String genToken, String genCode, String mobile, RequestFromType requestFromType, String smsType) {
        MobileValidationCode mobileValidationCode = _getMobileValidationCode(genToken, genCode, mobile, requestFromType, smsType);
        return smsSendCodeRedisService.insert(mobileValidationCode);
    }

    private MobileValidationCode _getMobileValidationCode(String genToken, String genCode, String mobile, RequestFromType requestFromType, String smsType) {
        MobileValidationCode mobileValidationCode = new MobileValidationCode();
        mobileValidationCode.setMobile(mobile);
        mobileValidationCode.setVersion(0);
        mobileValidationCode.setCreateTime(new Date());
        mobileValidationCode.setRequestFromType(requestFromType);
        mobileValidationCode.setSmsType(smsType);
        mobileValidationCode.setStatus(true);
        mobileValidationCode.setValidationCode(genCode);
        mobileValidationCode.setUpdateTime(new Date());
        mobileValidationCode.setToken(genToken);
        return mobileValidationCode;
    }

    private void _completed(StreamObserver<SendCodeResult> responseObserver, SendCodeResult result) {
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    @Override
    public void validate(ValidationCodeInfo request, StreamObserver<ValidationResult> responseObserver) {
        String clientInfo = APPClientCache.get(request.getToken());
        if (null == clientInfo || "".equals(clientInfo)) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.AUTHFAILED.getCode()).setMessage(CodeConfig.AUTHFAILED.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            return;
        }
        String mobile = request.getMobile();
        if (mobile == null || "".equals(mobile)) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.MOBILENULL.getCode()).setMessage(CodeConfig.MOBILENULL.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            return;
        }
        String inputCode = request.getInputCode();
        if (inputCode == null || "".equals(inputCode)) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.INPUTCODENULL.getCode()).setMessage(CodeConfig.INPUTCODENULL.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            return;
        }
        String codeToken = request.getCodeToken();
        if (codeToken == null || "".equals(codeToken)) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.CODETOKENNULL.getCode()).setMessage(CodeConfig.CODETOKENNULL.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            return;
        }
        MobileValidationCode mobileValidationCode = smsSendCodeRedisService.get(codeToken, mobile);
        if (mobileValidationCode == null || !mobileValidationCode.getStatus()) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.CODETOKENERROR.getCode()).setMessage(CodeConfig.CODETOKENERROR.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            return;
        }
        int timeoutMin = ResourceBundleUtil.getIntegerValue("sms.code.timeout", "sms-config", false);
        Date timeoutDate = CalendarUtil.calDateForMin(mobileValidationCode.getCreateTime(), timeoutMin);
        if (CalendarUtil.isAfter(new Date(), timeoutDate)) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.CODETIMEOUT.getCode()).setMessage(CodeConfig.CODETIMEOUT.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            _updateValidateResult(mobileValidationCode, "timeout");
            return;
        }
        if (!inputCode.equals(mobileValidationCode.getValidationCode())) {
            _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.CODEVALIDATEFAILED.getCode()).setMessage(CodeConfig.CODEVALIDATEFAILED.getValue()).setStatus(ValidationResult.Status.FAILED).build());
            return;
        }
        _completed(responseObserver, ValidationResult.newBuilder().setCode(CodeConfig.SUCCESS.getCode()).setMessage(CodeConfig.SUCCESS.getValue()).setStatus(ValidationResult.Status.SUC).build());
        _updateValidateResult(mobileValidationCode, "suc");
    }

    @Override
    public void search(SearchCodeInfo request, StreamObserver<SearchResultInfo> responseObserver) {
        String clientInfo = APPClientCache.get(request.getToken());
        if (null == clientInfo || "".equals(clientInfo)) {
            _completed(responseObserver, SearchResultInfo.newBuilder().setCode(CodeConfig.AUTHFAILED.getCode()).setMessage(CodeConfig.AUTHFAILED.getValue()).build());
            return;
        }
        String mobile = request.getMobile();
        if (mobile == null || "".equals(mobile)) {
            _completed(responseObserver, SearchResultInfo.newBuilder().setCode(CodeConfig.MOBILENULL.getCode()).setMessage(CodeConfig.MOBILENULL.getValue()).build());
            return;
        }
        List<MobileValidationCode> list = smsSendCodeRedisService.searchWithMobile(mobile);
        Map<String, CodeInfo> codeInfoMap = new HashMap<String, CodeInfo>();
        if (list != null) {
            for (MobileValidationCode mobileValidationCode : list) {
                CodeInfo codeInfo = CodeInfo.newBuilder().setValidationCode(mobileValidationCode.getValidationCode()).setSenderOrg(mobileValidationCode.getSenderOrg()).setStatus(mobileValidationCode.getStatus()).build();
                codeInfoMap.put(String.valueOf(mobileValidationCode.getCreateTime().getTime()), codeInfo);
            }
        }
        _completed(responseObserver, SearchResultInfo.newBuilder().setCode(CodeConfig.SUCCESS.getCode()).setMessage(CodeConfig.SUCCESS.getValue()).setMobile(mobile).putAllMap(codeInfoMap).build());

    }

    private void _updateValidateResult(MobileValidationCode mobileValidationCode, String message) {
        mobileValidationCode.setUpdateTime(new Date());
        mobileValidationCode.setStatus(false);
        if (message != null && !"".equals(message)) {
            mobileValidationCode.setMessage((mobileValidationCode.getMessage() == null ? "" : mobileValidationCode.getMessage()) + ":" + message);
        }
        smsSendCodeRedisService.updateValidateResult(mobileValidationCode);
    }

    private void _completed(StreamObserver<ValidationResult> responseObserver, ValidationResult result) {
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }

    private void _completed(StreamObserver<SearchResultInfo> responseObserver, SearchResultInfo result) {
        responseObserver.onNext(result);
        responseObserver.onCompleted();
    }
}
