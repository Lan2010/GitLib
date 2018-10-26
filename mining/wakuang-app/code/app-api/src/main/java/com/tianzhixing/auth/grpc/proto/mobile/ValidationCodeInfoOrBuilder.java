// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MobileValidationService.proto

package com.tianzhixing.auth.grpc.proto.mobile;

public interface ValidationCodeInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mobile.ValidationCodeInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *输入的验证码 - 必填
   * </pre>
   *
   * <code>string inputCode = 1;</code>
   */
  java.lang.String getInputCode();
  /**
   * <pre>
   *输入的验证码 - 必填
   * </pre>
   *
   * <code>string inputCode = 1;</code>
   */
  com.google.protobuf.ByteString
      getInputCodeBytes();

  /**
   * <pre>
   *验证授权码 - 必填
   * </pre>
   *
   * <code>string codeToken = 2;</code>
   */
  java.lang.String getCodeToken();
  /**
   * <pre>
   *验证授权码 - 必填
   * </pre>
   *
   * <code>string codeToken = 2;</code>
   */
  com.google.protobuf.ByteString
      getCodeTokenBytes();

  /**
   * <pre>
   *手机号 - 必填
   * </pre>
   *
   * <code>string mobile = 3;</code>
   */
  java.lang.String getMobile();
  /**
   * <pre>
   *手机号 - 必填
   * </pre>
   *
   * <code>string mobile = 3;</code>
   */
  com.google.protobuf.ByteString
      getMobileBytes();

  /**
   * <pre>
   *平台身份识别 - 必填
   * </pre>
   *
   * <code>string token = 4;</code>
   */
  java.lang.String getToken();
  /**
   * <pre>
   *平台身份识别 - 必填
   * </pre>
   *
   * <code>string token = 4;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();
}
