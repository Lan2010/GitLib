package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

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
 *账户星点操作服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: account_starpoint_operation.proto")
public final class AccountStarPointOperationServiceGrpc {

  private AccountStarPointOperationServiceGrpc() {}

  public static final String SERVICE_NAME = "account.starpoint.operation.AccountStarPointOperationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> getCollectionMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> getCollectionMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult> getCollectionMethod;
    if ((getCollectionMethod = AccountStarPointOperationServiceGrpc.getCollectionMethod) == null) {
      synchronized (AccountStarPointOperationServiceGrpc.class) {
        if ((getCollectionMethod = AccountStarPointOperationServiceGrpc.getCollectionMethod) == null) {
          AccountStarPointOperationServiceGrpc.getCollectionMethod = getCollectionMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.operation.AccountStarPointOperationService", "collection"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointOperationServiceMethodDescriptorSupplier("collection"))
                  .build();
          }
        }
     }
     return getCollectionMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> getWithdrawMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> getWithdrawMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> getWithdrawMethod;
    if ((getWithdrawMethod = AccountStarPointOperationServiceGrpc.getWithdrawMethod) == null) {
      synchronized (AccountStarPointOperationServiceGrpc.class) {
        if ((getWithdrawMethod = AccountStarPointOperationServiceGrpc.getWithdrawMethod) == null) {
          AccountStarPointOperationServiceGrpc.getWithdrawMethod = getWithdrawMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.operation.AccountStarPointOperationService", "withdraw"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointOperationServiceMethodDescriptorSupplier("withdraw"))
                  .build();
          }
        }
     }
     return getWithdrawMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> getAwardMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> getAwardMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> getAwardMethod;
    if ((getAwardMethod = AccountStarPointOperationServiceGrpc.getAwardMethod) == null) {
      synchronized (AccountStarPointOperationServiceGrpc.class) {
        if ((getAwardMethod = AccountStarPointOperationServiceGrpc.getAwardMethod) == null) {
          AccountStarPointOperationServiceGrpc.getAwardMethod = getAwardMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.operation.AccountStarPointOperationService", "award"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointOperationServiceMethodDescriptorSupplier("award"))
                  .build();
          }
        }
     }
     return getAwardMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> getBindAddressListMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo,
      com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> getBindAddressListMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> getBindAddressListMethod;
    if ((getBindAddressListMethod = AccountStarPointOperationServiceGrpc.getBindAddressListMethod) == null) {
      synchronized (AccountStarPointOperationServiceGrpc.class) {
        if ((getBindAddressListMethod = AccountStarPointOperationServiceGrpc.getBindAddressListMethod) == null) {
          AccountStarPointOperationServiceGrpc.getBindAddressListMethod = getBindAddressListMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo, com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.starpoint.operation.AccountStarPointOperationService", "bindAddressList"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountStarPointOperationServiceMethodDescriptorSupplier("bindAddressList"))
                  .build();
          }
        }
     }
     return getBindAddressListMethod;
  }

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
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
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
      asyncUnimplementedUnaryCall(getCollectionMethod(), responseObserver);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public void withdraw(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> responseObserver) {
      asyncUnimplementedUnaryCall(getWithdrawMethod(), responseObserver);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public void award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnimplementedUnaryCall(getAwardMethod(), responseObserver);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public void bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnimplementedUnaryCall(getBindAddressListMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getCollectionMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionStarPointInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.CollectionResult>(
                  this, METHODID_COLLECTION)))
          .addMethod(
            getWithdrawMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult>(
                  this, METHODID_WITHDRAW)))
          .addMethod(
            getAwardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo,
                com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult>(
                  this, METHODID_AWARD)))
          .addMethod(
            getBindAddressListMethod(),
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
          getChannel().newCall(getCollectionMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public void withdraw(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public void award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAwardMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public void bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getBindAddressListMethod(), getCallOptions()), request, responseObserver);
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
          getChannel(), getCollectionMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult withdraw(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request) {
      return blockingUnaryCall(
          getChannel(), getWithdrawMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult award(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request) {
      return blockingUnaryCall(
          getChannel(), getAwardMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult bindAddressList(com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request) {
      return blockingUnaryCall(
          getChannel(), getBindAddressListMethod(), getCallOptions(), request);
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
          getChannel().newCall(getCollectionMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *提现 - 暂时未开启
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawResult> withdraw(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.WithdrawInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getWithdrawMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *奖励
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> award(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getAwardMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *绑定通讯录奖励
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AwardResult> bindAddressList(
        com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.BindAddressAwardInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getBindAddressListMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_COLLECTION = 0;
  private static final int METHODID_WITHDRAW = 1;
  private static final int METHODID_AWARD = 2;
  private static final int METHODID_BIND_ADDRESS_LIST = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountStarPointOperationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AccountStarPointOperationServiceImplBase serviceImpl, int methodId) {
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

  private static abstract class AccountStarPointOperationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountStarPointOperationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.starpoint.operation.AccountStarPointOperationServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountStarPointOperationService");
    }
  }

  private static final class AccountStarPointOperationServiceFileDescriptorSupplier
      extends AccountStarPointOperationServiceBaseDescriptorSupplier {
    AccountStarPointOperationServiceFileDescriptorSupplier() {}
  }

  private static final class AccountStarPointOperationServiceMethodDescriptorSupplier
      extends AccountStarPointOperationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountStarPointOperationServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountStarPointOperationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountStarPointOperationServiceFileDescriptorSupplier())
              .addMethod(getCollectionMethod())
              .addMethod(getWithdrawMethod())
              .addMethod(getAwardMethod())
              .addMethod(getBindAddressListMethod())
              .build();
        }
      }
    }
    return result;
  }
}
