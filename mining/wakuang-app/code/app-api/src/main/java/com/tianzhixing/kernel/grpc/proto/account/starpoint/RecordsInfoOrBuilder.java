// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

public interface RecordsInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.starpoint.RecordsInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *操作星点数
   * </pre>
   *
   * <code>string operStarPoint = 1;</code>
   */
  java.lang.String getOperStarPoint();
  /**
   * <pre>
   *操作星点数
   * </pre>
   *
   * <code>string operStarPoint = 1;</code>
   */
  com.google.protobuf.ByteString
      getOperStarPointBytes();

  /**
   * <pre>
   *操作类型(CONSUME, INCREMENT,FROZEN)
   * </pre>
   *
   * <code>string operationType = 2;</code>
   */
  java.lang.String getOperationType();
  /**
   * <pre>
   *操作类型(CONSUME, INCREMENT,FROZEN)
   * </pre>
   *
   * <code>string operationType = 2;</code>
   */
  com.google.protobuf.ByteString
      getOperationTypeBytes();

  /**
   * <pre>
   *记录类型(TASK, ADVERTISEMENT, BASIC，RANDOM，CONSUME)
   * </pre>
   *
   * <code>string recordsType = 3;</code>
   */
  java.lang.String getRecordsType();
  /**
   * <pre>
   *记录类型(TASK, ADVERTISEMENT, BASIC，RANDOM，CONSUME)
   * </pre>
   *
   * <code>string recordsType = 3;</code>
   */
  com.google.protobuf.ByteString
      getRecordsTypeBytes();

  /**
   * <pre>
   *任务ID
   * </pre>
   *
   * <code>string taskId = 4;</code>
   */
  java.lang.String getTaskId();
  /**
   * <pre>
   *任务ID
   * </pre>
   *
   * <code>string taskId = 4;</code>
   */
  com.google.protobuf.ByteString
      getTaskIdBytes();

  /**
   * <pre>
   *广告ID
   * </pre>
   *
   * <code>string advertisementId = 5;</code>
   */
  java.lang.String getAdvertisementId();
  /**
   * <pre>
   *广告ID
   * </pre>
   *
   * <code>string advertisementId = 5;</code>
   */
  com.google.protobuf.ByteString
      getAdvertisementIdBytes();

  /**
   * <pre>
   *备注
   * </pre>
   *
   * <code>string remark = 6;</code>
   */
  java.lang.String getRemark();
  /**
   * <pre>
   *备注
   * </pre>
   *
   * <code>string remark = 6;</code>
   */
  com.google.protobuf.ByteString
      getRemarkBytes();

  /**
   * <pre>
   *创建时间
   * </pre>
   *
   * <code>int64 createTime = 7;</code>
   */
  long getCreateTime();
}
