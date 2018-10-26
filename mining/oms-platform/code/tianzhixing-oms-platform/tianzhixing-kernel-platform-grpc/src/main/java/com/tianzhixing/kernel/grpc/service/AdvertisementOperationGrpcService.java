package com.tianzhixing.kernel.grpc.service;

import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.commons.ex.AccountStarPointOperationException;
import com.tianzhixing.kernel.grpc.handler.AdvertisementOperationHandler;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementOperationServiceGrpc;
import com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult;
import com.tianzhixing.kernel.grpc.util.CodeConfigUtil;
import com.tianzhixing.kernel.grpc.util.FormatStarPointUtil;
import com.tianzhixing.kernel.grpc.util.ValidatorUtil;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointInfoMapper;
import com.tianzhixing.kernel.rpc.mapper.account.AccountStarPointOperationInfoMapper;
import com.tianzhixing.oms.utils.FormatUtil;
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
@GrpcService(AdvertisementOperationServiceGrpc.class)
public class AdvertisementOperationGrpcService extends AdvertisementOperationServiceGrpc.AdvertisementOperationServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(AdvertisementOperationGrpcService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AdvertisementOperationHandler advertisementOperationHandler;

    @Override
    public void clickOrAccess(AdvertisementInfo request, StreamObserver<ClickResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _onClickCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null, null);
            return;
        }
        if (!ValidatorUtil.vaildateTime(request.getClickTime())) {
            _onClickCompleted(CodeConfigUtil.PARAMERROR, "request.click.time.error", responseObserver, null, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getAdvertId())) {
            _onClickCompleted(CodeConfigUtil.PARAMERROR, "request.advert.id.null", responseObserver, null, null, null);
            return;
        }
        if (StringUtils.isEmpty(request.getStarPoint()) || !FormatUtil.isDecimal(request.getStarPoint()) || Double.valueOf(request.getStarPoint()) < 0) {
            _onClickCompleted(CodeConfigUtil.PARAMERROR, "request.advert.starpoint.error", responseObserver, null, null, null);
            return;
        }
        if (0 != request.getOperationType() && 1 != request.getOperationType()) {
            _onClickCompleted(CodeConfigUtil.PARAMERROR, "request.advert.operation.type.error", responseObserver, null, null, null);
            return;
        }
        AccountStarPointOperationInfoMapper accountStarPointOperationInfoMapper;
        try {
            accountStarPointOperationInfoMapper = advertisementOperationHandler.clickOrAccessAdvertisement(request.getAccountToken(), request.getAdvertId(), Double.valueOf(request.getStarPoint()), new Date(request.getClickTime()), request.getOperationType());
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onClickCompleted(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver, null, null, null);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed operate account starpoint for click advertisement, cause[" + ex.getMessage() + "] ......");
            _onClickCompleted(CodeConfigUtil.SYSTEMERROR, "click.advert.error", responseObserver, null, null, null);
            return;
        }
        _onClickCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getAvailableStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getAccountStarPointInfoMapper().getFrozenStarPoint()), FormatStarPointUtil.format(accountStarPointOperationInfoMapper.getStarPoint()));
    }

    private void _onClickCompleted(int code, String message, StreamObserver<ClickResult> responseObserver, String availableStarPoint, String frozenStarPoint, String starPoint) {
        responseObserver.onNext(_clickResult(code, message, availableStarPoint, frozenStarPoint, starPoint));
        responseObserver.onCompleted();
    }

    private ClickResult _clickResult(int code, String message, String availableStarPoint, String frozenStarPoint, String starPoint) {
        ClickResult.Builder builder = ClickResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (StringUtils.isNotEmpty(availableStarPoint))
            builder.setAvailableStarPoint(availableStarPoint);
        if (StringUtils.isNotEmpty(frozenStarPoint))
            builder.setFrozenStarPoint(frozenStarPoint);
        if (StringUtils.isNotEmpty(starPoint))
            builder.setStarPoint(starPoint);
        return builder.build();
    }


}
