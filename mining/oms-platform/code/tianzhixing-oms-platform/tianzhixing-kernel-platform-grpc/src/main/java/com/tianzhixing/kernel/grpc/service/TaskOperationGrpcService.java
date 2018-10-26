package com.tianzhixing.kernel.grpc.service;

import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.grpc.handler.TaskOperationHandler;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.task.operation.Result;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo;
import com.tianzhixing.kernel.grpc.proto.task.operation.TaskOperationServiceGrpc;
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
@GrpcService(TaskOperationServiceGrpc.class)
public class TaskOperationGrpcService extends TaskOperationServiceGrpc.TaskOperationServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(TaskOperationGrpcService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private TaskOperationHandler taskOperationHandler;

    @Override
    public void accept(TaskInfo request, StreamObserver<Result> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _completed(validatorResult.getCode(), validatorResult.getMessage(), responseObserver);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getOperationTime())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.operation.time.error", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getTaskId())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.task.id.null", responseObserver);
            return;
        }
        try {
            taskOperationHandler.accept(request.getAccountToken(), request.getTaskId(), new Date(request.getOperationTime()));
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed accept task[" + request.getTaskId() + "], cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.SYSTEMERROR, "task.accept.error", responseObserver);
            return;
        }
        _completed(CodeConfigUtil.SUCCESS, "request.suc", responseObserver);

    }

    @Override
    public void cancel(TaskInfo request, StreamObserver<Result> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _completed(validatorResult.getCode(), validatorResult.getMessage(), responseObserver);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getOperationTime())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.operation.time.error", responseObserver);
            return;
        }
        if (StringUtils.isEmpty(request.getTaskId())) {
            _completed(CodeConfigUtil.PARAMERROR, "request.task.id.null", responseObserver);
            return;
        }
        try {
            taskOperationHandler.cancel(request.getAccountToken(), request.getTaskId(), new Date(request.getOperationTime()));
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed cancel task[" + request.getTaskId() + "], cause[" + ex.getMessage() + "] ......");
            _completed(CodeConfigUtil.SYSTEMERROR, "task.cancel.error", responseObserver);
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
