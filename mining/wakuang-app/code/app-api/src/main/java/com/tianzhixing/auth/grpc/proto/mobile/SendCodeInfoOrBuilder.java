// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MobileValidationService.proto

package com.tianzhixing.auth.grpc.proto.mobile;

public interface SendCodeInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mobile.SendCodeInfo)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   *手机号码-必填
   * </pre>
   *
   * <code>string mobile = 1;</code>
   */
  java.lang.String getMobile();
  /**
   * <pre>
   *手机号码-必填
   * </pre>
   *
   * <code>string mobile = 1;</code>
   */
  com.google.protobuf.ByteString
      getMobileBytes();

  /**
   * <pre>
   *平台身份识别-必填
   * </pre>
   *
   * <code>string token = 2;</code>
   */
  java.lang.String getToken();
  /**
   * <pre>
   *平台身份识别-必填
   * </pre>
   *
   * <code>string token = 2;</code>
   */
  com.google.protobuf.ByteString
      getTokenBytes();

  /**
   * <pre>
   *发送代码分类 - 必填
   * </pre>
   *
   * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
   */
  int getSendCodeTypeValue();
  /**
   * <pre>
   *发送代码分类 - 必填
   * </pre>
   *
   * <code>.mobile.SendCodeInfo.SendCodeType sendCodeType = 3;</code>
   */
  com.tianzhixing.auth.grpc.proto.mobile.SendCodeInfo.SendCodeType getSendCodeType();

  /**
   * <pre>
   *验证码长度 -必填(4-8长度)
   * </pre>
   *
   * <code>int32 codeLength = 4;</code>
   */
  int getCodeLength();
}
