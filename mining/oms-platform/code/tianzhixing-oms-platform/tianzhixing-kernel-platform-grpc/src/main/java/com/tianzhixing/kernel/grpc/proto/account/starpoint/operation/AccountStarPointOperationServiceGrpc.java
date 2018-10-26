package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

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
 *账户星点操作服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: account_starpoint_operation.proto")
public class AccountStarPointOperationServiceGrpc {

  private AccountStarPointOperationServiceGrpc() {}

  public static final String SERVICE_NAME = "account.starpoint.operation.AccountStarPointOperationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> METHOD_COLLECTION =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.operation.AccountStarPointOperationService", "collection"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> METHOD_WITHDRAW =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.operation.AccountStarPointOperationService", "withdraw"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> METHOD_AWARD =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.operation.AccountStarPointOperationService", "award"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> METHOD_BIND_ADDRESS_LIST =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.starpoint.operation.AccountStarPointOperationService", "bindAddressList"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountStarPointOperationServiceStub newStub(io.grpc.Channel channel) {
    return new AccountStarPointOperationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountStarPointOperationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AccountStarPointOperationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static AccountStarPointOperationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AccountStarPointOperationServiceFutureStub(channel);
  }

  /**
   * <pre>
   *账户星点操作服务
   * </pre>
   */
  public static abstract class AccountStarPointOperationServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *采集
     * </pre>
     */
    public void collection(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_COLLECTION, responseObserver);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public void withdraw(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_WITHDRAW, responseObserver);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public void award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_AWARD, responseObserver);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public void bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_BIND_ADDRESS_LIST, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_COLLECTION,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult>(
                  this, METHODID_COLLECTION)))
          .addMethod(
            METHOD_WITHDRAW,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            METHOD_AWARD,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>(
                  this, METHODID_AWARD)))
          .addMethod(
            METHOD_BIND_ADDRESS_LIST,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>(
                  this, METHODID_BIND_ADDRESS_LIST)))
          .build();
    }
  }

  /**
   * <pre>
   *账户星点操作服务
   * </pre>
   */
  public static final class AccountStarPointOperationServiceStub extends io.grpc.stub.AbstractStub<AccountStarPointOperationServiceStub> {
    private AccountStarPointOperationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountStarPointOperationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountStarPointOperationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountStarPointOperationServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *采集
     * </pre>
     */
    public void collection(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_COLLECTION, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public void withdraw(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_WITHDRAW, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public void award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_AWARD, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public void bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_BIND_ADDRESS_LIST, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *账户星点操作服务
   * </pre>
   */
  public static final class AccountStarPointOperationServiceBlockingStub extends io.grpc.stub.AbstractStub<AccountStarPointOperationServiceBlockingStub> {
    private AccountStarPointOperationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountStarPointOperationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountStarPointOperationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountStarPointOperationServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *采集
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult collection(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_COLLECTION, getCallOptions(), request);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult withdraw(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_WITHDRAW, getCallOptions(), request);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_AWARD, getCallOptions(), request);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_BIND_ADDRESS_LIST, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *账户星点操作服务
   * </pre>
   */
  public static final class AccountStarPointOperationServiceFutureStub extends io.grpc.stub.AbstractStub<AccountStarPointOperationServiceFutureStub> {
    private AccountStarPointOperationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountStarPointOperationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountStarPointOperationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountStarPointOperationServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *采集
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> collection(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_COLLECTION, getCallOptions()), request);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> withdraw(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_WITHDRAW, getCallOptions()), request);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> award(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_AWARD, getCallOptions()), request);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> bindAddressList(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_BIND_ADDRESS_LIST, getCallOptions()), request);
    }
  }

  private static final int METHODID_COLLECTION = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_AWARD = 2;
  private static final int METHODID_BIND_ADDRESS_LIST = 3;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountStarPointOperationServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(AccountStarPointOperationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_COLLECTION:
          serviceImpl.collection((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult>) responseObserver);
          break;
        case METHODID_WITHDRAW:
          serviceImpl.withdraw((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult>) responseObserver);
          break;
        case METHODID_AWARD:
          serviceImpl.award((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>) responseObserver);
          break;
        case METHODID_BIND_ADDRESS_LIST:
          serviceImpl.bindAddressList((com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>) responseObserver);
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
        METHOD_COLLECTION,
        METHOD_WITHDRAW,
        METHOD_AWARD,
        METHOD_BIND_ADDRESS_LIST);
  }

}
