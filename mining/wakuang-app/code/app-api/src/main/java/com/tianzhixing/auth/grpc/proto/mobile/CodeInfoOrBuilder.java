// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MobileValidationService.proto

package com.tianzhixing.auth.grpc.proto.mobile;

public interface CodeInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mobile.CodeInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *状态
   * </pre>
   *
   * <code>bool status = 1;</code>
   */
  boolean getStatus();

  /**
   * <pre>
   *验证码
   * </pre>
   *
   * <code>string validationCode = 2;</code>
   */
  java.lang.String getValidationCode();
  /**
   * <pre>
   *验证码
   * </pre>
   *
   * <code>string validationCode = 2;</code>
   */
  com.google.protobuf.ByteString
      getValidationCodeBytes();

  /**
   * <pre>
   * 发送机构
   * </pre>
   *
   * <code>string senderOrg = 3;</code>
   */
  java.lang.String getSenderOrg();
  /**
   * <pre>
   * 发送机构
   * </pre>
   *
   * <code>string senderOrg = 3;</code>
   */
  com.google.protobuf.ByteString
      getSenderOrgBytes();
}