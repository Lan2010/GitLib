// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: response_entity.proto

package com.tianzhixing.kernel.grpc.proto;

public interface ResponseEntityOrBuilder extends
    // @@protoc_insertion_point(interface_extends:ResponseEntity)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *请求状态 200=请求处理成功，500=请求处理失败，502=账户未找到，300=业务逻辑失败（message）
   * </pre>
   *
   * <code>optional int32 code = 1;</code>
   */
  int getCode();

  /**
   * <pre>
   *信息
   * </pre>
   *
   * <code>optional string message = 2;</code>
   */
  java.lang.String getMessage();
  /**
   * <pre>
   *信息
   * </pre>
   *
   * <code>optional string message = 2;</code>
   */
  com.google.protobuf.ByteString
      getMessageBytes();
}
