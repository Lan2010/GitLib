// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

public interface RecordsWithDayConditionOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.starpoint.RecordsWithDayCondition)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *需存储的第三方唯一token 必填
   * </pre>
   *
   * <code>string accountToken = 1;</code>
   */
  java.lang.String getAccountToken();
  /**
   * <pre>
   *需存储的第三方唯一token 必填
   * </pre>
   *
   * <code>string accountToken = 1;</code>
   */
  com.google.protobuf.ByteString
      getAccountTokenBytes();

  /**
   * <pre>
   *安全校验码-必填
   * </pre>
   *
   * <code>string token = 2;</code>
   */
  java.lang.String getToken();
  /**
   * <pre>
   *安全校验码-必填
   * </pre>
   *
   * <code>string token = 2;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <pre>
   *天数-必填
   * </pre>
   *
   * <code>int32 days = 3;</code>
   */
  int getDays();

  /**
   * <pre>
   *结束时间-必填
   * </pre>
   *
   * <code>int64 endTime = 4;</code>
   */
  long getEndTime();
}
