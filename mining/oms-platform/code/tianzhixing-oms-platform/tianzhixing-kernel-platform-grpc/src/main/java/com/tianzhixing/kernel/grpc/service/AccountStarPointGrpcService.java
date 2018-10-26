package com.tianzhixing.kernel.grpc.service;

import com.tianzhixing.kernel.commons.em.StarPointRecordsType;
import com.tianzhixing.kernel.commons.ex.AccountStarPointNotFoundException;
import com.tianzhixing.kernel.grpc.handler.AccountInfoHandler;
import com.tianzhixing.kernel.grpc.handler.AccountStarPointHandler;
import com.tianzhixing.kernel.grpc.proto.ResponseEntity;
import com.tianzhixing.kernel.grpc.proto.account.starpoint.*;
import com.tianzhixing.kernel.grpc.util.CodeConfigUtil;
import com.tianzhixing.kernel.grpc.util.FormatStarPointUtil;
import com.tianzhixing.kernel.grpc.util.ValidatorUtil;
import com.tianzhixing.kernel.rpc.mapper.account.*;
import com.tianzhixing.oms.utils.CalculateUtil;
import com.tianzhixing.oms.utils.CalendarUtil;
import io.grpc.stub.StreamObserver;
import net.devh.springboot.autoconfigure.grpc.server.GrpcService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by routine.k on 2018/6/21.
 */
@GrpcService(AccountStarPointServiceGrpc.class)
public class AccountStarPointGrpcService extends AccountStarPointServiceGrpc.AccountStarPointServiceImplBase {

    private static Logger LOGGER = LoggerFactory.getLogger(AccountStarPointGrpcService.class);

    @Autowired
    private MessageSource messageSource;

    @Autowired
    private AccountInfoHandler accountInfoHandler;

    @Autowired
    private AccountStarPointHandler accountStarPointHandler;

    @Override
    public void starPoint(AccountInfo request, StreamObserver<Result> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _onCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null);
            return;
        }
        AccountStarPointInfoMapper accountStarPointInfoMapper;
        try {
            accountStarPointInfoMapper = accountInfoHandler.getAccountStartPoint(request.getAccountToken());
        } catch (AccountStarPointNotFoundException ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onCompleted(CodeConfigUtil.STARPOINTNOTFOUND, "account.not.found", responseObserver, null, null);
            return;
        } catch (Exception ex) {
            LOGGER.error("...... failed operate account starpoint, cause[" + ex.getMessage() + "] ......");
            _onCompleted(CodeConfigUtil.SYSTEMERROR, "search.starpoint.operation.error", responseObserver, null, null);
            return;
        }
        _onCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, FormatStarPointUtil.format(accountStarPointInfoMapper.getAvailableStarPoint()), FormatStarPointUtil.format(accountStarPointInfoMapper.getFrozenStarPoint()));
    }

    private void _onCompleted(int code, String message, StreamObserver<Result> responseObserver, String availableStarPoint, String frozenStarPoint) {
        responseObserver.onNext(_result(code, message, availableStarPoint, frozenStarPoint));
        responseObserver.onCompleted();
    }

    private Result _result(int code, String message, String availableStarPoint, String frozenStarPoint) {
        Result.Builder builder = Result.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (StringUtils.isNotEmpty(availableStarPoint))
            builder.setAvailableStarPoint(availableStarPoint);
        if (StringUtils.isNotEmpty(frozenStarPoint))
            builder.setFrozenStarPoint(frozenStarPoint);
        return builder.build();
    }

    @Override
    public void records(RecordsCondition request, StreamObserver<RecordsResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _onCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null);
            return;
        }
        if (request.getBeginTime() != 0l && !ValidatorUtil.vaildateTime(request.getBeginTime())) {
            _onCompleted(CodeConfigUtil.PARAMERROR, "request.begin.time.error", responseObserver, null, null);
            return;
        }
        if (request.getEndTime() != 0l && !ValidatorUtil.vaildateTime(request.getEndTime())) {
            _onCompleted(CodeConfigUtil.PARAMERROR, "request.end.time.error", responseObserver, null, null);
            return;
        }
        if (request.getPageMapper() == null) {
            _onCompleted(CodeConfigUtil.PARAMERROR, "request.pagemapper.error", responseObserver, null, null);
            return;
        }
        StarPointRecordsType starPointRecordsType = null;
        if (!"ALL".equals(request.getRecordsType())) {
            for (StarPointRecordsType spr : StarPointRecordsType.values()) {
                if (spr.name().equals(request.getRecordsType())) {
                    starPointRecordsType = spr;
                }
            }
            if (starPointRecordsType == null) {
                if (request.getPageMapper() == null) {
                    _onCompleted(CodeConfigUtil.PARAMERROR, "request.records.type.error", responseObserver, null, null);
                    return;
                }
            }
        }
        AccountStarPointRecordsInfoWithPagerMapper accountStarPointRecordsInfoWithPagerMapper = accountStarPointHandler.records(
                request.getAccountToken(),
                request.getBeginTime() == 0l ? null : new Date(request.getBeginTime()),
                request.getEndTime() == 0l ? null : new Date(request.getEndTime()),
                request.getPageMapper().getFrom(),
                request.getPageMapper().getSize(),
                request.getTaskId(),
                request.getAdvertisementId(),
                starPointRecordsType);
        List<RecordsInfo> recordsInfoList = new ArrayList<>();
        for (AccountStarPointRecordsInfoMapper accountStarPointRecordsInfoMapper : accountStarPointRecordsInfoWithPagerMapper.getAccountStarPointRecordsInfoMapperList()) {
            RecordsInfo.Builder builder = RecordsInfo.newBuilder();
            builder.setOperStarPoint(FormatStarPointUtil.format(accountStarPointRecordsInfoMapper.getOperStarPoint()));
            builder.setOperationType(accountStarPointRecordsInfoMapper.getOperationType().name());
            builder.setRecordsType(accountStarPointRecordsInfoMapper.getRecordsType().name());
            if (accountStarPointRecordsInfoMapper.getTaskId() != null) {
                builder.setTaskId(accountStarPointRecordsInfoMapper.getTaskId());
            }
            if (accountStarPointRecordsInfoMapper.getAdvertisementId() != null) {
                builder.setAdvertisementId(accountStarPointRecordsInfoMapper.getAdvertisementId());
            }
            if (accountStarPointRecordsInfoMapper.getRemark() != null) {
                builder.setRemark(accountStarPointRecordsInfoMapper.getRemark());
            }
            builder.setCreateTime(accountStarPointRecordsInfoMapper.getCreateTime().getTime());
            recordsInfoList.add(builder.build());
        }
        _onCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, accountStarPointRecordsInfoWithPagerMapper.getTotal(), recordsInfoList);

    }

    private void _onCompleted(int code, String message, StreamObserver<RecordsResult> responseObserver, Long total, List<RecordsInfo> recordsInfoList) {
        responseObserver.onNext(_result(code, message, total, recordsInfoList));
        responseObserver.onCompleted();
    }

    private RecordsResult _result(int code, String message, Long total, List<RecordsInfo> recordsInfoList) {
        RecordsResult.Builder builder = RecordsResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (total != null)
            builder.setTotal(total.longValue());
        if (recordsInfoList != null && !recordsInfoList.isEmpty())
            builder.addAllRecords(recordsInfoList);
        return builder.build();
    }


    @Override
    public void unCollectionRecords(UnCollectionRecordsCondition request, StreamObserver<UnCollectionRecordsResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _unCollectionOnCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null);
            return;
        }
        if (request.getBeginTime() != 0l && !ValidatorUtil.vaildateTime(request.getBeginTime())) {
            _unCollectionOnCompleted(CodeConfigUtil.PARAMERROR, "request.begin.time.error", responseObserver, null);
            return;
        }
        if (request.getEndTime() != 0l && !ValidatorUtil.vaildateTime(request.getEndTime())) {
            _unCollectionOnCompleted(CodeConfigUtil.PARAMERROR, "request.end.time.error", responseObserver, null);
            return;
        }
        List<UnCollectionStarPointRecordsInfoMapper> unCollectionStarPointRecordsInfoMapperList = accountStarPointHandler.unCollectionRecords(
                request.getAccountToken(),
                request.getBeginTime() == 0l ? null : new Date(request.getBeginTime()),
                request.getEndTime() == 0l ? null : new Date(request.getEndTime()));
        List<UnCollectionRecordsInfo> unCollectionRecordsInfoList = new ArrayList<>();
        for (UnCollectionStarPointRecordsInfoMapper unCollectionStarPointRecordsInfoMapper : unCollectionStarPointRecordsInfoMapperList) {
            UnCollectionRecordsInfo.Builder builder = UnCollectionRecordsInfo.newBuilder();
            builder.setOperStarPoint(FormatStarPointUtil.format(unCollectionStarPointRecordsInfoMapper.getOperStarPoint()));
            builder.setLongitudeAndLatitude(unCollectionStarPointRecordsInfoMapper.getLongitudeAndLatitude());
            builder.setRecordsType(unCollectionStarPointRecordsInfoMapper.getRecordsType().name());
            if (unCollectionStarPointRecordsInfoMapper.getTaskId() != null) {
                builder.setTaskId(unCollectionStarPointRecordsInfoMapper.getTaskId());
            }
            if (unCollectionStarPointRecordsInfoMapper.getAdvertisementId() != null) {
                builder.setAdvertisementId(unCollectionStarPointRecordsInfoMapper.getAdvertisementId());
            }
            if (unCollectionStarPointRecordsInfoMapper.getRemark() != null) {
                builder.setRemark(unCollectionStarPointRecordsInfoMapper.getRemark());
            }
            builder.setCreateTime(unCollectionStarPointRecordsInfoMapper.getCreateTime().getTime());
            builder.setOverdueTime(CalendarUtil.calDateForHour(unCollectionStarPointRecordsInfoMapper.getCreateTime(), unCollectionStarPointRecordsInfoMapper.getTimeoutHour()).getTime());
            builder.setRecordToken(unCollectionStarPointRecordsInfoMapper.getRecordToken());
            unCollectionRecordsInfoList.add(builder.build());
        }
        _unCollectionOnCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, unCollectionRecordsInfoList);
    }

    private void _unCollectionOnCompleted(int code, String message, StreamObserver<UnCollectionRecordsResult> responseObserver, List<UnCollectionRecordsInfo> unCollectionRecordsInfoList) {
        responseObserver.onNext(_unCollectionResult(code, message, unCollectionRecordsInfoList));
        responseObserver.onCompleted();
    }

    private UnCollectionRecordsResult _unCollectionResult(int code, String message, List<UnCollectionRecordsInfo> unCollectionRecordsInfoList) {
        UnCollectionRecordsResult.Builder builder = UnCollectionRecordsResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (unCollectionRecordsInfoList != null && !unCollectionRecordsInfoList.isEmpty())
            builder.addAllRecords(unCollectionRecordsInfoList);
        return builder.build();
    }

    @Override
    public void ranking(RankingInfo request, StreamObserver<RankingResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorToken(request.getToken());
        if (!validatorResult.getStatus()) {
            _rankingOnCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null, null);
            return;
        }
        if (request.getPageMapper() == null) {
            _rankingOnCompleted(CodeConfigUtil.PARAMERROR, "request.pagemapper.error", responseObserver, null, null);
            return;
        }
        AccountWithStarPointInfoWithPagerMapper accountWithStarPointInfoWithPagerMapper = accountInfoHandler.ranking(validatorResult.getOrg(), request.getPageMapper().getFrom(), request.getPageMapper().getSize());
        List<RankingRecordsInfo> rankingRecordsInfoList = new ArrayList<>();
        for (AccountWithStarPointInfoMapper accountWithStarPointInfoMapper : accountWithStarPointInfoWithPagerMapper.getAccountWithStarPointInfoMapperList()) {
            rankingRecordsInfoList.add(RankingRecordsInfo.newBuilder()
                    .setThirdToken(accountWithStarPointInfoMapper.getAccountInfoMapper().getThirdToken())
                    .setAccountToken(accountWithStarPointInfoMapper.getAccountInfoMapper().getAccountToken())
                    .setStarPoint(CalculateUtil.plus(accountWithStarPointInfoMapper.getAccountStarPointInfoMapper().getAvailableStarPoint(), accountWithStarPointInfoMapper.getAccountStarPointInfoMapper().getFrozenStarPoint(), 4)).build());
        }
        _rankingOnCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, accountWithStarPointInfoWithPagerMapper.getTotal(), rankingRecordsInfoList);

    }

    private void _rankingOnCompleted(int code, String message, StreamObserver<RankingResult> responseObserver, Long total, List<RankingRecordsInfo> rankingRecordsInfoList) {
        responseObserver.onNext(_rankingResult(code, message, total, rankingRecordsInfoList));
        responseObserver.onCompleted();
    }

    private RankingResult _rankingResult(int code, String message, Long total, List<RankingRecordsInfo> rankingRecordsInfoList) {
        RankingResult.Builder builder = RankingResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (total != null)
            builder.setTotal(total.longValue());
        if (rankingRecordsInfoList != null && !rankingRecordsInfoList.isEmpty())
            builder.addAllRecords(rankingRecordsInfoList);
        return builder.build();
    }

    @Override
    public void recordsWithDay(RecordsWithDayCondition request, StreamObserver<RecordsWithDayResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _recordsWithDayOnCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null);
            return;
        }
        if (request.getEndTime() == 0l || !ValidatorUtil.vaildateTime(request.getEndTime())) {
            _recordsWithDayOnCompleted(CodeConfigUtil.PARAMERROR, "request.end.time.error", responseObserver, null);
            return;
        }
        List<AccountStarPointWithDayRecordsMapper> accountStarPointInfoMapperList = accountInfoHandler.recordsWithDay(request.getAccountToken(), new Date(request.getEndTime()), request.getDays());
        Collections.sort(accountStarPointInfoMapperList, new Comparator<AccountStarPointWithDayRecordsMapper>() {
            @Override
            public int compare(AccountStarPointWithDayRecordsMapper value1, AccountStarPointWithDayRecordsMapper value2) {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
                try {
                    long date1 = simpleDateFormat.parse(value1.getDate()).getTime();
                    long date2 = simpleDateFormat.parse(value2.getDate()).getTime();
                    if (date1 > date2) {
                        return 1;
                    }
                    if (date1 == date2) {
                        return 0;
                    }
                    return -1;
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                throw new RuntimeException("request.failed");
            }
        });
        List<RecordsWithDayInfo> recordsWithDayInfoList = new ArrayList<>();
        for (AccountStarPointWithDayRecordsMapper accountStarPointInfoMapper : accountStarPointInfoMapperList) {
            recordsWithDayInfoList.add(RecordsWithDayInfo.newBuilder()
                    .setStarPoint(FormatStarPointUtil.format(accountStarPointInfoMapper.getStarPoint()))
                    .setDate(accountStarPointInfoMapper.getDate()).build());
        }
        _recordsWithDayOnCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, recordsWithDayInfoList);
    }

    private void _recordsWithDayOnCompleted(int code, String message, StreamObserver<RecordsWithDayResult> responseObserver, List<RecordsWithDayInfo> recordsWithDayInfoList) {
        responseObserver.onNext(_recordsWithDayResult(code, message, recordsWithDayInfoList));
        responseObserver.onCompleted();
    }

    private RecordsWithDayResult _recordsWithDayResult(int code, String message, List<RecordsWithDayInfo> recordsWithDayInfoList) {
        RecordsWithDayResult.Builder builder = RecordsWithDayResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (recordsWithDayInfoList != null && !recordsWithDayInfoList.isEmpty())
            builder.addAllRecords(recordsWithDayInfoList);
        return builder.build();
    }

    @Override
    public void starPointWithTask(StarPointWithTaskInfo request, StreamObserver<StarPointWithTaskResult> responseObserver) {
        ValidatorUtil.ValidatorResult validatorResult = ValidatorUtil.validatorTokenAndAccount(request.getToken(), request.getAccountToken());
        if (!validatorResult.getStatus()) {
            _starPointWithTaskOnCompleted(validatorResult.getCode(), validatorResult.getMessage(), responseObserver, null);
            return;
        }
        if (request.getTaskIdsCount() == 0) {
            _starPointWithTaskOnCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, null);
            return;
        }
        List<StarPointWithTaskResultDetail> resultDetails = new ArrayList<>();
        List<String> taskIds = request.getTaskIdsList();
//        while(request.getTaskIdsList().iterator().hasNext()){
//            taskIds.add(request.getTaskIdsList().iterator().next());
//        }
        List<AccountStarPointWithTaskMapper> accountStarPointWithTaskMappers = accountInfoHandler.starPointWithTask(request.getAccountToken(), taskIds);
        for (AccountStarPointWithTaskMapper accountStarPointWithTaskMapper : accountStarPointWithTaskMappers) {
            resultDetails.add(StarPointWithTaskResultDetail.newBuilder()
                    .setStarPoint(FormatStarPointUtil.format(accountStarPointWithTaskMapper.getStarPoint()))
                    .setTaskId(accountStarPointWithTaskMapper.getTask()).build());
        }
        _starPointWithTaskOnCompleted(CodeConfigUtil.SUCCESS, "request.suc", responseObserver, resultDetails);
    }

    private void _starPointWithTaskOnCompleted(int code, String message, StreamObserver<StarPointWithTaskResult> responseObserver, List<StarPointWithTaskResultDetail> starPointWithTaskResultDetails) {
        responseObserver.onNext(_starPointWithTaskResult(code, message, starPointWithTaskResultDetails));
        responseObserver.onCompleted();
    }

    private StarPointWithTaskResult _starPointWithTaskResult(int code, String message, List<StarPointWithTaskResultDetail> starPointWithTaskResultDetails) {
        StarPointWithTaskResult.Builder builder = StarPointWithTaskResult.newBuilder();
        builder.setResponseEntity(ResponseEntity.newBuilder().setCode(code).setMessage(messageSource.getMessage(message, null, message, LocaleContextHolder.getLocale())).build());
        if (starPointWithTaskResultDetails != null && !starPointWithTaskResultDetails.isEmpty())
            builder.addAllRecords(starPointWithTaskResultDetails);
        return builder.build();
    }
}
