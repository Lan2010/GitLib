package com.tianzhixing.kernel.grpc.proto.device.operation;

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
 *设备操作服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: device_operation.proto")
public class DeviceOperationServiceGrpc {

  private DeviceOperationServiceGrpc() {}

  public static final String SERVICE_NAME = "device.operation.DeviceOperationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo,
      com.tianzhixing.kernel.grpc.proto.device.operation.Result> METHOD_BIND =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "device.operation.DeviceOperationService", "bind"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.device.operation.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo,
      com.tianzhixing.kernel.grpc.proto.device.operation.Result> METHOD_UN_BIND =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "device.operation.DeviceOperationService", "unBind"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.device.operation.Result.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static DeviceOperationServiceStub newStub(io.grpc.Channel channel) {
    return new DeviceOperationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static DeviceOperationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new DeviceOperationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static DeviceOperationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new DeviceOperationServiceFutureStub(channel);
  }

  /**
   * <pre>
   *设备操作服务
   * </pre>
   */
  public static abstract class DeviceOperationServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *绑定
     * </pre>
     */
    public void bind(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.device.operation.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_BIND, responseObserver);
    }

    /**
     * <pre>
     *解除绑定
     * </pre>
     */
    public void unBind(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.device.operation.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_UN_BIND, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_BIND,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo,
                com.tianzhixing.kernel.grpc.proto.device.operation.Result>(
                  this, METHODID_BIND)))
          .addMethod(
            METHOD_UN_BIND,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo,
                com.tianzhixing.kernel.grpc.proto.device.operation.Result>(
                  this, METHODID_UN_BIND)))
          .build();
    }
  }

  /**
   * <pre>
   *设备操作服务
   * </pre>
   */
  public static final class DeviceOperationServiceStub extends io.grpc.stub.AbstractStub<DeviceOperationServiceStub> {
    private DeviceOperationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DeviceOperationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceOperationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DeviceOperationServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *绑定
     * </pre>
     */
    public void bind(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.device.operation.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_BIND, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *解除绑定
     * </pre>
     */
    public void unBind(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.device.operation.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_UN_BIND, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *设备操作服务
   * </pre>
   */
  public static final class DeviceOperationServiceBlockingStub extends io.grpc.stub.AbstractStub<DeviceOperationServiceBlockingStub> {
    private DeviceOperationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DeviceOperationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceOperationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DeviceOperationServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *绑定
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.device.operation.Result bind(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_BIND, getCallOptions(), request);
    }

    /**
     * <pre>
     *解除绑定
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.device.operation.Result unBind(com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_UN_BIND, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *设备操作服务
   * </pre>
   */
  public static final class DeviceOperationServiceFutureStub extends io.grpc.stub.AbstractStub<DeviceOperationServiceFutureStub> {
    private DeviceOperationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private DeviceOperationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected DeviceOperationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new DeviceOperationServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *绑定
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.device.operation.Result> bind(
        com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_BIND, getCallOptions()), request);
    }

    /**
     * <pre>
     *解除绑定
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.device.operation.Result> unBind(
        com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_UN_BIND, getCallOptions()), request);
    }
  }

  private static final int METHODID_BIND = 0;
  private static final int METHODID_UN_BIND = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final DeviceOperationServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(DeviceOperationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_BIND:
          serviceImpl.bind((com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.device.operation.Result>) responseObserver);
          break;
        case METHODID_UN_BIND:
          serviceImpl.unBind((com.tianzhixing.kernel.grpc.proto.device.operation.DeviceInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.device.operation.Result>) responseObserver);
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
        METHOD_BIND,
        METHOD_UN_BIND);
  }

}
