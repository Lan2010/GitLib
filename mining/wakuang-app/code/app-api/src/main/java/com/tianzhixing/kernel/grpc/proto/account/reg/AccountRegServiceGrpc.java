package com.tianzhixing.kernel.grpc.proto.account.reg;

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
 *账户注册服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: account_reg.proto")
public final class AccountRegServiceGrpc {

  private AccountRegServiceGrpc() {}

  public static final String SERVICE_NAME = "account.reg.AccountRegService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo,
      com.tianzhixing.kernel.grpc.proto.account.reg.Result> getRegMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo,
      com.tianzhixing.kernel.grpc.proto.account.reg.Result> getRegMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo, com.tianzhixing.kernel.grpc.proto.account.reg.Result> getRegMethod;
    if ((getRegMethod = AccountRegServiceGrpc.getRegMethod) == null) {
      synchronized (AccountRegServiceGrpc.class) {
        if ((getRegMethod = AccountRegServiceGrpc.getRegMethod) == null) {
          AccountRegServiceGrpc.getRegMethod = getRegMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo, com.tianzhixing.kernel.grpc.proto.account.reg.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.reg.AccountRegService", "reg"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.reg.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountRegServiceMethodDescriptorSupplier("reg"))
                  .build();
          }
        }
     }
     return getRegMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo,
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> getCheckMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo,
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> getCheckMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo, com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> getCheckMethod;
    if ((getCheckMethod = AccountRegServiceGrpc.getCheckMethod) == null) {
      synchronized (AccountRegServiceGrpc.class) {
        if ((getCheckMethod = AccountRegServiceGrpc.getCheckMethod) == null) {
          AccountRegServiceGrpc.getCheckMethod = getCheckMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo, com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "account.reg.AccountRegService", "check"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AccountRegServiceMethodDescriptorSupplier("check"))
                  .build();
          }
        }
     }
     return getCheckMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AccountRegServiceStub newStub(io.grpc.Channel channel) {
    return new AccountRegServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AccountRegServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AccountRegServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AccountRegServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AccountRegServiceFutureStub(channel);
  }

  /**
   * <pre>
   *账户注册服务
   * </pre>
   */
  public static abstract class AccountRegServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *注册
     * </pre>
     */
    public void reg(com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.Result> responseObserver) {
      asyncUnimplementedUnaryCall(getRegMethod(), responseObserver);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public void check(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> responseObserver) {
      asyncUnimplementedUnaryCall(getCheckMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getRegMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo,
                com.tianzhixing.kernel.grpc.proto.account.reg.Result>(
                  this, METHODID_REG)))
          .addMethod(
            getCheckMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo,
                com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult>(
                  this, METHODID_CHECK)))
          .build();
    }
  }

  /**
   * <pre>
   *账户注册服务
   * </pre>
   */
  public static final class AccountRegServiceStub extends io.grpc.stub.AbstractStub<AccountRegServiceStub> {
    private AccountRegServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountRegServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountRegServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountRegServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *注册
     * </pre>
     */
    public void reg(com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getRegMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public void check(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *账户注册服务
   * </pre>
   */
  public static final class AccountRegServiceBlockingStub extends io.grpc.stub.AbstractStub<AccountRegServiceBlockingStub> {
    private AccountRegServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountRegServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountRegServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountRegServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *注册
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.reg.Result reg(com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo request) {
      return blockingUnaryCall(
          getChannel(), getRegMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult check(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request) {
      return blockingUnaryCall(
          getChannel(), getCheckMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *账户注册服务
   * </pre>
   */
  public static final class AccountRegServiceFutureStub extends io.grpc.stub.AbstractStub<AccountRegServiceFutureStub> {
    private AccountRegServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AccountRegServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AccountRegServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AccountRegServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *注册
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.reg.Result> reg(
        com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getRegMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> check(
        com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getCheckMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_REG = 0;
  private static final int METHODID_CHECK = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountRegServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AccountRegServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_REG:
          serviceImpl.reg((com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.Result>) responseObserver);
          break;
        case METHODID_CHECK:
          serviceImpl.check((com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult>) responseObserver);
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

  private static abstract class AccountRegServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AccountRegServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.account.reg.AccountRegServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AccountRegService");
    }
  }

  private static final class AccountRegServiceFileDescriptorSupplier
      extends AccountRegServiceBaseDescriptorSupplier {
    AccountRegServiceFileDescriptorSupplier() {}
  }

  private static final class AccountRegServiceMethodDescriptorSupplier
      extends AccountRegServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AccountRegServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AccountRegServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AccountRegServiceFileDescriptorSupplier())
              .addMethod(getRegMethod())
              .addMethod(getCheckMethod())
              .build();
        }
      }
    }
    return result;
  }
}
