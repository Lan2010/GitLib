package com.tianzhixing.auth.grpc.proto.mobile;

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
 *手机验证服务类
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: MobileValidationService.proto")
public final class MobileValidationSerivceGrpc {

  private MobileValidationSerivceGrpc() {}

  public static final String SERVICE_NAME = "mobile.MobileValidationSerivce";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo,
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult> getSendMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo,
      com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult> getSendMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo, com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult> getSendMethod;
    if ((getSendMethod = MobileValidationSerivceGrpc.getSendMethod) == null) {
      synchronized (MobileValidationSerivceGrpc.class) {
        if ((getSendMethod = MobileValidationSerivceGrpc.getSendMethod) == null) {
          MobileValidationSerivceGrpc.getSendMethod = getSendMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo, com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "mobile.MobileValidationSerivce", "send"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult.getDefaultInstance()))
                  .setSchemaDescriptor(new MobileValidationSerivceMethodDescriptorSupplier("send"))
                  .build();
          }
        }
     }
     return getSendMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo,
      com.tianzhixing.auth.grpc.proto.mobile.ValidationResult> getValidateMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo,
      com.tianzhixing.auth.grpc.proto.mobile.ValidationResult> getValidateMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo, com.tianzhixing.auth.grpc.proto.mobile.ValidationResult> getValidateMethod;
    if ((getValidateMethod = MobileValidationSerivceGrpc.getValidateMethod) == null) {
      synchronized (MobileValidationSerivceGrpc.class) {
        if ((getValidateMethod = MobileValidationSerivceGrpc.getValidateMethod) == null) {
          MobileValidationSerivceGrpc.getValidateMethod = getValidateMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo, com.tianzhixing.auth.grpc.proto.mobile.ValidationResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "mobile.MobileValidationSerivce", "validate"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.mobile.ValidationResult.getDefaultInstance()))
                  .setSchemaDescriptor(new MobileValidationSerivceMethodDescriptorSupplier("validate"))
                  .build();
          }
        }
     }
     return getValidateMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo,
      com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo> getSearchMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo,
      com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo> getSearchMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo, com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo> getSearchMethod;
    if ((getSearchMethod = MobileValidationSerivceGrpc.getSearchMethod) == null) {
      synchronized (MobileValidationSerivceGrpc.class) {
        if ((getSearchMethod = MobileValidationSerivceGrpc.getSearchMethod) == null) {
          MobileValidationSerivceGrpc.getSearchMethod = getSearchMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo, com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "mobile.MobileValidationSerivce", "search"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo.getDefaultInstance()))
                  .setSchemaDescriptor(new MobileValidationSerivceMethodDescriptorSupplier("search"))
                  .build();
          }
        }
     }
     return getSearchMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static MobileValidationSerivceStub newStub(io.grpc.Channel channel) {
    return new MobileValidationSerivceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static MobileValidationSerivceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new MobileValidationSerivceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static MobileValidationSerivceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new MobileValidationSerivceFutureStub(channel);
  }

  /**
   * <pre>
   *手机验证服务类
   * </pre>
   */
  public static abstract class MobileValidationSerivceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *发送验证码
     * </pre>
     */
    public void send(com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult> responseObserver) {
      asyncUnimplementedUnaryCall(getSendMethod(), responseObserver);
    }

    /**
     * <pre>
     *执行验证
     * </pre>
     */
    public void validate(com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.ValidationResult> responseObserver) {
      asyncUnimplementedUnaryCall(getValidateMethod(), responseObserver);
    }

    /**
     * <pre>
     *根据手机号码查询验证码（仅能够检索到24小时内且未被使用过的记录信息）
     * </pre>
     */
    public void search(com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo> responseObserver) {
      asyncUnimplementedUnaryCall(getSearchMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getSendMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo,
                com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult>(
                  this, METHODID_SEND)))
          .addMethod(
            getValidateMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo,
                com.tianzhixing.auth.grpc.proto.mobile.ValidationResult>(
                  this, METHODID_VALIDATE)))
          .addMethod(
            getSearchMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo,
                com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo>(
                  this, METHODID_SEARCH)))
          .build();
    }
  }

  /**
   * <pre>
   *手机验证服务类
   * </pre>
   */
  public static final class MobileValidationSerivceStub extends io.grpc.stub.AbstractStub<MobileValidationSerivceStub> {
    private MobileValidationSerivceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MobileValidationSerivceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MobileValidationSerivceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MobileValidationSerivceStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送验证码
     * </pre>
     */
    public void send(com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *执行验证
     * </pre>
     */
    public void validate(com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.ValidationResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getValidateMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *根据手机号码查询验证码（仅能够检索到24小时内且未被使用过的记录信息）
     * </pre>
     */
    public void search(com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *手机验证服务类
   * </pre>
   */
  public static final class MobileValidationSerivceBlockingStub extends io.grpc.stub.AbstractStub<MobileValidationSerivceBlockingStub> {
    private MobileValidationSerivceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MobileValidationSerivceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MobileValidationSerivceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MobileValidationSerivceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送验证码
     * </pre>
     */
    public com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult send(com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo request) {
      return blockingUnaryCall(
          getChannel(), getSendMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *执行验证
     * </pre>
     */
    public com.tianzhixing.auth.grpc.proto.mobile.ValidationResult validate(com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo request) {
      return blockingUnaryCall(
          getChannel(), getValidateMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *根据手机号码查询验证码（仅能够检索到24小时内且未被使用过的记录信息）
     * </pre>
     */
    public com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo search(com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo request) {
      return blockingUnaryCall(
          getChannel(), getSearchMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *手机验证服务类
   * </pre>
   */
  public static final class MobileValidationSerivceFutureStub extends io.grpc.stub.AbstractStub<MobileValidationSerivceFutureStub> {
    private MobileValidationSerivceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private MobileValidationSerivceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected MobileValidationSerivceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new MobileValidationSerivceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *发送验证码
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult> send(
        com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getSendMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *执行验证
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.auth.grpc.proto.mobile.ValidationResult> validate(
        com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getValidateMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *根据手机号码查询验证码（仅能够检索到24小时内且未被使用过的记录信息）
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo> search(
        com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getSearchMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_SEND = 0;
  private static final int METHODID_VALIDATE = 1;
  private static final int METHODID_SEARCH = 2;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final MobileValidationSerivceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(MobileValidationSerivceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_SEND:
          serviceImpl.send((com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.SendCodeResult>) responseObserver);
          break;
        case METHODID_VALIDATE:
          serviceImpl.validate((com.tianzhixing.auth.grpc.proto.mobile.ValidationCodeInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.ValidationResult>) responseObserver);
          break;
        case METHODID_SEARCH:
          serviceImpl.search((com.tianzhixing.auth.grpc.proto.mobile.SearchCodeInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.mobile.SearchResultInfo>) responseObserver);
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

  private static abstract class MobileValidationSerivceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    MobileValidationSerivceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.auth.grpc.proto.mobile.MobileValidationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("MobileValidationSerivce");
    }
  }

  private static final class MobileValidationSerivceFileDescriptorSupplier
      extends MobileValidationSerivceBaseDescriptorSupplier {
    MobileValidationSerivceFileDescriptorSupplier() {}
  }

  private static final class MobileValidationSerivceMethodDescriptorSupplier
      extends MobileValidationSerivceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    MobileValidationSerivceMethodDescriptorSupplier(String methodName) {
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
      synchronized (MobileValidationSerivceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new MobileValidationSerivceFileDescriptorSupplier())
              .addMethod(getSendMethod())
              .addMethod(getValidateMethod())
              .addMethod(getSearchMethod())
              .build();
        }
      }
    }
    return result;
  }
}
