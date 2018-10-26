// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: account_reg.proto

package com.tianzhixing.kernel.grpc.proto.account.reg;

public interface ResultOrBuilder extends
    // @@protoc_insertion_point(interface_extends:account.reg.Result)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *账户token，调用机构需存储，唯一识别账户标识码
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  java.lang.String getAccountToken();
  /**
   * <pre>
   *账户token，调用机构需存储，唯一识别账户标识码
   * </pre>
   *
   * <code>optional string accountToken = 1;</code>
   */
  com.google.protobuf.ByteString
      getAccountTokenBytes();

  /**
   * <pre>
   *第三方传入的token
   * </pre>
   *
   * <code>optional string thirdToken = 2;</code>
   */
  java.lang.String getThirdToken();
  /**
   * <pre>
   *第三方传入的token
   * </pre>
   *
   * <code>optional string thirdToken = 2;</code>
   */
  com.google.protobuf.ByteString
      getThirdTokenBytes();

  /**
   * <pre>
   *code : 200=ok, 300=param error, 501=already exists
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  boolean hasResponseEntity();
  /**
   * <pre>
   *code : 200=ok, 300=param error, 501=already exists
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntity getResponseEntity();
  /**
   * <pre>
   *code : 200=ok, 300=param error, 501=already exists
   * </pre>
   *
   * <code>optional .ResponseEntity responseEntity = 3;</code>
   */
  com.tianzhixing.kernel.grpc.proto.ResponseEntityOrBuilder getResponseEntityOrBuilder();
}
