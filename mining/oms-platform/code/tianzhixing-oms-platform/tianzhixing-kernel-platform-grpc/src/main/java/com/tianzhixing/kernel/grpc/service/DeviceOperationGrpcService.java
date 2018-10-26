package com.tianzhixing.kernel.grpc.service;

import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.ex.DeviceOperationException;
import com.tianzhixing.kernel.grpc.handler.DeviceOperationHandler;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo;
import com.tianzhixing.kernel.grpc.proto.device.operation.DeviceOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.device.operation.Result;
import com.tianzhixing.kernel.grpc.util.CodeConfigUtil;
import com.tianzhixing.kernel.grpc.util.ValidatorUtil;
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
 * Created by routine.k on 2018/6/22.
 */
@GrpcService(DeviceOperationServiceGrpc.class)
public class DeviceOperationGrpcService extends DeviceOperationServiceGrpc.DeviceOperationServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(DeviceOperationServiceGrpc.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private DeviceOperationHandler deviceOperationHandler;

    @Override
    public void bind(DeviceInfo request, StreamObserver<Result> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _completed(validatorResult.getCode(), validatorResult.getMessage(), responseObserver);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getOperationTime())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.operation.time.error", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceId())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.id.null", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceType())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.type.null", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceMAC())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.mac.null", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceModel())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.model.null", responseObserver);
            return;
        }
        try {
            deviceOperationHandler.bind(request.getAccountToken(), request.getDeviceId(), request.getDeviceType(), request.getDeviceMAC(), request.getDeviceModel(), new Date(request.getOperationTime()));
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed bind device[" + request.getDeviceId() + "], cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.SYSTEMERROR, "device.bind.error", responseObserver);
            return;
        }
        _completed(CodeConfigUtil.SUCCESS, "request.suc", responseObserver);
    }

    @Override
    public void unBind(DeviceInfo request, StreamObserver<Result> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _completed(validatorResult.getCode(), validatorResult.getMessage(), responseObserver);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getOperationTime())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.operation.time.error", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceId())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.id.null", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceType())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.type.null", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceMAC())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.mac.null", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getDeviceModel())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.device.model.null", responseObserver);
            return;
        }
        try {
            deviceOperationHandler.unBind(request.getAccountToken(), request.getDeviceId(), new Date(request.getOperationTime()));
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed unbind device[" + request.getDeviceId() + "], cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.SYSTEMERROR, "device.unbind.error", responseObserver);
            return;
        }
        _completed(CodeConfigUtil.SUCCESS, "request.suc", responseObserver);
    }

    private void _completed(int code, String message, StreamObserver<Result> responseObserver) {
        responseObserver.onNext(_result(code, message));
        responseObserver.onCompleted();
    }

    private Result _result(int code, String message) {
        Result.Builder builder = Result.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        return builder.build();
    }
}
