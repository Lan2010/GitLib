// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

public interface CollectionResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.starpoint.operation.CollectionResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *可用星点数
   * </pre>
   *
   * <code>optional string availableStarPoint = 1;</code>
   */
  java.lang.String getAvailableStarPoint();
  /**
   * <pre>
   *可用星点数
   * </pre>
   *
   * <code>optional string availableStarPoint = 1;</code>
   */
  com.google.protobuf.ByteString
      getAvailableStarPointBytes();

  /**
   * <pre>
   *冻结星点数量
   * </pre>
   *
   * <code>optional string frozenStarPoint = 2;</code>
   */
  java.lang.String getFrozenStarPoint();
  /**
   * <pre>
   *冻结星点数量
   * </pre>
   *
   * <code>optional string frozenStarPoint = 2;</code>
   */
  com.google.protobuf.ByteString
      getFrozenStarPointBytes();

  /**
   * <pre>
   *采集星点数
   * </pre>
   *
   * <code>optional string starpoint = 4;</code>
   */
  java.lang.String getStarpoint();
  /**
   * <pre>
   *采集星点数
   * </pre>
   *
   * <code>optional string starpoint = 4;</code>
   */
  com.google.protobuf.ByteString
      getStarpointBytes();

  /**
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  boolean hasResponseEntity();
  /**
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity();
  /**
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder();
}
