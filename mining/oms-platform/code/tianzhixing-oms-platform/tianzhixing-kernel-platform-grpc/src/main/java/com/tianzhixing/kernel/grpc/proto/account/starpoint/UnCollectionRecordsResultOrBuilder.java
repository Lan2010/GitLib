// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_starpoint.proto

package com.tianzhixing.kernel.grpc.proto.account.starpoint;

public interface UnCollectionRecordsResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.starpoint.UnCollectionRecordsResult)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
   */
  java.util.List<com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsInfo> 
      getRecordsList();
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
   */
  com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsInfo getRecords(int index);
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
   */
  int getRecordsCount();
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
   */
  java.util.List<? extends com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsInfoOrBuilder> 
      getRecordsOrBuilderList();
  /**
   * <pre>
   *记录
   * </pre>
   *
   * <code>repeated .account.starpoint.UnCollectionRecordsInfo records = 1;</code>
   */
  com.tianzhixing.kernel.grpc.proto.account.starpoint.UnCollectionRecordsInfoOrBuilder getRecordsOrBuilder(
      int index);

  /**
   * <code>optional .ResponseEntity responseEntity = 2;</code>
   */
  boolean hasResponseEntity();
  /**
   * <code>optional .ResponseEntity responseEntity = 2;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity();
  /**
   * <code>optional .ResponseEntity responseEntity = 2;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder();
}
