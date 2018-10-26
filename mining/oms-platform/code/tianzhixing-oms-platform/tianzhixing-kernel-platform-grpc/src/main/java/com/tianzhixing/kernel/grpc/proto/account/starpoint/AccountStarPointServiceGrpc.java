package com.tianzhixing.kernel.grpc.proto.account.starpoint;

import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;

/**
 * <pre>
 *账户星点查询服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: account_starpoint.proto")
public class AccountStarPointServiceGrpc {

  private AccountStarPointServiceGrpc() {}

  public static final String SERVICE_NAME = "account.starpoint.AccountStarPointService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> METHOD_STAR_POINT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.AccountStarPointService", "starPoint"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> METHOD_RANKING =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.AccountStarPointService", "ranking"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> METHOD_RECORDS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.AccountStarPointService", "records"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> METHOD_UN_COLLECTION_RECORDS =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.AccountStarPointService", "unCollectionRecords"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> METHOD_RECORDS_WITH_DAY =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.AccountStarPointService", "recordsWithDay"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> METHOD_STAR_POINT_WITH_TASK =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.AccountStarPointService", "starPointWithTask"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountStarPointServiceStub newStub(io.grpc.Channel channel) {
    return new AccountStarPointServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountStarPointServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AccountStarPointServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static AccountStarPointServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AccountStarPointServiceFutureStub(channel);
  }

  /**
   * <pre>
   *账户星点查询服务
   * </pre>
   */
  public static abstract class AccountStarPointServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *查询
     * </pre>
     */
    public void starPoint(com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STAR_POINT, responseObserver);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public void ranking(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RANKING, responseObserver);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public void records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECORDS, responseObserver);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public void unCollectionRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UN_COLLECTION_RECORDS, responseObserver);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public void recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_RECORDS_WITH_DAY, responseObserver);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public void starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_STAR_POINT_WITH_TASK, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_STAR_POINT,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.Result>(
                  this, METHODID_STAR_POINT)))
          .addMethod(
            METHOD_RANKING,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult>(
                  this, METHODID_RANKING)))
          .addMethod(
            METHOD_RECORDS,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult>(
                  this, METHODID_RECORDS)))
          .addMethod(
            METHOD_UN_COLLECTION_RECORDS,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult>(
                  this, METHODID_UN_COLLECTION_RECORDS)))
          .addMethod(
            METHOD_RECORDS_WITH_DAY,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult>(
                  this, METHODID_RECORDS_WITH_DAY)))
          .addMethod(
            METHOD_STAR_POINT_WITH_TASK,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult>(
                  this, METHODID_STAR_POINT_WITH_TASK)))
          .build();
    }
  }

  /**
   * <pre>
   *账户星点查询服务
   * </pre>
   */
  public static final class AccountStarPointServiceStub extends io.grpc.stub.AbstractStub<AccountStarPointServiceStub> {
    private AccountStarPointServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountStarPointServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountStarPointServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountStarPointServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *查询
     * </pre>
     */
    public void starPoint(com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_STAR_POINT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public void ranking(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RANKING, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public void records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECORDS, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public void unCollectionRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UN_COLLECTION_RECORDS, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public void recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_RECORDS_WITH_DAY, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public void starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_STAR_POINT_WITH_TASK, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *账户星点查询服务
   * </pre>
   */
  public static final class AccountStarPointServiceBlockingStub extends io.grpc.stub.AbstractStub<AccountStarPointServiceBlockingStub> {
    private AccountStarPointServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountStarPointServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountStarPointServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountStarPointServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *查询
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.Result starPoint(com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_STAR_POINT, getCallOptions(), request);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult ranking(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RANKING, getCallOptions(), request);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECORDS, getCallOptions(), request);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult unCollectionRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UN_COLLECTION_RECORDS, getCallOptions(), request);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request) {
      return blockingUnaryCall(
          getChannel(), METHOD_RECORDS_WITH_DAY, getCallOptions(), request);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_STAR_POINT_WITH_TASK, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *账户星点查询服务
   * </pre>
   */
  public static final class AccountStarPointServiceFutureStub extends io.grpc.stub.AbstractStub<AccountStarPointServiceFutureStub> {
    private AccountStarPointServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountStarPointServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountStarPointServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountStarPointServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *查询
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> starPoint(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_STAR_POINT, getCallOptions()), request);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> ranking(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RANKING, getCallOptions()), request);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> records(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECORDS, getCallOptions()), request);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> unCollectionRecords(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UN_COLLECTION_RECORDS, getCallOptions()), request);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> recordsWithDay(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_RECORDS_WITH_DAY, getCallOptions()), request);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> starPointWithTask(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_STAR_POINT_WITH_TASK, getCallOptions()), request);
    }
  }

  private static final int METHODID_STAR_POINT = 0;
  private static final int METHODID_RANKING = 1;
  private static final int METHODID_RECORDS = 2;
  private static final int METHODID_UN_COLLECTION_RECORDS = 3;
  private static final int METHODID_RECORDS_WITH_DAY = 4;
  private static final int METHODID_STAR_POINT_WITH_TASK = 5;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountStarPointServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(AccountStarPointServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_STAR_POINT:
          serviceImpl.starPoint((com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.Result>) responseObserver);
          break;
        case METHODID_RANKING:
          serviceImpl.ranking((com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult>) responseObserver);
          break;
        case METHODID_RECORDS:
          serviceImpl.records((com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult>) responseObserver);
          break;
        case METHODID_UN_COLLECTION_RECORDS:
          serviceImpl.unCollectionRecords((com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult>) responseObserver);
          break;
        case METHODID_RECORDS_WITH_DAY:
          serviceImpl.recordsWithDay((com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult>) responseObserver);
          break;
        case METHODID_STAR_POINT_WITH_TASK:
          serviceImpl.starPointWithTask((com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_STAR_POINT,
        METHOD_RANKING,
        METHOD_RECORDS,
        METHOD_UN_COLLECTION_RECORDS,
        METHOD_RECORDS_WITH_DAY,
        METHOD_STAR_POINT_WITH_TASK);
  }

}
