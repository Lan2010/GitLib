package com.tianzhixing.kernel.grpc.proto.account.reg;

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
 *账户注册服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: account_reg.proto")
public class AccountRegServiceGrpc {

  private AccountRegServiceGrpc() {}

  public static final String SERVICE_NAME = "account.reg.AccountRegService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo,
      com.tianzhixing.kernel.grpc.proto.account.reg.Result> METHOD_REG =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.reg.AccountRegService", "reg"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.reg.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo,
      com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> METHOD_CHECK =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "account.reg.AccountRegService", "check"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult.getDefaultInstance()));

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
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
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
      asyncUnimplementedUnaryCall(METHOD_REG, responseObserver);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public void check(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CHECK, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_REG,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.account.reg.AccountInfo,
                com.tianzhixing.kernel.grpc.proto.account.reg.Result>(
                  this, METHODID_REG)))
          .addMethod(
            METHOD_CHECK,
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
          getChannel().newCall(METHOD_REG, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public void check(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CHECK, getCallOptions()), request, responseObserver);
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
          getChannel(), METHOD_REG, getCallOptions(), request);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult check(com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CHECK, getCallOptions(), request);
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
          getChannel().newCall(METHOD_REG, getCallOptions()), request);
    }

    /**
     * <pre>
     *检查手机号
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileResult> check(
        com.tianzhixing.kernel.grpc.proto.account.reg.CheckMobileInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CHECK, getCallOptions()), request);
    }
  }

  private static final int METHODID_REG = 0;
  private static final int METHODID_CHECK = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AccountRegServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(AccountRegServiceImplBase serviceImpl, int methodId) {
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

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    return new io.grpc.ServiceDescriptor(SERVICE_NAME,
        METHOD_REG,
        METHOD_CHECK);
  }

}
