// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: device_operation.proto

package com.tianzhixing.kernel.grpc.proto.device.operation;

public interface ResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:device.operation.Result)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *code : 200=ok, 300=param error, 503=alread bound
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 1;</code>
   */
  boolean hasResponseEntity();
  /**
   * <pre>
   *code : 200=ok, 300=param error, 503=alread bound
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 1;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity();
  /**
   * <pre>
   *code : 200=ok, 300=param error, 503=alread bound
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 1;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder();
}
