// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: MobileValidationService.proto

package com.tianzhixing.auth.grpc.proto.mobile;

public interface SearchCodeInfoOrBuilder extends
    // @@protoc_insertion_point(interface_extends:mobile.SearchCodeInfo)
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
}