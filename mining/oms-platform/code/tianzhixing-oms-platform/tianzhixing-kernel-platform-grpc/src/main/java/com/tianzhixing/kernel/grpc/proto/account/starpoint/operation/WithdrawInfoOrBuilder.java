// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint_operation.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint.operation;

public interface WithdrawInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.starpoint.operation.WithdrawInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *需存储的第三方唯一token 必填
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  java.lang.String getAccountToken();
  /**
   * <pre>
   *需存储的第三方唯一token 必填
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
   *提现时间  必填
   * </pre>
   *
   * <code>optional int64 withdrawTime = 3;</code>
   */
  long getWithdrawTime();

  /**
   * <pre>
   *提现数量  必填
   * </pre>
   *
   * <code>optional string withdrawCount = 4;</code>
   */
  java.lang.String getWithdrawCount();
  /**
   * <pre>
   *提现数量  必填
   * </pre>
   *
   * <code>optional string withdrawCount = 4;</code>
   */
  com.google.protobuf.ByteString
      getWithdrawCountBytes();
}