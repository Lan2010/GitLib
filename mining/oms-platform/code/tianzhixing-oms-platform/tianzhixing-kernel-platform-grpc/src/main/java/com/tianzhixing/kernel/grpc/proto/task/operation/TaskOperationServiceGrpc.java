package com.tianzhixing.kernel.grpc.proto.task.operation;

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
 *任务操作服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.0.0)",
    comments = "Source: task_operation.proto")
public class TaskOperationServiceGrpc {

  private TaskOperationServiceGrpc() {}

  public static final String SERVICE_NAME = "task.operation.TaskOperationService";

  // Static method descriptors that strictly reflect the proto.
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
      com.tianzhixing.kernel.grpc.proto.task.operation.Result> METHOD_ACCEPT =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "task.operation.TaskOperationService", "accept"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.task.operation.Result.getDefaultInstance()));
  @io.grpc.ExperimentalApi("https://github.com/grpc/grpc-java/issues/1901")
  public static final io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
      com.tianzhixing.kernel.grpc.proto.task.operation.Result> METHOD_CANCEL =
      io.grpc.MethodDescriptor.create(
          io.grpc.MethodDescriptor.MethodType.UNARY,
          generateFullMethodName(
              "task.operation.TaskOperationService", "cancel"),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo.getDefaultInstance()),
          io.grpc.protobuf.ProtoUtils.marshaller(com.tianzhixing.kernel.grpc.proto.task.operation.Result.getDefaultInstance()));

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static TaskOperationServiceStub newStub(io.grpc.Channel channel) {
    return new TaskOperationServiceStub(channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static TaskOperationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    return new TaskOperationServiceBlockingStub(channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary and streaming output calls on the service
   */
  public static TaskOperationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    return new TaskOperationServiceFutureStub(channel);
  }

  /**
   * <pre>
   *任务操作服务
   * </pre>
   */
  public static abstract class TaskOperationServiceImplBase implements io.grpc.BindableService {

    /**
     * <pre>
     *接受任务
     * </pre>
     */
    public void accept(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_ACCEPT, responseObserver);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public void cancel(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result> responseObserver) {
      asyncUnimplementedUnaryCall(METHOD_CANCEL, responseObserver);
    }

    @java.lang.Override public io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            METHOD_ACCEPT,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
                com.tianzhixing.kernel.grpc.proto.task.operation.Result>(
                  this, METHODID_ACCEPT)))
          .addMethod(
            METHOD_CANCEL,
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
                com.tianzhixing.kernel.grpc.proto.task.operation.Result>(
                  this, METHODID_CANCEL)))
          .build();
    }
  }

  /**
   * <pre>
   *任务操作服务
   * </pre>
   */
  public static final class TaskOperationServiceStub extends io.grpc.stub.AbstractStub<TaskOperationServiceStub> {
    private TaskOperationServiceStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TaskOperationServiceStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TaskOperationServiceStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TaskOperationServiceStub(channel, callOptions);
    }

    /**
     * <pre>
     *接受任务
     * </pre>
     */
    public void accept(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_ACCEPT, getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public void cancel(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(METHOD_CANCEL, getCallOptions()), request, responseObserver);
    }
  }

  /**
   * <pre>
   *任务操作服务
   * </pre>
   */
  public static final class TaskOperationServiceBlockingStub extends io.grpc.stub.AbstractStub<TaskOperationServiceBlockingStub> {
    private TaskOperationServiceBlockingStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TaskOperationServiceBlockingStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TaskOperationServiceBlockingStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TaskOperationServiceBlockingStub(channel, callOptions);
    }

    /**
     * <pre>
     *接受任务
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.task.operation.Result accept(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_ACCEPT, getCallOptions(), request);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.task.operation.Result cancel(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request) {
      return blockingUnaryCall(
          getChannel(), METHOD_CANCEL, getCallOptions(), request);
    }
  }

  /**
   * <pre>
   *任务操作服务
   * </pre>
   */
  public static final class TaskOperationServiceFutureStub extends io.grpc.stub.AbstractStub<TaskOperationServiceFutureStub> {
    private TaskOperationServiceFutureStub(io.grpc.Channel channel) {
      super(channel);
    }

    private TaskOperationServiceFutureStub(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected TaskOperationServiceFutureStub build(io.grpc.Channel channel,
        io.grpc.CallOptions callOptions) {
      return new TaskOperationServiceFutureStub(channel, callOptions);
    }

    /**
     * <pre>
     *接受任务
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.task.operation.Result> accept(
        com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_ACCEPT, getCallOptions()), request);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.task.operation.Result> cancel(
        com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request) {
      return futureUnaryCall(
          getChannel().newCall(METHOD_CANCEL, getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCEPT = 0;
  private static final int METHODID_CANCEL = 1;

  private static class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TaskOperationServiceImplBase serviceImpl;
    private final int methodId;

    public MethodHandlers(TaskOperationServiceImplBase serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_ACCEPT:
          serviceImpl.accept((com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result>) responseObserver);
          break;
        case METHODID_CANCEL:
          serviceImpl.cancel((com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo) request,
              (io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result>) responseObserver);
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
        METHOD_ACCEPT,
        METHOD_CANCEL);
  }

}
