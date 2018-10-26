package com.tianzhixing.kernel.grpc.service;

import com.tianzhixing.bussiness.commons.em.SystemParamType;
import com.tianzhixing.kernel.commons.ex.AccountStarPointAwardException;
import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.grpc.handler.AccountSarPointOperationHandler;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.*;
import com.tianzhixing.kernel.grpc.util.CodeConfigUtil;
import com.tianzhixing.kernel.grpc.util.FormatStarPointUtil;
import com.tianzhixing.kernel.grpc.util.ValidatorUtil;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.oms.utils.CalculateUtil;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by routine.k on 2018/6/22.
 */
@GrpcService(AccountStarPointOperationServiceGrpc.class)
public class AccountStarPointOperationGrpcService extends AccountStarPointOperationServiceGrpc.AccountStarPointOperationServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountStarPointOperationGrpcService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountSarPointOperationHandler accountSarPointOperationHandler;

    @Override
    public void collection(CollectionStarPointInfo request, StreamObserver<CollectionResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _onCollectionCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null, null);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getCollectionTime())) {
            _onCollectionCompleted(CodeConfigUtil.PARAMERROR, "request.collection.time.error", responseObserver, null, null, null);
            return;
        }
        if (request.getRecordsList() == null) {
            _onCollectionCompleted(CodeConfigUtil.PARAMERROR, "request.reocords.list.null", responseObserver, null, null, null);
            return;
        }
        Set<String> recordsSet = new HashSet<String>();
        for (StarPointRecordsInfo starPointRecordsInfo : request.getRecordsList()) {
            if (StringUtils.isEmpty(starPointRecordsInfo.getRecordToken())) {
                _onCollectionCompleted(CodeConfigUtil.PARAMERROR, "request.reocords.token.null", responseObserver, null, null, null);
                return;
            }
            if (recordsSet.contains(starPointRecordsInfo.getRecordToken())) {
                _onCollectionCompleted(CodeConfigUtil.PARAMERROR, "request.reocords.token.repeat", responseObserver, null, null, null);
                return;
            }
            recordsSet.add(starPointRecordsInfo.getRecordToken());
        }
        //判定广告信息
        if (StringUtils.isNotEmpty(request.getAdvertId())) {
            if (0 != request.getAdvertIdOperationType() && 1 != request.getAdvertIdOperationType()) {
                _onCollectionCompleted(CodeConfigUtil.PARAMERROR, "request.advert.operation.type.error", responseObserver, null, null, null);
                return;
            }
        }
        AccountStarPointOperationInfoMapper accountStarPointOperationInfoMapper = null;
        try {
            accountStarPointOperationInfoMapper = accountSarPointOperationHandler.collection(request.getAccountToken(), recordsSet, request.getAdvertId(), request.getAdvertIdOperationType());
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onCollectionCompleted(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver, null, null, null);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onCollectionCompleted(CodeConfigUtil.SYSTEMERROR, "starpoint.operation.error", responseObserver, null, null, null);
            return;
        }
        _onCollectionCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getAvailableStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getFrozenStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getStarPoint()));
    }

    @Override
    public void award(AwardInfo request, StreamObserver<AwardResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _onAwardCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null, null);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getAdwardTime())) {
            _onAwardCompleted(CodeConfigUtil.PARAMERROR, "request.award.time.error", responseObserver, null, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getAdwardType())) {
            _onAwardCompleted(CodeConfigUtil.PARAMERROR, "request.awardtype.null", responseObserver, null, null, null);
            return;
        }
        AccountStarPointOperationInfoMapper accountStarPointOperationInfoMapper;
        try {
            accountStarPointOperationInfoMapper = accountSarPointOperationHandler.award(SystemParamType.valueOf(request.getAdwardType()), request.getAccountToken());
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onAwardCompleted(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver, null, null, null);
            return;
        } catch (AccountStarPointAwardException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onAwardCompleted(CodeConfigUtil.STARPOINTAWARDEXCEPTION, "award.type.error", responseObserver, null, null, null);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onAwardCompleted(CodeConfigUtil.SYSTEMERROR, "starpoint.operation.error", responseObserver, null, null, null);
            return;
        }
        _onAwardCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getAvailableStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getFrozenStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getStarPoint()));
    }

    private void _onAwardCompleted(int code, String message, StreamObserver<AwardResult> responseObserver, String availableStarPoint, String frozenStarPoint, String starPoint) {
        responseObserver.onNext(_awardResult(code, message, availableStarPoint, frozenStarPoint, starPoint));
        responseObserver.onCompleted();
    }

    private AwardResult _awardResult(int code, String message, String availableStarPoint, String frozenStarPoint, String starPoint) {
        AwardResult.Builder builder = AwardResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (StringUtils.isNotEmpty(availableStarPoint))
            builder.setAvailableStarPoint(availableStarPoint);
        if (StringUtils.isNotEmpty(frozenStarPoint))
            builder.setFrozenStarPoint(frozenStarPoint);
        if (StringUtils.isNotEmpty(starPoint))
            builder.setStarPoint(starPoint);
        return builder.build();
    }

    @Override
    public void withdraw(WithdrawInfo request, StreamObserver<WithdrawResult> responseObserver) {
        super.withdraw(request, responseObserver);
    }

    private void _onCollectionCompleted(int code, String message, StreamObserver<CollectionResult> responseObserver, String availableStarPoint, String frozenStarPoint, String starPoint) {
        responseObserver.onNext(_collectionResult(code, message, availableStarPoint, frozenStarPoint, starPoint));
        responseObserver.onCompleted();
    }

    private CollectionResult _collectionResult(int code, String message, String availableStarPoint, String frozenStarPoint, String starPoint) {
        CollectionResult.Builder builder = CollectionResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (StringUtils.isNotEmpty(availableStarPoint))
            builder.setAvailableStarPoint(availableStarPoint);
        if (StringUtils.isNotEmpty(frozenStarPoint))
            builder.setFrozenStarPoint(frozenStarPoint);
        if (StringUtils.isNotEmpty(starPoint))
            builder.setStarpoint(starPoint);
        return builder.build();
    }

    @Override
    public void bindAddressList(BindAddressAwardInfo request, StreamObserver<AwardResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _onAwardCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null, null);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getAdwardTime())) {
            _onAwardCompleted(CodeConfigUtil.PARAMERROR, "request.award.time.error", responseObserver, null, null, null);
            return;
        }
        if (request.getContactCount() == 0) {
            _onAwardCompleted(CodeConfigUtil.PARAMERROR, "request.contactcount.null", responseObserver, null, null, null);
            return;
        }
        AccountStarPointOperationInfoMapper accountStarPointOperationInfoMapper;
        try {
            accountStarPointOperationInfoMapper = accountSarPointOperationHandler.bindAddressListAward(request.getContactCount(), request.getAccountToken());
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onAwardCompleted(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver, null, null, null);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onAwardCompleted(CodeConfigUtil.SYSTEMERROR, "starpoint.operation.error", responseObserver, null, null, null);
            return;
        }
        _onAwardCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getAvailableStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getFrozenStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getStarPoint()));
    }
}
