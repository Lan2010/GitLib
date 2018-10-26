package com.tianzhixing.kernel.grpc.proto.account.starpoint;

import static io.grpc.MethodDescriptor.generateFullMethodName;
import static io.grpc.stub.ClientCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ClientCalls.asyncClientStreamingCall;
import static io.grpc.stub.ClientCalls.asyncServerStreamingCall;
import static io.grpc.stub.ClientCalls.asyncUnaryCall;
import static io.grpc.stub.ClientCalls.blockingServerStreamingCall;
import static io.grpc.stub.ClientCalls.blockingUnaryCall;
import static io.grpc.stub.ClientCalls.futureUnaryCall;
import static io.grpc.stub.ServerCalls.asyncBidiStreamingCall;
import static io.grpc.stub.ServerCalls.asyncClientStreamingCall;
import static io.grpc.stub.ServerCalls.asyncServerStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnaryCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedStreamingCall;
import static io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall;

/**
 * <pre>
 *账户星点查询服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: account_starpoint.proto")
public final class AccountStarPointServiceGrpc {

  private AccountStarPointServiceGrpc() {}

  public static final String SERVICE_NAME = "account.starpoint.AccountStarPointService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> getStarPointMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> getStarPointMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.Result> getStarPointMethod;
    if ((getStarPointMethod = AccountStarPointServiceGrpc.getStarPointMethod) == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        if ((getStarPointMethod = AccountStarPointServiceGrpc.getStarPointMethod) == null) {
          AccountStarPointServiceGrpc.getStarPointMethod = getStarPointMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.AccountStarPointService", "starPoint"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointServiceMethodDescriptorSupplier("starPoint"))
                  .build();
          }
        }
     }
     return getStarPointMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> getRankingMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> getRankingMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> getRankingMethod;
    if ((getRankingMethod = AccountStarPointServiceGrpc.getRankingMethod) == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        if ((getRankingMethod = AccountStarPointServiceGrpc.getRankingMethod) == null) {
          AccountStarPointServiceGrpc.getRankingMethod = getRankingMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.AccountStarPointService", "ranking"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointServiceMethodDescriptorSupplier("ranking"))
                  .build();
          }
        }
     }
     return getRankingMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> getRecordsMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> getRecordsMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> getRecordsMethod;
    if ((getRecordsMethod = AccountStarPointServiceGrpc.getRecordsMethod) == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        if ((getRecordsMethod = AccountStarPointServiceGrpc.getRecordsMethod) == null) {
          AccountStarPointServiceGrpc.getRecordsMethod = getRecordsMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.AccountStarPointService", "records"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointServiceMethodDescriptorSupplier("records"))
                  .build();
          }
        }
     }
     return getRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> getUnCollectionRecordsMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> getUnCollectionRecordsMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition, com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> getUnCollectionRecordsMethod;
    if ((getUnCollectionRecordsMethod = AccountStarPointServiceGrpc.getUnCollectionRecordsMethod) == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        if ((getUnCollectionRecordsMethod = AccountStarPointServiceGrpc.getUnCollectionRecordsMethod) == null) {
          AccountStarPointServiceGrpc.getUnCollectionRecordsMethod = getUnCollectionRecordsMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition, com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.AccountStarPointService", "unCollectionRecords"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointServiceMethodDescriptorSupplier("unCollectionRecords"))
                  .build();
          }
        }
     }
     return getUnCollectionRecordsMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> getRecordsWithDayMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> getRecordsWithDayMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> getRecordsWithDayMethod;
    if ((getRecordsWithDayMethod = AccountStarPointServiceGrpc.getRecordsWithDayMethod) == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        if ((getRecordsWithDayMethod = AccountStarPointServiceGrpc.getRecordsWithDayMethod) == null) {
          AccountStarPointServiceGrpc.getRecordsWithDayMethod = getRecordsWithDayMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition, com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.AccountStarPointService", "recordsWithDay"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointServiceMethodDescriptorSupplier("recordsWithDay"))
                  .build();
          }
        }
     }
     return getRecordsWithDayMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> getStarPointWithTaskMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> getStarPointWithTaskMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> getStarPointWithTaskMethod;
    if ((getStarPointWithTaskMethod = AccountStarPointServiceGrpc.getStarPointWithTaskMethod) == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        if ((getStarPointWithTaskMethod = AccountStarPointServiceGrpc.getStarPointWithTaskMethod) == null) {
          AccountStarPointServiceGrpc.getStarPointWithTaskMethod = getStarPointWithTaskMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.AccountStarPointService", "starPointWithTask"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointServiceMethodDescriptorSupplier("starPointWithTask"))
                  .build();
          }
        }
     }
     return getStarPointWithTaskMethod;
  }

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
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
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
      asyncUnimplementedUnaryCall(getStarPointMethod(), responseObserver);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public void ranking(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRankingMethod(), responseObserver);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public void records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public void unCollectionRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> responseObserver) {
      asyncUnimplementedUnaryCall(getUnCollectionRecordsMethod(), responseObserver);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public void recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> responseObserver) {
      asyncUnimplementedUnaryCall(getRecordsWithDayMethod(), responseObserver);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public void starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> responseObserver) {
      asyncUnimplementedUnaryCall(getStarPointWithTaskMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getStarPointMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.Result>(
                  this, METHODID_STAR_POINT)))
          .addMethod(
            getRankingMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult>(
                  this, METHODID_RANKING)))
          .addMethod(
            getRecordsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult>(
                  this, METHODID_RECORDS)))
          .addMethod(
            getUnCollectionRecordsMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult>(
                  this, METHODID_UN_COLLECTION_RECORDS)))
          .addMethod(
            getRecordsWithDayMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult>(
                  this, METHODID_RECORDS_WITH_DAY)))
          .addMethod(
            getStarPointWithTaskMethod(),
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
          getChannel().newCall(getStarPointMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public void ranking(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRankingMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public void records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public void unCollectionRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getUnCollectionRecordsMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public void recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRecordsWithDayMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public void starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getStarPointWithTaskMethod(), getCallOptions()), request, responseObserver);
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
          getChannel(), getStarPointMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult ranking(com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request) {
      return blockingUnaryCall(
          getChannel(), getRankingMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult records(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request) {
      return blockingUnaryCall(
          getChannel(), getRecordsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult unCollectionRecords(com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request) {
      return blockingUnaryCall(
          getChannel(), getUnCollectionRecordsMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult recordsWithDay(com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request) {
      return blockingUnaryCall(
          getChannel(), getRecordsWithDayMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult starPointWithTask(com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request) {
      return blockingUnaryCall(
          getChannel(), getStarPointWithTaskMethod(), getCallOptions(), request);
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
          getChannel().newCall(getStarPointMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *查询排行
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingResult> ranking(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RankingInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getRankingMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *查询流水
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsResult> records(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsCondition request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *查询未采集的星点记录
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsResult> unCollectionRecords(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsCondition request) {
      return futureUnaryCall(
          getChannel().newCall(getUnCollectionRecordsMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *查询当前日期内资金流水统计，以天为单位
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayResult> recordsWithDay(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.RecordsWithDayCondition request) {
      return futureUnaryCall(
          getChannel().newCall(getRecordsWithDayMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *根据任务查询用户已采集星点数
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskResult> starPointWithTask(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.StarPointWithTaskInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getStarPointWithTaskMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_STAR_POINT = 0;
  private static final int METHODID_RANKING = 1;
  private static final int METHODID_RECORDS = 2;
  private static final int METHODID_UN_COLLECTION_RECORDS = 3;
  private static final int METHODID_RECORDS_WITH_DAY = 4;
  private static final int METHODID_STAR_POINT_WITH_TASK = 5;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountStarPointServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AccountStarPointServiceImplBase serviceImpl, int methodId) {
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

  private static abstract class AccountStarPointServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountStarPointServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.AccountStarPointServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountStarPointService");
    }
  }

  private static final class AccountStarPointServiceFileDescriptorSupplier
      extends AccountStarPointServiceBaseDescriptorSupplier {
    AccountStarPointServiceFileDescriptorSupplier() {}
  }

  private static final class AccountStarPointServiceMethodDescriptorSupplier
      extends AccountStarPointServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountStarPointServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (AccountStarPointServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountStarPointServiceFileDescriptorSupplier())
              .addMethod(getStarPointMethod())
              .addMethod(getRankingMethod())
              .addMethod(getRecordsMethod())
              .addMethod(getUnCollectionRecordsMethod())
              .addMethod(getRecordsWithDayMethod())
              .addMethod(getStarPointWithTaskMethod())
              .build();
        }
      }
    }
    return result;
  }
}
