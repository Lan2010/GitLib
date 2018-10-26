package com.tianzhixing.auth.grpc.proto.idcard;

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
 *身份证验证服务类
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: IDCardAuthService.proto")
public final class IDCardAuthSerivceGrpc {

  private IDCardAuthSerivceGrpc() {}

  public static final String SERVICE_NAME = "idcard.IDCardAuthSerivce";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo,
      com.tianzhixing.auth.grpc.proto.idcard.AuthResult> getAuthIDCardMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo,
      com.tianzhixing.auth.grpc.proto.idcard.AuthResult> getAuthIDCardMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo, com.tianzhixing.auth.grpc.proto.idcard.AuthResult> getAuthIDCardMethod;
    if ((getAuthIDCardMethod = IDCardAuthSerivceGrpc.getAuthIDCardMethod) == null) {
      synchronized (IDCardAuthSerivceGrpc.class) {
        if ((getAuthIDCardMethod = IDCardAuthSerivceGrpc.getAuthIDCardMethod) == null) {
          IDCardAuthSerivceGrpc.getAuthIDCardMethod = getAuthIDCardMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo, com.tianzhixing.auth.grpc.proto.idcard.AuthResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "idcard.IDCardAuthSerivce", "authIDCard"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.auth.grpc.proto.idcard.AuthResult.getDefaultInstance()))
                  .setSchemaDescriptor(new IDCardAuthSerivceMethodDescriptorSupplier("authIDCard"))
                  .build();
          }
        }
     }
     return getAuthIDCardMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static IDCardAuthSerivceStub newStub(io.grpc.Channel channel) {
    return new IDCardAuthSerivceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static IDCardAuthSerivceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new IDCardAuthSerivceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static IDCardAuthSerivceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new IDCardAuthSerivceFutureStub(channel);
  }

  /**
   * <pre>
   *身份证验证服务类
   * </pre>
   */
  public static abstract class IDCardAuthSerivceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *验证身份证（请首先验证身份证基本规则信息）
     * </pre>
     */
    public void authIDCard(com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.idcard.AuthResult> responseObserver) {
      asyncUnimplementedUnaryCall(getAuthIDCardMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAuthIDCardMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo,
                com.tianzhixing.auth.grpc.proto.idcard.AuthResult>(
                  this, METHODID_AUTH_IDCARD)))
          .build();
    }
  }

  /**
   * <pre>
   *身份证验证服务类
   * </pre>
   */
  public static final class IDCardAuthSerivceStub extends io.grpc.stub.AbstractStub<IDCardAuthSerivceStub> {
    private IDCardAuthSerivceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IDCardAuthSerivceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IDCardAuthSerivceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IDCardAuthSerivceStub(channel, callOptions);
    }

    /**
     * <pre>
     *验证身份证（请首先验证身份证基本规则信息）
     * </pre>
     */
    public void authIDCard(com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.idcard.AuthResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getAuthIDCardMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *身份证验证服务类
   * </pre>
   */
  public static final class IDCardAuthSerivceBlockingStub extends io.grpc.stub.AbstractStub<IDCardAuthSerivceBlockingStub> {
    private IDCardAuthSerivceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IDCardAuthSerivceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IDCardAuthSerivceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IDCardAuthSerivceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *验证身份证（请首先验证身份证基本规则信息）
     * </pre>
     */
    public com.tianzhixing.auth.grpc.proto.idcard.AuthResult authIDCard(com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo request) {
      return blockingUnaryCall(
          getChannel(), getAuthIDCardMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *身份证验证服务类
   * </pre>
   */
  public static final class IDCardAuthSerivceFutureStub extends io.grpc.stub.AbstractStub<IDCardAuthSerivceFutureStub> {
    private IDCardAuthSerivceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private IDCardAuthSerivceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected IDCardAuthSerivceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new IDCardAuthSerivceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *验证身份证（请首先验证身份证基本规则信息）
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.auth.grpc.proto.idcard.AuthResult> authIDCard(
        com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getAuthIDCardMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_AUTH_IDCARD = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final IDCardAuthSerivceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(IDCardAuthSerivceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_AUTH_IDCARD:
          serviceImpl.authIDCard((com.tianzhixing.auth.grpc.proto.idcard.IDCardInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.auth.grpc.proto.idcard.AuthResult>) responseObserver);
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

  private static abstract class IDCardAuthSerivceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    IDCardAuthSerivceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.auth.grpc.proto.idcard.IDCardAuthProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("IDCardAuthSerivce");
    }
  }

  private static final class IDCardAuthSerivceFileDescriptorSupplier
      extends IDCardAuthSerivceBaseDescriptorSupplier {
    IDCardAuthSerivceFileDescriptorSupplier() {}
  }

  private static final class IDCardAuthSerivceMethodDescriptorSupplier
      extends IDCardAuthSerivceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    IDCardAuthSerivceMethodDescriptorSupplier(String methodName) {
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
      synchronized (IDCardAuthSerivceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new IDCardAuthSerivceFileDescriptorSupplier())
              .addMethod(getAuthIDCardMethod())
              .build();
        }
      }
    }
    return result;
  }
}
