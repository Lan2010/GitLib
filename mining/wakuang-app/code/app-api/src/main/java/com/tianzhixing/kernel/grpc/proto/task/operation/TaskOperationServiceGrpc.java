package com.tianzhixing.kernel.grpc.proto.task.operation;

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
 *任务操作服务
 * </pre>
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.13.1)",
    comments = "Source: task_operation.proto")
public final class TaskOperationServiceGrpc {

  private TaskOperationServiceGrpc() {}

  public static final String SERVICE_NAME = "task.operation.TaskOperationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
      com.tianzhixing.kernel.grpc.proto.task.operation.Result> getAcceptMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
      com.tianzhixing.kernel.grpc.proto.task.operation.Result> getAcceptMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo, com.tianzhixing.kernel.grpc.proto.task.operation.Result> getAcceptMethod;
    if ((getAcceptMethod = TaskOperationServiceGrpc.getAcceptMethod) == null) {
      synchronized (TaskOperationServiceGrpc.class) {
        if ((getAcceptMethod = TaskOperationServiceGrpc.getAcceptMethod) == null) {
          TaskOperationServiceGrpc.getAcceptMethod = getAcceptMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo, com.tianzhixing.kernel.grpc.proto.task.operation.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "task.operation.TaskOperationService", "accept"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.task.operation.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new TaskOperationServiceMethodDescriptorSupplier("accept"))
                  .build();
          }
        }
     }
     return getAcceptMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
      com.tianzhixing.kernel.grpc.proto.task.operation.Result> getCancelMethod;

  public static io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
      com.tianzhixing.kernel.grpc.proto.task.operation.Result> getCancelMethod() {
    io.grpc.MethodDescriptor<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo, com.tianzhixing.kernel.grpc.proto.task.operation.Result> getCancelMethod;
    if ((getCancelMethod = TaskOperationServiceGrpc.getCancelMethod) == null) {
      synchronized (TaskOperationServiceGrpc.class) {
        if ((getCancelMethod = TaskOperationServiceGrpc.getCancelMethod) == null) {
          TaskOperationServiceGrpc.getCancelMethod = getCancelMethod = 
              io.grpc.MethodDescriptor.<com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo, com.tianzhixing.kernel.grpc.proto.task.operation.Result>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(
                  "task.operation.TaskOperationService", "cancel"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.tianzhixing.kernel.grpc.proto.task.operation.Result.getDefaultInstance()))
                  .setSchemaDescriptor(new TaskOperationServiceMethodDescriptorSupplier("cancel"))
                  .build();
          }
        }
     }
     return getCancelMethod;
  }

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
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
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
      asyncUnimplementedUnaryCall(getAcceptMethod(), responseObserver);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public void cancel(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result> responseObserver) {
      asyncUnimplementedUnaryCall(getCancelMethod(), responseObserver);
    }

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
          .addMethod(
            getAcceptMethod(),
            asyncUnaryCall(
              new MethodHandlers<
                com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo,
                com.tianzhixing.kernel.grpc.proto.task.operation.Result>(
                  this, METHODID_ACCEPT)))
          .addMethod(
            getCancelMethod(),
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
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public void cancel(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request,
        io.grpc.stub.StreamObserver<com.tianzhixing.kernel.grpc.proto.task.operation.Result> responseObserver) {
      asyncUnaryCall(
          getChannel().newCall(getCancelMethod(), getCallOptions()), request, responseObserver);
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
          getChannel(), getAcceptMethod(), getCallOptions(), request);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public com.tianzhixing.kernel.grpc.proto.task.operation.Result cancel(com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request) {
      return blockingUnaryCall(
          getChannel(), getCancelMethod(), getCallOptions(), request);
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
          getChannel().newCall(getAcceptMethod(), getCallOptions()), request);
    }

    /**
     * <pre>
     *取消任务
     * </pre>
     */
    public com.google.common.util.concurrent.ListenableFuture<com.tianzhixing.kernel.grpc.proto.task.operation.Result> cancel(
        com.tianzhixing.kernel.grpc.proto.task.operation.TaskInfo request) {
      return futureUnaryCall(
          getChannel().newCall(getCancelMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_ACCEPT = 0;
  private static final int METHODID_CANCEL = 1;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final TaskOperationServiceImplBase serviceImpl;
    private final int methodId;

    MethodHandlers(TaskOperationServiceImplBase serviceImpl, int methodId) {
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

  private static abstract class TaskOperationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    TaskOperationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.tianzhixing.kernel.grpc.proto.task.operation.TaskOperationServiceProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("TaskOperationService");
    }
  }

  private static final class TaskOperationServiceFileDescriptorSupplier
      extends TaskOperationServiceBaseDescriptorSupplier {
    TaskOperationServiceFileDescriptorSupplier() {}
  }

  private static final class TaskOperationServiceMethodDescriptorSupplier
      extends TaskOperationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    TaskOperationServiceMethodDescriptorSupplier(String methodName) {
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
      synchronized (TaskOperationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new TaskOperationServiceFileDescriptorSupplier())
              .addMethod(getAcceptMethod())
              .addMethod(getCancelMethod())
              .build();
        }
      }
    }
    return result;
  }
}
