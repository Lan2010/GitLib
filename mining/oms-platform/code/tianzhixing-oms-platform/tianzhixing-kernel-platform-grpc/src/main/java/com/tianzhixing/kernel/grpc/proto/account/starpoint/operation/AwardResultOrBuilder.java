// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

public interface AwardResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.starpoint.operation.AwardResult)
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
   *奖励的星点数
   * </pre>
   *
   * <code>optional string starPoint = 4;</code>
   */
  java.lang.String getStarPoint();
  /**
   * <pre>
   *奖励的星点数
   * </pre>
   *
   * <code>optional string starPoint = 4;</code>
   */
  com.google.protobuf.ByteString
      getStarPointBytes();

  /**
   * <pre>
   *code : 200=ok, 300=param error, 502=operation error
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  boolean hasResponseEntity();
  /**
   * <pre>
   *code : 200=ok, 300=param error, 502=operation error
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity();
  /**
   * <pre>
   *code : 200=ok, 300=param error, 502=operation error
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder();
}
