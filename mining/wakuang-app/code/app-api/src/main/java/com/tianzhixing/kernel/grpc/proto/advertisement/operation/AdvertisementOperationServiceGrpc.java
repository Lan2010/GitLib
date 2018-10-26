package com.tianzhixing.kernel.grpc.proto.advertisement.operation;

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
 *广告操作服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: advertisement_operation.proto")
public final class AdvertisementOperationServiceGrpc {

  private AdvertisementOperationServiceGrpc() {}

  public static final String SERVICE_NAME = "advertisement.operation.AdvertisementOperationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo,
      com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult> getClickOrAccessMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo,
      com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult> getClickOrAccessMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo, com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult> getClickOrAccessMethod;
    if ((getClickOrAccessMethod = AdvertisementOperationServiceGrpc.getClickOrAccessMethod) == null) {
      synchronized (AdvertisementOperationServiceGrpc.class) {
        if ((getClickOrAccessMethod = AdvertisementOperationServiceGrpc.getClickOrAccessMethod) == null) {
          AdvertisementOperationServiceGrpc.getClickOrAccessMethod = getClickOrAccessMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo, com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "advertisement.operation.AdvertisementOperationService", "clickOrAccess"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult.getDefaultInstance()))
                  .setSchemaDescriptor(new AdvertisementOperationServiceMethodDescriptorSupplier("clickOrAccess"))
                  .build();
          }
        }
     }
     return getClickOrAccessMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static AdvertisementOperationServiceStub newStub(io.grpc.Channel channel) {
    return new AdvertisementOperationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static AdvertisementOperationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new AdvertisementOperationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static AdvertisementOperationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new AdvertisementOperationServiceFutureStub(channel);
  }

  /**
   * <pre>
   *广告操作服务
   * </pre>
   */
  public static abstract class AdvertisementOperationServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *广告操作接口
     * </pre>
     */
    public void clickOrAccess(com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult> responseObserver) {
      asyncUnimplementedUnaryCall(getClickOrAccessMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getClickOrAccessMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo,
                com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult>(
                  this, METHODID_CLICK_OR_ACCESS)))
          .build();
    }
  }

  /**
   * <pre>
   *广告操作服务
   * </pre>
   */
  public static final class AdvertisementOperationServiceStub extends io.grpc.stub.AbstractStub<AdvertisementOperationServiceStub> {
    private AdvertisementOperationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdvertisementOperationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdvertisementOperationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdvertisementOperationServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *广告操作接口
     * </pre>
     */
    public void clickOrAccess(com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getClickOrAccessMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *广告操作服务
   * </pre>
   */
  public static final class AdvertisementOperationServiceBlockingStub extends io.grpc.stub.AbstractStub<AdvertisementOperationServiceBlockingStub> {
    private AdvertisementOperationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdvertisementOperationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdvertisementOperationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdvertisementOperationServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *广告操作接口
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult clickOrAccess(com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo request) {
      return blockingUnaryCall(
          getChannel(), getClickOrAccessMethod(), getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *广告操作服务
   * </pre>
   */
  public static final class AdvertisementOperationServiceFutureStub extends io.grpc.stub.AbstractStub<AdvertisementOperationServiceFutureStub> {
    private AdvertisementOperationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private AdvertisementOperationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected AdvertisementOperationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new AdvertisementOperationServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *广告操作接口
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult> clickOrAccess(
        com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getClickOrAccessMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CLICK_OR_ACCESS = 0;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AdvertisementOperationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(AdvertisementOperationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CLICK_OR_ACCESS:
          serviceImpl.clickOrAccess((com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.advertisement.operation.ClickResult>) responseObserver);
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

  private static abstract class AdvertisementOperationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    AdvertisementOperationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.advertisement.operation.AdvertisementOperationServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("AdvertisementOperationService");
    }
  }

  private static final class AdvertisementOperationServiceFileDescriptorSupplier
      extends AdvertisementOperationServiceBaseDescriptorSupplier {
    AdvertisementOperationServiceFileDescriptorSupplier() {}
  }

  private static final class AdvertisementOperationServiceMethodDescriptorSupplier
      extends AdvertisementOperationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    AdvertisementOperationServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (AdvertisementOperationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new AdvertisementOperationServiceFileDescriptorSupplier())
              .addMethod(getClickOrAccessMethod())
              .build();
        }
      }
    }
    return result;
  }
}
