// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: task_operation.proto

package com.tianzhixing.kernel.grpc.proto.task.operation;

public interface TaskInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:task.operation.TaskInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *accountToken-必填
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  java.lang.String getAccountToken();
  /**
   * <pre>
   *accountToken-必填
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  com.google.protobuf.ByteString
      getAccountTokenBytes();

  /**
   * <pre>
   *安全校验码-必填
   * </pre>
   *
   * <code>optional string token = 2;</code>
   */
  java.lang.String getToken();
  /**
   * <pre>
   *安全校验码-必填
   * </pre>
   *
   * <code>optional string token = 2;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <pre>
   *任务ID-必填
   * </pre>
   *
   * <code>optional string taskId = 3;</code>
   */
  java.lang.String getTaskId();
  /**
   * <pre>
   *任务ID-必填
   * </pre>
   *
   * <code>optional string taskId = 3;</code>
   */
  com.google.protobuf.ByteString
      getTaskIdBytes();

  /**
   * <pre>
   *操作时间-必填
   * </pre>
   *
   * <code>optional int64 operationTime = 4;</code>
   */
  long getOperationTime();
}
