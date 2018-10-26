package com.tianzhixing.kernel.grpc.service;

import com.tianzhixing.kernel.commons.ex.AccountAlreadyExitsException;
import com.tianzhixing.kernel.grpc.exception.AccountRegException;
import com.tianzhixing.kernel.grpc.handler.AccountInfoHandler;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.reg.*;
import com.tianzhixing.kernel.grpc.util.CodeConfigUtil;
import com.tianzhixing.kernel.grpc.util.ValidatorUtil;
import com.tianzhixing.kernel.rpc.mapper.account.AccountInfoMapper;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.Date;

/**
 * Created by routine.k on 2018/6/21.
 */
@GrpcService(AccountRegServiceGrpc.class)
public class AccountRegGrpcService extends AccountRegServiceGrpc.AccountRegServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountRegGrpcService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountInfoHandler accountInfoHandler;

    @Override
    public void reg(AccountInfo request, StreamObserver<Result> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorToken(request.getToken());
        if (!validatorResult.getStatus()) {
            _onCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            _onCompleted(CodeConfigUtil.PARAMERROR, "request.mobile.null", responseObserver, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getThirdToken())) {
            _onCompleted(CodeConfigUtil.PARAMERROR, "request.third.token.null", responseObserver, null, null);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getRegTime())) {
            _onCompleted(CodeConfigUtil.PARAMERROR, "request.reg.time.error", responseObserver, null, null);
            return;
        }
        String accountToken = null;
        try {
            accountToken = accountInfoHandler.add(request.getMobile(), request.getThirdToken(), new Date(request.getRegTime()), validatorResult.getOrg(), request.getReferrerToken());
        } catch (AccountAlreadyExitsException ex) {
            LOGGER.error("...... failed reg account, cause[ account.already.exists: " + ex.getMessage() + " ] ......");
            _onCompleted(CodeConfigUtil.STARPOINTALREADYEXISTS, "mobile.already.exists", responseObserver, ex.getMessage(), null);
        } catch (AccountRegException ex) {
            LOGGER.error("...... failed reg account, cause[" + ex.getMessage() + "] ......");
            _onCompleted(CodeConfigUtil.REGEXCEPTION, ex.getMessage(), responseObserver, null, null);
        } catch (Exception ex) {
            LOGGER.error("...... failed reg account, cause[" + ex.getMessage() + "] ......");
            _onCompleted(CodeConfigUtil.SYSTEMERROR, "reg.error", responseObserver, null, null);
            return;
        }
        _onCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, accountToken, request.getThirdToken());
    }

    private void _onCompleted(int code, String message, StreamObserver<Result> responseObserver, String accountToken, String thirdToken) {
        responseObserver.onNext(_result(code, message, accountToken, thirdToken));
        responseObserver.onCompleted();
    }

    private Result _result(int code, String message, String accountToken, String thirdToken) {
        Result.Builder builder = Result.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (StringUtils.isNotEmpty(accountToken))
            builder.setAccountToken(accountToken);
        if (StringUtils.isNotEmpty(thirdToken))
            builder.setThirdToken(thirdToken);
        return builder.build();
    }

    @Override
    public void check(CheckMobileInfo request, StreamObserver<CheckMobileResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorToken(request.getToken());
        if (!validatorResult.getStatus()) {
            _onCheckCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getMobile())) {
            _onCheckCompleted(CodeConfigUtil.PARAMERROR, "request.mobile.null", responseObserver, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getOrg())) {
            _onCheckCompleted(CodeConfigUtil.PARAMERROR, "request.org.null", responseObserver, null, null);
            return;
        }
        AccountInfoMapper accountInfoMapper = accountInfoHandler.getByMobileAndOrg(request.getMobile(), request.getOrg());
        String accountToken = accountInfoMapper == null ? null : accountInfoMapper.getAccountToken();
        Boolean exists = accountInfoMapper != null;
        _onCheckCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, accountToken, exists);
    }

    private void _onCheckCompleted(int code, String message, StreamObserver<CheckMobileResult> responseObserver, String accountToken, Boolean exists) {
        responseObserver.onNext(_onCheckResult(code, message, accountToken, exists));
        responseObserver.onCompleted();
    }

    private CheckMobileResult _onCheckResult(int code, String message, String accountToken, Boolean exists) {
        CheckMobileResult.Builder builder = CheckMobileResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (StringUtils.isNotEmpty(accountToken))
            builder.setAccountToken(accountToken);
        if (exists != null)
            builder.setExists(exists);
        return builder.build();
    }
}
